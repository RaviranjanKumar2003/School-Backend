package com.example.stud_erp.service.Implementation;

import com.example.stud_erp.entity.InquiryFollowUp;
import com.example.stud_erp.payload.InquiryFollowUpDto;
import com.example.stud_erp.repository.InquiryFollowUpRepository;
import com.example.stud_erp.service.InquiryFollowUpService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InquiryFollowUpServiceImpl implements InquiryFollowUpService {

    private final InquiryFollowUpRepository repository;

    public InquiryFollowUpServiceImpl(InquiryFollowUpRepository repository) {
        this.repository = repository;
    }

    @Override
    public InquiryFollowUp addFollowUp(InquiryFollowUpDto dto) {

        InquiryFollowUp f = new InquiryFollowUp();

        f.setInquiryId(dto.getInquiryId());
        f.setRemark(dto.getRemark());
        f.setNextFollowUpDate(dto.getNextFollowUpDate());
        f.setStatus(dto.getStatus());
        f.setUpdatedBy(dto.getUpdatedBy());

        return repository.save(f);
    }

    @Override
    public List<InquiryFollowUp> getByInquiryId(Long inquiryId) {
        return repository.findByInquiryId(inquiryId);
    }

    @Override
    public List<InquiryFollowUp> getAll() {
        return repository.findAll();
    }
}