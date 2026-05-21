package com.example.stud_erp.controller;

import com.example.stud_erp.entity.InquiryFollowUp;
import com.example.stud_erp.payload.InquiryFollowUpDto;
import com.example.stud_erp.service.InquiryFollowUpService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/followups")
@CrossOrigin("*")
public class InquiryFollowUpController {

    private final InquiryFollowUpService service;

    public InquiryFollowUpController(InquiryFollowUpService service) {
        this.service = service;
    }

    // =========================
    // ADD FOLLOW UP
    // =========================

    @PostMapping
    public InquiryFollowUp add(@RequestBody InquiryFollowUpDto dto) {
        return service.addFollowUp(dto);
    }

    // =========================
    // GET BY INQUIRY
    // =========================

    @GetMapping("/inquiry/{inquiryId}")
    public List<InquiryFollowUp> getByInquiry(@PathVariable Long inquiryId) {
        return service.getByInquiryId(inquiryId);
    }

    // =========================
    // GET ALL
    // =========================

    @GetMapping
    public List<InquiryFollowUp> getAll() {
        return service.getAll();
    }
}