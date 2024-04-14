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


@Service
public class AuthService {
    private final AuthRepository authRepository;
    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(AuthRepository authRepository, UserRepository userRepository, JWTService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.authRepository = authRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

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

    public List<RegisterResponseDTO> showUsers(){
        var users = authRepository.findAll();
        return users.stream().map((user) -> new RegisterResponseDTO(user.getId(), user.getUserName(), user.getRole()))
                .collect(Collectors.toList());
    }

    public RegisterResponseDTO showUser(long id){
        var user = authRepository.findById(id).orElseThrow(() -> new UserDoesntExistsException(null));
        return new RegisterResponseDTO(user.getId(), user.getUserName(), user.getRole());
    }

    public void delete(long id){
        if(!authRepository.existsById(id)) {
            throw new UserDoesntExistsException(null);
        } else {
            authRepository.deleteById(id);
            userRepository.deleteById(id);
        }
    }

    public RegisterResponseDTO updateUserName(long userId, String newUserName) {
        var auth = authRepository.findById(userId).orElseThrow(() -> new UserAlreadyExistsException(null));
        auth.setUserName(newUserName);
        var updateUser = authRepository.save(auth);
        return new RegisterResponseDTO(updateUser.getId(), updateUser.getUserName(), updateUser.getRole());
    }

    public void updateEmail(long userId, String newEmail){
        var user = userRepository.findById(userId).orElseThrow(() -> new UserAlreadyExistsException("Book not found"));
        user.setEmail(newEmail);
        userRepository.save(user);
    }

    public void changePassword(long userId, String newPassword){
        var auth = authRepository.findById(userId).orElseThrow(() -> new UserAlreadyExistsException("Book not found"));
        auth.setPassword(newPassword);
         authRepository.save(auth);
    }

}
