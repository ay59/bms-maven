package com.theatres.exception;

public class TheatreAlreadyExistsException extends RuntimeException {

    public TheatreAlreadyExistsException(String message) {
        super(message);
    }
}
