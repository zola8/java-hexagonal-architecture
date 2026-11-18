package com.example.payments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PaymentsApplication {
    public static void main(String[] args) {
        System.out.println("http://localhost:8080/swagger-ui/index.html");
        SpringApplication.run(PaymentsApplication.class, args);
    }
}
