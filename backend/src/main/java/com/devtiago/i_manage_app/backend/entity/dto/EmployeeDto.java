package com.devtiago.i_manage_app.backend.entity.dto;

import com.devtiago.i_manage_app.backend.entity.enums.Operation;
import com.devtiago.i_manage_app.backend.entity.enums.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record EmployeeDto(
    String workerNo,
    String fullName,
    String email,
    String phoneNumber,
    String recruitmentCompany,
    Operation operation,
    String department,
    LocalDate birthdayDate,
    int age,
    String genre,
    Status status,
    LocalDate admissionDate,
    LocalDateTime registryDate
) { }
