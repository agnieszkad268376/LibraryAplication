package eduib.library.repositories;

import eduib.library.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing Book entity
 */
@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
