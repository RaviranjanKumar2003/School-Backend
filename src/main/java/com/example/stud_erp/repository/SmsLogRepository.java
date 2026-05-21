package com.example.stud_erp.repository;

import com.example.stud_erp.entity.SmsLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SmsLogRepository extends JpaRepository<SmsLog, Long> {

    List<SmsLog> findByInquiryId(Long inquiryId);

    List<SmsLog> findByPhone(String phone);
}