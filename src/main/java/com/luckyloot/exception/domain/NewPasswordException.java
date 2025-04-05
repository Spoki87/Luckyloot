package com.luckyloot.exception.domain;

import com.luckyloot.exception.base.BusinessException;

public class NewPasswordException extends BusinessException {
    public NewPasswordException(String message) {
        super(message);
    }
}
