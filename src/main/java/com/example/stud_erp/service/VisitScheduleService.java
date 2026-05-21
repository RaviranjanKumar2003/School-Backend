package com.example.stud_erp.service;

import com.example.stud_erp.entity.VisitSchedule;

import java.util.List;

public interface VisitScheduleService {

    VisitSchedule createSchedule(VisitSchedule visitSchedule);

    List<VisitSchedule> getByInquiryId(Long inquiryId);

    List<VisitSchedule> getAllSchedules();

    VisitSchedule updateStatus(Long id, String status);
}