package com.example.payments.adapter.out.persistence.jpa;

import com.example.payments.application.port.out.PaymentRepository;
import com.example.payments.domain.Payment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional(readOnly = true)
public class PaymentJpaAdapter implements PaymentRepository {

    private final PaymentJpaRepository paymentRepository;

    public PaymentJpaAdapter(PaymentJpaRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional
    public Payment save(Payment payment) {
        PaymentEntity saved = paymentRepository.save(PaymentMapper.toEntity(payment));
        return PaymentMapper.toDomain(saved);
    }

    @Override
    public Optional<Payment> findById(String id) {
        return paymentRepository.findById(id).map(PaymentMapper::toDomain);
    }

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll().stream().map(PaymentMapper::toDomain).toList();
    }
}
