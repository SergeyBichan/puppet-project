CREATE TABLE requisites (
                            id SERIAL PRIMARY KEY,
                            account_num VARCHAR(255),
                            correspondent_account_number VARCHAR(255),
                            inn VARCHAR(12),
                            kpp VARCHAR(9),
                            kbk VARCHAR(20),
                            user_id BIGINT,
                            FOREIGN KEY (user_id) REFERENCES users(id)
);