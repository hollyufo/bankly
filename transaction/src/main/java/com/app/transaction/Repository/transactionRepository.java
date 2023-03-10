package com.app.transaction.Repository;

import com.app.transaction.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface transactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByGovId(String govId);
    // get all the transaction by govId
    List<Transaction> findAllByGovId(String govId);
}

