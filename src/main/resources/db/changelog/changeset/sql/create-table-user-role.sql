CREATE TABLE user_roles(
    user_id INT NOT NULL,
    user_role VARCHAR(10) NOT NULL,
    CONSTRAINT fk_user_role FOREIGN KEY (user_id) REFERENCES users(id)
)