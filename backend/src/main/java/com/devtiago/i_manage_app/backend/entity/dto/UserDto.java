package com.devtiago.i_manage_app.backend.entity.dto;

import com.devtiago.i_manage_app.backend.entity.Employee;
import com.devtiago.i_manage_app.backend.entity.enums.UserRole;

import java.time.LocalDateTime;
import java.util.Set;

public record UserDto(
        String username,
        String password,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Set<UserRole> userRoles,
        Employee employee
) { }
