package com.ahmed.leavemanagement.dto;

import com.ahmed.leavemanagement.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTO {


    private Long id;


    @NotBlank(message = "First name is required")
    private String firstName;


    @NotBlank(message = "Last name is required")
    private String lastName;


    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;


    @NotNull(message = "Department is required")
    private Long departmentId;


    private Role role;


    private Long managerId;

}