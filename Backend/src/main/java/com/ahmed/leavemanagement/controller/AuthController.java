package com.ahmed.leavemanagement.controller;


import com.ahmed.leavemanagement.dto.RegisterRequest;
import com.ahmed.leavemanagement.entity.User;
import com.ahmed.leavemanagement.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.ahmed.leavemanagement.dto.LoginRequest;
import com.ahmed.leavemanagement.dto.LoginResponse;



@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;


    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request
    ){

       return authService.login(request);

   }

    @PostMapping("/register")
    public User register(
            @RequestBody RegisterRequest request
    ){

        return authService.register(request);

    }

}