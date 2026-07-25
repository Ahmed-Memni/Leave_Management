package com.ahmed.leavemanagement.controller;

import com.ahmed.leavemanagement.dto.DepartmentDTO;
import com.ahmed.leavemanagement.entity.Department;
import com.ahmed.leavemanagement.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @PostMapping
    public Department createDepartment(
            @Valid @RequestBody DepartmentDTO departmentDTO
    ) {
        return departmentService.createDepartment(departmentDTO);
    }
}