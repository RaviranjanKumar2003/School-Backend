package com.example.stud_erp.service.Implementation;

import com.example.stud_erp.entity.CallLog;
import com.example.stud_erp.payload.CallLogDto;
import com.example.stud_erp.repository.CallLogRepository;
import com.example.stud_erp.service.CallLogService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CallLogServiceImpl implements CallLogService {

    private final CallLogRepository repository;

    public CallLogServiceImpl(CallLogRepository repository) {
        this.repository = repository;
    }

    @Override
    public CallLog createCallLog(CallLogDto dto) {

        CallLog log = new CallLog();

        log.setInquiryId(dto.getInquiryId());
        log.setPhone(dto.getPhone());
        log.setCallStatus(dto.getCallStatus());
        log.setDurationInSeconds(dto.getDurationInSeconds());
        log.setRemarks(dto.getRemarks());
        log.setCalledBy(dto.getCalledBy());

        return repository.save(log);
    }

    @Override
    public List<CallLog> getByInquiryId(Long inquiryId) {
        return repository.findByInquiryId(inquiryId);
    }

    @Override
    public List<CallLog> getAll() {
        return repository.findAll();
    }
}