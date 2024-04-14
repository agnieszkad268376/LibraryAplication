package eduib.library.controller;

import eduib.library.controller.DTO.AddBookDTO;
import eduib.library.controller.DTO.AddBookResponseDTO;
import eduib.library.controller.DTO.GetBookDTO;
import eduib.library.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller class handling book endpoints.
 */
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    /**
     * Constructs a BookController object
     * @param bookService The BookService class
     */
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Showing all books
     * @return List of GetBookDTO
     */
    @PreAuthorize("permitAll()")
    @GetMapping("/getAllBooks")
    public List<GetBookDTO> getAllBooks(){
        return bookService.getAll();
    }

    /**
     * Shows a book with given id
     * @param id book's id (long)
     * @return GetBookDTO
     */
    @PreAuthorize("permitAll()")
    @GetMapping("/getById/{id}")
    public GetBookDTO getById(@PathVariable long id){
        return bookService.getOne(id);
    }

    /**
     * Adds a new book
     * @param book new book (AddBookDTO)
     * @return ResponseEntity with AddBookResponseDTO and HttpStatus.CREATED.
     */
    @PreAuthorize("hasRole('LIBRARIAN')")
    @PostMapping("/add")
    public ResponseEntity<AddBookResponseDTO> add(@RequestBody AddBookDTO book){
        var newBook =  bookService.add(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    /**
     * Deletes a book with given id
     * @param id Tbook's id (long)
     * @return ResponseEntity with HttpStatus.NO_CONTENT.
     */
    @PreAuthorize("hasRole('LIBRARIAN')")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Updates the ISBN of a book with given id
     * @param bookId book's id (long)
     * @param newISBN new ISBN. (String)
     * @return GetBookDTO
     */
    @PreAuthorize("hasRole('LIBRARIAN')")
    @PatchMapping("/updateISBN/{bookId}/{newISBN}")
    public GetBookDTO updateISBN(@PathVariable long bookId, @PathVariable String newISBN){
        bookService.updateISBN(bookId, newISBN);
        return bookService.getOne(bookId);
    }

    /**
     * Updates the title of a book with given id
     * @param bookId book's id (long)
     * @param newTitle  new title. (String)
     * @return GetBookDTO
     */
    @PreAuthorize("hasRole('LIBRARIAN')")
    @PatchMapping("/updateTitle/{bookId}/{newTitle}")
    public GetBookDTO updateTitle(@PathVariable long bookId, @PathVariable String newTitle){
        bookService.updateTitle(bookId, newTitle);
        return bookService.getOne(bookId);
    }

    /**
     * Updates the Author of a book with given id
     * @param bookId book's id (long)
     * @param newAuthor  new Author. (String)
     * @return GetBookDTO
     */
    @PreAuthorize("hasRole('LIBRARIAN')")
    @PatchMapping("/updateAuthor/{bookId}/{newAuthor}")
    public GetBookDTO updateAuthor(@PathVariable long bookId, @PathVariable String newAuthor){
        bookService.updateAuthor(bookId, newAuthor);
        return bookService.getOne(bookId);
    }

    /**
     * Updates the Publisher of a book with given id
     * @param bookId book's id (long)
     * @param newPublisher  new Publisher. (String)
     * @return GetBookDTO
     */
    @PreAuthorize("hasRole('LIBRARIAN')")
    @PatchMapping("/updatePublisher/{bookId}/{newPublisher}")
    public GetBookDTO updatePublisher(@PathVariable long bookId, @PathVariable String newPublisher){
        bookService.updatePublisher(bookId, newPublisher);
        return bookService.getOne(bookId);
    }

    /**
     * Updates the PublishYear of a book with given id
     * @param bookId book's id (long)
     * @param newPublishYear  new PublishYear. (int)
     * @return GetBookDTO
     */
    @PreAuthorize("hasRole('LIBRARIAN')")
    @PatchMapping("/updatePublishYear/{bookId}/{newPublishYear}")
    public GetBookDTO updatePublishYear(@PathVariable long bookId, @PathVariable int newPublishYear){
        bookService.updatePublishYear(bookId, newPublishYear);
        return bookService.getOne(bookId);
    }

    /**
     * Updates the AvailableCopies of a book with given id
     * @param bookId book's id (long)
     * @param newAvailableCopies  new AvailableCopies. (String)
     * @return GetBookDTO
     */
    @PreAuthorize("hasRole('LIBRARIAN')")
    @PatchMapping("/updateAvailableCopies/{bookId}/{newAvailableCopies}")
    public GetBookDTO updateAvailableCopies(@PathVariable long bookId, @PathVariable String newAvailableCopies){
        bookService.updateAvailableCopies(bookId, newAvailableCopies);
        return bookService.getOne(bookId);
    }

}
