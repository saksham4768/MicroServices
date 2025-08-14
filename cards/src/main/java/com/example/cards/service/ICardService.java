package com.example.cards.service;

import com.example.cards.dto.CardDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

/**
 * Service interface for card-related business operations.
 * Defines the contract for managing card entities in the system.
 */
public interface ICardService {
    
    /**
     * Creates a new card for the given mobile number.
     *
     * @param mobileNumber The mobile number to associate with the new card (must be 10 digits)
     * @throws com.example.cards.exception.CardAlreadyxistsException if a card already exists for the given mobile number
     */
    void createCard(String mobileNumber);

    /**
     * Fetches card details for the given mobile number.
     *
     * @param mobileNumber The mobile number to fetch card details for (must be 10 digits)
     * @return CardDto containing the card details
     * @throws com.example.cards.exception.ResourceNotFoundException if no card is found for the given mobile number
     */
    CardDto fetchCard(@Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits") String mobileNumber);

    /**
     * Updates the card details.
     *
     * @param cardsDto The card data transfer object containing updated information
     * @return true if the update was successful, false otherwise
     * @throws com.example.cards.exception.ResourceNotFoundException if no card is found with the given card number
     * @throws jakarta.validation.ValidationException if the card data is invalid
     */
    boolean updateCard(@Valid CardDto cardsDto);

    /**
     * Deletes the card associated with the given mobile number.
     *
     * @param mobileNumber The mobile number associated with the card to delete (must be 10 digits)
     * @return true if the deletion was successful, false otherwise
     * @throws com.example.cards.exception.ResourceNotFoundException if no card is found for the given mobile number
     */
    boolean deleteCard(@Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits") String mobileNumber);
}
