package eduib.library.errors;

public class LoanDoesntExistsException extends RuntimeException{
    public LoanDoesntExistsException(String messege){
        super(messege);
    }

}
