package com.luckyloot.exception.domain;

import com.luckyloot.exception.base.BusinessException;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException() {
        super("Email is incorrect.");
    }
}
