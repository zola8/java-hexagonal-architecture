package com.example.payments.application.port.in;

import java.math.BigDecimal;

public record CreatePaymentCommand(String orderId, BigDecimal amount, String currency) {
}
