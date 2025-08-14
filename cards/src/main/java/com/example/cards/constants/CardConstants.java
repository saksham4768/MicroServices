package com.example.cards.constants;

/**
 * Utility class containing application-wide constants for card-related operations.
 * This class cannot be instantiated.
 */
public final class CardConstants {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private CardConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /** Default card type for new cards */
    public static final String CREDIT_CARD = "Credit Card";
    
    /** Default credit limit for new cards (1,00,000) */
    public static final int NEW_CARD_LIMIT = 1_00_000;
    
    // HTTP Status Codes and Messages
    
    /** HTTP 201 Created status code */
    public static final String STATUS_201 = "201";
    
    /** Success message for card creation */
    public static final String MESSAGE_201 = "Card created successfully";
    
    /** HTTP 200 OK status code */
    public static final String STATUS_200 = "200";
    
    /** Generic success message */
    public static final String MESSAGE_200 = "Request processed successfully";
    
    /** HTTP 417 Expectation Failed status code */
    public static final String STATUS_417 = "417";
    
    /** Error message for failed update operations */
    public static final String MESSAGE_417_UPDATE = "Update operation failed. Please try again or contact Dev team";
    
    /** Error message for failed delete operations */
    public static final String MESSAGE_417_DELETE = "Delete operation failed. Please try again or contact Dev team";
    
    // The following constants are commented out as they are not currently in use
    // public static final String STATUS_500 = "500";
    // public static final String MESSAGE_500 = "An error occurred. Please try again or contact Dev team";
}
