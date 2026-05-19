package com.example.stud_erp.service;

import com.example.stud_erp.entity.Inquiry;

import java.util.List;

public interface InquiryService {

    // ==============================
    // CREATE INQUIRY
    // ==============================

    Inquiry createInquiry(Inquiry inquiry);

    // ==============================
    // GET ALL INQUIRIES
    // ==============================

    List<Inquiry> getAllInquiries();

    // ==============================
    // GET BY SCHOOL CODE
    // ==============================

    List<Inquiry> getBySchoolCode(String schoolCode);
}