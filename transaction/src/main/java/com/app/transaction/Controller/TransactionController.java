package com.app.transaction.Controller;


import com.app.transaction.Entity.Transaction;
import com.app.transaction.Service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;
    private final WebClient webClient;

    // get all transactions for with a specific govId
    @GetMapping("/all/{govId}")
    public String getAllTransactions(@PathVariable String govId) {
        return transactionService.getAllTransactions(govId).toString();
    }
    // save transaction
    @GetMapping("/{type}/save/{govId}/{amount}")
    public Transaction saveTransaction(@PathVariable String govId, @PathVariable int amount, @PathVariable String type) {
        webClient.get().uri("http://localhost:8081/api/v1/wallet/get/" + govId).retrieve().bodyToMono(String.class).block();
       if (type.equals("deposit")) {
           webClient.put().uri("http://localhost:8081/api/v1/wallet/update/" + govId + "/" + amount).retrieve().bodyToMono(String.class).block();
       } else if (type.equals("withdraw")) {
           webClient.put().uri("http://localhost:8081/api/v1/wallet/update/" + govId + "/" + -amount).retrieve().bodyToMono(String.class).block();
       }
        return transactionService.saveTransaction(Transaction.builder().govId(govId).transactionAmount(String.valueOf(amount)).transactionType(type).build());
    }
}
