package com.app.transaction.Service;


import com.app.transaction.Dto.Request.TransactionRequest;
import com.app.transaction.Dto.Request.walletDto;
import com.app.transaction.Dto.Response.TransactionResponse;
import com.app.transaction.Entity.Transaction;
import com.app.transaction.Exceptions.InsufficientBalanceException;
import com.app.transaction.Exceptions.InvalidTransactionTypeException;
import com.app.transaction.Proxies.WalletProxy;
import com.app.transaction.Repository.transactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final transactionRepository transactionRepository;
    private final WalletProxy walletProxy;


    // save transaction
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
    // get all transactions for with a specific govId
    public List<Transaction> getAllTransactions(String govId) {
        return transactionRepository.findByGovId(govId);
    }
    // making a transaction for a specific govId
    public TransactionResponse makeTransaction(TransactionRequest transactionDto) throws InvalidTransactionTypeException, InsufficientBalanceException {
        String govId = transactionDto.getGovId();
        double amount = transactionDto.getAmount();
        String transactionType = transactionDto.getTransactionType();

        if (!transactionType.equals("deposit") && !transactionType.equals("withdraw")) {
            throw new InvalidTransactionTypeException("Invalid transaction type. Only deposit and withdraw are allowed");
        }

        walletDto wallet = walletProxy.getWallet(govId).getBody().getWallet();
        if (transactionType.equals("withdraw")) {
            if (wallet.getBalance() < amount) {
                throw new InsufficientBalanceException("Insufficient balance");
            } else {
                wallet.setBalance(wallet.getBalance() - amount);
            }
        } else {
            wallet.setBalance(wallet.getBalance() + amount);
        }

        walletProxy.updateWallet(wallet.getGovId(), wallet.getBalance());
        Transaction transaction = Transaction.builder()
                .govId(govId)
                .transactionType(transactionType)
                .transactionAmount(String.valueOf(amount))
                .transactionDate(String.valueOf(System.currentTimeMillis()))
                .transactionStatus("success")
                .build();
        transaction = transactionRepository.save(transaction);

        return new TransactionResponse(transaction, "Transaction successful");
    }

}
