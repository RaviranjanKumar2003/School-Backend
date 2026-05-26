package com.example.stud_erp.controller;

import com.example.stud_erp.payload.TeacherAssignmentDto;
import com.example.stud_erp.service.TeacherAssignmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher-assignments")
@CrossOrigin("*")
public class TeacherAssignmentController {

    @Autowired
    private TeacherAssignmentService service;

    // =====================================================
    // CREATE
    // =====================================================

    @PostMapping
    public TeacherAssignmentDto createAssignment(
            @RequestBody TeacherAssignmentDto dto
    ) {

        return service.createAssignment(dto);
    }

    // =====================================================
    // GET ALL
    // =====================================================

    @GetMapping
    public List<TeacherAssignmentDto> getAll() {

        return service.getAllAssignments();
    }

    // =====================================================
    // GET BY SCHOOL
    // =====================================================

    @GetMapping("/school/{schoolId}")
    public List<TeacherAssignmentDto> getBySchool(
            @PathVariable Long schoolId
    ) {

        return service.getBySchool(schoolId);
    }

    // =====================================================
    // GET BY CLASS
    // =====================================================

    @GetMapping("/class/{classId}")
    public List<TeacherAssignmentDto> getByClass(
            @PathVariable Long classId
    ) {

        return service.getByClass(classId);
    }

    // =====================================================
    // GET BY TEACHER
    // =====================================================

    @GetMapping("/teacher/{teacherId}")
    public List<TeacherAssignmentDto> getByTeacher(
            @PathVariable Long teacherId
    ) {

        return service.getByProfessor(teacherId);
    }

    // =====================================================
    // DELETE
    // =====================================================

    @DeleteMapping("/{id}")
    public String deleteAssignment(
            @PathVariable Long id
    ) {

        service.deleteAssignment(id);

        return "Assignment Deleted Successfully";
    }
}