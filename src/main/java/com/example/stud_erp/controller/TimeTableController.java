package com.example.stud_erp.controller;

import com.example.stud_erp.payload.TimeTableDto;
import com.example.stud_erp.service.TimeTableService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timetable")
@CrossOrigin("*")
public class TimeTableController {

    @Autowired
    private TimeTableService service;

    // ================= CREATE =================

    @PostMapping("/create")
    public TimeTableDto create(
            @RequestBody TimeTableDto dto
    ) {

        return service.createTimeTable(dto);
    }

    // ================= GET ALL =================

    @GetMapping
    public List<TimeTableDto> getAll() {

        return service.getAllTimeTables();
    }

    // ================= SCHOOL =================

    @GetMapping("/school/{schoolId}")
    public List<TimeTableDto> getBySchool(
            @PathVariable Long schoolId
    ) {

        return service.getBySchool(schoolId);
    }

    // ================= CLASS =================

    @GetMapping("/class/{classId}")
    public List<TimeTableDto> getByClass(
            @PathVariable Long classId
    ) {

        return service.getByClass(classId);
    }

    // ================= CLASS + SECTION =================

    @GetMapping("/class/{classId}/section/{section}")
    public List<TimeTableDto> getByClassSection(
            @PathVariable Long classId,
            @PathVariable String section
    ) {

        return service.getByClassAndSection(
                classId,
                section
        );
    }

    // ================= TEACHER =================

    @GetMapping("/teacher/{teacherId}")
    public List<TimeTableDto> getByTeacher(
            @PathVariable Long teacherId
    ) {

        return service.getByTeacher(teacherId);
    }

    // ================= DELETE =================

    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable Long id
    ) {

        service.deleteTimeTable(id);

        return "TimeTable Deleted Successfully";
    }
}