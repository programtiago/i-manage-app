package com.devtiago.i_manage_app.backend.entity.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record EmployeeDto(
    String workerNo,
    String fullName,
    String email,
    String recruitmentCompany,
    String operation,
    String department,
    LocalDate birthdayDate,
    int age,
    LocalDate admissionDate,
    LocalDateTime registryDate
) { }
