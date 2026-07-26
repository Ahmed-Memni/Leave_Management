package com.ahmed.leavemanagement.mapper;

import com.ahmed.leavemanagement.dto.UserDTO;
import com.ahmed.leavemanagement.dto.UserResponse;
import com.ahmed.leavemanagement.entity.User;

import org.springframework.stereotype.Component;


@Component
public class UserMapper {


    public UserDTO toDTO(User user) {

        UserDTO dto = new UserDTO();

        dto.setId(user.getId());

        dto.setFirstName(user.getFirstName());

        dto.setLastName(user.getLastName());

        dto.setEmail(user.getEmail());

        dto.setRole(user.getRole());


        if (user.getDepartment() != null) {
            dto.setDepartmentId(
                    user.getDepartment().getId()
            );
        }


        if (user.getManager() != null) {
            dto.setManagerId(
                    user.getManager().getId()
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

                .managerId(
                        user.getManager() != null
                                ? user.getManager().getId()
                                : null
                )

                .managerName(
                        user.getManager() != null
                                ? user.getManager().getFirstName()
                                      + " "
                                      + user.getManager().getLastName()
                                : null
                )

                .build();
    }




    public User toEntity(UserDTO dto) {

        User user = new User();


        user.setId(dto.getId());

        user.setFirstName(
                dto.getFirstName()
        );


        user.setLastName(
                dto.getLastName()
        );


        user.setEmail(
                dto.getEmail()
        );


        user.setRole(
                dto.getRole()
        );


        return user;
    }
}