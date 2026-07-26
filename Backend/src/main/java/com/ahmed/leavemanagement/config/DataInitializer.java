package com.ahmed.leavemanagement.config;

import com.ahmed.leavemanagement.entity.Department;
import com.ahmed.leavemanagement.entity.LeaveRequest;
import com.ahmed.leavemanagement.entity.User;
import com.ahmed.leavemanagement.enums.LeaveStatus;
import com.ahmed.leavemanagement.enums.Role;
import com.ahmed.leavemanagement.repository.DepartmentRepository;
import com.ahmed.leavemanagement.repository.LeaveRequestRepository;
import com.ahmed.leavemanagement.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;


@Configuration
public class DataInitializer {


    @Bean
    CommandLineRunner initDatabase(
            UserRepository userRepository,
            DepartmentRepository departmentRepository,
            LeaveRequestRepository leaveRequestRepository,
            PasswordEncoder passwordEncoder
    ) {

        return args -> {


            // Create IT department if it does not exist

            Department it = departmentRepository.findByName("IT")
                    .orElseGet(() -> {

                        Department department = new Department();

                        department.setName("IT");

                        return departmentRepository.save(department);
                    });



            // Create HR department if it does not exist

            Department hr = departmentRepository.findByName("Human Resources")
                    .orElseGet(() -> {

                        Department department = new Department();

                        department.setName("Human Resources");

                        return departmentRepository.save(department);
                    });



            // Create Admin if it does not exist

            User admin = userRepository.findByEmail("admin@test.com")
                    .orElseGet(() -> {

                        User user = new User();

                        user.setFirstName("Ahmed");
                        user.setLastName("Admin");
                        user.setEmail("admin@test.com");

                        user.setPassword(
                                passwordEncoder.encode("admin123")
                        );

                        user.setRole(Role.ADMIN);


                        return userRepository.save(user);
                    });



            // Create Manager if it does not exist

            User manager = userRepository.findByEmail("manager@test.com")
                    .orElseGet(() -> {

                        User user = new User();

                        user.setFirstName("John");
                        user.setLastName("Manager");
                        user.setEmail("manager@test.com");

                        user.setPassword(
                                passwordEncoder.encode("manager123")
                        );

                        user.setRole(Role.MANAGER);

                        user.setDepartment(it);


                        return userRepository.save(user);
                    });



            // Create Employee if it does not exist

            User employee = userRepository.findByEmail("employee@test.com")
                    .orElseGet(() -> {

                        User user = new User();

                        user.setFirstName("Ahmed");
                        user.setLastName("Employee");
                        user.setEmail("employee@test.com");

                        user.setPassword(
                                passwordEncoder.encode("employee123")
                        );

                        user.setRole(Role.EMPLOYEE);

                        user.setDepartment(it);

                        user.setManager(manager);


                        return userRepository.save(user);
                    });



            // Create Leave Requests if none exist

            if (leaveRequestRepository.count() == 0) {


                LeaveRequest pendingRequest = new LeaveRequest();

                pendingRequest.setEmployee(employee);

                pendingRequest.setStartDate(
                        LocalDate.of(2026, 8, 10)
                );

                pendingRequest.setEndDate(
                        LocalDate.of(2026, 8, 15)
                );

                pendingRequest.setReason(
                        "Summer vacation"
                );

                pendingRequest.setStatus(
                        LeaveStatus.PENDING
                );



                LeaveRequest approvedRequest = new LeaveRequest();

                approvedRequest.setEmployee(employee);

                approvedRequest.setApprover(manager);

                approvedRequest.setStartDate(
                        LocalDate.of(2026, 9, 1)
                );

                approvedRequest.setEndDate(
                        LocalDate.of(2026, 9, 3)
                );

                approvedRequest.setReason(
                        "Personal leave"
                );

                approvedRequest.setStatus(
                        LeaveStatus.APPROVED
                );


                leaveRequestRepository.save(pendingRequest);

                leaveRequestRepository.save(approvedRequest);
            }



            System.out.println("Database initialization completed!");
        };
    }
}