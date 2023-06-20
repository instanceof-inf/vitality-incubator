package com.johan.blignaut.entelect.vitalityincubator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CustomInternalServerException extends RuntimeException {

    private String message;

    public CustomInternalServerException() {
        super();
    }

    public CustomInternalServerException(String message) {
        super(message);
        this.message = message;
    }

    public CustomInternalServerException(Exception e) {
        super(e);
    }

    public String getMessage() {
        return message;
    }
}
