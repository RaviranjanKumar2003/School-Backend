// ======================================================
// CONTROLLER
// File => HODAttendanceController.java
// ======================================================

package com.example.stud_erp.controller;

import com.example.stud_erp.payload.HODAttendanceDto;
import com.example.stud_erp.service.HODAttendanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/hod-attendance")

@CrossOrigin("*")

public class HODAttendanceController {

    @Autowired
    private HODAttendanceService service;

    // =====================================================
    // MARK ATTENDANCE
    // =====================================================

    @PostMapping("/mark")
    public HODAttendanceDto markAttendance(
            @RequestBody
            HODAttendanceDto dto
    ) {

        return service.markAttendance(dto);
    }

    // =====================================================
    // GET ALL
    // =====================================================

    @GetMapping("/all")
    public List<HODAttendanceDto>
    getAllAttendance() {

        return service.getAllAttendance();
    }

    // =====================================================
    // GET BY HOD
    // =====================================================

    @GetMapping("/hod/{hodId}")
    public List<HODAttendanceDto>
    getByHod(
            @PathVariable
            Long hodId
    ) {

        return service.getAttendanceByHod(
                hodId
        );
    }

    // =====================================================
    // GET BY DATE
    // =====================================================

    @GetMapping("/date/{date}")
    public List<HODAttendanceDto>
    getByDate(
            @PathVariable
            LocalDate date
    ) {

        return service.getAttendanceByDate(
                date
        );
    }

    // =====================================================
    // GET SINGLE
    // =====================================================

    @GetMapping("/single")
    public HODAttendanceDto
    getSingleAttendance(

            @RequestParam
            Long hodId,

            @RequestParam
            LocalDate attendanceDate
    ) {

        return service.getByHodAndDate(
                hodId,
                attendanceDate
        );
    }
}