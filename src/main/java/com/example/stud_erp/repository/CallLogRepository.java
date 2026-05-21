package com.example.stud_erp.repository;

import com.example.stud_erp.entity.CallLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CallLogRepository extends JpaRepository<CallLog, Long> {

    List<CallLog> findByInquiryId(Long inquiryId);
}