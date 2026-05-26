package com.example.stud_erp.repository;

import com.example.stud_erp.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacilityRepository
        extends JpaRepository<Facility, Long> {

    // =====================================================
    // GET BY SCHOOL
    // =====================================================

    List<Facility> findBySchoolId(
            Long schoolId
    );

    // =====================================================
    // GET ACTIVE FACILITIES
    // =====================================================

    List<Facility> findByActiveTrue();

    // =====================================================
    // GET ACTIVE FACILITIES BY SCHOOL
    // =====================================================

    List<Facility> findBySchoolIdAndActiveTrue(
            Long schoolId
    );
}