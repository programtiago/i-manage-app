package com.devtiago.i_manage_app.backend.entity;

import com.devtiago.i_manage_app.backend.entity.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Represents a system user responsible for authentication and access control.
 * Stores credentials and audit timestamps.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "TB_USER")
public class User {

    @Id
    private String username;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<UserRole> userRoles;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", referencedColumnName = "workerNo")
    private Employee employee;
}
