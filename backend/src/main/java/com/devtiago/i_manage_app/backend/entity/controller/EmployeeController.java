package com.devtiago.i_manage_app.backend.entity.controller;

import com.devtiago.i_manage_app.backend.entity.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
}
