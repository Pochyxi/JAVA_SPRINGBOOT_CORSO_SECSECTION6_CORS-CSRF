package com.developez.repository;

import com.developez.model.Accounts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends CrudRepository<Accounts, Long> {

    // Metodo per trovare un oggetto "Accounts" in base all'ID del cliente.
    Accounts findByCustomerId(int customerId);

}
