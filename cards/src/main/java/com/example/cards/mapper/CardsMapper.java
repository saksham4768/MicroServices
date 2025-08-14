package com.example.cards.mapper;

import com.example.cards.dto.CardDto;
import com.example.cards.entity.Cards;

/**
 * Mapper class for converting between Cards entities and CardDto objects.
 * Provides static methods for mapping between the entity and DTO layers.
 */
public class CardsMapper {
    /**
     * Maps a Cards entity to a CardDto.
     * Copies all relevant fields from the entity to the DTO.
     *
     * @param cards The source Cards entity
     * @param cardDto The target CardDto to populate
     * @return The populated CardDto
     * @throws IllegalArgumentException if either parameter is null
     */
    public static CardDto mapToCardDto(Cards cards, CardDto cardDto) {
        if (cards == null || cardDto == null) {
            throw new IllegalArgumentException("Neither cards nor cardDto can be null");
        }
        cardDto.setMobileNumber(cards.getMobileNumber());
        cardDto.setCardNumber(cards.getCardNumber());
        cardDto.setCardType(cards.getCardType());
        cardDto.setTotalLimit(cards.getTotalLimit());
        cardDto.setAmountUsed(cards.getAmountUsed());
        cardDto.setAvailableAmount(cards.getAvailableAmount());
        return cardDto;
    }

    /**
     * Maps a CardDto to a Cards entity.
     * Copies all relevant fields from the DTO to the entity.
     *
     * @param cardDto The source CardDto
     * @param cards The target Cards entity to populate
     * @return The populated Cards entity
     * @throws IllegalArgumentException if either parameter is null
     */
    public static Cards mapToCards(CardDto cardDto, Cards cards) {
        if (cardDto == null || cards == null) {
            throw new IllegalArgumentException("Neither cardDto nor cards can be null");
        }
        cards.setMobileNumber(cardDto.getMobileNumber());
        cards.setCardNumber(cardDto.getCardNumber());
        cards.setCardType(cardDto.getCardType());
        cards.setTotalLimit(cardDto.getTotalLimit());
        cards.setAmountUsed(cardDto.getAmountUsed());
        cards.setAvailableAmount(cardDto.getAvailableAmount());
        return cards;
    }
}
