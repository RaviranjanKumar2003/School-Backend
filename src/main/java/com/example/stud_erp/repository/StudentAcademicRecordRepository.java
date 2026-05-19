package com.example.stud_erp.repository;

import com.example.stud_erp.entity.StudentAcademicRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentAcademicRecordRepository
        extends JpaRepository<StudentAcademicRecord, Long> {

    List<StudentAcademicRecord>
    findByStudentId(Long studentId);

}