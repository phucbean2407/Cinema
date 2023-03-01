package fa.training.handler;

import fa.training.model.respone.DataResponse;
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
    @ExceptionHandler(Exception.class)
    public static DataResponse handleException(Exception ex){
        return new DataResponse(true,ex.getMessage());
    }
    @ExceptionHandler(MultipartException.class)
    public static ResponseEntity handleMultipartException(MultipartException ex) {
        return new ResponseEntity("Invalid Upload Request, For more information: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResponseEntity handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        return new ResponseEntity("HTTP request method not supported for this operation, For more information: " + ex.getMessage(),HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(IOException.class)
    public static ResponseEntity handleIOException(Exception ex) {
        return new ResponseEntity("IO Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
