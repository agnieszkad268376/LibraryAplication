package eduib.library.controller;

import eduib.library.controller.DTO.AddBookDTO;
import eduib.library.controller.DTO.AddBookResponseDTO;
import eduib.library.controller.DTO.GetBookDTO;
import eduib.library.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/getAllBooks")
    public List<GetBookDTO> getAllBooks(){
        return bookService.getAll();
    }

    @GetMapping("/getById/{id}")
    public GetBookDTO getById(@PathVariable long id){
        return bookService.getOne(id);
    }

    // Requestbody, żeby podać jsosa zawierącego dane książki do metody
    @PostMapping("/add")
    public ResponseEntity<AddBookResponseDTO> add(@RequestBody AddBookDTO book){
        var newBook =  bookService.add(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
