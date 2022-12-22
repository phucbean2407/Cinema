package fa.training.exception;

import fa.training.respone.DataResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(IllegalArgumentException.class)
    public  DataResponse handleIllegalArgument(IllegalArgumentException ex){
        return new DataResponse(true,"NULL ENTITY, Detail: "+ ex.getMessage());
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleConstraintViolationException(ConstraintViolationException ex){
        return new ResponseEntity("Exist Data in database", HttpStatus.OK);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(RuntimeException ex){
        return new ResponseEntity(ex.getMessage(), HttpStatus.OK);
    }
    @ExceptionHandler(RetryException.class)
    public DataResponse handleRetryException(RetryException ex){
        return new DataResponse(true, ex.getMessage());
    }
}

