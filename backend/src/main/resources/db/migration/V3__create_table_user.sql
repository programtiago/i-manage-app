CREATE TABLE TB_USER (
    username VARCHAR(20) PRIMARY KEY,
    password VARCHAR(255),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
)