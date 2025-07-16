package com.devtiago.i_manage_app.backend.controller;

import com.devtiago.i_manage_app.backend.entity.dto.EmployeeDto;
import com.devtiago.i_manage_app.backend.entity.dto.FullEmployeeDto;
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
    public List<FullEmployeeDto> findAll(){
        return employeeService.getAllEmployees();
    }

    @PostMapping("/create-with-user")
    public FullEmployeeDto createWithUser(@RequestBody @Valid EmployeeDto employee){
        return employeeService.createWithUser(employee);
    }

    @PostMapping("/create")
    public FullEmployeeDto create(@RequestBody @Valid EmployeeDto employee){
        return employeeService.create(employee);
    }

    @PutMapping("/{workerNo}")
    public EmployeeDto update(@RequestBody @Valid EmployeeDto employee, @PathVariable Long workerNo){
        return employeeService.update(employee, workerNo);
    }

    @GetMapping("/{workerNo}")
    public EmployeeDto findByWorkerNo(@PathVariable Long workerNo){
        return employeeService.findByWorkerNo(workerNo);
    }

    @PutMapping("deactivate/{workerNo}")
    public void deactivate(@PathVariable Long workerNo){
        employeeService.deactivateEmployee(workerNo);
    }
}
