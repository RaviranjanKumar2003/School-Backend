package com.example.stud_erp.controller;

import com.example.stud_erp.entity.CallLog;
import com.example.stud_erp.payload.CallLogDto;
import com.example.stud_erp.service.CallLogService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calllogs")
@CrossOrigin("*")
public class CallLogController {

    private final CallLogService service;

    public CallLogController(CallLogService service) {
        this.service = service;
    }

    // =========================
    // CREATE CALL LOG
    // =========================

    @PostMapping
    public CallLog create(@RequestBody CallLogDto dto) {
        return service.createCallLog(dto);
    }

    // =========================
    // GET BY INQUIRY
    // =========================

    @GetMapping("/inquiry/{inquiryId}")
    public List<CallLog> getByInquiry(@PathVariable Long inquiryId) {
        return service.getByInquiryId(inquiryId);
    }

    // =========================
    // GET ALL CALL LOGS
    // =========================

    @GetMapping
    public List<CallLog> getAll() {
        return service.getAll();
    }
}