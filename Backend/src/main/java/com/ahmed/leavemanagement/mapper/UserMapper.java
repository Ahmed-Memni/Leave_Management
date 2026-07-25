package com.ahmed.leavemanagement.mapper;

import com.ahmed.leavemanagement.dto.UserDTO;
import com.ahmed.leavemanagement.entity.User;
import com.ahmed.leavemanagement.dto.UserResponse;

import org.springframework.stereotype.Component;


@Component
public class UserMapper {


    public UserDTO toDTO(User user) {

        UserDTO dto = new UserDTO();

        dto.setId(user.getId());

        // Combine firstName + lastName into name
        dto.setName(
                user.getFirstName() + " " + user.getLastName()
        );

        dto.setEmail(user.getEmail());


        if (user.getDepartment() != null) {
            dto.setDepartmentId(
                    user.getDepartment().getId()
            );
        }


        return dto;
    }


public UserResponse toResponse(User user) {

    return UserResponse.builder()
            .id(user.getId())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .role(user.getRole())
            .department(
                    user.getDepartment() != null
                            ? user.getDepartment().getName()
                            : null
            )
            .build();
}

    public User toEntity(UserDTO dto) {

        User user = new User();


        user.setId(dto.getId());

        // Split name into firstName and lastName
        String[] names = dto.getName().split(" ", 2);


        user.setFirstName(names[0]);


        if (names.length > 1) {
            user.setLastName(names[1]);
        } else {
            user.setLastName("");
        }


        user.setEmail(dto.getEmail());


        return user;
    }
}