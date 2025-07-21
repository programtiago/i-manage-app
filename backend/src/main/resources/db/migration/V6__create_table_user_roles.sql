CREATE TABLE TB_USER_ROLES (
    user_id BIGINT,
    user_roles VARCHAR(50),
    FOREIGN KEY (user_id) REFERENCES TB_USER(id)
)