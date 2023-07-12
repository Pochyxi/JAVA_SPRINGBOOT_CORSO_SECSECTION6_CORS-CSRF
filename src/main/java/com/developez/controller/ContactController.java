package com.developez.controller;

import com.developez.model.Contact;
import com.developez.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

@RestController
// DOV'E' LOMBOK? :....(
public class ContactController {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @PostMapping("/contact")
    public Contact saveContactInquiryDetails( @RequestBody Contact contact) {
        // Genera un numero di richiesta di servizio unico per il contatto
        contact.setContactId(getServiceReqNumber());
        // Imposta la data di creazione come la data corrente
        contact.setCreateDt(new Date(System.currentTimeMillis()));
        // Salva il contatto nel repository
        return contactRepository.save(contact);
    }

    // Genera un numero di richiesta di servizio unico
    public String getServiceReqNumber() {
        Random random = new Random();
        int ranNum = random.nextInt(999999999 - 9999) + 9999;
        return "SR" + ranNum;
    }


}
