package com.example.payments.adapter.in.rest;

import com.example.payments.domain.Payment;

import java.math.BigDecimal;

public record PaymentResponse(String id, String orderId, BigDecimal amount,
                              String currency, String status) {

    public static PaymentResponse from(Payment p) {
        return new PaymentResponse(
            p.getId(),
            p.getOrderId(),
            p.getAmount(),
            p.getCurrency(),
            p.getStatus().name()
        );
    }
}
