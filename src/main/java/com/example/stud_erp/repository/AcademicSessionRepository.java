package com.example.stud_erp.repository;

import com.example.stud_erp.entity.AcademicSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AcademicSessionRepository
        extends JpaRepository<AcademicSession, Long> {

    Optional<AcademicSession> findByActiveTrue();
}