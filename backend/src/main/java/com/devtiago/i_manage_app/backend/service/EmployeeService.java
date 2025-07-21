package com.devtiago.i_manage_app.backend.service;

import com.devtiago.i_manage_app.backend.entity.Employee;
import com.devtiago.i_manage_app.backend.entity.User;
import com.devtiago.i_manage_app.backend.entity.dto.EmployeeDto;
import com.devtiago.i_manage_app.backend.entity.dto.EmployeeWithoutUserDto;
import com.devtiago.i_manage_app.backend.entity.dto.FullEmployeeDto;
import com.devtiago.i_manage_app.backend.entity.enums.Status;
import com.devtiago.i_manage_app.backend.exceptions.EmployeeException;
import com.devtiago.i_manage_app.backend.exceptions.UserException;
import com.devtiago.i_manage_app.backend.repository.EmployeeRepository;
import com.devtiago.i_manage_app.backend.repository.UserRepository;
import com.devtiago.i_manage_app.backend.utils.PasswordUserGenerator;
import com.devtiago.i_manage_app.backend.utils.RoleAssign;
import com.devtiago.i_manage_app.backend.utils.mapper.EmployeeMapper;
import com.devtiago.i_manage_app.backend.utils.mapper.UserMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    private final UserMapper userMapper;
    private final EmployeeMapper employeeMapper;

    public List<FullEmployeeDto> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toFullDto)
                .collect(Collectors.toList());
    }

    public FullEmployeeDto create(EmployeeDto employeeDto){
        int age = calculateAge(employeeDto.birthdayDate());

        Employee newEmployee = employeeMapper.toEntity(employeeDto);

        validateUniqueEmployeeFields(employeeDto);

        try {
            newEmployee.setStatus(Status.ACTIVE);
            newEmployee.setRegistryDate(LocalDateTime.now());
            newEmployee.setAge(age);
            newEmployee.setUser(null);

            saveEmployee(newEmployee);
        }catch (Exception ex){
            throw new EmployeeException("Failed to save employee: " + ex.getMessage());
        }

        return employeeMapper.toFullDto(newEmployee);
    }

    @Transactional
    public FullEmployeeDto createWithUser(EmployeeDto employeeDto){
        int age = calculateAge(employeeDto.birthdayDate());

        Optional<Employee> existingEmployee = employeeRepository.findByWorkerNo(employeeDto.workerNo());

        if (existingEmployee.isPresent()){
            throw new EmployeeException("Employee with workerNo " + employeeDto.workerNo() + " already in use.");
        }

        Long userId = Long.valueOf(employeeDto.workerNo());
        if (userRepository.existsById(userId)){
            throw new UserException("User with id " + userId + " already exists.");
        }

        User newUserEmp = new User();
        newUserEmp.setId(userId);
        newUserEmp.setUsername(employeeDto.workerNo());
        newUserEmp.setPassword(PasswordUserGenerator.generateFromName(employeeDto.fullName()));
        newUserEmp.setCreatedAt(LocalDateTime.now());
        newUserEmp.setUserRoles(Collections.singleton(RoleAssign.resolveRole(employeeDto.department())));

        userRepository.save(newUserEmp);

        validateUniqueEmployeeFields(employeeDto);

        Employee newEmployee = employeeMapper.toEntity(employeeDto);

        newEmployee.setRegistryDate(LocalDateTime.now());
        newEmployee.setStatus(Status.ACTIVE);
        newEmployee.setUser(newUserEmp);

        newEmployee.setAge(age);

        if (newEmployee.getAge() < 18 || age > 67){
            throw new EmployeeException("Age must be between 18 and 67");
        }

        logger.info("Creating employee: " + newEmployee);

        saveEmployee(newEmployee);

        logger.info("Created Employee: {}", newEmployee);

        return employeeMapper.toFullDto(newEmployee);
    }

    public EmployeeDto update(EmployeeDto employeeDto, String workerNo){
        Employee existingEmp = employeeRepository.findById(workerNo)
                .orElseThrow(() -> new EmployeeException("Employee not found with worker number " + workerNo));

        existingEmp.setFullName(employeeDto.fullName());
        existingEmp.setPhoneNumber(employeeDto.phoneNumber());
        existingEmp.setRecruitmentCompany(employeeDto.recruitmentCompany());
        existingEmp.setDepartment(employeeDto.department());
        existingEmp.setBirthdayDate(employeeDto.birthdayDate());
        existingEmp.setEmail(employeeDto.email());

        existingEmp.setStatus(existingEmp.getStatus());
        existingEmp.setRegistryDate(existingEmp.getRegistryDate());

        Employee updatedEmp = employeeRepository.save(existingEmp);

        return employeeMapper.toDto(updatedEmp);
    }

    public EmployeeDto findByWorkerNo(String workerNo) {
        Employee employee = employeeRepository.findById(workerNo)
                .orElseThrow(() -> new EmployeeException("Employee not found with worker number " + workerNo));

        return employeeMapper.toDto(employee);
    }

    public void deactivateEmployee(String workerNo) {
        Employee employee = employeeRepository.findById(workerNo)
                .orElseThrow(() -> new EmployeeException("Employee not found with worker number " + workerNo));

        employee.setStatus(Status.INACTIVE);

        User userEmp = employee.getUser();
        userEmp.getUserRoles().clear();

        employeeRepository.save(employee);
        userRepository.save(userEmp);
    }

    private void validateUniqueEmployeeFields(EmployeeDto employeeDto){
        //Validates workerNo and phoneNumber
        if (employeeRepository.existsByWorkerNo(employeeDto.workerNo())){
            throw new EmployeeException("The workerNo " + employeeDto.workerNo() +  " is already in use.");
        }

        if (employeeRepository.existsByPhoneNumber(employeeDto.phoneNumber())){
            throw new EmployeeException("The phoneNumber " + employeeDto.phoneNumber() +  " is already in use.");
        }

        if (employeeRepository.existsByEmail(employeeDto.email())){
            throw new EmployeeException("The email " + employeeDto.email() +  " is already in use.");
        }
    }

    @Transactional
    private void saveEmployee(Employee employee){
        try {
            employeeRepository.save(employee);
        }catch (Exception ex){
            throw new EmployeeException("Failed to save employee: " + ex.getMessage());
        }
    }

    private int calculateAge(LocalDate birthdayDate){
        if (birthdayDate == null){
            throw new IllegalArgumentException("BirthdayDate is required");
        }
        return Period.between(birthdayDate, LocalDate.now()).getYears();
    }
}
