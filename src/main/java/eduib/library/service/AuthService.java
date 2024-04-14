package eduib.library.service;

import eduib.library.controller.DTO.*;
import eduib.library.entity.AuthEntity;
import eduib.library.entity.UserEntity;
import eduib.library.errors.BookDoesntExistsException;
import eduib.library.errors.UserAlreadyExistsException;
import eduib.library.errors.UserDoesntExistsException;
import eduib.library.errors.WrongPasswordException;
import eduib.library.repositories.AuthRepository;
import eduib.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Servis for Auth
 * It communicates with Auth and User Repositories and JWTService
 */
@Service
public class AuthService {
    private final AuthRepository authRepository;
    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructs an AuthService object
     * @param authRepository Repository for authentication-related methods. (AuthRepository)
     * @param userRepository Repository for user-related methods. (UserRepository)
     * @param jwtService generating JWT tokens. (JWTService)
     * @param passwordEncoder Encode password (PasswordEncoder)
     */
    @Autowired
    public AuthService(AuthRepository authRepository, UserRepository userRepository, JWTService jwtService, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Adding new user.
     * @param registerDTO data for the new user. (RegisterDTO)
     * @return Response with user details.
     * @throws UserAlreadyExistsException If a user with the same username already exists.
     */
    public RegisterResponseDTO register(RegisterDTO registerDTO){

        Optional<AuthEntity> isUserExists = authRepository.findByUserName(registerDTO.getUserName());
        if (isUserExists.isPresent()){
            throw new UserAlreadyExistsException(null);
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(registerDTO.getUserName());
        userEntity.setEmail(registerDTO.getEmail());
        userEntity.setFullUserName(registerDTO.getUserName());
        UserEntity createUser = userRepository.save(userEntity);

        AuthEntity authEntity = new AuthEntity();
        authEntity.setUserName(registerDTO.getUserName());
        authEntity.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        authEntity.setRole(registerDTO.getRole());
        authEntity.setUser(createUser);

        AuthEntity createdAuth = authRepository.save(authEntity);
        return new RegisterResponseDTO(createdAuth.getId(), createdAuth.getUserName(), createdAuth.getRole());
    }

    /**
     * Allow to log in the user
     * @param loginDTO Login data (LoginDTO)
     * @return Response with authentication token (String)
     * @throws UserDoesntExistsException If the user does not exist.
     * @throws WrongPasswordException    If the given password is incorrect.
     */
    public LoginResponseDTO login(LoginDTO loginDTO) {
        AuthEntity authEntity = authRepository.findByUserName(loginDTO.getUserName())
                .orElseThrow(() -> new UserDoesntExistsException(null));

        String pass = passwordEncoder.encode(loginDTO.getPassword());
        if (!passwordEncoder.matches(loginDTO.getPassword(), authEntity.getPassword())) {
            throw new WrongPasswordException("Wrong password");
        }

        String token = jwtService.generateToken(authEntity);

        return new LoginResponseDTO(token);
    }

    /**
     * Getting a list of all users.
     * @return List of all users.
     */
    public List<RegisterResponseDTO> showUsers(){
        var users = authRepository.findAll();
        return users.stream().map((user) -> new RegisterResponseDTO(user.getId(), user.getUserName(), user.getRole()))
                .collect(Collectors.toList());
    }

    /**
     * Getting one user with given user id
     * @param id User ID. (long)
     * @return User data
     * @throws UserDoesntExistsException If the user does not exist.
     */
    public RegisterResponseDTO showUser(long id){
        var user = authRepository.findById(id).orElseThrow(() -> new UserDoesntExistsException(null));
        return new RegisterResponseDTO(user.getId(), user.getUserName(), user.getRole());
    }

    /**
     * Deletes a user with given id
     * @param id User ID. (long)
     * @throws UserDoesntExistsException If the user does not exist.
     */
    public void delete(long id){
        if(!authRepository.existsById(id)) {
            throw new UserDoesntExistsException(null);
        } else {
            authRepository.deleteById(id);
            userRepository.deleteById(id);
        }
    }

    /**
     * Updates the username with given id.
     * @param userId     User ID. (long)
     * @param newUserName New username. (String)
     * @return Updated user data.
     * @throws UserAlreadyExistsException If a user with the new username does not exist.
     */
    public RegisterResponseDTO updateUserName(long userId, String newUserName) {
        var auth = authRepository.findById(userId).orElseThrow(() -> new UserDoesntExistsException(null));
        auth.setUserName(newUserName);
        var updateUser = authRepository.save(auth);
        return new RegisterResponseDTO(updateUser.getId(), updateUser.getUserName(), updateUser.getRole());
    }

    /**
     * Updates the email with given id.
     * @param userId   User ID. (long)
     * @param newEmail New email address. (String)
     * @throws UserAlreadyExistsException If a user with the new username does not exist.
     */
    public void updateEmail(long userId, String newEmail){
        var user = userRepository.findById(userId).orElseThrow(() -> new UserDoesntExistsException(null));
        user.setEmail(newEmail);
        userRepository.save(user);
    }

    /**
     * Changes the password with given id
     * @param userId      User ID. (long)
     * @param newPassword New password. (String)
     * @throws UserAlreadyExistsException If a user with the new username does not exist.
     */
    public void changePassword(long userId, String newPassword){
        var auth = authRepository.findById(userId).orElseThrow(() -> new UserDoesntExistsException(null));
        auth.setPassword(newPassword);
         authRepository.save(auth);
    }

}
