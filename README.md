# java-hexagonal-architecture

# Payments

A small Spring Boot service demonstrating **hexagonal architecture** (ports & adapters).

REST only — no database, no Kafka. Payments live in memory and are lost on restart.

## Requirements

- Java 21+
- Maven 3.9+

## Run

```bash
mvn spring-boot:run
```

- API: http://localhost:8080/api/payments
- Swagger UI: http://localhost:8080/swagger-ui.html
- OpenAPI spec: http://localhost:8080/v3/api-docs

## Endpoints

| Method | Path                          | Description                |
|--------|-------------------------------|----------------------------|
| POST   | `/api/payments`               | Create a payment           |
| GET    | `/api/payments`               | List all payments          |
| GET    | `/api/payments/{id}`          | Get one payment            |
| POST   | `/api/payments/{id}/complete` | Complete a pending payment |

Errors: `400` invalid input · `404` unknown id · `409` already completed.

```bash
curl -X POST localhost:8080/api/payments \
  -H 'Content-Type: application/json' \
  -d '{"orderId":"o-1","amount":19.99,"currency":"EUR"}'

curl -X POST localhost:8080/api/payments/<id>/complete
```

## Structure

```
com.example.payments
├── domain/                 business model (Payment) — plain Java
├── application/            use cases + ports — plain Java
│   ├── port/in/            PaymentUseCase
│   └── port/out/           PaymentRepository
├── adapter/
│   ├── in/rest/            controller, DTOs, OpenAPI, error handling
│   └── out/persistence/    in-memory PaymentRepository
└── config/                 Spring wiring
```

**Rule:** dependencies point inward. Only `adapter/` and `config/` may import Spring, Jakarta or Swagger.

## Tests

```bash
mvn test
```

## Extending

The core never changes when you add infrastructure:

- **Postgres** → new `PaymentRepository` implementation in `adapter/out/persistence`
- **Kafka consumer** → new listener in `adapter/in/kafka` calling the same `PaymentUseCase`
- **Payment provider** → new outbound port + HTTP adapter
