package com.devtiago.i_manage_app.backend.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class EmployeeException extends RuntimeException {

    private final ErrorResponse errorResponse;

    public EmployeeException(String message){
        super(message);
        this.errorResponse = new ErrorResponse(
                message,
                LocalDateTime.now(),
                "EmployeeException",
                HttpStatus.BAD_REQUEST.value()
        );
    }

    public EmployeeException(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }
}
