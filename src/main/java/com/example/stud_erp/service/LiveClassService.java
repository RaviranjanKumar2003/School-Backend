package com.example.stud_erp.service;

import com.example.stud_erp.entity.LiveClass;
import com.example.stud_erp.enums.LiveClassStatus;
import com.example.stud_erp.payload.LiveClassDto;
import com.example.stud_erp.payload.LiveClassResponse;

import java.time.LocalDate;
import java.util.List;

public interface LiveClassService {

    // =====================================================
    // CREATE
    // =====================================================

    LiveClass create(LiveClassDto dto);

    // =====================================================
    // LIVE ACTIONS
    // =====================================================

    LiveClass start(Long liveClassId);

    LiveClass end(Long liveClassId);

    LiveClass join(Long liveClassId, Long studentId);

    LiveClass leave(Long liveClassId, Long studentId);

    // =====================================================
    // UPDATE
    // =====================================================

    LiveClass update(
            Long liveClassId,
            LiveClassDto dto
    );

    LiveClass updateRecording(
            Long liveClassId,
            String recordingUrl
    );

    // =====================================================
    // DELETE
    // =====================================================

    void delete(Long liveClassId);

    // =====================================================
    // SINGLE
    // =====================================================

    LiveClass getById(Long liveClassId);

    LiveClass getCurrentClass(Long classId);

    LiveClass getCurrentProfessorClass(
            Long professorId
    );

    // =====================================================
    // SCHOOL
    // =====================================================

    List<LiveClass> getSchoolClasses(
            Long schoolId
    );

    List<LiveClass> getSchoolClassesByStatus(
            Long schoolId,
            LiveClassStatus status
    );

    // =====================================================
    // CLASS
    // =====================================================

    List<LiveClass> getClassLiveHistory(
            Long classId
    );

    List<LiveClass> getClassLiveHistoryByStatus(
            Long classId,
            LiveClassStatus status
    );

    // =====================================================
    // PROFESSOR
    // =====================================================

    List<LiveClassResponse> getProfessorClasses(
            Long professorId
    );

    List<LiveClass> getProfessorClassesByStatus(
            Long professorId,
            LiveClassStatus status
    );

    // =====================================================
    // LIVE CLASSES
    // =====================================================

    List<LiveClass> getAllLiveClasses();

    List<LiveClass> getSchoolLiveClasses(
            Long schoolId
    );

    // =====================================================
    // DATE FILTER
    // =====================================================

    List<LiveClass> getClassesByDate(
            LocalDate date
    );

    List<LiveClass> getSchoolClassesByDate(
            Long schoolId,
            LocalDate date
    );

    // =====================================================
    // ANALYTICS
    // =====================================================

    long countSchoolClasses(
            Long schoolId
    );

    long countSchoolLiveClasses(
            Long schoolId
    );

    long countProfessorClasses(
            Long professorId
    );

    long countClassClasses(
            Long classId
    );

    // =====================================================
    // DASHBOARD
    // =====================================================

    LiveClass getLatestSchoolClass(
            Long schoolId
    );

    LiveClass getLatestProfessorClass(
            Long professorId
    );

    LiveClass getLatestClassClass(
            Long classId
    );

    List<LiveClassResponse> getLiveClassesForStudent(Long studentId);
}