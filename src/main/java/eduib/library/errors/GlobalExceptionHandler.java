package eduib.library.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleOtherExceptions(Exception ex) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
//    }

}