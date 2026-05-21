package com.example.stud_erp.service.implementation;

import com.example.stud_erp.entity.SmsLog;
import com.example.stud_erp.repository.SmsLogRepository;
import com.example.stud_erp.service.SmsLogService;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SmsLogServiceImpl implements SmsLogService {

    private final SmsLogRepository repository;

    public SmsLogServiceImpl(SmsLogRepository repository) {
        this.repository = repository;
    }

    @Override
    public SmsLog sendSms(SmsLog smsLog) {

        smsLog.setSentAt(LocalDateTime.now());

        // 🔥 FUTURE INTEGRATION:
        // Here you will integrate SMS Gateway like:
        // - Fast2SMS
        // - MSG91
        // - Twilio

        if (smsLog.getStatus() == null) {
            smsLog.setStatus("SENT");
        }

        return repository.save(smsLog);
    }

    @Override
    public List<SmsLog> getByInquiryId(Long inquiryId) {
        return repository.findByInquiryId(inquiryId);
    }

    @Override
    public List<SmsLog> getByPhone(String phone) {
        return repository.findByPhone(phone);
    }
}