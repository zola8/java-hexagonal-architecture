package com.example.payments.adapter.in.rest;


import com.example.payments.application.port.in.CreatePaymentCommand;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreatePaymentRequest(
    @NotBlank String orderId,
    @NotNull @DecimalMin("0.01") BigDecimal amount,
    @NotBlank @Size(min = 3, max = 3) String currency) {

    public CreatePaymentCommand toCommand() {
        return new CreatePaymentCommand(orderId, amount, currency);
    }
}
