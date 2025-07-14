package com.devtiago.i_manage_app.backend.entity.dto;

import java.time.LocalDateTime;

public record UserDto(
        String username,
        String password,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) { }
