package com.example.stud_erp.service;

import com.example.stud_erp.payload.TeacherAssignmentDto;

import java.util.List;

public interface TeacherAssignmentService {

    // =====================================================
    // CREATE
    // =====================================================

    TeacherAssignmentDto createAssignment(
            TeacherAssignmentDto dto
    );

    // =====================================================
    // GET ALL
    // =====================================================

    List<TeacherAssignmentDto> getAllAssignments();

    // =====================================================
    // SCHOOL
    // =====================================================

    List<TeacherAssignmentDto> getBySchool(
            Long schoolId
    );

    // =====================================================
    // CLASS
    // =====================================================

    List<TeacherAssignmentDto> getByClass(
            Long classId
    );

    // =====================================================
    // TEACHER
    // =====================================================

    List<TeacherAssignmentDto> getByProfessor(
            Long professorId
    );

    // =====================================================
    // DELETE
    // =====================================================

    void deleteAssignment(Long id);
}