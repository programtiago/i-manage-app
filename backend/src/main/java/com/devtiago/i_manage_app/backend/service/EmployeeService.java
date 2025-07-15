package com.devtiago.i_manage_app.backend.service;

import com.devtiago.i_manage_app.backend.entity.Employee;
import com.devtiago.i_manage_app.backend.entity.User;
import com.devtiago.i_manage_app.backend.entity.dto.EmployeeDto;
import com.devtiago.i_manage_app.backend.entity.enums.Status;
import com.devtiago.i_manage_app.backend.entity.enums.UserRole;
import com.devtiago.i_manage_app.backend.exceptions.EmployeeException;
import com.devtiago.i_manage_app.backend.repository.EmployeeRepository;
import com.devtiago.i_manage_app.backend.repository.UserRepository;
import com.devtiago.i_manage_app.backend.utils.PasswordUserGenerator;
import com.devtiago.i_manage_app.backend.utils.RoleAssign;
import com.devtiago.i_manage_app.backend.utils.mapper.EmployeeMapper;
import com.devtiago.i_manage_app.backend.utils.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    private final UserMapper userMapper;
    private final EmployeeMapper employeeMapper;

    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }
    public EmployeeDto createWithUser(Employee employee){
        if (employeeRepository.existsByWorkerNo(employee.getWorkerNo())){
            throw new EmployeeException("The workerNo " + employee.getWorkerNo() +  " is already in use.");
        }

        if (employeeRepository.existsByPhoneNumber(employee.getPhoneNumber())){
            throw new EmployeeException("The phoneNumber " + employee.getPhoneNumber() +  " is already in use.");
        }

        Employee storedEmp;

        try {
            storedEmp = employeeRepository.save(employee);
        }catch (Exception ex){
            throw new EmployeeException("Failed to save employee: " + ex.getMessage());
        }

        try {
            User userEmp = new User();
            userEmp.setUsername(storedEmp.getWorkerNo().toString());
            userEmp.setPassword(PasswordUserGenerator.generateFromName(storedEmp.getFullName()));
            userEmp.setCreatedAt(LocalDateTime.now());

            Set<UserRole> userRoles = Collections.singleton(
                    RoleAssign.resolveRole(storedEmp.getDepartment()));

            userEmp.setUserRoles(userRoles);
            userEmp.setEmployee(storedEmp);

            userRepository.save(userEmp);
        }catch (Exception ex){
        }

        return employeeMapper.toDto(storedEmp);
    }
}
