package com.example.stud_erp.repository;

import com.example.stud_erp.entity.LiveClass;
import com.example.stud_erp.enums.LiveClassStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LiveClassRepository extends JpaRepository<LiveClass, Long> {

    // =====================================================
    // SCHOOL
    // =====================================================

    List<LiveClass> findBySchool_IdOrderByCreatedAtDesc(Long schoolId);

    List<LiveClass> findBySchool_IdAndStatusOrderByCreatedAtDesc(
            Long schoolId,
            LiveClassStatus status
    );

    List<LiveClass> findBySchool_IdAndStatus(
            Long schoolId,
            LiveClassStatus status
    );

    // =====================================================
    // CLASS
    // =====================================================

    List<LiveClass> findByClassEntity_IdOrderByCreatedAtDesc(Long classId);

    List<LiveClass> findByClassEntity_IdAndStatusOrderByCreatedAtDesc(
            Long classId,
            LiveClassStatus status
    );

    List<LiveClass> findByClassEntity_IdAndStatus(
            Long classId,
            LiveClassStatus status
    );

    // =====================================================
    // PROFESSOR
    // =====================================================

    List<LiveClass> findByProfessor_IdOrderByCreatedAtDesc(Long professorId);

    List<LiveClass> findByProfessor_IdAndStatusOrderByCreatedAtDesc(
            Long professorId,
            LiveClassStatus status
    );

    List<LiveClass> findByProfessor_IdAndStatus(
            Long professorId,
            LiveClassStatus status
    );

    // =====================================================
    // CURRENT LIVE CLASS
    // =====================================================

    Optional<LiveClass> findTopByClassEntity_IdAndStatusOrderByCreatedAtDesc(
            Long classId,
            LiveClassStatus status
    );

    Optional<LiveClass> findTopByProfessor_IdAndStatusOrderByCreatedAtDesc(
            Long professorId,
            LiveClassStatus status
    );

    // =====================================================
    // STATUS BASED
    // =====================================================

    List<LiveClass> findByStatus(LiveClassStatus status);

    // =====================================================
    // DATE FILTERS
    // =====================================================

    List<LiveClass> findByScheduledDate(LocalDate scheduledDate);

    List<LiveClass> findBySchool_IdAndScheduledDate(
            Long schoolId,
            LocalDate scheduledDate
    );

    // =====================================================
    // EXISTENCE CHECKS
    // =====================================================

    boolean existsByProfessor_IdAndStatus(
            Long professorId,
            LiveClassStatus status
    );

    boolean existsByClassEntity_IdAndStatus(
            Long classId,
            LiveClassStatus status
    );

    // =====================================================
    // DASHBOARD COUNTS
    // =====================================================

    long countBySchool_Id(Long schoolId);

    long countBySchool_IdAndStatus(
            Long schoolId,
            LiveClassStatus status
    );

    long countByProfessor_Id(Long professorId);

    long countByClassEntity_Id(Long classId);

    // =====================================================
    // LATEST CLASS
    // =====================================================

    Optional<LiveClass> findTopBySchool_IdOrderByCreatedAtDesc(Long schoolId);

    Optional<LiveClass> findTopByProfessor_IdOrderByCreatedAtDesc(Long professorId);

    Optional<LiveClass> findTopByClassEntity_IdOrderByCreatedAtDesc(Long classId);

    List<LiveClass>
    findByProfessor_IdAndDeletedFalseOrderByCreatedAtDesc(
            Long professorId
    );

    List<LiveClass>
    findByClassEntity_IdAndDeletedFalseOrderByCreatedAtDesc(
            Long classId
    );

    List<LiveClass>
    findBySchool_IdAndDeletedFalseOrderByCreatedAtDesc(
            Long schoolId
    );




}