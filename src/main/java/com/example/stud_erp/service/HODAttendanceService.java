// ======================================================
// SERVICE
// File => HODAttendanceService.java
// ======================================================

package com.example.stud_erp.service;

import com.example.stud_erp.payload.HODAttendanceDto;

import java.time.LocalDate;
import java.util.List;

public interface HODAttendanceService {

    // CREATE

    HODAttendanceDto markAttendance(
            HODAttendanceDto dto
    );

    // GET ALL

    List<HODAttendanceDto> getAllAttendance();

    // GET BY HOD

    List<HODAttendanceDto>
    getAttendanceByHod(
            Long hodId
    );

    // GET BY DATE

    List<HODAttendanceDto>
    getAttendanceByDate(
            LocalDate date
    );

    // GET SINGLE

    HODAttendanceDto
    getByHodAndDate(
            Long hodId,
            LocalDate date
    );
}