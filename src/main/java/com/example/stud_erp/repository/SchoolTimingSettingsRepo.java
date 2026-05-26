package com.example.stud_erp.repository;

import com.example.stud_erp.entity.SchoolTimingSettings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SchoolTimingSettingsRepo
        extends JpaRepository<SchoolTimingSettings, Long> {

    Optional<SchoolTimingSettings> findBySchoolId(
            Long schoolId
    );
}