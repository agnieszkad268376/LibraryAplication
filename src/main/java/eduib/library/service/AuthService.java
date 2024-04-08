package eduib.library.service;

import eduib.library.commonTypes.UserRole;
import eduib.library.controller.DTO.LoginDTO;
import eduib.library.controller.DTO.LoginResponseDTO;
import eduib.library.controller.DTO.RegisterDTO;
import eduib.library.controller.DTO.RegisterResponseDTO;
import eduib.library.entity.AuthEntity;
import eduib.library.entity.UserEntity;
import eduib.library.repositories.AuthRepository;
import eduib.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AuthService {
    private final AuthRepository authRepository;
    private final UserRepository userRepository;
    private final JWTService jwtService;

    @Autowired
    public AuthService(AuthRepository authRepository, UserRepository userRepository, JWTService jwtService) {
        this.authRepository = authRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public RegisterResponseDTO register(RegisterDTO registerDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(registerDTO.getEmail());
        userEntity.setFullUserName(registerDTO.getUserName());
        UserEntity createUser = userRepository.save(userEntity);

        AuthEntity authEntity = new AuthEntity();
        authEntity.setUserName(registerDTO.getUserName());
        authEntity.setPassword(registerDTO.getPassword());
        authEntity.setRole(registerDTO.getRole());
        authEntity.setUser(createUser);

        AuthEntity createdAuth = authRepository.save(authEntity);
        return new RegisterResponseDTO(createdAuth.getId(), createdAuth.getUserName(), createdAuth.getRole());
    }

    public LoginResponseDTO login(LoginDTO loginDTO) {
        AuthEntity authEntity = authRepository.findByUserName(loginDTO.getUserName())
                .orElseThrow(RuntimeException::new);

        if (!authEntity.getPassword().equals(loginDTO.getPassword())) {
            throw new RuntimeException();
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
        var user = authRepository.findById(id).orElseThrow(RuntimeException::new);
        return new RegisterResponseDTO(user.getId(), user.getUserName(), user.getRole());
    }

    public void delete(long id){
        if(!authRepository.existsById(id)) {
            throw new RuntimeException();
        } else {
            authRepository.deleteById(id);
        }
    }


}
