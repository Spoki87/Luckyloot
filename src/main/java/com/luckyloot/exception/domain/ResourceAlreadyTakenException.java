package com.luckyloot.exception.domain;

public class ResourceAlreadyTakenException extends RuntimeException {
    public ResourceAlreadyTakenException(String message){
        super(message);
    }
}
