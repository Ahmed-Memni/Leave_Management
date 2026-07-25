package com.ahmed.leavemanagement.mapper;

import com.ahmed.leavemanagement.dto.DepartmentDTO;
import com.ahmed.leavemanagement.entity.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {

    public DepartmentDTO toDTO(Department department) {

        DepartmentDTO dto = new DepartmentDTO();

        dto.setId(department.getId());
        dto.setName(department.getName());

        return dto;
    }

    public Department toEntity(DepartmentDTO dto) {

        Department department = new Department();

        department.setId(dto.getId());
        department.setName(dto.getName());

        return department;
    }
}