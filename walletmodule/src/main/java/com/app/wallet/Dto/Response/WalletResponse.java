package com.app.wallet.Dto.Response;


public class WalletResponse {
    private com.app.wallet.Entity.wallet wallet;
    private String message;

    public WalletResponse(com.app.wallet.Entity.wallet wallet, String message) {
        this.wallet = wallet;
        this.message = message;
    }

    public com.app.wallet.Entity.wallet getWallet() {
        return wallet;
    }

    public void setWallet(com.app.wallet.Entity.wallet wallet) {
        this.wallet = wallet;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}