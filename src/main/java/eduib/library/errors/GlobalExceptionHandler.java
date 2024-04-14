package eduib.library.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for handling custom exceptions.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Users already exists");
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<String> WrongPasswordException(WrongPasswordException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Wrong username or password");
    }

    @ExceptionHandler(UserDoesntExistsException.class)
    public ResponseEntity<String> UserDoesntExistsException(WrongPasswordException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("User with given Id does not exists");
    }

    @ExceptionHandler(BookDoesntExistsException.class)
    public ResponseEntity<String> BookDoesntExistsException(WrongPasswordException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Book with given Id do not exists");
    }

    @ExceptionHandler(UserNameNotFound.class)
    public ResponseEntity<String> UserNameNotFound(WrongPasswordException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("User with this userName does not exists");
    }

    @ExceptionHandler(LoanDoesntExistsException.class)
    public ResponseEntity<String> LoanDoesntExistsException(WrongPasswordException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Loan with given Id does not exists");
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleOtherExceptions(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
    }

}