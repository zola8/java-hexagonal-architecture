package com.example.payments.adapter.in.rest;

import com.example.payments.application.port.in.PaymentUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@Tag(name = "Payments", description = "Register and complete payments")
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentUseCase payments;   // depends on the PORT, not the service

    public PaymentController(PaymentUseCase payments) {
        this.payments = payments;
    }

    @Operation(summary = "Register a new payment")
    @ApiResponse(responseCode = "201", description = "Payment created")
    @ApiResponse(responseCode = "400", description = "Invalid payload",
        content = @Content(mediaType = "application/problem+json",
            schema = @Schema(implementation = ProblemDetail.class)))
    @PostMapping
    public ResponseEntity<PaymentResponse> create(@Valid @RequestBody CreatePaymentRequest request) {
        var payment = payments.create(request.toCommand());
        return ResponseEntity.created(URI.create("/api/payments/" + payment.getId()))
            .body(PaymentResponse.from(payment));
    }

    @Operation(summary = "Fetch one payment")
    @ApiResponse(responseCode = "200", description = "Found")
    @ApiResponse(responseCode = "404", description = "Unknown id",
        content = @Content(mediaType = "application/problem+json",
            schema = @Schema(implementation = ProblemDetail.class)))
    @GetMapping("/{id}")
    public PaymentResponse get(@PathVariable String id) {
        return PaymentResponse.from(payments.getById(id));
    }

    @Operation(summary = "List all payments")
    @GetMapping
    public List<PaymentResponse> list() {
        return payments.findAll().stream().map(PaymentResponse::from).toList();
    }

    @Operation(summary = "Complete a pending payment",
        description = "Fails with 409 if the payment is already completed")
    @ApiResponse(responseCode = "200", description = "Completed")
    @ApiResponse(responseCode = "409", description = "Already completed",
        content = @Content(mediaType = "application/problem+json",
            schema = @Schema(implementation = ProblemDetail.class)))
    @PostMapping("/{id}/complete")
    public PaymentResponse complete(@PathVariable String id) {
        return PaymentResponse.from(payments.complete(id));
    }

}
