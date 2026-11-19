package com.example.payments.adapter.out.persistence;

import com.example.payments.application.port.out.PaymentRepository;
import com.example.payments.domain.Payment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

//@Component
public class InMemoryPaymentRepository implements PaymentRepository {

    private final ConcurrentMap<String, Payment> store = new ConcurrentHashMap<>();

    @Override
    public Payment save(Payment p) {
        store.put(p.getId(), p);
        return p;
    }

    @Override
    public Optional<Payment> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Payment> findAll() {
        return List.copyOf(store.values());
    }
}
