package com.example.stud_erp.controller;

import com.example.stud_erp.entity.School;
import com.example.stud_erp.repository.SchoolRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/schools")
@CrossOrigin("*")
public class PublicSchoolController {

    private final SchoolRepository schoolRepository;

    public PublicSchoolController(
            SchoolRepository schoolRepository
    ) {
        this.schoolRepository = schoolRepository;
    }

    // =========================================
    // GET SCHOOL BY SLUG
    // =========================================

    @GetMapping("/{slug}")
    public School getSchoolBySlug(
            @PathVariable String slug
    ) {

        return schoolRepository.findBySlug(slug);
    }
}