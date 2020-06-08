package com.web.app.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IncorrectPasswordException extends Exception {
    public IncorrectPasswordException(String message) {
        super(message);
    }
}
