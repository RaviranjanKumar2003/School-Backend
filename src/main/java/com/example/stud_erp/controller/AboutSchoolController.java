// ======================================================
// CONTROLLER -> AboutSchoolController
// ======================================================

package com.example.stud_erp.controller;

import com.example.stud_erp.payload.AboutSchoolDto;
import com.example.stud_erp.service.AboutSchoolService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/about-school")
@CrossOrigin("*")
public class AboutSchoolController {

    @Autowired
    private AboutSchoolService aboutSchoolService;

    // ======================================================
    // CREATE OR UPDATE
    // ======================================================

    @PostMapping("/{schoolId}")
    public AboutSchoolDto createOrUpdate(

            @PathVariable Long schoolId,

            @RequestBody AboutSchoolDto dto
    ) {

        return aboutSchoolService.createOrUpdate(
                schoolId,
                dto
        );
    }

    // ======================================================
    // GET BY SCHOOL ID
    // ======================================================

    @GetMapping("/{schoolId}")
    public AboutSchoolDto getBySchoolId(
            @PathVariable Long schoolId
    ) {

        return aboutSchoolService.getBySchoolId(
                schoolId
        );
    }
}