package eduib.library.service;

import eduib.library.controller.DTO.GetUserDTO;
import eduib.library.entity.AuthEntity;
import eduib.library.entity.UserEntity;
import eduib.library.repositories.AuthRepository;
import eduib.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final AuthRepository authRepository;

    @Autowired
    public UserService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public GetUserDTO getUserByUserName(String userName){
        AuthEntity authEntity = authRepository.findByUserName(userName).orElseThrow(RuntimeException::new);
        UserEntity userEntity = authEntity.getUser();
        return new GetUserDTO(userEntity.getId(), userEntity.getUserName(), userEntity.getEmail());
    }
}
