package com.example.payments.adapter.out.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

interface PaymentJpaRepository extends JpaRepository<PaymentEntity, String> {
}
