package com.developez.repository;

import com.developez.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    // Trova un cliente in base all'email.
    List<Customer> findByEmail(String email);

}
