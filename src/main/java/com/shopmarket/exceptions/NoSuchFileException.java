package com.shopmarket.exceptions;

public final class NoSuchFileException extends RuntimeException{
    public NoSuchFileException() {
        super();
    }

    public NoSuchFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchFileException(String message) {
        super(message);
    }
}
