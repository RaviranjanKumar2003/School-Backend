// ======================================================
// REPOSITORY -> AboutSchoolRepository
// ======================================================

package com.example.stud_erp.repository;

import com.example.stud_erp.entity.AboutSchool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AboutSchoolRepository
        extends JpaRepository<AboutSchool, Long> {

    Optional<AboutSchool> findBySchoolId(Long schoolId);
}