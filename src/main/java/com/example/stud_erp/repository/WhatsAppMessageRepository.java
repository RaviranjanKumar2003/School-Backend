package com.example.stud_erp.repository;

import com.example.stud_erp.entity.WhatsAppMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WhatsAppMessageRepository extends JpaRepository<WhatsAppMessage, Long> {

    List<WhatsAppMessage> findByInquiryId(Long inquiryId);

    List<WhatsAppMessage> findByPhone(String phone);
}