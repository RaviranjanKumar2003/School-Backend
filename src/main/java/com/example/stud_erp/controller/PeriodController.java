package com.example.stud_erp.controller;

import com.example.stud_erp.payload.PeriodDto;
import com.example.stud_erp.service.PeriodService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/periods")
@CrossOrigin("*")
public class PeriodController {

    @Autowired
    private PeriodService service;

    // =====================================================
    // CREATE
    // =====================================================

    @PostMapping
    public PeriodDto createPeriod(
            @RequestBody PeriodDto dto
    ) {

        return service.createPeriod(dto);
    }

    // =====================================================
    // GET BY SCHOOL
    // =====================================================

    @GetMapping("/school/{schoolId}")
    public List<PeriodDto> getBySchool(
            @PathVariable Long schoolId
    ) {

        return service.getBySchool(schoolId);
    }

    // =====================================================
    // UPDATE
    // =====================================================

    @PutMapping("/{id}")
    public PeriodDto updatePeriod(
            @PathVariable Long id,
            @RequestBody PeriodDto dto
    ) {

        return service.updatePeriod(id, dto);
    }

    // =====================================================
    // DELETE
    // =====================================================

    @DeleteMapping("/{id}")
    public String deletePeriod(
            @PathVariable Long id
    ) {

        service.deletePeriod(id);

        return "Period Deleted Successfully";
    }

    // =====================================================
    // DELETE BY SCHOOL
    // =====================================================

    @DeleteMapping("/school/{schoolId}")
    public String deleteSchoolPeriods(
            @PathVariable Long schoolId
    ) {

        service.deleteBySchool(schoolId);

        return "All School Periods Deleted Successfully";
    }
}