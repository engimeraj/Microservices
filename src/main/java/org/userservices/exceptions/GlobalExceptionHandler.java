package org.userservices.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.userservices.payload.ApiStatus;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiStatus> handleResourceNotFoundException(ResourceNotFoundException ex){
        String message = ex.getMessage();
        ApiStatus a = new ApiStatus();
        a.setMessage(message);
        a.setStatus(HttpStatus.NOT_FOUND);
        a.setSuccess(true);
        return new ResponseEntity<>(a,HttpStatus.OK);
    }
}
