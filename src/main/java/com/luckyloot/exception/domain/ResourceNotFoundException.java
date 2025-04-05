package com.luckyloot.exception.domain;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(Class<?> resourceClass, Object resourceId) {
        super(resourceClass.getSimpleName() + " with value " + resourceId + " not found.");
    }
}
