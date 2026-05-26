package com.example.stud_erp.service.Implementation;

import com.example.stud_erp.entity.Facility;
import com.example.stud_erp.entity.School;
import com.example.stud_erp.payload.FacilityDto;
import com.example.stud_erp.repository.FacilityRepository;
import com.example.stud_erp.repository.SchoolRepository;
import com.example.stud_erp.service.FacilityService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacilityServiceImpl
        implements FacilityService {

    // =====================================================
    // REPOSITORIES
    // =====================================================

    private final FacilityRepository facilityRepository;

    private final SchoolRepository schoolRepository;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public FacilityServiceImpl(
            FacilityRepository facilityRepository,
            SchoolRepository schoolRepository
    ) {

        this.facilityRepository =
                facilityRepository;

        this.schoolRepository =
                schoolRepository;
    }

    // =====================================================
    // CREATE
    // =====================================================

    @Override
    public FacilityDto createFacility(
            FacilityDto dto
    ) {

        School school =
                schoolRepository.findById(
                        dto.getSchoolId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "School not found"
                        )
                );

        Facility facility =
                new Facility();

        facility.setTitle(
                dto.getTitle()
        );

        facility.setDescription(
                dto.getDescription()
        );

        facility.setIcon(
                dto.getIcon()
        );

        facility.setTotalCount(
                dto.getTotalCount()
        );

        facility.setActive(
                dto.getActive() != null
                        ? dto.getActive()
                        : true
        );

        facility.setSchool(school);

        Facility saved =
                facilityRepository.save(
                        facility
                );

        return mapToDto(saved);
    }

    // =====================================================
    // UPDATE
    // =====================================================

    @Override
    public FacilityDto updateFacility(
            Long id,
            FacilityDto dto
    ) {

        Facility facility =
                facilityRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Facility not found"
                                )
                        );

        facility.setTitle(
                dto.getTitle()
        );

        facility.setDescription(
                dto.getDescription()
        );

        facility.setIcon(
                dto.getIcon()
        );

        facility.setTotalCount(
                dto.getTotalCount()
        );

        facility.setActive(
                dto.getActive()
        );

        Facility updated =
                facilityRepository.save(
                        facility
                );

        return mapToDto(updated);
    }

    // =====================================================
    // DELETE
    // =====================================================

    @Override
    public void deleteFacility(
            Long id
    ) {

        facilityRepository.deleteById(id);
    }

    // =====================================================
    // GET BY ID
    // =====================================================

    @Override
    public FacilityDto getFacilityById(
            Long id
    ) {

        Facility facility =
                facilityRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Facility not found"
                                )
                        );

        return mapToDto(facility);
    }

    // =====================================================
    // GET ALL
    // =====================================================

    @Override
    public List<FacilityDto>
    getAllFacilities() {

        return facilityRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // =====================================================
    // GET BY SCHOOL
    // =====================================================

    @Override
    public List<FacilityDto>
    getFacilitiesBySchool(
            Long schoolId
    ) {

        return facilityRepository
                .findBySchoolIdAndActiveTrue(
                        schoolId
                )
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // =====================================================
    // DTO MAPPER
    // =====================================================

    private FacilityDto mapToDto(
            Facility facility
    ) {

        FacilityDto dto =
                new FacilityDto();

        dto.setId(
                facility.getId()
        );

        dto.setTitle(
                facility.getTitle()
        );

        dto.setDescription(
                facility.getDescription()
        );

        dto.setIcon(
                facility.getIcon()
        );

        dto.setTotalCount(
                facility.getTotalCount()
        );

        dto.setActive(
                facility.getActive()
        );

        if (facility.getSchool() != null) {

            dto.setSchoolId(
                    facility.getSchool().getId()
            );

            dto.setSchoolName(
                    facility.getSchool().getSchoolName()
            );
        }

        return dto;
    }
}