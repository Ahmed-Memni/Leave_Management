package com.ahmed.leavemanagement.service;


import com.ahmed.leavemanagement.dto.LeaveRequestDTO;
import com.ahmed.leavemanagement.entity.LeaveRequest;
import com.ahmed.leavemanagement.entity.User;
import com.ahmed.leavemanagement.enums.LeaveStatus;
import com.ahmed.leavemanagement.mapper.LeaveRequestMapper;
import com.ahmed.leavemanagement.repository.LeaveRequestRepository;
import com.ahmed.leavemanagement.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LeaveRequestService {


    private final LeaveRequestRepository leaveRequestRepository;
    private final UserRepository userRepository;
    private final LeaveRequestMapper leaveRequestMapper;


    public LeaveRequestService(
            LeaveRequestRepository leaveRequestRepository,
            UserRepository userRepository,
            LeaveRequestMapper leaveRequestMapper
    ) {

        this.leaveRequestRepository = leaveRequestRepository;
        this.userRepository = userRepository;
        this.leaveRequestMapper = leaveRequestMapper;
    }


    public List<LeaveRequest> getAllRequests() {

        return leaveRequestRepository.findAll();
    }



    public LeaveRequest createRequest(
        LeaveRequestDTO dto,
        User currentUser
) {


    LeaveRequest leaveRequest =
            leaveRequestMapper.toEntity(dto);


    leaveRequest.setEmployee(currentUser);


    leaveRequest.setStatus(
            LeaveStatus.PENDING
    );


    return leaveRequestRepository.save(leaveRequest);
}



    public LeaveRequest approveRequest(Long id, User manager) {


    LeaveRequest request =
            leaveRequestRepository.findById(id)
            .orElseThrow();


    request.setStatus(
            LeaveStatus.APPROVED
    );


    request.setApprover(manager);


    return leaveRequestRepository.save(request);
}


    public LeaveRequest rejectRequest(Long id, User manager) {


    LeaveRequest request =
            leaveRequestRepository.findById(id)
            .orElseThrow();


    request.setStatus(
            LeaveStatus.REJECTED
    );


    request.setApprover(manager);


    return leaveRequestRepository.save(request);
}
}