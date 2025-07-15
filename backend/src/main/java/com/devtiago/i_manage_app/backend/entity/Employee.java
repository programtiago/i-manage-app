package com.devtiago.i_manage_app.backend.entity;

import com.devtiago.i_manage_app.backend.entity.enums.Operation;
import com.devtiago.i_manage_app.backend.entity.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

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
    @NotNull(message = "Worker Number is required")
    @Column(name = "worker_no")
    @Min(value = 3000, message = "Worker Number must be at least 3000")
    @Max(value = 100000, message = "Worker Number must be at most 100000")
    private Long workerNo;
    @Column(name = "full_name", length = 100, nullable = false)
    @NotBlank(message = "Full name is required")
    @Length(min = 10, max = 100, message = "Full name should be between 10 and 100 characters.")
    private String fullName;
    /**
     * Employee's corporate or personal email
     */
    @Column(name =  "email", length = 100)
    @Length(min = 15, max = 60, message = "Email should be between 15 and 60 characters.")
    @NotBlank(message = "Email is required")
    private String email;
    @Column(name = "phone_number",length = 14, nullable = false)
    @NotBlank(message = "Phone Number is required")
    @Length(min = 9, max = 14, message = "Phone number should be between 9 and 14 characters.")
    private String phoneNumber;
    /**
     * Recruitment agency or source if applicable.
     */
    @Length(min = 5, max = 20, message = "Recruitment company should be between 5 and 20 characters.")
    @Column(name = "recruitment_company", length = 30, nullable = false)
    @NotBlank(message = "Recruitment company is required.")
    private String recruitmentCompany;
    /**
     * Operational area (e.g., OPERATION X, OPERATION Y).
     * Can exist multiple operations inside company environment
     * An employee can only be part of ONE operation
     */
    @Enumerated(EnumType.STRING)
    @NotNull
    private Operation operation;
    @NotBlank(message = "Department company is required.")
    @Column(name = "department", length = 50, nullable = false)
    private String department;
    @NotNull
    @Column(name = "birthday_date")
    private LocalDate birthdayDate;
    /**
     * Current age (its computed depending on birthdayDate).
     */
    @Column(name = "age", nullable = false)
    private int age;
    @Column(name = "genre", nullable = false, length = 9)
    @NotBlank(message = "Genre is required.")
    private String genre;
    /**
     * Current employment status of the employee (e.g., ACTIVE, INACTIVE, ON_LEAVE).
     */
    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;
    /**
     * Date when the employee joined the company.
     */
    @NotNull(message = "Admission date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate admissionDate;
    /**
     * Timestamp of when the employee was registered in the system.
     */
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime registryDate;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonManagedReference
    private User user;

    public Employee(Long workerNo, String fullName, String email, String phoneNumber, String recruitmentCompany, Operation operation,
                    String department, LocalDate birthdayDate, int age, String genre, Status status, LocalDate admissionDate, LocalDateTime registryDate) {
        this.workerNo = workerNo;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.recruitmentCompany = recruitmentCompany;
        this.operation = operation;
        this.department = department;
        this.birthdayDate = birthdayDate;
        this.age = age;
        this.genre = genre;
        this.status = status;
        this.admissionDate = admissionDate;
        this.registryDate = registryDate;
    }
}
