-- Створення таблиці авторів
CREATE TABLE IF NOT EXISTS authors (
                                       id BIGSERIAL PRIMARY KEY,
                                       first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    birth_date
