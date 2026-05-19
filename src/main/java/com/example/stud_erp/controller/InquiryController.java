package com.example.stud_erp.controller;

import com.example.stud_erp.entity.Inquiry;
import com.example.stud_erp.service.InquiryService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inquiries")
@CrossOrigin("*")
public class InquiryController {

    // =====================================
    // SERVICE
    // =====================================

    private final InquiryService inquiryService;

    public InquiryController(
            InquiryService inquiryService
    ) {
        this.inquiryService =
                inquiryService;
    }

    // =====================================
    // CREATE INQUIRY
    // =====================================

    @PostMapping
    public Inquiry createInquiry(
            @RequestBody Inquiry inquiry
    ) {

        return inquiryService
                .createInquiry(
                        inquiry
                );
    }

    // =====================================
    // GET ALL INQUIRIES
    // =====================================

    @GetMapping
    public List<Inquiry> getAllInquiries() {

        return inquiryService
                .getAllInquiries();
    }

    // =====================================
    // GET BY SCHOOL CODE
    // =====================================

    @GetMapping("/school/{schoolCode}")
    public List<Inquiry> getBySchoolCode(
            @PathVariable String schoolCode
    ) {

        return inquiryService
                .getBySchoolCode(
                        schoolCode
                );
    }
}