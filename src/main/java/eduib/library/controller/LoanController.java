package eduib.library.controller;


import eduib.library.controller.DTO.AddLoanDTO;
import eduib.library.controller.DTO.LoanResponseDTO;
import eduib.library.entity.LoanEntity;
import eduib.library.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
