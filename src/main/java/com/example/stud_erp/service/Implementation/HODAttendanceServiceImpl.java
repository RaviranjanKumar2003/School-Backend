// ======================================================
// SERVICE IMPL
// File => HODAttendanceServiceImpl.java
// ======================================================

package com.example.stud_erp.service.impl;

import com.example.stud_erp.entity.HODAttendance;
import com.example.stud_erp.payload.HODAttendanceDto;
import com.example.stud_erp.repository.HODAttendanceRepo;
import com.example.stud_erp.service.HODAttendanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HODAttendanceServiceImpl
        implements HODAttendanceService {

    @Autowired
    private HODAttendanceRepo repo;

    // =====================================================
    // DTO CONVERT
    // =====================================================

    private HODAttendanceDto mapToDto(
            HODAttendance a
    ) {

        HODAttendanceDto dto =
                new HODAttendanceDto();

        dto.setId(a.getId());
        dto.setSchoolId(a.getSchoolId());
        dto.setHodId(a.getHodId());
        dto.setStatus(a.getStatus());
        dto.setAttendanceDate(
                a.getAttendanceDate()
        );

        dto.setCreatedDate(
                a.getCreatedDate()
        );

        dto.setCreatedBy(
                a.getCreatedBy()
        );

        dto.setCreatedByRole(
                a.getCreatedByRole()
        );

        dto.setCreatedByName(
                a.getCreatedByName()
        );

        dto.setUpdatedBy(
                a.getUpdatedBy()
        );

        dto.setUpdatedByRole(
                a.getUpdatedByRole()
        );

        dto.setUpdatedByName(
                a.getUpdatedByName()
        );

        dto.setUpdatedDate(
                a.getUpdatedDate()
        );

        return dto;
    }

    // =====================================================
    // CREATE
    // =====================================================

    @Override
    public HODAttendanceDto
    markAttendance(
            HODAttendanceDto dto
    ) {

        HODAttendance attendance =
                new HODAttendance();

        attendance.setSchoolId(
                dto.getSchoolId()
        );

        attendance.setHodId(
                dto.getHodId()
        );

        attendance.setStatus(
                dto.getStatus()
        );

        attendance.setAttendanceDate(
                dto.getAttendanceDate()
        );

        attendance.setCreatedDate(
                LocalDate.now()
        );

        attendance.setCreatedBy(
                dto.getCreatedBy()
        );

        attendance.setCreatedByRole(
                dto.getCreatedByRole()
        );

        attendance.setCreatedByName(
                dto.getCreatedByName()
        );

        HODAttendance saved =
                repo.save(attendance);

        return mapToDto(saved);
    }

    // =====================================================
    // GET ALL
    // =====================================================

    @Override
    public List<HODAttendanceDto>
    getAllAttendance() {

        return repo.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // =====================================================
    // GET BY HOD
    // =====================================================

    @Override
    public List<HODAttendanceDto>
    getAttendanceByHod(
            Long hodId
    ) {

        return repo.findByHodId(hodId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // =====================================================
    // GET BY DATE
    // =====================================================

    @Override
    public List<HODAttendanceDto>
    getAttendanceByDate(
            LocalDate date
    ) {

        return repo.findByAttendanceDate(date)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // =====================================================
    // GET SINGLE
    // =====================================================

    @Override
    public HODAttendanceDto
    getByHodAndDate(
            Long hodId,
            LocalDate date
    ) {

        HODAttendance attendance =
                repo.findByHodIdAndAttendanceDate(
                                hodId,
                                date
                        )
                        .orElseThrow(
                                () ->
                                        new RuntimeException(
                                                "Attendance not found"
                                        )
                        );

        return mapToDto(attendance);
    }
}