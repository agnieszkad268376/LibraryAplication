package eduib.library.service;

import eduib.library.entity.AuthEntity;
import eduib.library.errors.UserNameNotFound;
import eduib.library.repositories.AuthRepository;

/**
 * Service class for identifying user identity.
 * It communicates with Auth Repository
 */
public class IndentityService {

    private final AuthRepository authRepository;

    /**
     * Constructs an IdentityService object
     * @param authRepository Repository for authentication-related methods.
     */
    public IndentityService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    /**
     * Check if the given userId matches the userId associated with the username
     * @param userName Username (String)
     * @param userId   Users id (long)
     * @return True if the user ID matches the user ID associated with the username, false otherwise.
     * @throws UserNameNotFound If the username is not found.
     */
    public boolean indentify(String userName, Long userId){
        if(userId == null|| userName == null){
            return false;
        }
        AuthEntity authEntity = authRepository.findByUserName(userName).orElseThrow(()->new UserNameNotFound(null));

        return userId == authEntity.getUser().getId();
    }
}
