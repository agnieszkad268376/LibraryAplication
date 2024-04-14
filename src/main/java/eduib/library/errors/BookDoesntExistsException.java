package eduib.library.errors;

public class BookDoesntExistsException extends RuntimeException{
    public BookDoesntExistsException(String message){
        super(message);
    }
}
