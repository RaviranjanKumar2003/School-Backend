package com.example.stud_erp.controller;

import com.example.stud_erp.payload.FacilityDto;
import com.example.stud_erp.service.FacilityService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facilities")
@CrossOrigin("*")
public class FacilityController {

    // =====================================================
    // SERVICE
    // =====================================================

    private final FacilityService facilityService;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public FacilityController(
            FacilityService facilityService
    ) {

        this.facilityService =
                facilityService;
    }

    // =====================================================
    // CREATE
    // =====================================================

    @PostMapping
    public FacilityDto createFacility(

            @RequestBody FacilityDto dto

    ) {

        return facilityService
                .createFacility(dto);
    }

    // =====================================================
    // UPDATE
    // =====================================================

    @PutMapping("/{id}")
    public FacilityDto updateFacility(

            @PathVariable Long id,

            @RequestBody FacilityDto dto

    ) {

        return facilityService
                .updateFacility(
                        id,
                        dto
                );
    }

    // =====================================================
    // DELETE
    // =====================================================

    @DeleteMapping("/{id}")
    public String deleteFacility(

            @PathVariable Long id

    ) {

        facilityService
                .deleteFacility(id);

        return "Facility deleted successfully";
    }

    // =====================================================
    // GET BY ID
    // =====================================================

    @GetMapping("/{id}")
    public FacilityDto getFacilityById(

            @PathVariable Long id

    ) {

        return facilityService
                .getFacilityById(id);
    }

    // =====================================================
    // GET ALL
    // =====================================================

    @GetMapping
    public List<FacilityDto>
    getAllFacilities() {

        return facilityService
                .getAllFacilities();
    }

    // =====================================================
    // GET BY SCHOOL
    // =====================================================

    @GetMapping("/school/{schoolId}")
    public List<FacilityDto>
    getFacilitiesBySchool(

            @PathVariable Long schoolId

    ) {

        return facilityService
                .getFacilitiesBySchool(
                        schoolId
                );
    }
}