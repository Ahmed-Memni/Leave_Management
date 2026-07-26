package com.ahmed.leavemanagement.service;


import com.ahmed.leavemanagement.dto.UserDTO;
import com.ahmed.leavemanagement.dto.UserResponse;
import com.ahmed.leavemanagement.entity.Department;
import com.ahmed.leavemanagement.entity.User;
import com.ahmed.leavemanagement.mapper.UserMapper;
import com.ahmed.leavemanagement.repository.DepartmentRepository;
import com.ahmed.leavemanagement.repository.UserRepository;
import com.ahmed.leavemanagement.repository.DepartmentRepository;
import java.util.List;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {


    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final UserMapper userMapper;


    public UserService(
            UserRepository userRepository,
            DepartmentRepository departmentRepository,
            UserMapper userMapper
    ) {
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
        this.userMapper = userMapper;
    }



    public List<UserResponse> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }


    public List<UserResponse> getTeam(User manager) {


    return userRepository.findByManager(manager)
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




    public User createUser(UserDTO userDTO) {


    User user = userMapper.toEntity(userDTO);



    if(userDTO.getDepartmentId() != null) {

        Department department =
                departmentRepository.findById(
                        userDTO.getDepartmentId()
                )
                .orElseThrow(
                        () -> new RuntimeException(
                                "Department not found"
                        )
                );


        user.setDepartment(department);
    }



    if(userDTO.getManagerId() != null) {

        User manager =
                userRepository.findById(
                        userDTO.getManagerId()
                )
                .orElseThrow(
                        () -> new RuntimeException(
                                "Manager not found"
                        )
                );


        user.setManager(manager);
    }



    return userRepository.save(user);
}


public User getUserById(Long id) {

    return userRepository.findById(id)
            .orElseThrow(
                    () -> new RuntimeException(
                            "User not found"
                    )
            );
}


public User updateUser(
        Long id,
        UserDTO dto
) {


    User user =
            userRepository.findById(id)
            .orElseThrow(
                    () -> new RuntimeException(
                            "User not found"
                    )
            );



    user.setFirstName(
            dto.getFirstName()
    );


    user.setLastName(
            dto.getLastName()
    );


    user.setEmail(
            dto.getEmail()
    );



    if(dto.getRole() != null) {

        user.setRole(
                dto.getRole()
        );

    }



    if(dto.getDepartmentId() != null) {

        Department department =
                departmentRepository.findById(
                        dto.getDepartmentId()
                )
                .orElseThrow();


        user.setDepartment(department);
    }



    if(dto.getManagerId() != null) {

        User manager =
                userRepository.findById(
                        dto.getManagerId()
                )
                .orElseThrow();


        user.setManager(manager);
    }



    return userRepository.save(user);
}

public void deleteUser(Long id) {

    User user =
            userRepository.findById(id)
            .orElseThrow(
                    () -> new RuntimeException(
                            "User not found"
                    )
            );


    userRepository.delete(user);
}
}