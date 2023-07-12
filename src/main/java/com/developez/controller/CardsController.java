package com.developez.controller;

import com.developez.model.Cards;
import com.developez.repository.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardsController {

    private final CardsRepository cardsRepository;

    @Autowired
    public CardsController(CardsRepository cardsRepository) {
        this.cardsRepository = cardsRepository;
    }

    @GetMapping("/myCards")
    public List<Cards> getCardDetails( @RequestParam int id) {
        // Recupera i dettagli delle carte in base all'ID del cliente
        List<Cards> cards = cardsRepository.findByCustomerId(id);

        // Verifica se sono presenti carte
        if (cards != null) {
            // Restituisci la lista delle carte trovate
            return cards;
        } else {
            // Se non sono presenti carte, restituisci null
            return null;
        }
    }


}
