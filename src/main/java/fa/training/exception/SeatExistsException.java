package fa.training.exception;

public class SeatExistsException extends RuntimeException {

    public SeatExistsException(String errorMessage) {
        super(errorMessage);
    }
}
