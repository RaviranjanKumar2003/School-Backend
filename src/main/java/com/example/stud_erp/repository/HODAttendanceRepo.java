// ======================================================
// REPOSITORY
// File => HODAttendanceRepo.java
// ======================================================

package com.example.stud_erp.repository;

import com.example.stud_erp.entity.HODAttendance;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HODAttendanceRepo
        extends JpaRepository<HODAttendance, Long> {

    List<HODAttendance> findBySchoolId(Long schoolId);

    List<HODAttendance> findByHodId(Long hodId);

    List<HODAttendance> findByAttendanceDate(LocalDate attendanceDate);

    Optional<HODAttendance>
    findByHodIdAndAttendanceDate(
            Long hodId,
            LocalDate attendanceDate
    );
}