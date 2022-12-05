package fa.training.exception;

import fa.training.respone.DataResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(Exception.class)
    public static DataResponse handleException(Exception ex){
        return new DataResponse(true,ex.getMessage());
    }
}
