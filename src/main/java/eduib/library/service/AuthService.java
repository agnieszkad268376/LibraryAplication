package eduib.library.service;

import eduib.library.controller.DTO.LoginDTO;
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

    @Autowired
    public AuthService(AuthRepository authRepository, UserRepository userRepository) {
        this.authRepository = authRepository;
        this.userRepository = userRepository;
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

    public void login(LoginDTO loginDTO) {
        AuthEntity authEntity = authRepository.findByUserName(loginDTO.getUserName())
                .orElseThrow(RuntimeException::new);
        if (!authEntity.getPassword().equals(loginDTO.getPassword())) {
            throw new RuntimeException();
        }
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

    


}
