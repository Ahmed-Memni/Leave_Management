package com.ahmed.leavemanagement.service;

import com.ahmed.leavemanagement.dto.DepartmentDTO;
import com.ahmed.leavemanagement.entity.Department;
import com.ahmed.leavemanagement.mapper.DepartmentMapper;
import com.ahmed.leavemanagement.repository.DepartmentRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {


    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;


    public DepartmentService(
            DepartmentRepository departmentRepository,
            DepartmentMapper departmentMapper
    ) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }


    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }


    public Department createDepartment(
            DepartmentDTO departmentDTO
    ) {

        Department department =
                departmentMapper.toEntity(departmentDTO);

        return departmentRepository.save(department);
    }
}