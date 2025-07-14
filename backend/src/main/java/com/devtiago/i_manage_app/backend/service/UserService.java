package com.devtiago.i_manage_app.backend.service;

import com.devtiago.i_manage_app.backend.entity.User;
import com.devtiago.i_manage_app.backend.entity.dto.UserDto;
import com.devtiago.i_manage_app.backend.repository.UserRepository;
import com.devtiago.i_manage_app.backend.utils.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDto> findAll(){
        List<User> users = userRepository.findAll();

        return userMapper.toListDto(users);
    }
}
