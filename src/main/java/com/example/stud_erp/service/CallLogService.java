package com.example.stud_erp.service;

import com.example.stud_erp.entity.CallLog;
import com.example.stud_erp.payload.CallLogDto;

import java.util.List;

public interface CallLogService {

    CallLog createCallLog(CallLogDto dto);

    List<CallLog> getByInquiryId(Long inquiryId);

    List<CallLog> getAll();
}