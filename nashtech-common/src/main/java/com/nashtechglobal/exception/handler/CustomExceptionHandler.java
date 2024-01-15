package com.nashtechglobal.exception.handler;



import com.nashtechglobal.exception.ResourceNotFoundException;
import com.nashtechglobal.exception.utils.ErrorResponse;
import com.nashtechglobal.exception.utils.ErrorStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;


@ControllerAdvice
public class CustomExceptionHandler {
    /**
     * Handles a ResourceNotFoundException and returns an
     * ErrorResponse with a status code of 404.
     * @param exception the ResourceNotFoundException that was thrown.
     * @return a ResponseEntity containing the ErrorResponse
     * and the HTTP status code.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ErrorResponse>
    noRecordFoundException(final Exception exception) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ErrorStatus.RECORD_NOT_FOUND,
                LocalDateTime.now(),
                exception.getLocalizedMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
