package com.developez.controller;

import com.developez.model.AccountTransactions;
import com.developez.repository.AccountTransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BalanceController {

    private final AccountTransactionsRepository accountTransactionsRepository;

    @Autowired
    public BalanceController(AccountTransactionsRepository accountTransactionsRepository) {
        this.accountTransactionsRepository = accountTransactionsRepository;
    }

    @GetMapping("/myBalance")
    public List<AccountTransactions> getBalanceDetails( @RequestParam int id) {
        // Recupera i dettagli delle transazioni dell'account in base all'ID del cliente
        List<AccountTransactions> accountTransactions = accountTransactionsRepository.
                findByCustomerIdOrderByTransactionDtDesc(id);

        // Verifica se sono presenti transazioni
        if (accountTransactions != null) {
            // Restituisci la lista delle transazioni trovate
            return accountTransactions;
        } else {
            // Se non sono presenti transazioni, restituisci null
            return null;
        }
    }

}
