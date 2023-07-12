package com.developez.controller;

import com.developez.model.Customer;
import com.developez.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class LoginController {

    private final CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LoginController( CustomerRepository customerRepository, PasswordEncoder passwordEncoder ) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Registra un nuovo utente
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer) {
        Customer savedCustomer = null;
        ResponseEntity response = null;
        try {
            String hashPwd = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(hashPwd);
            customer.setCreateDt(String.valueOf(new Date(System.currentTimeMillis())));
            savedCustomer = customerRepository.save(customer);
            if (savedCustomer.getId() > 0) {
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("Given user details are successfully registered");
            }
        } catch (Exception ex) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured due to " + ex.getMessage());
        }
        return response;
    }

    @RequestMapping("/user")
    public Customer getUserDetailsAfterLogin( Authentication authentication) {
        // Recupera l'utente autenticato in base all'email
        List<Customer> customers = customerRepository.findByEmail(authentication.getName());

        // Verifica se sono presenti utenti
        if (customers.size() > 0) {
            // Restituisci il primo utente trovato
            return customers.get(0);
        } else {
            // Se non sono presenti utenti, restituisci null
            return null;
        }
    }
}
