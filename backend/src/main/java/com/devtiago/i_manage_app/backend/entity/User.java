package com.devtiago.i_manage_app.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
}
