CREATE TABLE TB_USER (
    username BIGINT PRIMARY KEY,
    password VARCHAR(255),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
)