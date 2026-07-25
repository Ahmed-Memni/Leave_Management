package com.ahmed.leavemanagement.service;


import com.ahmed.leavemanagement.dto.LoginRequest;
import com.ahmed.leavemanagement.dto.LoginResponse;
import com.ahmed.leavemanagement.dto.RegisterRequest;

import com.ahmed.leavemanagement.entity.User;

import com.ahmed.leavemanagement.enums.Role;

import com.ahmed.leavemanagement.repository.UserRepository;

import com.ahmed.leavemanagement.security.JwtService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {


    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;



    public LoginResponse login(LoginRequest request) {


        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );


        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );


        String token = jwtService.generateToken(user.getEmail());


        return new LoginResponse(token);
    }



    public User register(RegisterRequest request){


        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exists");
        }


        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        user.setRole(Role.EMPLOYEE);


        return userRepository.save(user);
    }

}