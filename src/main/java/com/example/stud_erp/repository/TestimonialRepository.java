package com.example.stud_erp.repository;

import com.example.stud_erp.entity.Testimonial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestimonialRepository
        extends JpaRepository<Testimonial, Long> {

    List<Testimonial> findBySchoolId(
            Long schoolId
    );

    List<Testimonial> findBySchoolIdAndActiveTrue(
            Long schoolId
    );
}