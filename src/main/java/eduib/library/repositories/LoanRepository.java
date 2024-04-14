package eduib.library.repositories;

import eduib.library.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for managing Loan entity
 */
@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Long> {

    /**
     * Finds loan by user's id
     * @param userId users is (long)
     * @return LoanEntity
     */
    List<LoanEntity> findByUserId(long userId);

    /**
     * Find loan by users's id where the return date is not null
     * @param userId user's id
     * @return LoanEntity
     */
    List<LoanEntity> findByUserIdAndReturnDateIsNotNull(long userId);

}
