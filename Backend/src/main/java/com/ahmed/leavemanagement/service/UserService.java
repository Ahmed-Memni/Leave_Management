package com.ahmed.leavemanagement.service;


import com.ahmed.leavemanagement.dto.UserDTO;
import com.ahmed.leavemanagement.entity.User;
import com.ahmed.leavemanagement.mapper.UserMapper;
import com.ahmed.leavemanagement.repository.UserRepository;
import com.ahmed.leavemanagement.dto.UserResponse;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {


    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public UserService(
            UserRepository userRepository,
            UserMapper userMapper
    ) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    public List<UserResponse> getAllUsers() {

    return userRepository.findAll()
            .stream()
            .map(userMapper::toResponse)
            .toList();
}


    public User getUserByEmail(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(
                    () -> new RuntimeException("User not found")
                );
    }


    public User createUser(
            UserDTO userDTO
    ) {

        User user = userMapper.toEntity(userDTO);

        return userRepository.save(user);
    }
}