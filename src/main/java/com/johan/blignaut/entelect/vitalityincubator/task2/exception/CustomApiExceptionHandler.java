package com.johan.blignaut.entelect.vitalityincubator.task2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final ResponseEntity<CustomApiErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new CustomApiErrorDetails(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
