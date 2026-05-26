package com.example.stud_erp.repository;

import com.example.stud_erp.entity.TeacherAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherAssignmentRepo
        extends JpaRepository<TeacherAssignment, Long> {

    List<TeacherAssignment> findBySchoolId(Long schoolId);

    List<TeacherAssignment> findByClassId(Long classId);

    List<TeacherAssignment> findByProfessorId(Long professorId);

    List<TeacherAssignment> findByClassIdAndSection(
            Long classId,
            String section
    );
}