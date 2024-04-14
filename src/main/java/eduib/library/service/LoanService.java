package eduib.library.service;

import eduib.library.controller.DTO.*;
import eduib.library.entity.BookEntity;
import eduib.library.entity.LoanEntity;
import eduib.library.entity.UserEntity;
import eduib.library.errors.BookDoesntExistsException;
import eduib.library.errors.LoanDoesntExistsException;
import eduib.library.errors.UserDoesntExistsException;
import eduib.library.repositories.AuthRepository;
import eduib.library.repositories.BookRepository;
import eduib.library.repositories.LoanRepository;
import eduib.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing loan-related methods.
 * If communicates with loan book and user Repositories
 */
@Service
public class LoanService extends IndentityService{
    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    /**
     * Constructs a LoanService object
     * @param loanRepository    Repository for loan-related methods.
     * @param userRepository    Repository for user-related methods.
     * @param bookRepository    Repository for book-related methods.
     * @param authRepository    Repository for authentication-related operations.
     */
    @Autowired
    public LoanService(LoanRepository loanRepository, UserRepository userRepository, BookRepository bookRepository,
                       AuthRepository authRepository) {
        super(authRepository);
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    /**
     * Adds a new loan
     * @param loanDTO loan's data (LoanDTO)
     * @return Response with data of loan.
     */
    public LoanResponseDTO add(AddLoanDTO loanDTO) {

        UserEntity user = userRepository.findById(loanDTO.getUserId())
                .orElseThrow(() -> new UserDoesntExistsException(null));
        BookEntity book = bookRepository.findById(loanDTO.getBookId())
                .orElseThrow(() -> new BookDoesntExistsException(null));

        LoanEntity loanEntity = new LoanEntity();
        loanEntity.setBook(book);
        loanEntity.setUser(user);
        loanEntity.setLoanDate(new Date(System.currentTimeMillis()));
        loanEntity.setTerminDate(loanDTO.getTerminDate());
        loanRepository.save(loanEntity);

        int copies = Integer.valueOf(book.getAvailableCopies()) - 1;
        book.setAvailableCopies(String.valueOf(copies));
        bookRepository.save(book);

        return new LoanResponseDTO(loanEntity.getId(), loanEntity.getLoanDate(), loanEntity.getTerminDate(),
                loanEntity.getUser().getId(), loanEntity.getBook().getBookId());
    }

    /**
     * Getting details of a loan with given id
     * @param id loans id (long)
     * @return Data of the loan
     * @throws LoanDoesntExistsException If the loan with the given ID does not exist.
     */
    @PostAuthorize("hasRole('LIBRARIAN') or this.indentify(authentication.name, #userId)")
    public GetLoanDTO getById(long id){
        LoanEntity loanEntity = loanRepository.findById(id).orElseThrow(()-> new LoanDoesntExistsException(null));
        GetUserDTO userDTO = new GetUserDTO(loanEntity.getUser().getId(), loanEntity.getUser().getUserName(),
                loanEntity.getUser().getEmail());
        GetBookDTO bookDTO = new GetBookDTO(loanEntity.getBook().getBookId(), loanEntity.getBook().getISBN(),
                loanEntity.getBook().getTitle(), loanEntity.getBook().getAuthor(), loanEntity.getBook().getPublisher(),
                loanEntity.getBook().getPublishYear(), loanEntity.getBook().getAvailableCopies());

        return new GetLoanDTO(loanEntity.getId(), loanEntity.getLoanDate(), loanEntity.getTerminDate(), userDTO, bookDTO);
    }

    /**
     * Getting all loans
     * @param userId usersId (long)
     * @return List of loan.
     */
    @PreAuthorize("hasRole('LIBRARIAN') or this.indentify(authentication.name, #userId)")
    public List<GetLoanDTO> getAll(Long userId){

        List<LoanEntity> loanList = loanRepository.findAll();

        if(userId == null){
            loanList = loanRepository.findAll();
        } else {
            loanList = loanRepository.findByUserId(userId);
        }
        return loanList.stream().map(this::loanMap).collect(Collectors.toList());

    }

    /**
     * Returns a loan.
     * @param returnLoanDTO returnig loan data (ReturnLoanDTO)
     * @return Data of the returned loan.
     */
    public GetReturnLoanDTO returnLoan(ReturnLoanDTO returnLoanDTO){

        LoanEntity loanEntity = loanRepository.findById(returnLoanDTO.getLoanId())
                .orElseThrow(()-> new LoanDoesntExistsException(null));
        GetUserDTO userDTO = new GetUserDTO(loanEntity.getUser().getId(), loanEntity.getUser().getUserName(),
                loanEntity.getUser().getEmail());
        GetBookDTO bookDTO = new GetBookDTO(loanEntity.getBook().getBookId(), loanEntity.getBook().getISBN(),
                loanEntity.getBook().getTitle(), loanEntity.getBook().getAuthor(), loanEntity.getBook().getPublisher(),
                loanEntity.getBook().getPublishYear(), loanEntity.getBook().getAvailableCopies());
        loanEntity.setReturnDate(returnLoanDTO.getReturnDate());
        var updatedLoan = loanRepository.save(loanEntity);

        int copies = Integer.valueOf(loanEntity.getBook().getAvailableCopies()) + 1;
        loanEntity.getBook().setAvailableCopies(String.valueOf(copies));
        bookRepository.save(loanEntity.getBook());

        return new GetReturnLoanDTO(updatedLoan.getId(), updatedLoan.getLoanDate(), updatedLoan.getTerminDate(),
                updatedLoan.getReturnDate(), userDTO, bookDTO);

    }

    /**
     * Getting loans history of a user with given Id.
     * @param userId users id (long)
     * @return List of loan details representing loan history of the user.
     */
    public List<GetLoanDTO> getUsersHistory(long userId){
        List<LoanEntity> loanEntities = loanRepository.findByUserIdAndReturnDateIsNotNull(userId);

        List<GetLoanDTO> loanHistory = loanEntities.stream().map(this::loanMap).collect(Collectors.toList());
        return loanHistory;
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
