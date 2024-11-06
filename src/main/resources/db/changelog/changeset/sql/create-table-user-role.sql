CREATE TABLE user_role(
    user_id INT NOT NULL,
    user_role VARCHAR(30) NOT NULL,
    CONSTRAINT fk_user_role FOREIGN KEY (user_id) REFERENCES users(id)
)