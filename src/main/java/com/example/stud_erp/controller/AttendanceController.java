package com.example.stud_erp.controller;

import com.example.stud_erp.payload.ClassSessionDTO;
import com.example.stud_erp.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin("*")
public class AttendanceController {

    @Autowired
    private AttendanceService service;

    // SAVE CLASS ATTENDANCE
    @PostMapping("/save")
    public ClassSessionDTO save(@RequestBody ClassSessionDTO dto) {
        return service.saveAttendance(dto);
    }

    // CLASS VIEW
    @GetMapping("/class/{classNumber}")
    public List<ClassSessionDTO> getByClass(@PathVariable Integer classNumber) {
        return service.getClassAttendance(classNumber);
    }

    // STUDENT VIEW
    @GetMapping("/student/{id}")
    public List<ClassSessionDTO> getByStudent(@PathVariable Long id) {
        return service.getStudentAttendance(id);
    }
}