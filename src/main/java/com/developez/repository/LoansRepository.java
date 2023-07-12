package com.developez.repository;

import com.developez.model.Loans;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoansRepository extends CrudRepository<Loans, Long> {

    // Trova i prestiti di un cliente, ordinati per data di inizio (discendente).
    List<Loans> findByCustomerIdOrderByStartDtDesc(int customerId);

}
