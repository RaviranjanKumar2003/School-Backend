package com.example.stud_erp.repository;

import com.example.stud_erp.entity.ClassSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ClassSessionRepository extends JpaRepository<ClassSession, Long> {

    List<ClassSession> findByClassName(String className);

    Optional<ClassSession> findByClassNameAndAttendanceDate(
            String className,
            LocalDate attendanceDate
    );

    List<ClassSession> findByAttendanceDate(LocalDate attendanceDate);
}