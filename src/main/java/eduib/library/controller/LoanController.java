package eduib.library.controller;


import eduib.library.controller.DTO.*;
import eduib.library.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class handling loan endpoints.
 */
@RestController
@RequestMapping("/loan")
public class LoanController {

    private final LoanService loanService;

    /**
     * Constructs a LoanController object
     * @param loanService The LoanService class
     */
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    /**
     * Adds a new loan.
     * @param loanDTO AddLoanDTO data.
     * @return ResponseEntity with LoanResponseDTO and HttpStatus.CREATED.
     */
    @PostMapping("/add")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<LoanResponseDTO> add(@RequestBody AddLoanDTO loanDTO) {
        var newLoan = loanService.add(loanDTO);
        return new ResponseEntity<>(newLoan, HttpStatus.CREATED);
    }

    /**
     * Getting a loan with given id.
     * @param id loan's id (long)
     * @return ResponseEntity with GetLoanDTO and HttpStatus.OK.
     */
    @GetMapping("getById/{id}")
    public ResponseEntity<GetLoanDTO> getById(@PathVariable long id){
        GetLoanDTO loanDTO = loanService.getById(id);
        return new ResponseEntity<>(loanDTO, HttpStatus.OK);
    }

    /**
     * Getting all loans
     * @param userId (Optional) user's id
     * @return ResponseEntity with List of loans and HttpStatus.OK.
     */
    @GetMapping("getAll")
    public ResponseEntity<List<GetLoanDTO>> getAll(@RequestParam(required = false) Long userId){
        List<GetLoanDTO> loansdto = loanService.getAll(userId);
        return new ResponseEntity<>(loansdto, HttpStatus.OK);
    }

    /**
     * Returns a loan.
     * @param returnLoanDTO ReturnLoanDTO with required data
     * @return GetReturnLoanDTO
     */
    @PatchMapping("/returnLoan")
    public GetReturnLoanDTO returnLoan(@RequestBody ReturnLoanDTO returnLoanDTO){

        var updatedLoan = loanService.returnLoan(returnLoanDTO);
        return updatedLoan;
    }

    /**
     * Getting loan history of a user with given id.
     * @param userId user's id
     * @return ResponseEntity with List of GetLoanDTO and HttpStatus.OK.
     */
    @GetMapping("/getUsersHistory/{userId}")
    public ResponseEntity<List<GetLoanDTO>> getUsesrsHistory(@PathVariable long userId){
        List<GetLoanDTO> history = loanService.getUsersHistory(userId);
        return new ResponseEntity<>(history, HttpStatus.OK);
    }
}
