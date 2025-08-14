package com.example.cards.repository;

import com.example.cards.entity.Cards;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for performing database operations on Cards entities.
 * Extends JpaRepository to provide CRUD operations and custom query methods.
 */
@Repository
public interface CardRepository extends JpaRepository<Cards, Long> {
    
    /**
     * Finds a card by mobile number.
     *
     * @param mobileNumber The mobile number to search for (must be 10 digits)
     * @return An Optional containing the found card, or empty if not found
     */
    Optional<Cards> findByMobileNumber(String mobileNumber);

    /**
     * Finds a card by card number.
     *
     * @param cardNumber The card number to search for
     * @return An Optional containing the found card, or empty if not found
     */
    Optional<Cards> findByCardNumber(String cardNumber);
}
