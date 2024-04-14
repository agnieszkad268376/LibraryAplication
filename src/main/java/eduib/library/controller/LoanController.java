package eduib.library.controller;


import eduib.library.controller.DTO.AddLoanDTO;
import eduib.library.controller.DTO.GetLoanDTO;
import eduib.library.controller.DTO.LoanResponseDTO;
import eduib.library.entity.LoanEntity;
import eduib.library.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {

    private final LoanService loanService;


    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/add")
    public ResponseEntity<LoanResponseDTO> add(@RequestBody AddLoanDTO loanDTO) {
        var newLoan = loanService.add(loanDTO);
        return new ResponseEntity<>(newLoan, HttpStatus.CREATED);
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<GetLoanDTO> getById(@PathVariable long id){
        GetLoanDTO loanDTO = loanService.getById(id);
        return new ResponseEntity<>(loanDTO, HttpStatus.OK);
    }
    @GetMapping("getByUserId/{user_id}")
    public ResponseEntity<List<GetLoanDTO>> getByUserId(@PathVariable long userId){
        return null;
    }

    @GetMapping("getByBookId/{book_id}")
    public ResponseEntity<List<GetLoanDTO>> getByBookId(@PathVariable long bookId){
        return null;
    }

    @GetMapping("getAll")
    public ResponseEntity<List<GetLoanDTO>> getAll(){
        List<GetLoanDTO> loansdto = loanService.getAll();
        return new ResponseEntity<>(loansdto, HttpStatus.OK);
    }
}
