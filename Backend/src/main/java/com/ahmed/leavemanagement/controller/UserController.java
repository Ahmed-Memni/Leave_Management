package com.ahmed.leavemanagement.controller;

import com.ahmed.leavemanagement.dto.UserDTO;
import com.ahmed.leavemanagement.entity.User;
import com.ahmed.leavemanagement.service.UserService;
import com.ahmed.leavemanagement.dto.UserResponse;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {


    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    // GET ALL USERS
    @GetMapping
public List<UserResponse> getAllUsers() {
    return userService.getAllUsers();
}


    // CREATE USER
    @PostMapping
    public User createUser(
            @Valid @RequestBody UserDTO userDTO
    ) {
        return userService.createUser(userDTO);
    }


    // GET USER BY EMAIL
    @GetMapping("/email/{email}")
    public User getUserByEmail(
            @PathVariable String email
    ) {
        return userService.getUserByEmail(email);
    }
}