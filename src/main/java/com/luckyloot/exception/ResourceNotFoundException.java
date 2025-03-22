package com.luckyloot.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(Class<?> resourceClass, Object resourceId) {
        super(resourceClass.getSimpleName() + " with ID " + resourceId + " not found.");
    }
}
