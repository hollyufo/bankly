package com.app.transaction.Controller;


import com.app.transaction.Dto.Response.WalletResponse;
import com.app.transaction.Entity.Transaction;
import com.app.transaction.Proxies.WalletProxy;
import com.app.transaction.Service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
