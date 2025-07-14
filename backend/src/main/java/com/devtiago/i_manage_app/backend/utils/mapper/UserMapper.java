package com.devtiago.i_manage_app.backend.utils.mapper;

import com.devtiago.i_manage_app.backend.entity.Employee;
import com.devtiago.i_manage_app.backend.entity.User;
import com.devtiago.i_manage_app.backend.entity.dto.EmployeeDto;
import com.devtiago.i_manage_app.backend.entity.dto.UserDto;
import com.devtiago.i_manage_app.backend.entity.enums.Status;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User toEntity(UserDto userDto){
        if (userDto == null) return null;

        return new User(userDto.username(), userDto.password(), userDto.createdAt(), userDto.updatedAt());
    }

    public UserDto toDto(User user){
        if (user == null) return null;

        return new UserDto(user.getUsername(), user.getPassword(), user.getCreatedAt(), user.getUpdatedAt());
    }

    public UserDto newUser(User user){
        if (user == null) return null;

        return new UserDto(user.getUsername(), user.getPassword(), LocalDateTime.now(), user.getUpdatedAt());
    }

    public List<UserDto> toListDto(List<User> users){
        return users.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
