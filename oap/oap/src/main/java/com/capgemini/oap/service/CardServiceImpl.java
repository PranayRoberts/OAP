package com.capgemini.oap.service;

import com.capgemini.oap.exception.CardNotFoundException;
import com.capgemini.oap.model.Card;
import com.capgemini.oap.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@Transactional
public class CardServiceImpl implements CardService{

    Logger logger = Logger.getLogger(CardServiceImpl.class.getName());
    public CardServiceImpl() {

    }
    private CardRepository cardRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository){
        this.cardRepository = cardRepository;
    }


    @Override
    public Card addCard(Card card) {
        cardRepository.save(card);
        return card;
    }

    @Override
    public List<Card> getAllCards() {
        logger.info("Finding all Cards");
        return cardRepository.findAll();
    }

    @Override
    public Card getCardById(Integer card_id) {
        Optional<Card> optionalCard=cardRepository.findById(card_id);
        if(optionalCard.isEmpty()) {
            logger.warning("Invalid Card ID entered or does not exist");
            throw new CardNotFoundException("Invalid Card ID entered or does not exist");
        }
        logger.info("Get a Card By ID");
        return optionalCard.get();
    }

    @Override
    public Card getCardByName(String cardName) {
        Optional<Card> optionalCard=cardRepository.findBycardName(cardName);
        if(optionalCard.isEmpty()) {
            logger.warning("Invalid Card Name entered or does not exist");
            throw new CardNotFoundException("Incorrect Card Name or does not exist");
        }
        logger.info("Get a Card By Name");
        return optionalCard.get();
    }

    @Override
    public void deleteCard(Integer card_id) {
        Optional<Card> optionalCard=cardRepository.findById(card_id);
        if(optionalCard.isEmpty()) {
            logger.warning("Invalid Card ID entered or does not exist");
            throw new CardNotFoundException("Card ID entered does not exist");
        }
        logger.info("Deleting a card By ID");
        cardRepository.deleteById(card_id);
    }

    @Override
    public Card updateCard(Integer card_id, Card card) {
        Optional<Card> optionalCard=cardRepository.findById(card_id);
        if(optionalCard.isEmpty()) {
            logger.warning("Invalid Card ID entered or does not exist");
            throw new CardNotFoundException("Card ID entered does not exist");
        }
        logger.info("Updating Card Details");
        optionalCard.get().setCardName(card.getCardName());
        optionalCard.get().setCard_type(card.getCard_type());
        return optionalCard.get();
    }
}
