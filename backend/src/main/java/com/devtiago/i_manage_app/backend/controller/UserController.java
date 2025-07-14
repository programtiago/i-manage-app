package com.devtiago.i_manage_app.backend.controller;

import com.devtiago.i_manage_app.backend.entity.dto.UserDto;
import com.devtiago.i_manage_app.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDto> findAll(){
        return userService.findAll();
    }
}
