INSERT INTO TB_EMPLOYEE (
    worker_no,
    full_name,
    email,
    phone_number,
    recruitment_company,
    operation,
    department,
    birthday_date,
    age,
    genre,
    status,
    admission_date,
    registry_date
)
VALUES
(30001, 'Ana Beatriz Silva', 'ana.silva@gmail.com', '965462341', 'Synergie',
            'OPERATION_X', 'Recursos Humanos', '1990-04-12', 35, 'FEMALE',
            'ACTIVE', '2020-06-01', CURRENT_TIMESTAMP),

(30002, 'Tiago Filipe Ferreira da Silva', 'programtiago@gmail.com', '932345321', 'Intern',
        'OPERATION_Y', 'Tecnologias de Informação', '1996-02-05', 29, 'MASCULINO',
        'ACTIVE', '2021-10-23', CURRENT_TIMESTAMP
);

