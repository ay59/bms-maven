package com.theatres.exception;

public class ScreenNotFoundException extends RuntimeException {
    public ScreenNotFoundException(String message) {
        super(message);
    }
}

