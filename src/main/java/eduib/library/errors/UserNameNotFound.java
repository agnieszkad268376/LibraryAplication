package eduib.library.errors;

public class UserNameNotFound extends RuntimeException{
    public UserNameNotFound(String message){
        super(message);
    }
}
