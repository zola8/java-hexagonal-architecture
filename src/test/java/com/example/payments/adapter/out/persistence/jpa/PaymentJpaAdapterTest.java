package com.example.payments.adapter.out.persistence.jpa;

import com.example.payments.application.port.out.PaymentRepository;
import com.example.payments.domain.Payment;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional          // rolls back after each test — your dev data is untouched
class PaymentJpaAdapterTest {

    @Autowired
    PaymentRepository repository;   // injected by the PORT
    @Autowired
    EntityManager em;

    @Test
    void round_trips_a_payment_through_postgres() {
        Payment saved = repository.save(Payment.create("o-99", new BigDecimal("42.50"), "EUR"));

        em.flush();
        em.clear();     // empty the JPA cache, so the read really hits the database

        Payment found = repository.findById(saved.getId()).orElseThrow();
        assertThat(found.getAmount()).isEqualByComparingTo("42.50");
        assertThat(found.getCurrency()).isEqualTo("EUR");
        assertThat(found.getStatus()).isEqualTo(Payment.Status.PENDING);
    }

    @Test
    void persists_a_status_change() {
        Payment p = repository.save(Payment.create("o-100", BigDecimal.TEN, "EUR"));
        p.complete();
        repository.save(p);

        em.flush();
        em.clear();

        assertThat(repository.findById(p.getId()).orElseThrow().getStatus())
            .isEqualTo(Payment.Status.COMPLETED);
    }
}
