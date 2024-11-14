CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       first_name VARCHAR(25) NOT NULL,
                       last_name VARCHAR(25) NOT NULL,
                       birth_date DATE NOT NULL,
                       inn VARCHAR(12) NOT NULL UNIQUE,
                       snils VARCHAR(11) NOT NULL UNIQUE,
                       passport_number VARCHAR(20) NOT NULL UNIQUE,
                       login VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(25) NOT NULL
);