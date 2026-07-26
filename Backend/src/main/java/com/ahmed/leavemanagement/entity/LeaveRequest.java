package com.ahmed.leavemanagement.entity;

import com.ahmed.leavemanagement.enums.LeaveStatus;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "leave_requests")
public class LeaveRequest {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private LocalDate startDate;


    private LocalDate endDate;


    private String reason;


    @Enumerated(EnumType.STRING)
    private LeaveStatus status;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private User employee;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approver_id")
    private User approver;

}