package com.capgemini.oap.service;

import com.capgemini.oap.exception.CardNotFoundException;
import com.capgemini.oap.model.Card;
import com.capgemini.oap.repository.CardRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class CardServiceTest {

    @InjectMocks
    private CardService service = new CardServiceImpl();

    @Mock
    private CardRepository cardRepository;


    private final Card c1 = new Card(1, "Booster", "OLA");
    private final Card c2 = new Card(2, "Pre-Booster", "AddDrop");

    private final List<Card> list = new ArrayList<>();

    {
        c1.setCard_id(1);
        c2.setCard_id(2);
        list.addAll(List.of(c1, c2));
    }

    @Test
    void getCardsTest() {
        when(cardRepository.findAll()).thenReturn(list);

        List<Card> cardList = service.getAllCards();

        assertThat(cardList.size()).isEqualTo(2);
    }

    @Test
    void getCardByIdTest() {
        int id = 1;
        when(cardRepository.findById(id)).thenReturn(Optional.of(c1));
        Card card = service.getCardById(id);
        assertThat(card.getCard_id().equals(id));
    }
    @Test
    void getCardByIdWithExceptionTest(){
        CardNotFoundException cardNotFoundException=assertThrows(CardNotFoundException.class,
                ()->service.getCardById(1));
        assertEquals("Invalid Card ID entered or does not exist",cardNotFoundException.getMessage());
    }

    @Test
    void getCardByNameTest() {
        String cname="Booster";
        when(cardRepository.findBycardName(cname)).thenReturn(Optional.of(c1));
        Card card = service.getCardByName(cname);
        assertThat(card.getCardName()).isEqualTo(cname);
    }

    @Test
    void getCardByNameWithExceptionTest(){
        CardNotFoundException cardNotFoundException=assertThrows(CardNotFoundException.class,
                ()->service.getCardByName("Amplifier"));
        assertEquals("Incorrect Card Name or does not exist",cardNotFoundException.getMessage());
    }

    @Test
    void addCardTest() {

        when(cardRepository.save(c1)).thenReturn(c1);

        Card card = service.addCard(c1);

        assertThat(card.getCard_type()).isEqualTo(c1.getCard_type());
    }

    @Test
    void deleteCardTest() {
        Card card=new Card();
        card.setCard_id(3);
        card.setCardName("Card3");
        card.setCard_type("Pass");
        Optional<Card> optionalCard=Optional.of(card);
        when(cardRepository.findById(3)).thenReturn(optionalCard);
        service.getCardById(3);
        service.deleteCard(3);
        verify(cardRepository, times(1)).deleteById(3);
    }

    @Test
    void deleteCardWithExceptionTest(){
        CardNotFoundException cardNotFoundException=assertThrows(CardNotFoundException.class,
                ()->service.deleteCard(1));
        assertEquals("Card ID entered does not exist",cardNotFoundException.getMessage());
    }

    @Test
    void updateCardTest(){
        int id = 2;
        when(cardRepository.findById(id)).thenReturn(Optional.of(c2));

        Card temp=c2;
        String ctype = "Ola";
        temp.setCard_type(ctype);

        Card card = service.updateCard(id,c2);

        assertThat(card.getCard_type()).isEqualTo(ctype);
    }
    @Test
    void updateCardWithExceptionTest(){
        CardNotFoundException cardNotFoundException=assertThrows(CardNotFoundException.class,
                ()->service.updateCard(1,c1));
        assertEquals("Card ID entered does not exist",cardNotFoundException.getMessage());
    }
    @Test
    void exceptionTest(){
        int id=150;

        when(cardRepository.findById(id)).thenThrow(CardNotFoundException.class);

        assertThrows(CardNotFoundException.class,()->service.getCardById(id));

    }
}

