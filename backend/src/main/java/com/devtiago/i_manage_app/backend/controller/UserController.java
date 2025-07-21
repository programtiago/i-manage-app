package com.devtiago.i_manage_app.backend.controller;

import com.devtiago.i_manage_app.backend.entity.User;
import com.devtiago.i_manage_app.backend.entity.dto.UserDto;
import com.devtiago.i_manage_app.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{workerNo}")
    public UserDto update(@RequestBody UserDto user, @PathVariable String workerNo){
        return userService.update(user, workerNo);
    }

}
