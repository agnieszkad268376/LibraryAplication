package eduib.library.repositories;

import eduib.library.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for managing User entity
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
