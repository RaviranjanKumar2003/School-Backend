package com.example.stud_erp.repository;

import com.example.stud_erp.entity.StuAttendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StuAttendanceRepository extends JpaRepository<StuAttendance, Long> {

    List<StuAttendance> findByClassNumberAndDate(Integer classNumber, LocalDate date);

    // STUDENT ATTENDANCE
    List<StuAttendance> findByStudentId(Long studentId);

    // DAILY SUMMARY
    List<StuAttendance> findByDate(LocalDate date);

    // 🔥 FIXED
    Optional<StuAttendance> findByStudent_IdAndDateAndClassNumber(
            Long studentId,
            LocalDate date,
            Integer classNumber
    );
}