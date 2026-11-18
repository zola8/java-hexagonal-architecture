package com.example.payments.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OpenApiTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void publishes_the_payments_contract() throws Exception {
        mvc.perform(get("/v3/api-docs"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.info.title").value("Payments API"))
            .andExpect(jsonPath("$.paths['/api/payments'].post").exists())
            .andExpect(jsonPath("$.paths['/api/payments/{id}/complete'].post").exists())
            .andExpect(jsonPath("$.components.schemas.CreatePaymentRequest").exists())
            .andExpect(jsonPath("$.components.schemas.PaymentResponse").exists());
    }
}