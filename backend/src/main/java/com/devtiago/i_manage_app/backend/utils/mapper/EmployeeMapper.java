package com.devtiago.i_manage_app.backend.utils.mapper;

import com.devtiago.i_manage_app.backend.entity.Employee;
import com.devtiago.i_manage_app.backend.entity.User;
import com.devtiago.i_manage_app.backend.entity.dto.EmployeeDto;
import com.devtiago.i_manage_app.backend.entity.dto.UserDto;
import com.devtiago.i_manage_app.backend.entity.enums.Status;
import com.devtiago.i_manage_app.backend.entity.enums.UserRole;
import com.devtiago.i_manage_app.backend.repository.UserRepository;
import com.devtiago.i_manage_app.backend.utils.PasswordUserGenerator;
import com.devtiago.i_manage_app.backend.utils.RoleAssign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Mapper class to convert between {@link Employee} entities and {@link EmployeeDto} data transfer objects.
 * <p>
 * This class provides methods for:
 * <ul>
 *  <li>Mapping an {@link EmployeeDto} to an {@link Employee} entity.</li>
 *  <li>Mapping an {@link EmployeeDto} to an {@link Employee} with default set values for registryDate and status</li>
 *  <li>Mapping an {@link Employee} entity to an {@link EmployeeDto}</li>
 *  <li>Converting a list of {@link Employee} entities into a list of {@link EmployeeDto} objects.</li>
 * </ul>
 */
@Component
@RequiredArgsConstructor
public class EmployeeMapper {

    private final UserRepository userRepository;

    public Employee toEntity(EmployeeDto employeeDto){
        if (employeeDto == null) return null;

        User user = null;
        if (employeeDto.user() != null){
            user = new User();
            user.setUsername(employeeDto.user().username());
            user.setPassword(employeeDto.user().password());
            user.setCreatedAt(employeeDto.user().createdAt());
            user.setUpdatedAt(employeeDto.user().updatedAt());
            user.setUserRoles(employeeDto.user().userRoles().stream()
                    .map(role -> UserRole.valueOf(role.toString()))
                    .collect(Collectors.toSet()));
        }
        return new Employee(employeeDto.workerNo(), employeeDto.fullName(), employeeDto.email(), employeeDto.phoneNumber(), employeeDto.recruitmentCompany(),
                employeeDto.operation(), employeeDto.department(), employeeDto.birthdayDate(), employeeDto.age(), employeeDto.genre(), employeeDto.status(), employeeDto.admissionDate(),
                employeeDto.registryDate(), user);
    }

    public Employee newUserToEntity(EmployeeDto employeeDto){
        if (employeeDto == null) return null;

        User user = new User();

        Set<UserRole> userRoles = Collections.singleton(
                RoleAssign.resolveRole(employeeDto.department()));

        user.setUsername(employeeDto.workerNo().toString());
        user.setPassword(PasswordUserGenerator.generateFromName(employeeDto.fullName()));
        user.setCreatedAt(LocalDateTime.now());
        user.setUserRoles(userRoles);

        Employee employee = new Employee(employeeDto.workerNo(), employeeDto.fullName(), employeeDto.email(), employeeDto.phoneNumber(), employeeDto.recruitmentCompany(),
                employeeDto.operation(), employeeDto.department(), employeeDto.birthdayDate(), employeeDto.age(), employeeDto.genre(), Status.ACTIVE, employeeDto.admissionDate(),
                LocalDateTime.now(), user);

        user.setEmployee(employee);

        return employee;
    }

    public EmployeeDto toDto(Employee employee){
        if (employee == null) return null;

        UserDto userDto = null;
        if (employee.getUser() != null){
            userDto = new UserDto(
                    employee.getUser().getUsername(),
                    employee.getUser().getPassword(),
                    employee.getUser().getCreatedAt(),
                    employee.getUser().getUpdatedAt(),
                    employee.getUser().getUserRoles()
            );
        }

        return new EmployeeDto(employee.getWorkerNo(), employee.getFullName(), employee.getEmail(), employee.getPhoneNumber(), employee.getRecruitmentCompany(),
                employee.getOperation(), employee.getDepartment(), employee.getBirthdayDate(), employee.getAge(), employee.getGenre(), employee.getStatus(), employee.getAdmissionDate(),
                employee.getRegistryDate(), userDto);
    }

    public List<EmployeeDto> toListDto(List<Employee> employees){
        return employees.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }


}
