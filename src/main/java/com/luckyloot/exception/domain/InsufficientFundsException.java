package com.luckyloot.exception.domain;

import com.luckyloot.exception.base.BusinessException;

public class InsufficientFundsException extends BusinessException {
    public InsufficientFundsException() {
        super("Not enough funds in wallet");
    }
}
