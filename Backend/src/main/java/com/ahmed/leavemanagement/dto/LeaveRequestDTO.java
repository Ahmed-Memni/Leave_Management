package com.ahmed.leavemanagement.dto;

import com.ahmed.leavemanagement.enums.LeaveStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class LeaveRequestDTO {


    private Long id;


    @NotNull(message = "Start date is required")
    @FutureOrPresent(message = "Start date cannot be in the past")
    private LocalDate startDate;


    @NotNull(message = "End date is required")
    private LocalDate endDate;


    @NotBlank(message = "Reason is required")
    private String reason;


    private LeaveStatus status;


    @NotNull(message = "Employee is required")
    private Long employeeId;


    @NotNull(message = "Employer is required")
    private Long employerId;
}