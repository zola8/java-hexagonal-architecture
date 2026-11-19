create TABLE payments (
    id       VARCHAR(36)   PRIMARY KEY,
    order_id VARCHAR(255)  NOT NULL,
    amount   NUMERIC(19,2) NOT NULL,
    currency VARCHAR(3)    NOT NULL,
    status   VARCHAR(20)   NOT NULL
);

create index idx_payments_order_id on payments (order_id);
