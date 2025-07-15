package com.devtiago.i_manage_app.backend.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class UserException extends RuntimeException {

    private final ErrorResponse errorResponse;

    public UserException(String message){
        super(message);
        this.errorResponse = new ErrorResponse(
                message,
                LocalDateTime.now(),
                "UserException",
                HttpStatus.BAD_REQUEST.value()
        );
    }

    public UserException(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }
}
