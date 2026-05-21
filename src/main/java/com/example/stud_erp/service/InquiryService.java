package com.example.stud_erp.service;

import com.example.stud_erp.entity.Inquiry;
import com.example.stud_erp.payload.InquiryDto;

import java.util.List;

public interface InquiryService {

    Inquiry createInquiry(InquiryDto dto);

    List<Inquiry> getAllInquiries();

    List<Inquiry> getBySchoolCode(String schoolCode);

    Inquiry updateInquiry(Long id, InquiryDto dto);

    Inquiry updateStatus(Long id, String status);

    Inquiry getInquiryById(Long id);
}