package com.example.stud_erp.service;

import com.example.stud_erp.entity.InquiryFollowUp;
import com.example.stud_erp.payload.InquiryFollowUpDto;

import java.util.List;

public interface InquiryFollowUpService {

    InquiryFollowUp addFollowUp(InquiryFollowUpDto dto);

    List<InquiryFollowUp> getByInquiryId(Long inquiryId);

    List<InquiryFollowUp> getAll();
}