package com.app.wallet.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "wallet")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class wallet {
    @Id
    private String id;
    private String govId;
    // balance is in mad
    private double balance;

}
