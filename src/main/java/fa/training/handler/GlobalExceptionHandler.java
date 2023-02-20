package fa.training.handler;

import fa.training.model.respone.DataResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public static DataResponse handleException(Exception ex){
        return new DataResponse(true,ex.getMessage());
    }
}
