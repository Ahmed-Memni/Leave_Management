package com.ahmed.leavemanagement.controller;

import com.ahmed.leavemanagement.dto.LeaveRequestDTO;
import com.ahmed.leavemanagement.entity.LeaveRequest;
import com.ahmed.leavemanagement.service.LeaveRequestService;
import org.springframework.security.core.Authentication;
import com.ahmed.leavemanagement.entity.User;

import jakarta.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/leave-requests")
public class LeaveRequestController {


    private final LeaveRequestService leaveRequestService;


    public LeaveRequestController(LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }



    // GET ALL LEAVE REQUESTS
    // Manager sees team requests
    // Admin sees all requests
    @GetMapping
    @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    public List<LeaveRequest> getAllRequests() {
        return leaveRequestService.getAllRequests();
    }



    // CREATE LEAVE REQUEST
    // Employee, Manager, Admin can request leave
    @PostMapping
    @PreAuthorize("hasAnyRole('EMPLOYEE','MANAGER','ADMIN')")
    public LeaveRequest createRequest(
        @Valid @RequestBody LeaveRequestDTO leaveRequestDTO,
        Authentication authentication
    ) {


    User currentUser =
            (User) authentication.getPrincipal();


    return leaveRequestService.createRequest(
            leaveRequestDTO,
            currentUser
    );
    }



    // APPROVE LEAVE REQUEST
    // Manager and Admin only
    @PutMapping("/{id}/approve")
    @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    public LeaveRequest approveRequest(
        @PathVariable Long id,
        Authentication authentication
    ) {


    User manager =
            (User) authentication.getPrincipal();


    return leaveRequestService.approveRequest(
            id,
            manager
    );
    }



    // REJECT LEAVE REQUEST
    // Manager and Admin only
   @PutMapping("/{id}/reject")
   @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    public LeaveRequest rejectRequest(
        @PathVariable Long id,
        Authentication authentication
  ) {


    User manager =
            (User) authentication.getPrincipal();


    return leaveRequestService.rejectRequest(
            id,
            manager
    );
    }

}