package fa.training.exception;

public class MovieShowTimeNotFoundException extends RuntimeException {
    public MovieShowTimeNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
