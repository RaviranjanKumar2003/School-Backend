package com.example.stud_erp.repository;

import com.example.stud_erp.entity.LiveSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LiveSessionRepository extends JpaRepository<LiveSession, Long> {

    // ================= ACTIVE SESSION BY CLASS =================

    Optional<LiveSession> findByClassNameAndActiveTrue(String className);

    // ================= ALL ACTIVE SESSIONS =================

    List<LiveSession> findByActiveTrue();

    // ================= ACTIVE SESSION BY PROFESSOR =================

    Optional<LiveSession> findByProfessorNameAndActiveTrue(String professorName);

    // ================= CLASS HISTORY =================

    List<LiveSession> findByClassNameOrderByStartedAtDesc(String className);

    // ================= PROFESSOR HISTORY =================

    List<LiveSession> findByProfessorNameOrderByStartedAtDesc(String professorName);

    // ================= LATEST ACTIVE SESSION =================

    Optional<LiveSession> findTopByClassNameAndActiveTrueOrderByStartedAtDesc(
            String className
    );
}
