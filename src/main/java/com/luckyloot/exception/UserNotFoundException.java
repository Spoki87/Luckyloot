package com.luckyloot.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("Email is incorrect.");
    }
}
