package com.example.stud_erp.repository;

import com.example.stud_erp.entity.StuAttendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StuAttendanceRepository extends JpaRepository<StuAttendance, Long> {


    // ================= CLASS + DATE =================
    List<StuAttendance> findBySchoolIdAndClassIdAndDate(
            Long schoolId,
            Long classId,
            LocalDate date
    );

    // ================= STUDENT =================
    List<StuAttendance> findByStudent_Id(Long studentId);

    // ================= DATE =================
    List<StuAttendance> findBySchoolIdAndDate(
            Long schoolId,
            LocalDate date
    );

    // ================= DUPLICATE CHECK =================
    Optional<StuAttendance>
    findByStudent_IdAndDateAndClassId(
            Long studentId,
            LocalDate date,
            Long classId
    );

    // ================= WEEKLY =================
    List<StuAttendance> findBySchoolId(Long schoolId);


}