package com.developez.controller;

import com.developez.model.Accounts;
import com.developez.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    private final AccountsRepository accountsRepository;

    @Autowired
    public AccountController(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    @GetMapping("/myAccount")
    public Accounts getAccountDetails( @RequestParam int id) {
        // Recupera i dettagli dell'account in base all'ID del cliente
        Accounts accounts = accountsRepository.findByCustomerId(id);

        // Verifica se l'account è stato trovato
        if (accounts != null ) {
            // Restituisci l'account trovato
            return accounts;
        }else {
            // Se l'account non è stato trovato, restituisci null
            return null;
        }
    }
}
