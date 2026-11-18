package com.example.payments.application.port.out;

import com.example.payments.domain.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository {
    Payment save(Payment payment);

    Optional<Payment> findById(String id);

    List<Payment> findAll();
}
