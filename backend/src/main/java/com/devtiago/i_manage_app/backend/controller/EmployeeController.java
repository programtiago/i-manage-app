package com.devtiago.i_manage_app.backend.controller;

import com.devtiago.i_manage_app.backend.entity.Employee;
import com.devtiago.i_manage_app.backend.entity.dto.EmployeeDto;
import com.devtiago.i_manage_app.backend.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<EmployeeDto> findAll(){
        return employeeService.getAllEmployees();
    }
    @PostMapping
    public EmployeeDto create(@RequestBody @Valid Employee employee){
        return employeeService.create(employee);
    }
}
