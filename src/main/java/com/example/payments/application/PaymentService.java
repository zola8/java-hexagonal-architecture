package com.example.payments.application;

import com.example.payments.application.port.in.CreatePaymentCommand;
import com.example.payments.application.port.in.PaymentUseCase;
import com.example.payments.application.port.out.PaymentRepository;
import com.example.payments.domain.Payment;

import java.util.List;

public class PaymentService implements PaymentUseCase {

    private final PaymentRepository repository;

    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Payment create(CreatePaymentCommand cmd) {
        return repository.save(Payment.create(cmd.orderId(), cmd.amount(), cmd.currency()));
    }

    @Override
    public Payment getById(String id) {
        return repository.findById(id).orElseThrow(() -> new PaymentNotFoundException(id));
    }

    @Override
    public List<Payment> findAll() {
        return repository.findAll();
    }

    @Override
    public Payment complete(String id) {
        Payment payment = getById(id);
        payment.complete();
        return repository.save(payment);
    }
}
