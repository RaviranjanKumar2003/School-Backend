package com.example.stud_erp.controller;

import com.example.stud_erp.payload.SchoolTimingSettingsDto;
import com.example.stud_erp.service.SchoolTimingSettingsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/school-timing")
@CrossOrigin("*")
public class SchoolTimingSettingsController {

    @Autowired
    private SchoolTimingSettingsService service;

    // =====================================================
    // CREATE / UPDATE
    // =====================================================

    @PostMapping
    public SchoolTimingSettingsDto saveSettings(
            @RequestBody SchoolTimingSettingsDto dto
    ) {

        return service.saveSettings(dto);
    }

    // =====================================================
    // GET BY SCHOOL
    // =====================================================

    @GetMapping("/school/{schoolId}")
    public SchoolTimingSettingsDto getBySchool(
            @PathVariable Long schoolId
    ) {

        return service.getBySchoolId(schoolId);
    }

    // =====================================================
    // DELETE
    // =====================================================

    @DeleteMapping("/{id}")
    public String deleteSettings(
            @PathVariable Long id
    ) {

        service.deleteSettings(id);

        return "School Timing Settings Deleted Successfully";
    }
}