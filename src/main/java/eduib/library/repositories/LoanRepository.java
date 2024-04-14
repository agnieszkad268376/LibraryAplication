package eduib.library.repositories;

import eduib.library.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Long> {

    List<LoanEntity> findByUserId(long userId);
    List<LoanEntity> findByUserIdAndReturnDateIsNotNull(long userId);

}
