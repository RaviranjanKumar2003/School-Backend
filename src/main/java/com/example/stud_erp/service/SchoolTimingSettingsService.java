package com.example.stud_erp.service;

import com.example.stud_erp.payload.SchoolTimingSettingsDto;

public interface SchoolTimingSettingsService {

    // =====================================================
    // CREATE OR UPDATE
    // =====================================================

    SchoolTimingSettingsDto saveSettings(
            SchoolTimingSettingsDto dto
    );

    // =====================================================
    // GET BY SCHOOL
    // =====================================================

    SchoolTimingSettingsDto getBySchoolId(
            Long schoolId
    );

    // =====================================================
    // DELETE
    // =====================================================

    void deleteSettings(Long id);
}