package com.devtiago.i_manage_app.backend.utils.mapper;

import com.devtiago.i_manage_app.backend.entity.Employee;
import com.devtiago.i_manage_app.backend.entity.User;
import com.devtiago.i_manage_app.backend.entity.dto.EmployeeDto;
import com.devtiago.i_manage_app.backend.entity.dto.FullEmployeeDto;
import com.devtiago.i_manage_app.backend.entity.dto.UserDto;
import com.devtiago.i_manage_app.backend.entity.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Mapper class to convert between {@link Employee} entities and {@link EmployeeDto} data transfer objects.
 * <p>
 * This class provides methods for:
 * <ul>
 *  <li>Mapping an {@link EmployeeDto} to an {@link Employee} entity.</li>
 *  <li>Mapping an {@link Employee} entity to an {@link EmployeeDto}</li>
 *  <li>Mapping an {@link Employee} entity to an {@link FullEmployeeDto} this object includes property age</li>
 *  <li>Converting a list of {@link Employee} entities into a list of {@link EmployeeDto} objects.</li>
 * </ul>
 */
@Component
@RequiredArgsConstructor
public class EmployeeMapper {

    private final UserMapper userMapper;

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
                employeeDto.operation(), employeeDto.department(), employeeDto.birthdayDate(),  /*employeeDto.age(),*/ employeeDto.gender(), employeeDto.status(), employeeDto.admissionDate(),
                employeeDto.registryDate(), user);
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
                employee.getOperation(), employee.getDepartment(), employee.getBirthdayDate(), employee.getGender(), employee.getStatus(), employee.getAdmissionDate(),
                employee.getRegistryDate(), userDto);
    }


    public FullEmployeeDto toFullDto(Employee employee){
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

        return new FullEmployeeDto(employee.getWorkerNo(), employee.getFullName(), employee.getEmail(), employee.getPhoneNumber(), employee.getRecruitmentCompany(),
                employee.getOperation(), employee.getDepartment(), employee.getBirthdayDate(), employee.getAge(), employee.getGender(), employee.getStatus(), employee.getAdmissionDate(),
                employee.getRegistryDate(), userDto);
    }

    public List<FullEmployeeDto> toListDto(List<Employee> employees){
        return employees.stream()
                .map(this::toFullDto)
                .collect(Collectors.toList());
    }


}
