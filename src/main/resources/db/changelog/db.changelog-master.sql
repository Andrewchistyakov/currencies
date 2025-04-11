CREATE TABLE currency (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    base_currency VARCHAR(255) NOT NULL,
    price_change_rate VARCHAR(255) NOT NULL,
    description VARCHAR(1000) NOT NULL
);

