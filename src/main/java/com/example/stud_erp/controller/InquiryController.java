package com.example.stud_erp.controller;

import com.example.stud_erp.entity.Inquiry;
import com.example.stud_erp.payload.InquiryDto;
import com.example.stud_erp.service.InquiryService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inquiries")
@CrossOrigin("*")
public class InquiryController {

    private final InquiryService service;

    public InquiryController(InquiryService service) {
        this.service = service;
    }

    // =====================================
    // CREATE
    // =====================================

    @PostMapping
    public Inquiry create(@RequestBody InquiryDto dto) {
        return service.createInquiry(dto);
    }

    // =====================================
    // GET ALL
    // =====================================

    @GetMapping
    public List<Inquiry> getAll() {
        return service.getAllInquiries();
    }

    // =====================================
    // BY SCHOOL
    // =====================================

    @GetMapping("/school/{schoolCode}")
    public List<Inquiry> getBySchool(@PathVariable String schoolCode) {
        return service.getBySchoolCode(schoolCode);
    }

    // =====================================
    // UPDATE FULL
    // =====================================

    @PutMapping("/{id}")
    public Inquiry update(
            @PathVariable Long id,
            @RequestBody InquiryDto dto
    ) {
        return service.updateInquiry(id, dto);
    }

    // =====================================
    // STATUS UPDATE (VERY IMPORTANT)
    // =====================================

    @PatchMapping("/{id}/status")
    public Inquiry updateStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {
        return service.updateStatus(id, status);
    }

    // =====================================
    // GET BY ID
    // =====================================

    @GetMapping("/{id}")
    public Inquiry getById(@PathVariable Long id) {

        return service.getInquiryById(id);
    }
}