package com.developez.controller;

import com.developez.model.Loans;
import com.developez.repository.LoansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoansController {

    private final LoansRepository loanRepository;

    @Autowired
    public LoansController(LoansRepository loansRepository) {
        this.loanRepository = loansRepository;
    }

    @GetMapping("/myLoans")
    public List<Loans> getLoanDetails( @RequestParam int id) {
        // Recupera i dettagli dei prestiti in base all'ID del cliente, ordinati per data di inizio (discendente)
        List<Loans> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(id);

        // Verifica se sono presenti prestiti
        if (loans != null) {
            // Restituisci la lista dei prestiti trovati
            return loans;
        } else {
            // Se non sono presenti prestiti, restituisci null
            return null;
        }
    }

}
