package com.devtiago.i_manage_app.backend.service;

import com.devtiago.i_manage_app.backend.entity.Employee;
import com.devtiago.i_manage_app.backend.entity.User;
import com.devtiago.i_manage_app.backend.entity.dto.EmployeeDto;
import com.devtiago.i_manage_app.backend.entity.enums.Status;
import com.devtiago.i_manage_app.backend.entity.enums.UserRole;
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
    public EmployeeDto create(Employee employee){
        EmployeeDto empDto = employeeMapper.createEmployeeDto(employee);

        if (employee.getRegistryDate() == null) {
            employee.setRegistryDate(LocalDateTime.now());
        }

        if (employee.getStatus() == null) {
            employee.setStatus(Status.ACTIVE);
        }

        User userEmp = new User();
        userEmp.setUsername(String.valueOf(empDto.workerNo()));
        userEmp.setPassword(PasswordUserGenerator.generateFromName(empDto.fullName()));

        Set<UserRole> userRoles = Collections.singleton(RoleAssign.resolveRole(empDto.department()));
        userEmp.setUserRoles(userRoles);
        userEmp.setEmployee(employee);

        userMapper.newUser(userEmp);

        employeeRepository.save(employeeMapper.toEntity(empDto));
        userRepository.save(userEmp);

        return empDto;
    }
}
