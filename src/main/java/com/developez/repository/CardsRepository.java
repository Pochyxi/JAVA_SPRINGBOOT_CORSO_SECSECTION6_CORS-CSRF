package com.developez.repository;

import com.developez.model.Cards;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardsRepository extends CrudRepository<Cards, Long> {

    // Trova le carte per un determinato cliente.
    List<Cards> findByCustomerId( int customerId);

}
