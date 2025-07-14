CREATE TABLE TB_USER (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(255),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    employee_id BIGINT UNIQUE,
    CONSTRAINT fk_user_employee FOREIGN KEY (employee_id) REFERENCES TB_EMPLOYEE(worker_no)
)