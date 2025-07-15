package com.devtiago.i_manage_app.backend.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private String message;
    private LocalDateTime timestamp;
    private String errorType;
    private int status;
}
