package com.example.cards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when an attempt is made to create a card that already exists.
 * Results in an HTTP 400 Bad Request status code when thrown from a controller method.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CardAlreadyxistsException extends RuntimeException {
    
    /**
     * Constructs a new CardAlreadyxistsException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public CardAlreadyxistsException(String message) {
        super(message);
    }
}
