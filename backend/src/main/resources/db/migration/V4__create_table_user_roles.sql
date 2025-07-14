CREATE TABLE TB_USER_ROLES (
    user_username VARCHAR(50),
    user_roles VARCHAR(50),
    CONSTRAINT fk_user_roles_user FOREIGN KEY (user_username) REFERENCES TB_USER(username)
)