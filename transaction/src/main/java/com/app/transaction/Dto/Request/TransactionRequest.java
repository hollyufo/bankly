package com.app.transaction.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
class TransactionRequest {
    private String govId;
    private double amount;
    private String transactionType;
}
