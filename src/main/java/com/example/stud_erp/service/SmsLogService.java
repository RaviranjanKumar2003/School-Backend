package com.example.stud_erp.service;

import com.example.stud_erp.entity.SmsLog;

import java.util.List;

public interface SmsLogService {

    SmsLog sendSms(SmsLog smsLog);

    List<SmsLog> getByInquiryId(Long inquiryId);

    List<SmsLog> getByPhone(String phone);
}