package eduib.library.service;

import eduib.library.controller.DTO.AddBookDTO;
import eduib.library.controller.DTO.AddBookResponseDTO;
import eduib.library.controller.DTO.GetBookDTO;
import eduib.library.entity.BookEntity;
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
        var book =  bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        return new GetBookDTO(book.getBookId(), book.getISBN(),
                book.getTitle(), book.getAuthor(), book.getPublisher(), book.getPublishYear(),
                book.getAvailableCopies());
    }

    public void delete(long id){
        if(bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        } else {
            throw new RuntimeException();
        }
    }
}
