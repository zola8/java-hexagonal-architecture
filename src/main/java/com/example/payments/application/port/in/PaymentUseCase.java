package com.example.payments.application.port.in;

import com.example.payments.domain.Payment;

import java.util.List;

public interface PaymentUseCase {
    Payment create(CreatePaymentCommand command);

    Payment getById(String id);

    List<Payment> findAll();

    Payment complete(String id);
}
