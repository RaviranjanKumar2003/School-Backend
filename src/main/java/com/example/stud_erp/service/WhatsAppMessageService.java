package com.example.stud_erp.service;

import com.example.stud_erp.entity.WhatsAppMessage;

import java.util.List;

public interface WhatsAppMessageService {

    WhatsAppMessage sendMessage(WhatsAppMessage message);

    List<WhatsAppMessage> getByInquiryId(Long inquiryId);

    List<WhatsAppMessage> getByPhone(String phone);
}