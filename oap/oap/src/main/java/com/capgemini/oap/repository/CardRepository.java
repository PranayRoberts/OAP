package com.capgemini.oap.repository;

import com.capgemini.oap.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card,Integer> {
    Optional<Card> findBycardName(String card_name);
}
