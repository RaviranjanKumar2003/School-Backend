package com.example.stud_erp.repository;

import com.example.stud_erp.entity.SchoolStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolStatisticsRepository
        extends JpaRepository<SchoolStatistics, Long> {

    SchoolStatistics findBySchoolId(
            Long schoolId
    );
}