package com.example.stud_erp.service.Implementation;

import com.example.stud_erp.entity.VisitSchedule;
import com.example.stud_erp.repository.VisitScheduleRepository;
import com.example.stud_erp.service.VisitScheduleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitScheduleServiceImpl implements VisitScheduleService {

    private final VisitScheduleRepository repository;

    public VisitScheduleServiceImpl(VisitScheduleRepository repository) {
        this.repository = repository;
    }

    @Override
    public VisitSchedule createSchedule(VisitSchedule visitSchedule) {
        visitSchedule.setStatus(
                visitSchedule.getStatus() == null ? "SCHEDULED" : visitSchedule.getStatus()
        );
        return repository.save(visitSchedule);
    }

    @Override
    public List<VisitSchedule> getByInquiryId(Long inquiryId) {
        return repository.findByInquiryId(inquiryId);
    }

    @Override
    public List<VisitSchedule> getAllSchedules() {
        return repository.findAll();
    }

    @Override
    public VisitSchedule updateStatus(Long id, String status) {
        VisitSchedule schedule = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));

        schedule.setStatus(status);
        return repository.save(schedule);
    }
}