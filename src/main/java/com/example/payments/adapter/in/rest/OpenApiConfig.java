package com.example.payments.adapter.in.rest;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
class OpenApiConfig {

    @Bean
    OpenAPI paymentsOpenApi() {
        return new OpenAPI()
            .info(new Info()
                .title("Payments API")
                .version("v1")
                .description("REST adapter over the payments core"))
            .servers(List.of(new Server().url("http://localhost:8080").description("local")));
    }
}
