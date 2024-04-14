package eduib.library.service;

import eduib.library.controller.DTO.*;
import eduib.library.entity.BookEntity;
import eduib.library.entity.LoanEntity;
import eduib.library.entity.UserEntity;
import eduib.library.repositories.BookRepository;
import eduib.library.repositories.LoanRepository;
import eduib.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public GetLoanDTO getById(long id){
        LoanEntity loanEntity = loanRepository.findById(id).orElseThrow(RuntimeException::new);
        GetUserDTO userDTO = new GetUserDTO(loanEntity.getUser().getId(), loanEntity.getUser().getUserName(),
                loanEntity.getUser().getEmail());
        GetBookDTO bookDTO = new GetBookDTO(loanEntity.getBook().getBookId(), loanEntity.getBook().getISBN(),
                loanEntity.getBook().getTitle(), loanEntity.getBook().getAuthor(), loanEntity.getBook().getPublisher(),
                loanEntity.getBook().getPublishYear(), loanEntity.getBook().getAvailableCopies());

        return new GetLoanDTO(loanEntity.getId(), loanEntity.getLoanDate(), loanEntity.getTerminDate(), userDTO, bookDTO);
    }

    public List<GetLoanDTO> getAll(){

        List<LoanEntity> loanList = loanRepository.findAll();

        return loanList.stream().map(this::loanMap).collect(Collectors.toList());

    }

    private GetLoanDTO loanMap(LoanEntity loanEntity){
        GetUserDTO userDTO = new GetUserDTO(loanEntity.getUser().getId(), loanEntity.getUser().getUserName(),
                loanEntity.getUser().getEmail());
        GetBookDTO bookDTO = new GetBookDTO(loanEntity.getBook().getBookId(), loanEntity.getBook().getISBN(),
                loanEntity.getBook().getTitle(), loanEntity.getBook().getAuthor(), loanEntity.getBook().getPublisher(),
                loanEntity.getBook().getPublishYear(), loanEntity.getBook().getAvailableCopies());

        return new GetLoanDTO(loanEntity.getId(), loanEntity.getLoanDate(), loanEntity.getTerminDate(), userDTO, bookDTO);
    }
}
