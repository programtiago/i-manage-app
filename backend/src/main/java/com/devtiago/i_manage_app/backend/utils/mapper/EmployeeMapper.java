package com.devtiago.i_manage_app.backend.utils.mapper;

import com.devtiago.i_manage_app.backend.entity.Employee;
import com.devtiago.i_manage_app.backend.entity.User;
import com.devtiago.i_manage_app.backend.entity.dto.EmployeeDto;
import com.devtiago.i_manage_app.backend.entity.enums.Status;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Mapper class to convert between {@link Employee} entities and {@link EmployeeDto} data transfer objects.
 * <p>
 * This class provides methods for:
 * <ul>
 *  <li>Mapping an {@link EmployeeDto} to an {@link Employee} entity.</li>
 *  <li>Mapping an {@link Employee} entity to an {@link EmployeeDto}</li>
 *  <li>Creating a new {@link EmployeeDto} from an {@link Employee} for a new employee creation with {@link User}.</li>
 *  <li>Converting a list of {@link Employee} entities into a list of {@link EmployeeDto} objects.</li>
 * </ul>
 */
@Component
public class EmployeeMapper {


    public Employee toEntity(EmployeeDto employeeDto){
        if (employeeDto == null) return null;

        return new Employee(employeeDto.workerNo(), employeeDto.fullName(), employeeDto.email(), employeeDto.phoneNumber(), employeeDto.recruitmentCompany(),
                employeeDto.operation(), employeeDto.department(), employeeDto.birthdayDate(), employeeDto.age(), employeeDto.genre(), employeeDto.status(), employeeDto.admissionDate(),
                employeeDto.registryDate(), employeeDto.user());
    }

    public EmployeeDto toDto(Employee employee){
        if (employee == null) return null;

        return new EmployeeDto(employee.getWorkerNo(), employee.getFullName(), employee.getEmail(), employee.getPhoneNumber(), employee.getRecruitmentCompany(),
                employee.getOperation(), employee.getDepartment(), employee.getBirthdayDate(), employee.getAge(), employee.getGenre(), employee.getStatus(), employee.getAdmissionDate(),
                employee.getRegistryDate(), employee.getUser());
    }

    public EmployeeDto createEmployeeWithUser(Employee employee){
        if (employee == null) return null;

        return new EmployeeDto(employee.getWorkerNo(), employee.getFullName(), employee.getEmail(), employee.getPhoneNumber(), employee.getRecruitmentCompany(), employee.getOperation(),
                employee.getDepartment(), employee.getBirthdayDate(), employee.getAge(), employee.getGenre(), Status.ACTIVE, employee.getAdmissionDate(),
                LocalDateTime.now(), employee.getUser());
    }

    public List<EmployeeDto> toListDto(List<Employee> employees){
        return employees.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }


}
