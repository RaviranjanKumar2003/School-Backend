package com.example.stud_erp.service.Implementation;

import com.example.stud_erp.entity.School;
import com.example.stud_erp.entity.Testimonial;
import com.example.stud_erp.payload.TestimonialDto;
import com.example.stud_erp.repository.SchoolRepository;
import com.example.stud_erp.repository.TestimonialRepository;
import com.example.stud_erp.service.TestimonialService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestimonialServiceImpl
        implements TestimonialService {

    private final TestimonialRepository testimonialRepository;

    private final SchoolRepository schoolRepository;

    public TestimonialServiceImpl(
            TestimonialRepository testimonialRepository,
            SchoolRepository schoolRepository
    ) {

        this.testimonialRepository =
                testimonialRepository;

        this.schoolRepository =
                schoolRepository;
    }

    // =====================================================
    // CREATE
    // =====================================================

    @Override
    public TestimonialDto createTestimonial(
            TestimonialDto dto
    ) {

        School school =
                schoolRepository.findById(
                        dto.getSchoolId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "School not found"
                        )
                );

        Testimonial testimonial =
                new Testimonial();

        setData(testimonial, dto);

        testimonial.setSchool(school);

        Testimonial saved =
                testimonialRepository.save(
                        testimonial
                );

        return mapToDto(saved);
    }

    // =====================================================
    // UPDATE
    // =====================================================

    @Override
    public TestimonialDto updateTestimonial(
            Long id,
            TestimonialDto dto
    ) {

        Testimonial testimonial =
                testimonialRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Testimonial not found"
                                )
                        );

        setData(testimonial, dto);

        Testimonial updated =
                testimonialRepository.save(
                        testimonial
                );

        return mapToDto(updated);
    }

    // =====================================================
    // DELETE
    // =====================================================

    @Override
    public void deleteTestimonial(
            Long id
    ) {

        testimonialRepository.deleteById(id);
    }

    // =====================================================
    // GET BY ID
    // =====================================================

    @Override
    public TestimonialDto getTestimonialById(
            Long id
    ) {

        Testimonial testimonial =
                testimonialRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Testimonial not found"
                                )
                        );

        return mapToDto(testimonial);
    }

    // =====================================================
    // GET ALL
    // =====================================================

    @Override
    public List<TestimonialDto>
    getAllTestimonials() {

        return testimonialRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // =====================================================
    // GET BY SCHOOL
    // =====================================================

    @Override
    public List<TestimonialDto>
    getTestimonialsBySchool(
            Long schoolId
    ) {

        return testimonialRepository
                .findBySchoolId(
                        schoolId
                )
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // =====================================================
    // SET DATA
    // =====================================================

    private void setData(
            Testimonial testimonial,
            TestimonialDto dto
    ) {

        testimonial.setName(dto.getName());

        testimonial.setRole(dto.getRole());

        testimonial.setMessage(
                dto.getMessage()
        );

        testimonial.setImage(
                dto.getImage()
        );

        testimonial.setRating(
                dto.getRating()
        );

        testimonial.setActive(
                dto.getActive() != null
                        ? dto.getActive()
                        : true
        );
    }

    // =====================================================
    // DTO MAPPER
    // =====================================================

    private TestimonialDto mapToDto(
            Testimonial testimonial
    ) {

        TestimonialDto dto =
                new TestimonialDto();

        dto.setId(testimonial.getId());

        dto.setName(
                testimonial.getName()
        );

        dto.setRole(
                testimonial.getRole()
        );

        dto.setMessage(
                testimonial.getMessage()
        );

        dto.setImage(
                testimonial.getImage()
        );

        dto.setRating(
                testimonial.getRating()
        );

        dto.setActive(
                testimonial.getActive()
        );

        if (testimonial.getSchool() != null) {

            dto.setSchoolId(
                    testimonial.getSchool().getId()
            );

            dto.setSchoolName(
                    testimonial.getSchool().getSchoolName()
            );
        }

        return dto;
    }
}