package com.example.payments.adapter.out.persistence.jpa;

import com.example.payments.domain.Payment;

final class PaymentMapper {

    private PaymentMapper() {
    }

    static Payment toDomain(PaymentEntity e) {
        return Payment.restore(e.getId(), e.getOrderId(), e.getAmount(),
            e.getCurrency(), e.getStatus());
    }

    static PaymentEntity toEntity(Payment p) {
        return new PaymentEntity(p.getId(), p.getOrderId(), p.getAmount(),
            p.getCurrency(), p.getStatus());
    }
}
