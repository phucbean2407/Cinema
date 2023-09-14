package fa.training.exception;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
