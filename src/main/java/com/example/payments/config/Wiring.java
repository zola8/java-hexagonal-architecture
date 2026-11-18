package com.example.payments.config;

import com.example.payments.application.PaymentService;
import com.example.payments.application.port.in.PaymentUseCase;
import com.example.payments.application.port.out.PaymentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class Wiring {

    @Bean
    PaymentUseCase paymentUseCase(PaymentRepository repository) {
        return new PaymentService(repository);   // the core stays annotation-free
    }

}
