package com.example.stud_erp.repository;

import com.example.stud_erp.entity.InquiryFollowUp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InquiryFollowUpRepository extends JpaRepository<InquiryFollowUp, Long> {

    List<InquiryFollowUp> findByInquiryId(Long inquiryId);
}