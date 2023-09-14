package fa.training.handler;

import fa.training.exception.MovieShowTimeNotFoundException;
import fa.training.exception.PersonNotFoundException;
import fa.training.exception.SeatExistsException;
import fa.training.model.respone.DataResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import java.io.IOException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public  DataResponse handleIllegalArgument(IllegalArgumentException ex){
        return new DataResponse(true,"NULL ENTITY, Detail: "+ ex.getMessage());
    }
    @ExceptionHandler(SeatExistsException.class)
    public ResponseEntity handleSeatExistsException(SeatExistsException ex){
        return new ResponseEntity("Error! Seat is ordered, Detail: " +ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity handlePersonNotFoundException(PersonNotFoundException ex){
        return new ResponseEntity("Error Not found, Detail: " +ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MovieShowTimeNotFoundException.class)
    public ResponseEntity handleMovieShowTimeNotFoundException(MovieShowTimeNotFoundException ex){
        return new ResponseEntity("Error Not found Movie show time, Detail: " +ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleConstraintViolationException(ConstraintViolationException ex){
        return new ResponseEntity("Error, Detail: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(RuntimeException ex){
        return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResponseEntity handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        return new ResponseEntity("HTTP request method not supported for this operation, For more information: " + ex.getMessage(),HttpStatus.METHOD_NOT_ALLOWED);
    }
    @ExceptionHandler(Exception.class)
    public static DataResponse handleException(Exception ex){
        return new DataResponse(true,ex.getMessage());
    }
    @ExceptionHandler(MultipartException.class)
    public static ResponseEntity handleMultipartException(MultipartException ex) {
        return new ResponseEntity("Invalid Upload Request, For more information: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(IOException.class)
    public static ResponseEntity handleIOException(Exception ex) {
        return new ResponseEntity("IO Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
