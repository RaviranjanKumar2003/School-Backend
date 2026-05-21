package com.example.stud_erp.repository;

import com.example.stud_erp.entity.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {

    List<Inquiry> findBySchoolCode(String schoolCode);

    List<Inquiry> findByAssignedTo(String assignedTo);

    List<Inquiry> findByStatus(String status);
}