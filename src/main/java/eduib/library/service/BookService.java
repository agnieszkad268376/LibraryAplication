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

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

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

    public List<GetBookDTO> getAll(){
        var books = bookRepository.findAll();
        return books.stream().map((book) -> new GetBookDTO(book.getBookId(), book.getISBN(),
                book.getTitle(), book.getAuthor(), book.getPublisher(), book.getPublishYear(),
                book.getAvailableCopies())).collect(Collectors.toList());
    }

    public GetBookDTO getOne(long id){
        var book =  bookRepository.findById(id).orElseThrow(() -> new BookDoesntExistsException(null));
        return new GetBookDTO(book.getBookId(), book.getISBN(),
                book.getTitle(), book.getAuthor(), book.getPublisher(), book.getPublishYear(),
                book.getAvailableCopies());
    }

    public void delete(long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        } else {
            throw new BookDoesntExistsException(null);
        }
    }

    public GetBookDTO updateISBN(long bookId, String newISBN){
        var book = bookRepository.findById(bookId).orElseThrow(() -> new BookDoesntExistsException(null));
        book.setISBN(newISBN);
        var updateBook = bookRepository.save(book);
        return new GetBookDTO(updateBook.getBookId(), updateBook.getISBN(),
                updateBook.getTitle(), updateBook.getAuthor(), updateBook.getPublisher(), updateBook.getPublishYear(),
                updateBook.getAvailableCopies());
    }

    public GetBookDTO updateTitle(long bookId, String newTitle){
        var book = bookRepository.findById(bookId).orElseThrow(() -> new BookDoesntExistsException(null));
        book.setTitle(newTitle);
        var updateBook = bookRepository.save(book);
        return new GetBookDTO(updateBook.getBookId(), updateBook.getISBN(),
                updateBook.getTitle(), updateBook.getAuthor(), updateBook.getPublisher(), updateBook.getPublishYear(),
                updateBook.getAvailableCopies());
    }

    public GetBookDTO updateAuthor(long bookId, String newAuthor){
        var book = bookRepository.findById(bookId).orElseThrow(() -> new BookDoesntExistsException(null));
        book.setAuthor(newAuthor);
        var updateBook = bookRepository.save(book);
        return new GetBookDTO(updateBook.getBookId(), updateBook.getISBN(),
                updateBook.getTitle(), updateBook.getAuthor(), updateBook.getPublisher(), updateBook.getPublishYear(),
                updateBook.getAvailableCopies());
    }

    public GetBookDTO updatePublisher(long bookId, String newPublisher){
        var book = bookRepository.findById(bookId).orElseThrow(() -> new BookDoesntExistsException(null));
        book.setPublisher(newPublisher);
        var updateBook = bookRepository.save(book);
        return new GetBookDTO(updateBook.getBookId(), updateBook.getISBN(),
                updateBook.getTitle(), updateBook.getAuthor(), updateBook.getPublisher(), updateBook.getPublishYear(),
                updateBook.getAvailableCopies());
    }

    public GetBookDTO updatePublishYear(long bookId, int newPublishYear){
        var book = bookRepository.findById(bookId).orElseThrow(() -> new BookDoesntExistsException(null));
        book.setPublishYear(newPublishYear);
        var updateBook = bookRepository.save(book);
        return new GetBookDTO(updateBook.getBookId(), updateBook.getISBN(),
                updateBook.getTitle(), updateBook.getAuthor(), updateBook.getPublisher(), updateBook.getPublishYear(),
                updateBook.getAvailableCopies());
    }

    public GetBookDTO updateAvailableCopies(long bookId, String newAvailableCopies){
        var book = bookRepository.findById(bookId).orElseThrow(() -> new BookDoesntExistsException(null));
        book.setAvailableCopies(newAvailableCopies);
        var updateBook = bookRepository.save(book);
        return new GetBookDTO(updateBook.getBookId(), updateBook.getISBN(),
                updateBook.getTitle(), updateBook.getAuthor(), updateBook.getPublisher(), updateBook.getPublishYear(),
                updateBook.getAvailableCopies());
    }
}
