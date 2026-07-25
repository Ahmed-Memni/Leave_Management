package com.ahmed.leavemanagement.controller;

import com.ahmed.leavemanagement.dto.LeaveRequestDTO;
import com.ahmed.leavemanagement.entity.LeaveRequest;
import com.ahmed.leavemanagement.service.LeaveRequestService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave-requests")
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;


    public LeaveRequestController(LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }


    // GET ALL
    @GetMapping
    public List<LeaveRequest> getAllRequests() {
        return leaveRequestService.getAllRequests();
    }


    // CREATE
    @PostMapping
    public LeaveRequest createRequest(
            @Valid @RequestBody LeaveRequestDTO leaveRequestDTO
    ) {
        return leaveRequestService.createRequest(leaveRequestDTO);
    }


    // APPROVE
    @PutMapping("/{id}/approve")
    public LeaveRequest approveRequest(
            @PathVariable Long id
    ) {
        return leaveRequestService.approveRequest(id);
    }


    // REJECT
    @PutMapping("/{id}/reject")
    public LeaveRequest rejectRequest(
            @PathVariable Long id
    ) {
        return leaveRequestService.rejectRequest(id);
    }
}