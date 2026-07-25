package com.ahmed.leavemanagement.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentDTO {

    private Long id;

    @NotBlank(message = "Department name is required")
    private String name;
}