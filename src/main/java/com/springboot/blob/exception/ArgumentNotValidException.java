package com.springboot.blob.exception;

public class ArgumentNotValidException extends RuntimeException{
    private String message;

    public ArgumentNotValidException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
