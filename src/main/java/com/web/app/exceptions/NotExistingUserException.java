package com.web.app.exceptions;

public class NotExistingUserException extends Exception {
    public NotExistingUserException(String message) {
        super(message);
    }
}
