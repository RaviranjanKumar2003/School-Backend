package com.example.stud_erp.controller;

import com.example.stud_erp.entity.SmsLog;
import com.example.stud_erp.service.SmsLogService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sms")
@CrossOrigin("*")
public class SmsLogController {

    private final SmsLogService service;

    public SmsLogController(SmsLogService service) {
        this.service = service;
    }

    // =========================
    // SEND SMS
    // =========================

    @PostMapping("/send")
    public SmsLog send(@RequestBody SmsLog smsLog) {
        return service.sendSms(smsLog);
    }

    // =========================
    // BY INQUIRY
    // =========================

    @GetMapping("/inquiry/{id}")
    public List<SmsLog> byInquiry(@PathVariable Long id) {
        return service.getByInquiryId(id);
    }

    // =========================
    // BY PHONE
    // =========================

    @GetMapping("/phone/{phone}")
    public List<SmsLog> byPhone(@PathVariable String phone) {
        return service.getByPhone(phone);
    }
}