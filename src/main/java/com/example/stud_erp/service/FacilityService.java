package com.example.stud_erp.service;

import com.example.stud_erp.payload.FacilityDto;

import java.util.List;

public interface FacilityService {

    // =====================================================
    // CREATE
    // =====================================================

    FacilityDto createFacility(
            FacilityDto dto
    );

    // =====================================================
    // UPDATE
    // =====================================================

    FacilityDto updateFacility(
            Long id,
            FacilityDto dto
    );

    // =====================================================
    // DELETE
    // =====================================================

    void deleteFacility(
            Long id
    );

    // =====================================================
    // GET BY ID
    // =====================================================

    FacilityDto getFacilityById(
            Long id
    );

    // =====================================================
    // GET ALL
    // =====================================================

    List<FacilityDto> getAllFacilities();

    // =====================================================
    // GET BY SCHOOL
    // =====================================================

    List<FacilityDto> getFacilitiesBySchool(
            Long schoolId
    );
}