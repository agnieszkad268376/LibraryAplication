package eduib.library.service;

import eduib.library.controller.DTO.AddBookDTO;
import eduib.library.controller.DTO.AddBookResponseDTO;
import eduib.library.controller.DTO.GetBookDTO;
import eduib.library.entity.BookEntity;
import eduib.library.errors.BookDoesntExistsException;
import eduib.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing book-related operations.
 * Service communicate with BookRepository
 */
@Service
public class BookService {
    private final BookRepository bookRepository;

    /**
     * Constructs a BookService object
     * @param bookRepository Repository for book-related operations.
     */
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Adds a new book
     * @param book Details of the book. (AddBookDTO)
     * @return Response with details of the book. (AddBookResponseDTO)
     */
    public AddBookResponseDTO add(AddBookDTO book){
        var bookEntity = new BookEntity();
        bookEntity.setISBN(book.getISBN());
        bookEntity.setTitle(book.getTitle());
        bookEntity.setAuthor(book.getAuthor());
        bookEntity.setPublisher(book.getPublisher());
        bookEntity.setPublishYear(book.getPublishYear());
        bookEntity.setAvailableCopies(book.getAvailableCopies());
        var newBook = bookRepository.save(bookEntity);

        return new AddBookResponseDTO(newBook.getBookId(), newBook.getISBN(), newBook.getTitle(), newBook.getAuthor(),
                newBook.getPublisher(), newBook.getPublishYear(), newBook.getAvailableCopies());
    }

    /**
     * Getting data of all books.
     * @return List of book data. (List)
     */
    public List<GetBookDTO> getAll(){
        var books = bookRepository.findAll();
        return books.stream().map((book) -> new GetBookDTO(book.getBookId(), book.getISBN(),
                book.getTitle(), book.getAuthor(), book.getPublisher(), book.getPublishYear(),
                book.getAvailableCopies())).collect(Collectors.toList());
    }

    /**
     * Getting data of a book with given id.
     * @param id books id (long)
     * @return Details of the specified book. (GetBookDTO)
     * @throws BookDoesntExistsException If the book with the given ID does not exist.
     */
    public GetBookDTO getOne(long id){
        var book =  bookRepository.findById(id).orElseThrow(() -> new BookDoesntExistsException(null));
        return new GetBookDTO(book.getBookId(), book.getISBN(),
                book.getTitle(), book.getAuthor(), book.getPublisher(), book.getPublishYear(),
                book.getAvailableCopies());
    }

    /**
     * Deletes a book with given id.
     * @param id book id (long)
     * @throws BookDoesntExistsException If the book with the given ID does not exist.
     */
    public void delete(long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        } else {
            throw new BookDoesntExistsException(null);
        }
    }

    /**
     * Updates the ISBN of a book.
     * @param bookId   books id (long)
     * @param newISBN  New ISBN (String)
     * @return Updated book data.
     * @throws BookDoesntExistsException If the book with the given ID does not exist.
     */
    public GetBookDTO updateISBN(long bookId, String newISBN){
        var book = bookRepository.findById(bookId).orElseThrow(() -> new BookDoesntExistsException(null));
        book.setISBN(newISBN);
        var updateBook = bookRepository.save(book);
        return new GetBookDTO(updateBook.getBookId(), updateBook.getISBN(),
                updateBook.getTitle(), updateBook.getAuthor(), updateBook.getPublisher(), updateBook.getPublishYear(),
                updateBook.getAvailableCopies());
    }

    /**
     * Updates the title of a book.
     * @param bookId   books id (long)
     * @param newTitle  New title (String)
     * @return Updated book data.
     * @throws BookDoesntExistsException If the book with the given ID does not exist.
     */
    public GetBookDTO updateTitle(long bookId, String newTitle){
        var book = bookRepository.findById(bookId).orElseThrow(() -> new BookDoesntExistsException(null));
        book.setTitle(newTitle);
        var updateBook = bookRepository.save(book);
        return new GetBookDTO(updateBook.getBookId(), updateBook.getISBN(),
                updateBook.getTitle(), updateBook.getAuthor(), updateBook.getPublisher(), updateBook.getPublishYear(),
                updateBook.getAvailableCopies());
    }

    /**
     * Updates the Author of a book.
     * @param bookId   books id (long)
     * @param newAuthor  New Author (String)
     * @return Updated book data.
     * @throws BookDoesntExistsException If the book with the given ID does not exist.
     */
    public GetBookDTO updateAuthor(long bookId, String newAuthor){
        var book = bookRepository.findById(bookId).orElseThrow(() -> new BookDoesntExistsException(null));
        book.setAuthor(newAuthor);
        var updateBook = bookRepository.save(book);
        return new GetBookDTO(updateBook.getBookId(), updateBook.getISBN(),
                updateBook.getTitle(), updateBook.getAuthor(), updateBook.getPublisher(), updateBook.getPublishYear(),
                updateBook.getAvailableCopies());
    }

    /**
     * Updates the Publisher of a book.
     * @param bookId   books id (long)
     * @param newPublisher  New Publisher (String)
     * @return Updated book data.
     * @throws BookDoesntExistsException If the book with the given ID does not exist.
     */
    public GetBookDTO updatePublisher(long bookId, String newPublisher){
        var book = bookRepository.findById(bookId).orElseThrow(() -> new BookDoesntExistsException(null));
        book.setPublisher(newPublisher);
        var updateBook = bookRepository.save(book);
        return new GetBookDTO(updateBook.getBookId(), updateBook.getISBN(),
                updateBook.getTitle(), updateBook.getAuthor(), updateBook.getPublisher(), updateBook.getPublishYear(),
                updateBook.getAvailableCopies());
    }

    /**
     * Updates the PublishYear of a book.
     * @param bookId   books id (long)
     * @param newPublishYear  New PublishYear (int)
     * @return Updated book data.
     * @throws BookDoesntExistsException If the book with the given ID does not exist.
     */
    public GetBookDTO updatePublishYear(long bookId, int newPublishYear){
        var book = bookRepository.findById(bookId).orElseThrow(() -> new BookDoesntExistsException(null));
        book.setPublishYear(newPublishYear);
        var updateBook = bookRepository.save(book);
        return new GetBookDTO(updateBook.getBookId(), updateBook.getISBN(),
                updateBook.getTitle(), updateBook.getAuthor(), updateBook.getPublisher(), updateBook.getPublishYear(),
                updateBook.getAvailableCopies());
    }

    /**
     * Updates the AvailableCopies of a book.
     * @param bookId   books id (long)
     * @param newAvailableCopies  New AvailableCopies (String)
     * @return Updated book data.
     * @throws BookDoesntExistsException If the book with the given ID does not exist.
     */
    public GetBookDTO updateAvailableCopies(long bookId, String newAvailableCopies){
        var book = bookRepository.findById(bookId).orElseThrow(() -> new BookDoesntExistsException(null));
        book.setAvailableCopies(newAvailableCopies);
        var updateBook = bookRepository.save(book);
        return new GetBookDTO(updateBook.getBookId(), updateBook.getISBN(),
                updateBook.getTitle(), updateBook.getAuthor(), updateBook.getPublisher(), updateBook.getPublishYear(),
                updateBook.getAvailableCopies());
    }
}
