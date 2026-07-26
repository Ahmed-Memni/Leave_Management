package com.ahmed.leavemanagement.mapper;


import com.ahmed.leavemanagement.dto.LeaveRequestDTO;
import com.ahmed.leavemanagement.entity.LeaveRequest;

import org.springframework.stereotype.Component;


@Component
public class LeaveRequestMapper {


    public LeaveRequestDTO toDTO(LeaveRequest request) {

        LeaveRequestDTO dto = new LeaveRequestDTO();


        dto.setId(request.getId());

        dto.setStartDate(
                request.getStartDate()
        );

        dto.setEndDate(
                request.getEndDate()
        );

        dto.setReason(
                request.getReason()
        );

        dto.setStatus(
                request.getStatus()
        );


        return dto;
    }



    public LeaveRequest toEntity(LeaveRequestDTO dto) {

        LeaveRequest request = new LeaveRequest();


        request.setId(dto.getId());

        request.setStartDate(
                dto.getStartDate()
        );

        request.setEndDate(
                dto.getEndDate()
        );

        request.setReason(
                dto.getReason()
        );

        request.setStatus(
                dto.getStatus()
        );


        return request;
    }

}