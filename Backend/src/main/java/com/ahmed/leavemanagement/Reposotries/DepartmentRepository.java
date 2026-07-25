package com.ahmed.leavemanagement.repository;

import com.ahmed.leavemanagement.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}