package com.example.payments.application;

public class PaymentNotFoundException extends RuntimeException {

    public PaymentNotFoundException(String id) {
        super("payment not found: " + id);
    }

}