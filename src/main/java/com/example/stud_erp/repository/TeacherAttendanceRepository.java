package com.example.stud_erp.repository;

import com.example.stud_erp.entity.TeacherAttendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TeacherAttendanceRepository extends JpaRepository<TeacherAttendance, Long> {

    Optional<TeacherAttendance> findByTeacherIdAndDate(Long teacherId, LocalDate date);

    List<TeacherAttendance> findByDate(LocalDate date);

    List<TeacherAttendance> findBySchoolIdAndDate(Long schoolId, LocalDate date);

    List<TeacherAttendance> findBySchoolId(Long schoolId);

}