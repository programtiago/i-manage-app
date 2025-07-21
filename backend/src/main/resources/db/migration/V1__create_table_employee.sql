CREATE TABLE TB_EMPLOYEE (
    worker_no VARCHAR(5) PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    phone_number VARCHAR(20),
    recruitment_company VARCHAR(100),
    operation VARCHAR(50),
    department VARCHAR(100),
    birthday_date DATE,
    age INT CHECK (age >= 18 AND age <= 67),
    gender VARCHAR(20),
    status VARCHAR(50),
    admission_date DATE,
    registry_date TIMESTAMP,
    user_id BIGINT
);