package com.devtiago.i_manage_app.backend.service;

import com.devtiago.i_manage_app.backend.entity.User;
import com.devtiago.i_manage_app.backend.entity.dto.UserDto;
import com.devtiago.i_manage_app.backend.exceptions.UserException;
import com.devtiago.i_manage_app.backend.repository.UserRepository;
import com.devtiago.i_manage_app.backend.utils.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();

        return userMapper.toListDto(users);
    }

    public UserDto findByWorkerNo(String workerNo){
        User user = userRepository.findById(workerNo).orElseThrow(() -> new UserException("User not found with worker number " + workerNo));

        return userMapper.toDto(user);
    }

    public UserDto update(UserDto user, String workerNo){
        UserDto userDto = this.findByWorkerNo(workerNo);
        User existingUser = userMapper.toEntity(userDto);

        existingUser.setUsername(user.username());
        existingUser.setPassword(user.password());
        existingUser.setUserRoles(user.userRoles());
        existingUser.setUpdatedAt(LocalDateTime.now());

        userRepository.save(existingUser);

        return userMapper.toDto(existingUser);
    }
}
