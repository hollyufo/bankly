package com.app.transaction.Dto.Response;


import com.app.transaction.Dto.Request.walletDto;

public class WalletResponse {
    private walletDto wallet;
    private String message;

    public WalletResponse(walletDto wallet, String message) {
        this.wallet = wallet;
        this.message = message;
    }

    public walletDto getWallet() {
        return wallet;
    }

    public void setWallet(walletDto wallet) {
        this.wallet = wallet;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}