package com.example.stud_erp.controller;

import com.example.stud_erp.entity.VisitSchedule;
import com.example.stud_erp.service.VisitScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visit-schedules")
@CrossOrigin("*")
public class VisitScheduleController {

    private final VisitScheduleService service;

    public VisitScheduleController(VisitScheduleService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public VisitSchedule create(@RequestBody VisitSchedule visitSchedule) {
        return service.createSchedule(visitSchedule);
    }

    // GET ALL
    @GetMapping
    public List<VisitSchedule> getAll() {
        return service.getAllSchedules();
    }

    // GET BY INQUIRY
    @GetMapping("/inquiry/{id}")
    public List<VisitSchedule> getByInquiry(@PathVariable Long id) {
        return service.getByInquiryId(id);
    }

    // UPDATE STATUS
    @PutMapping("/{id}/status")
    public VisitSchedule updateStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {
        return service.updateStatus(id, status);
    }
}