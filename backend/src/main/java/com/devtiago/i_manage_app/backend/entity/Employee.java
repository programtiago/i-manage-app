package com.devtiago.i_manage_app.backend.entity;

import com.devtiago.i_manage_app.backend.entity.enums.Operation;
import com.devtiago.i_manage_app.backend.entity.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents an employee within the company system
 * This entity contains personal, contact, and employment related details
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "TB_EMPLOYEE")
public class Employee {

    /**
     * Unique worker number (internal employee ID)
     */
    @Id
    private String workerNo;
    private String fullName;
    /**
     * Employee's corporate or personal email
     */
    private String email;
    private String phoneNumber;
    /**
     * Recruitment agency or source if applicable.
     */
    private String recruitmentCompany;
    /**
     * Operational area (e.g., OPERATION X, OPERATION Y).
     * Can exist multiple operations inside company environment
     * An employee can only be part of ONE operation
     */
    @Enumerated(EnumType.STRING)
    private Operation operation;
    private String department;
    private LocalDate birthdayDate;
    /**
     * Current age (its computed depending on birthdayDate).
     */
    private int age;
    private String genre;
    /**
     * Current employment status of the employee (e.g., ACTIVE, INACTIVE, ON_LEAVE).
     */
    @Enumerated(EnumType.STRING)
    private Status status;
    /**
     * Date when the employee joined the company.
     */
    private LocalDate admissionDate;
    /**
     * Timestamp of when the employee was registered in the system.
     */
    private LocalDateTime registryDate;
}
