package com.devtiago.i_manage_app.backend.exceptions;

import com.devtiago.i_manage_app.backend.controller.EmployeeController;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice(assignableTypes = EmployeeController.class)
public class EmployeeExceptionHandler {

    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<ErrorResponse> handleEmployeeException(EmployeeException ex){
        ErrorResponse errorResponse = ex.getErrorResponse();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ErrorResponse> handleEmployeeValidation(MethodArgumentNotValidException ex){
        String errorFields = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ErrorResponse errorResponse = new ErrorResponse(
                errorFields,
                LocalDateTime.now(),
                "ValidationException",
                HttpStatus.BAD_REQUEST.value()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ErrorResponse> handleAgeValidation(DataIntegrityViolationException ex){
        String message = ex.getMessage();

        ErrorResponse errorResponse = new ErrorResponse(
                message,
                LocalDateTime.now(),
                "AgeException",
                HttpStatus.NOT_ACCEPTABLE.value()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
    }
}
