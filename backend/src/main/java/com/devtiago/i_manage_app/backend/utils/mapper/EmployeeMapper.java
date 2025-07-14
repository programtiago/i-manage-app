package com.devtiago.i_manage_app.backend.utils.mapper;

import com.devtiago.i_manage_app.backend.entity.Employee;
import com.devtiago.i_manage_app.backend.entity.dto.EmployeeDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {

    public Employee toEntity(EmployeeDto employeeDto){
        if (employeeDto == null) return null;

        return new Employee(employeeDto.workerNo(), employeeDto.fullName(), employeeDto.email(), employeeDto.phoneNumber(), employeeDto.recruitmentCompany(),
                employeeDto.operation(), employeeDto.department(), employeeDto.birthdayDate(), employeeDto.age(), employeeDto.genre(), employeeDto.status(), employeeDto.admissionDate(),
                employeeDto.registryDate());
    }

    public EmployeeDto toDto(Employee employee){
        if (employee == null) return null;

        return new EmployeeDto(employee.getWorkerNo(), employee.getFullName(), employee.getEmail(), employee.getPhoneNumber(), employee.getRecruitmentCompany(),
                employee.getOperation(), employee.getDepartment(), employee.getBirthdayDate(), employee.getAge(), employee.getGenre(), employee.getStatus(), employee.getAdmissionDate(),
                employee.getRegistryDate());
    }

    public List<EmployeeDto> toListDto(List<Employee> employees){
        return employees.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }


}
