package com.example.stud_erp.service.Implementation;

import com.example.stud_erp.entity.Inquiry;
import com.example.stud_erp.repository.InquiryRepository;
import com.example.stud_erp.service.InquiryService;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InquiryServiceImpl
        implements InquiryService {

    // =====================================
    // REPOSITORY
    // =====================================

    private final InquiryRepository inquiryRepository;

    public InquiryServiceImpl(
            InquiryRepository inquiryRepository
    ) {
        this.inquiryRepository =
                inquiryRepository;
    }

    // =====================================
    // CREATE INQUIRY
    // =====================================

    @Override
    public Inquiry createInquiry(
            Inquiry inquiry
    ) {

        inquiry.setCreatedAt(
                LocalDateTime.now()
        );

        return inquiryRepository.save(
                inquiry
        );
    }

    // =====================================
    // GET ALL INQUIRIES
    // =====================================

    @Override
    public List<Inquiry> getAllInquiries() {

        return inquiryRepository.findAll();
    }

    // =====================================
    // GET BY SCHOOL CODE
    // =====================================

    @Override
    public List<Inquiry> getBySchoolCode(
            String schoolCode
    ) {

        return inquiryRepository
                .findBySchoolCode(
                        schoolCode
                );
    }
}