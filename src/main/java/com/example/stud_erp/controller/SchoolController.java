package com.example.stud_erp.controller;

import com.example.stud_erp.payload.SchoolDto;
import com.example.stud_erp.service.SchoolService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schools")
@CrossOrigin("*")
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    // ================= CREATE SCHOOL + SCHOOL ADMIN =================
    @PostMapping
    public SchoolDto createSchool(
            @RequestBody SchoolDto dto
    ) {

        return schoolService.createSchool(dto);
    }

    // ================= UPDATE SCHOOL =================
    @PutMapping("/{id}")
    public SchoolDto updateSchool(
            @PathVariable Long id,
            @RequestBody SchoolDto dto
    ) {

        return schoolService.updateSchool(id, dto);
    }

    // ================= DELETE SCHOOL =================
    @DeleteMapping("/{id}")
    public String deleteSchool(
            @PathVariable Long id
    ) {

        schoolService.deleteSchool(id);

        return "School deleted successfully";
    }

    // ================= GET SCHOOL BY ID =================
    @GetMapping("/{id}")
    public SchoolDto getSchoolById(
            @PathVariable Long id
    ) {

        return schoolService.getSchoolById(id);
    }

    // ================= GET ALL SCHOOLS =================
    @GetMapping
    public List<SchoolDto> getAllSchools() {

        return schoolService.getAllSchools();
    }
}