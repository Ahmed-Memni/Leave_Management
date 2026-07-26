package com.ahmed.leavemanagement.controller;

import com.ahmed.leavemanagement.dto.UserDTO;
import com.ahmed.leavemanagement.entity.User;
import com.ahmed.leavemanagement.service.UserService;
import com.ahmed.leavemanagement.dto.UserResponse;
import org.springframework.security.core.Authentication;

import jakarta.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
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
    // Permission: VIEW_ALL_DATA
    // Role: ADMIN
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }



    // CREATE USER
    // Permission: CREATE_USER
    // Role: ADMIN
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public User createUser(
            @Valid @RequestBody UserDTO userDTO
    ) {
        return userService.createUser(userDTO);
    }



    // GET USER BY EMAIL
    // Permission: VIEW_USER
    // Role: ADMIN
    @GetMapping("/email/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    public User getUserByEmail(
            @PathVariable String email
    ) {
        return userService.getUserByEmail(email);
    }


    // GET USER BY ID
    // Permission: VIEW_USER
    // Role: ADMIN
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public User getUserById(
        @PathVariable Long id
    ){
    return userService.getUserById(id);
    }


    
    // GET USER BY ID
    // Permission: UPDATE_USER
    // Role: ADMIN
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public User updateUser(
        @PathVariable Long id,
        @RequestBody UserDTO dto
    ){
    return userService.updateUser(id,dto);
    }


 

    // GET USER BY ID
    // Permission: DELETE_USER
    // Role: ADMIN
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(
        @PathVariable Long id
    ){
    userService.deleteUser(id);
    }





    // GET USER BY ID
    // Permission: VIEW_TEAM
    // Role: MANAGER
    @GetMapping("/team")
    @PreAuthorize("hasRole('MANAGER')")
    public List<UserResponse> getMyTeam(
        Authentication authentication
     ){

    User manager =
            (User) authentication.getPrincipal();


    return userService.getTeam(manager);
    }
}