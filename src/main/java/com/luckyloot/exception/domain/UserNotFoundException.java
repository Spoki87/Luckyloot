package com.luckyloot.exception.domain;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("Email is incorrect.");
    }
}
