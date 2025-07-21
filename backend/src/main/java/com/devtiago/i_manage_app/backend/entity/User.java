package com.devtiago.i_manage_app.backend.entity;

import com.devtiago.i_manage_app.backend.entity.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    private Long id;

    @Column(name = "username", nullable = false)
    @NotBlank(message = "username is required")
    private String username;
    @Column(name = "password", nullable = false)
    @NotBlank(message = "password is required")
    private String password;
    @NotNull(message = "createdAt is required")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updatedAt;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "TB_USER_ROLES",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Enumerated(EnumType.STRING)
    @NotNull(message = "userRoles are required.")
    private Set<UserRole> userRoles;

    @OneToOne(mappedBy = "user")
    private Employee employee;

    public User(String username, String password, LocalDateTime createdAt, LocalDateTime updatedAt, Set<UserRole> userRoles) {
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userRoles = userRoles;
    }
}
