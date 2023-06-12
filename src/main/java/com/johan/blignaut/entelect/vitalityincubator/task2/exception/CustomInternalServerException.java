package com.johan.blignaut.entelect.vitalityincubator.task2.exception;

public class CustomInternalServerException extends RuntimeException {

    private String message;

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
