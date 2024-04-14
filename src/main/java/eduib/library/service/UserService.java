package eduib.library.service;

import eduib.library.controller.DTO.GetUserDTO;
import eduib.library.entity.AuthEntity;
import eduib.library.entity.UserEntity;
import eduib.library.errors.UserNameNotFound;
import eduib.library.repositories.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing user-related methods.
 * It comminicate with auth Repository
 */
@Service
public class UserService {

    private final AuthRepository authRepository;

    /**
     * Constructs a UserService object
     * @param authRepository Repository for authentication-related methods.
     */
    @Autowired
    public UserService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    /**
     * Geting user data by username.
     * @param userName (String)
     * @return Details of the user
     * @throws UserNameNotFound If the username is not found.
     */
    public GetUserDTO getUserByUserName(String userName){
        AuthEntity authEntity = authRepository.findByUserName(userName).orElseThrow(() -> new UserNameNotFound(null));
        UserEntity userEntity = authEntity.getUser();
        return new GetUserDTO(userEntity.getId(), userEntity.getUserName(), userEntity.getEmail());
    }
}
