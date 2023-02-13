package com.app.transaction.Controller;


import com.app.transaction.Dto.Request.TransactionDto;
import com.app.transaction.Dto.Response.TransactionResponse;
import com.app.transaction.Dto.Response.WalletResponse;
import com.app.transaction.Entity.Transaction;
import com.app.transaction.Exceptions.InsufficientBalanceException;
import com.app.transaction.Exceptions.InvalidTransactionTypeException;
import com.app.transaction.Proxies.WalletProxy;
import com.app.transaction.Service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;
    private final WalletProxy walletProxy;

    @GetMapping("/get/{govId}")
    public ResponseEntity<WalletResponse> getWallet (@PathVariable String govId){
        return walletProxy.getWallet(govId);
    }
    // making a transaction
    @PostMapping("/create")
    public ResponseEntity<TransactionResponse> makeTransaction(@RequestBody TransactionDto request) throws InsufficientBalanceException, InvalidTransactionTypeException {
        Transaction transaction = transactionService.makeTransaction(request).getTransaction();
        return ResponseEntity.ok(TransactionResponse.builder().transaction(transaction).message("Transaction made successfully").build());
    }
}
