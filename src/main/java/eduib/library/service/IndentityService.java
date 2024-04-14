package eduib.library.service;

import eduib.library.entity.AuthEntity;
import eduib.library.repositories.AuthRepository;

public class IndentityService {

    private final AuthRepository authRepository;

    public IndentityService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public boolean indentify(String userName, Long userId){
        if(userId == null|| userName == null){
            return false;
        }
        AuthEntity authEntity = authRepository.findByUserName(userName).orElseThrow(RuntimeException::new);

        return userId == authEntity.getUser().getId();
    }
}
