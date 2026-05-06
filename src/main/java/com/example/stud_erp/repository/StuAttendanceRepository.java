package com.example.stud_erp.repository;

import com.example.stud_erp.entity.StuAttendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface StuAttendanceRepository extends JpaRepository<StuAttendance, Long> {

    List<StuAttendance> findByClassNumberAndDate(Integer classNumber, LocalDate date);

    // STUDENT ATTENDANCE
    List<StuAttendance> findByStudentId(Long studentId);

    // DAILY SUMMARY
    List<StuAttendance> findByDate(LocalDate date);
}