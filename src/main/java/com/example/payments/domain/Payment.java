package com.example.payments.domain;

import java.math.BigDecimal;
import java.util.UUID;

public class Payment {

    public enum Status {PENDING, COMPLETED}

    private final String id;
    private final String orderId;
    private final BigDecimal amount;
    private final String currency;
    private Status status;

    private Payment(String id, String orderId, BigDecimal amount, String currency, Status status) {
        this.id = id;
        this.orderId = orderId;
        this.amount = amount;
        this.currency = currency;
        this.status = status;
    }

    public static Payment create(String orderId, BigDecimal amount, String currency) {
        if (orderId == null || orderId.isBlank())
            throw new IllegalArgumentException("orderId is required");
        if (amount == null || amount.signum() <= 0)
            throw new IllegalArgumentException("amount must be positive");
        if (currency == null || currency.length() != 3)
            throw new IllegalArgumentException("currency must be a 3-letter code");
        return new Payment(UUID.randomUUID().toString(), orderId, amount,
            currency.toUpperCase(), Status.PENDING);
    }

    public void complete() {
        if (status != Status.PENDING)
            throw new IllegalStateException("payment " + id + " is already " + status);
        this.status = Status.COMPLETED;
    }

    public String getId() {
        return id;
    }

    public String getOrderId() {
        return orderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public Status getStatus() {
        return status;
    }
}