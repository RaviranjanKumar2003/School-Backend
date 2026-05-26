package com.example.stud_erp.controller;

import com.example.stud_erp.payload.TestimonialDto;
import com.example.stud_erp.service.TestimonialService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/testimonials")
@CrossOrigin("*")
public class TestimonialController {

    private final TestimonialService testimonialService;

    public TestimonialController(
            TestimonialService testimonialService
    ) {

        this.testimonialService =
                testimonialService;
    }

    // =====================================================
    // CREATE
    // =====================================================

    @PostMapping
    public TestimonialDto createTestimonial(

            @RequestBody
            TestimonialDto dto

    ) {

        return testimonialService
                .createTestimonial(dto);
    }

    // =====================================================
    // UPDATE
    // =====================================================

    @PutMapping("/{id}")
    public TestimonialDto updateTestimonial(

            @PathVariable Long id,

            @RequestBody
            TestimonialDto dto

    ) {

        return testimonialService
                .updateTestimonial(
                        id,
                        dto
                );
    }

    // =====================================================
    // DELETE
    // =====================================================

    @DeleteMapping("/{id}")
    public String deleteTestimonial(

            @PathVariable Long id

    ) {

        testimonialService
                .deleteTestimonial(id);

        return "Testimonial deleted successfully";
    }

    // =====================================================
    // GET BY ID
    // =====================================================

    @GetMapping("/{id}")
    public TestimonialDto getTestimonialById(

            @PathVariable Long id

    ) {

        return testimonialService
                .getTestimonialById(id);
    }

    // =====================================================
    // GET ALL
    // =====================================================

    @GetMapping
    public List<TestimonialDto>
    getAllTestimonials() {

        return testimonialService
                .getAllTestimonials();
    }

    // =====================================================
    // GET BY SCHOOL
    // =====================================================

    @GetMapping("/school/{schoolId}")
    public List<TestimonialDto>
    getTestimonialsBySchool(

            @PathVariable Long schoolId

    ) {

        return testimonialService
                .getTestimonialsBySchool(
                        schoolId
                );
    }
}