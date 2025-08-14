package com.example.cards.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity class representing a Card in the system.
 * Extends BaseEntity to inherit common auditing fields like created/updated timestamps.
 */
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Cards extends BaseEntity {

    /**
     * Unique identifier for the card.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long cardId;

    /**
     * Mobile number associated with the card.
     * Must be a 10-digit number.
     */
    @Column(name = "mobile_number")
    private String mobileNumber;

    /**
     * Unique card number.
     * Generated automatically when a new card is created.
     */
    @Column(name = "card_number")
    private String cardNumber;

    /**
     * Type of the card (e.g., "Credit Card").
     */
    @Column(name = "card_type")
    private String cardType;

    /**
     * Total credit limit assigned to the card.
     */
    @Column(name = "total_limit")
    private int totalLimit;

    /**
     * Amount already used from the total limit.
     */
    @Column(name = "amount_used")
    private int amountUsed;

    /**
     * Available amount that can be used (totalLimit - amountUsed).
     */
    @Column(name = "available_amount")
    private int availableAmount;
}
