package com.app.transaction.Service;


import com.app.transaction.Entity.Transaction;
import com.app.transaction.Repository.transactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final transactionRepository transactionRepository;
    // save transaction
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
    // get all transactions for with a specific govId
    public List<Transaction> getAllTransactions(String govId) {
        return transactionRepository.findByGovId(govId);
    }

}
