package com.devtiago.i_manage_app.backend.entity.dto;

import com.devtiago.i_manage_app.backend.entity.enums.Operation;
import com.devtiago.i_manage_app.backend.entity.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record EmployeeDto(
        @NotBlank(message = "Worker Number is required")
        //@Min(value = 30000, message = "Worker Number must be at least 30000")
        //@Max(value = 100000, message = "Worker Number must be at most 100000")
        String workerNo,
        @NotBlank(message = "Full name is required")
        @Length(min = 10, max = 100, message = "Full name should be between 10 and 100 characters.")
        String fullName,
        @Length(min = 15, max = 60, message = "Email should be between 15 and 60 characters.")
        @NotBlank(message = "Email is required")
        String email,
        @NotBlank(message = "Phone Number is required")
        @Length(min = 9, max = 14, message = "Phone number should be between 9 and 14 characters.")
        String phoneNumber,
        @Length(min = 5, max = 20, message = "Recruitment company should be between 5 and 20 characters.")
        @NotBlank(message = "Recruitment company is required.")
        String recruitmentCompany,
        @NotNull
        Operation operation,
        @NotBlank(message = "Department company is required.")
        @Length(min = 10, max = 50, message = "Department should be between 10 and 50 characters.")
        String department,
        @NotNull
        @Past
        LocalDate birthdayDate,
        @NotBlank(message = "Genre is required.")
        String gender,
        Status status,
        @NotNull(message = "AdmissionDate is required")
        LocalDate admissionDate,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime registryDate,
        UserDto user
) { }
