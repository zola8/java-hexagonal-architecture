package com.example.payments.application;

import com.example.payments.adapter.out.persistence.InMemoryPaymentRepository;
import com.example.payments.application.port.in.CreatePaymentCommand;
import com.example.payments.domain.Payment;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PaymentServiceTest {

    private final PaymentService service = new PaymentService(new InMemoryPaymentRepository());

    @Test
    void creates_pending_payment() {
        var p = service.create(new CreatePaymentCommand("o-1", new BigDecimal("19.99"), "eur"));

        assertThat(p.getStatus()).isEqualTo(Payment.Status.PENDING);
        assertThat(p.getCurrency()).isEqualTo("EUR");
        assertThat(service.getById(p.getId())).isSameAs(p);
    }

    @Test
    void cannot_complete_twice() {
        var p = service.create(new CreatePaymentCommand("o-1", BigDecimal.TEN, "EUR"));
        service.complete(p.getId());

        assertThatThrownBy(() -> service.complete(p.getId()))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void rejects_negative_amount() {
        assertThatThrownBy(() -> service.create(new CreatePaymentCommand("o-1", new BigDecimal("-1"), "EUR")))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void unknown_id_throws_not_found() {
        assertThatThrownBy(() -> service.getById("nope"))
            .isInstanceOf(PaymentNotFoundException.class);
    }
}
