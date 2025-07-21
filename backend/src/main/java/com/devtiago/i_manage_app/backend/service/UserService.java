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

    public UserDto update(UserDto user, String username){
        User existingUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserException("User not found with the username " + username));

        if (!existingUser.getUsername().equals(user.username())) {
            if (userRepository.existsByUsername(user.username())) {
                throw new UserException("Username " + user.username() + " is already in use.");
            }
            existingUser.setUsername(user.username());
        }

        existingUser.setPassword(user.password());
        existingUser.setUserRoles(user.userRoles());
        existingUser.setUpdatedAt(LocalDateTime.now());

        userRepository.save(existingUser);

        return userMapper.toDto(existingUser);
    }
}
