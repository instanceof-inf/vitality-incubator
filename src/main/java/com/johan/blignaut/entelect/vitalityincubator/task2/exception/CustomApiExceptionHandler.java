package com.johan.blignaut.entelect.vitalityincubator.task2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.johan.blignaut.entelect.vitalityincubator.restclient.ApiException;

@ControllerAdvice
public class CustomApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<CustomApiErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new CustomApiErrorDetails(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ApiException.class)
    public final ResponseEntity<Object> handleApiExceptions(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new CustomApiErrorDetails(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
