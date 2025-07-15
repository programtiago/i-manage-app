package com.devtiago.i_manage_app.backend.entity.dto;

import com.devtiago.i_manage_app.backend.entity.Employee;
import com.devtiago.i_manage_app.backend.entity.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Set;

public record UserDto(
        @NotBlank(message = "username is required")
        String username,
        @NotBlank(message = "password is required")
        String password,
        @NotNull(message = "createdAt is required")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime createdAt,
        @NotNull(message = "updatedAt is required")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime updatedAt,
        @NotNull(message = "userRoles are required.")
        Set<UserRole> userRoles
        /*,@NotNull(message = "employee is required.")
        Employee employee*/
) { }
