package com.app.wallet.Exceptions;

public class InvalidTransactionTypeException extends Exception {
    public InvalidTransactionTypeException(String message) {
        super(message);
    }
}
