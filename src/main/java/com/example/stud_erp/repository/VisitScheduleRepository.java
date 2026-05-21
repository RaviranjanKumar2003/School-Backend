package com.example.stud_erp.repository;

import com.example.stud_erp.entity.VisitSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitScheduleRepository extends JpaRepository<VisitSchedule, Long> {

    List<VisitSchedule> findByInquiryId(Long inquiryId);
}