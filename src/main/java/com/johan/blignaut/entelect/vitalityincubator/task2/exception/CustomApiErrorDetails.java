package com.johan.blignaut.entelect.vitalityincubator.task2.exception;

public class CustomApiErrorDetails {

    private String message;

    public CustomApiErrorDetails(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
