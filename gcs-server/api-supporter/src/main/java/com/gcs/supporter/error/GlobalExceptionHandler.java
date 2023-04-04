package com.gcs.supporter.error;

import com.gcs.supporter.error.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.gcs.supporter.util.api.ApiUtil.error;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleApiException(ApiException e){
        return ResponseEntity.status(e.getErrorCode()
                        .getStatus())
                .body(error(e.getMessage(), HttpStatus.resolve(e.getStatus())));
    }

}
