package eduib.library.service;

import eduib.library.controller.DTO.AddLoanDTO;
import eduib.library.controller.DTO.LoanResponseDTO;
import eduib.library.entity.BookEntity;
import eduib.library.entity.LoanEntity;
import eduib.library.entity.UserEntity;
import eduib.library.repositories.BookRepository;
import eduib.library.repositories.LoanRepository;
import eduib.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class LoanService {
    private final LoanRepository loanRepository;

    private final UserRepository userRepository;

    private final BookRepository bookRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public LoanResponseDTO add(AddLoanDTO loanDTO) {

        UserEntity user = userRepository.findById(loanDTO.getUserId()).orElseThrow(RuntimeException::new);
        BookEntity book = bookRepository.findById(loanDTO.getBookId()).orElseThrow(RuntimeException::new);

        LoanEntity loanEntity = new LoanEntity();
        loanEntity.setBook(book);
        loanEntity.setUser(user);
        loanEntity.setLoanDate(new Date(System.currentTimeMillis()));
        loanEntity.setTerminDate(loanDTO.getTerminDate());
        loanRepository.save(loanEntity);

        return new LoanResponseDTO(loanEntity.getId(), loanEntity.getLoanDate(), loanEntity.getTerminDate(),
                loanEntity.getUser().getId(), loanEntity.getBook().getBookId());
    }
}
