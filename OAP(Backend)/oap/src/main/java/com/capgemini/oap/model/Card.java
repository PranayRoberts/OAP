package com.capgemini.oap.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    @Id
    @SequenceGenerator(name = "card_sequence",sequenceName ="card_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "card_sequence")
    private Integer card_id;
    private String cardName;
    private String card_type;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "edge_id")
    private Edge edge;

    public Card(int card_id, String cardName, String card_type) {
        this.card_id=card_id;
        this.cardName=cardName;
        this.card_type=card_type;
    }

    public Integer getCard_id() {
        return card_id;
    }

    public void setCard_id(Integer card_id) {
        this.card_id = card_id;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    @Override
    public String toString() {
        return "Card{" +
                "card_id=" + card_id +
                ", cardName='" + cardName + '\'' +
                ", card_type='" + card_type + '\'' +
                '}';
    }
}