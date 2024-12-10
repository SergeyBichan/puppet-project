package com.aston.frontendpracticeservice.exception;

public class UserIsFoundException extends RuntimeException {
    public UserIsFoundException(String message) {
        super(message);
    }
}
