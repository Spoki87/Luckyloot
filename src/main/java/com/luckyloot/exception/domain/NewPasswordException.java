package com.luckyloot.exception.domain;

public class NewPasswordException extends RuntimeException {
    public NewPasswordException(String message) {
        super(message);
    }
}
