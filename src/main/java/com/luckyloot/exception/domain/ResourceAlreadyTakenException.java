package com.luckyloot.exception.domain;

import com.luckyloot.exception.base.BusinessException;

public class ResourceAlreadyTakenException extends BusinessException {
    public ResourceAlreadyTakenException(String message){
        super(message);
    }
}
