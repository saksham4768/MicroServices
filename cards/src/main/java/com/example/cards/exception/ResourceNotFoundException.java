package com.example.cards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a requested resource is not found in the system.
 * Results in an HTTP 404 Not Found status code when thrown from a controller method.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    
    /**
     * Constructs a new ResourceNotFoundException with details about the missing resource.
     *
     * @param resourceName the name/type of the resource that was not found (e.g., "Card", "User")
     * @param fieldName the name of the field that was used in the search
     * @param fieldValue the value of the field that was used in the search
     */
    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s not found with the given input data %s : '%s'", 
                resourceName, fieldName, fieldValue));
    }
}
