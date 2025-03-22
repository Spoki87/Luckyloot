package com.luckyloot.exception;

public class ResourceAlreadyTakenException extends RuntimeException {
    public ResourceAlreadyTakenException(String message){
        super(message);
    }
}
