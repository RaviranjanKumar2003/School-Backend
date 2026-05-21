package com.example.stud_erp.controller;

import com.example.stud_erp.entity.Admission;
import com.example.stud_erp.service.AdmissionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admissions")
@CrossOrigin("*")
public class AdmissionController {

    private final AdmissionService service;

    public AdmissionController(AdmissionService service) {
        this.service = service;
    }

    // CREATE ADMISSION
    @PostMapping
    public Admission create(@RequestBody Admission admission) {
        return service.createAdmission(admission);
    }

    // GET ALL
    @GetMapping
    public List<Admission> getAll() {
        return service.getAllAdmissions();
    }

    // GET BY STATUS
    @GetMapping("/status/{status}")
    public List<Admission> getByStatus(@PathVariable String status) {
        return service.getByStatus(status);
    }

    // UPDATE STATUS
    @PutMapping("/{id}/status")
    public Admission updateStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {
        return service.updateStatus(id, status);
    }
}