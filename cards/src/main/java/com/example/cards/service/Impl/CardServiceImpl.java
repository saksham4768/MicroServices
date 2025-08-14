package com.example.cards.service.Impl;

import com.example.cards.constants.CardConstants;
import com.example.cards.dto.CardDto;
import com.example.cards.entity.Cards;
import com.example.cards.exception.CardAlreadyxistsException;
import com.example.cards.exception.ResourceNotFoundException;
import com.example.cards.mapper.CardsMapper;
import com.example.cards.repository.CardRepository;
import com.example.cards.service.ICardService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

/**
 * Implementation of the ICardService interface.
 * Provides the business logic for card-related operations.
 */
@Service
@RequiredArgsConstructor
public class CardServiceImpl implements ICardService {

    /**
     * Repository for performing database operations on Cards entities.
     */
    private final CardRepository cardsRepository;
    /**
     * {@inheritDoc}
     * @throws CardAlreadyxistsException if a card already exists for the given mobile number
     */
    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> optionalCards = cardsRepository.findByMobileNumber(mobileNumber);
        if(optionalCards.isPresent()){
            throw new CardAlreadyxistsException("Card already registered with given mobileNumber "+mobileNumber);
        }
        cardsRepository.save(createNewCard(mobileNumber));
    }

    /**
     * Creates a new card with default values for the given mobile number.
     * 
     * @param mobileNumber The mobile number to associate with the new card
     * @return A new Cards entity with default values
     */
    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardConstants.NEW_CARD_LIMIT);
        return newCard;
    }

    /**
     * {@inheritDoc}
     * @throws ResourceNotFoundException if no card is found for the given mobile number
     */
    @Override
    public CardDto fetchCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));
        return CardsMapper.mapToCardDto(cards, new CardDto());
    }

    /**
     * {@inheritDoc}
     * @throws ResourceNotFoundException if no card is found with the given card number
     */
    @Override
    public boolean updateCard(CardDto cardsDto) {
        Cards cards = cardsRepository.findByCardNumber(cardsDto.getCardNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Card", "CardNumber", cardsDto.getCardNumber()));
        CardsMapper.mapToCards(cardsDto, cards);
        cardsRepository.save(cards);
        return true;
    }

    /**
     * {@inheritDoc}
     * @throws ResourceNotFoundException if no card is found for the given mobile number
     */
    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));
        cardsRepository.deleteById(cards.getCardId());
        return true;
    }
}
