package eduib.library.repositories;

import eduib.library.entity.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for managing Auth entity
 */
public interface AuthRepository extends JpaRepository<AuthEntity, Long> {
    /**
     * Finds user by its username
     * @param userName (String)
     * @return if found the AuthEntity, if not null
     */
    Optional<AuthEntity> findByUserName(String userName);
}
