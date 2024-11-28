package com.theatres.exception;



public class TheatreNotFoundException extends RuntimeException {

    public TheatreNotFoundException() {
        super("Theatre not found.");
    }

    public TheatreNotFoundException(String message) {
        super(message);
    }

    public TheatreNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

