CREATE TABLE TB_USER (
    id BIGINT PRIMARY KEY,
    username VARCHAR(20),
    password VARCHAR(255),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
)