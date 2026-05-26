package com.example.stud_erp.service;

import com.example.stud_erp.payload.PeriodDto;

import java.util.List;

public interface PeriodService {

    // =====================================================
    // CREATE
    // =====================================================

    PeriodDto createPeriod(
            PeriodDto dto
    );

    // =====================================================
    // GET ALL BY SCHOOL
    // =====================================================

    List<PeriodDto> getBySchool(
            Long schoolId
    );

    // =====================================================
    // UPDATE
    // =====================================================

    PeriodDto updatePeriod(
            Long id,
            PeriodDto dto
    );

    // =====================================================
    // DELETE
    // =====================================================

    void deletePeriod(Long id);

    // =====================================================
    // DELETE SCHOOL PERIODS
    // =====================================================

    void deleteBySchool(Long schoolId);
}