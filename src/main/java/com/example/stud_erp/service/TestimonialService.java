package com.example.stud_erp.service;

import com.example.stud_erp.payload.TestimonialDto;

import java.util.List;

public interface TestimonialService {

    TestimonialDto createTestimonial(
            TestimonialDto dto
    );

    TestimonialDto updateTestimonial(
            Long id,
            TestimonialDto dto
    );

    void deleteTestimonial(
            Long id
    );

    TestimonialDto getTestimonialById(
            Long id
    );

    List<TestimonialDto> getAllTestimonials();

    List<TestimonialDto> getTestimonialsBySchool(
            Long schoolId
    );
}