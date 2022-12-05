package fa.training.exception;

import fa.training.respone.DataResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(IllegalArgumentException.class)
    public  DataResponse handleIllegalArgument(IllegalArgumentException ex){
        return new DataResponse(true,"NULL ENTITY, Detail: "+ ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public DataResponse handleRuntimeException(RuntimeException ex){
        return new DataResponse(true, ex.getMessage());
    }
    @ExceptionHandler(RetryException.class)
    public DataResponse handleRetryException(RetryException ex){
        return new DataResponse(true, ex.getMessage());
    }
}

