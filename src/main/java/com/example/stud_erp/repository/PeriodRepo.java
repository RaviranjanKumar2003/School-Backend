package com.example.stud_erp.repository;

import com.example.stud_erp.entity.Period;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PeriodRepo
        extends JpaRepository<Period, Long> {

    List<Period> findBySchoolIdOrderByPeriodNumberAsc(
            Long schoolId
    );

    void deleteBySchoolId(Long schoolId);
}