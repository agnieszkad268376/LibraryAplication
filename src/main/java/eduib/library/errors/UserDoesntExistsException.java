package eduib.library.errors;

public class UserDoesntExistsException extends RuntimeException{
    public UserDoesntExistsException(String message){
        super(message);
    }
}
