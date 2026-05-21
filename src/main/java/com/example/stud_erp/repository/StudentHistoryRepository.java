package com.example.stud_erp.repository;

import com.example.stud_erp.entity.StudentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentHistoryRepository extends JpaRepository<StudentHistory, Long> {

    List<StudentHistory> findByStudentId(Long studentId);
}