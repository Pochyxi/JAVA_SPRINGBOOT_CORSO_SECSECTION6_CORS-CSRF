package com.developez.repository;

import com.developez.model.AccountTransactions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountTransactionsRepository extends CrudRepository<AccountTransactions, String> {

    // Trova le transazioni dell'account per un determinato cliente, ordinate per data di transazione (descendente).
    List<AccountTransactions> findByCustomerIdOrderByTransactionDtDesc(int customerId);

}
