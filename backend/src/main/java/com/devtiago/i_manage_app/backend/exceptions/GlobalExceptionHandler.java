package com.devtiago.i_manage_app.backend.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex){
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                LocalDateTime.now(),
                ex.getClass().getSimpleName(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleDateInvalidFormat(HttpMessageNotReadableException ex){
        Throwable cause = ex.getCause();
        String message = "Invalid JSON format";

        if (cause instanceof InvalidFormatException ife){
            if (ife.getTargetType().equals(LocalDate.class)){
                message = "Invalid date format. Please use yyyy-MM-dd";
            }
        }else if (cause instanceof DateTimeParseException){
            message = "Invalid date format. Please use yyyy-MM-dd";
        }

        ErrorResponse errorResponse = new ErrorResponse(
                message,
                LocalDateTime.now(),
                "InvalidFormatException",
                HttpStatus.BAD_REQUEST.value()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
