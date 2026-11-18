package com.example.payments.adapter.in.rest;

import com.example.payments.application.port.in.CreatePaymentCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;


@Schema(description = "Payload to register a new payment")
public record CreatePaymentRequest(

    @Schema(description = "Order this payment belongs to", example = "o-123")
    @NotBlank String orderId,

    @Schema(description = "Amount in major currency units", example = "19.99")
    @NotNull @DecimalMin("0.01") BigDecimal amount,

    @Schema(description = "ISO-4217 currency code", example = "EUR")
    @NotBlank @Size(min = 3, max = 3) String currency) {

    public CreatePaymentCommand toCommand() {
        return new CreatePaymentCommand(orderId, amount, currency);
    }
}
