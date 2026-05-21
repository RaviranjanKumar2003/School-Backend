package com.example.stud_erp.service.Implementation;

import com.example.stud_erp.entity.Inquiry;
import com.example.stud_erp.payload.InquiryDto;
import com.example.stud_erp.repository.InquiryRepository;
import com.example.stud_erp.service.InquiryService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InquiryServiceImpl implements InquiryService {

    private final InquiryRepository repository;

    public InquiryServiceImpl(InquiryRepository repository) {
        this.repository = repository;
    }

    // =====================================
    // CREATE
    // =====================================

    @Override
    public Inquiry createInquiry(InquiryDto dto) {

        Inquiry inquiry = new Inquiry();

        inquiry.setStudentName(dto.getStudentName());
        inquiry.setParentName(dto.getParentName());
        inquiry.setPhone(dto.getPhone());
        inquiry.setEmail(dto.getEmail());
        inquiry.setMessage(dto.getMessage());
        inquiry.setSchoolCode(dto.getSchoolCode());

        // NEW FIELDS
        inquiry.setAssignedTo(dto.getAssignedTo());
        inquiry.setSource(dto.getSource());
        inquiry.setPriority(dto.getPriority());
        inquiry.setFollowUpDate(dto.getFollowUpDate());
        inquiry.setLastAction(dto.getLastAction());

        inquiry.setStatus("PENDING");

        return repository.save(inquiry);
    }

    // =====================================
    // GET ALL
    // =====================================

    @Override
    public List<Inquiry> getAllInquiries() {
        return repository.findAll();
    }

    // =====================================
    // BY SCHOOL
    // =====================================

    @Override
    public List<Inquiry> getBySchoolCode(String schoolCode) {
        return repository.findBySchoolCode(schoolCode);
    }

    // =====================================
    // UPDATE FULL INQUIRY
    // =====================================

    @Override
    public Inquiry updateInquiry(Long id, InquiryDto dto) {

        Inquiry inquiry = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inquiry not found"));

        inquiry.setStudentName(dto.getStudentName());
        inquiry.setParentName(dto.getParentName());
        inquiry.setPhone(dto.getPhone());
        inquiry.setEmail(dto.getEmail());
        inquiry.setMessage(dto.getMessage());

        inquiry.setAssignedTo(dto.getAssignedTo());
        inquiry.setSource(dto.getSource());
        inquiry.setPriority(dto.getPriority());
        inquiry.setFollowUpDate(dto.getFollowUpDate());
        inquiry.setLastAction(dto.getLastAction());

        return repository.save(inquiry);
    }

    // =====================================
    // STATUS UPDATE ONLY
    // =====================================

    @Override
    public Inquiry updateStatus(Long id, String status) {

        Inquiry inquiry = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inquiry not found"));

        inquiry.setStatus(status);

        return repository.save(inquiry);
    }

    @Override
    public Inquiry getInquiryById(Long id) {

        return repository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Inquiry Not Found"));
    }
}