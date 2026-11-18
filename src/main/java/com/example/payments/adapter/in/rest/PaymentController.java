package com.example.payments.adapter.in.rest;

import com.example.payments.application.port.in.PaymentUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentUseCase payments;   // depends on the PORT, not the service

    public PaymentController(PaymentUseCase payments) {
        this.payments = payments;
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> create(@Valid @RequestBody CreatePaymentRequest request) {
        var payment = payments.create(request.toCommand());
        return ResponseEntity.created(URI.create("/api/payments/" + payment.getId()))
            .body(PaymentResponse.from(payment));
    }

    @GetMapping("/{id}")
    public PaymentResponse get(@PathVariable String id) {
        return PaymentResponse.from(payments.getById(id));
    }

    @GetMapping
    public List<PaymentResponse> list() {
        return payments.findAll().stream().map(PaymentResponse::from).toList();
    }

    @PostMapping("/{id}/complete")
    public PaymentResponse complete(@PathVariable String id) {
        return PaymentResponse.from(payments.complete(id));
    }
}
