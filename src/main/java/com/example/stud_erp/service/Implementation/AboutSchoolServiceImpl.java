package com.example.stud_erp.service.Implementation;// ======================================================
// SERVICE IMPL -> AboutSchoolServiceImpl
// ======================================================

import com.example.stud_erp.entity.AboutSchool;
import com.example.stud_erp.entity.School;
import com.example.stud_erp.payload.AboutSchoolDto;
import com.example.stud_erp.repository.AboutSchoolRepository;
import com.example.stud_erp.repository.SchoolRepository;
import com.example.stud_erp.service.AboutSchoolService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AboutSchoolServiceImpl
        implements AboutSchoolService {

    @Autowired
    private AboutSchoolRepository aboutSchoolRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    // ======================================================
    // CREATE OR UPDATE
    // ======================================================

    @Override
    public AboutSchoolDto createOrUpdate(
            Long schoolId,
            AboutSchoolDto dto
    ) {

        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() ->
                        new RuntimeException("School not found"));

        AboutSchool aboutSchool =
                aboutSchoolRepository.findBySchoolId(schoolId)
                        .orElse(new AboutSchool());

        aboutSchool.setSchool(school);

        // BRANDING
        aboutSchool.setTagline(dto.getTagline());
        aboutSchool.setLogo(dto.getLogo());

        // CONTENT
        aboutSchool.setAbout(dto.getAbout());
        aboutSchool.setMission(dto.getMission());
        aboutSchool.setVision(dto.getVision());
        aboutSchool.setPrincipalMessage(dto.getPrincipalMessage());
        aboutSchool.setSchoolAdminMessage(dto.getSchoolAdminMessage());

        // DETAILS
        aboutSchool.setBoard(dto.getBoard());
        aboutSchool.setSchoolType(dto.getSchoolType());
        aboutSchool.setMedium(dto.getMedium());
        aboutSchool.setEstablishedYear(
                dto.getEstablishedYear()
        );

        // CONTACT
        aboutSchool.setWebsite(dto.getWebsite());

        // SOCIAL
        aboutSchool.setFacebookLink(
                dto.getFacebookLink()
        );

        aboutSchool.setInstagramLink(
                dto.getInstagramLink()
        );

        aboutSchool.setYoutubeLink(
                dto.getYoutubeLink()
        );

        AboutSchool saved =
                aboutSchoolRepository.save(aboutSchool);

        return mapToDto(saved);
    }

    // ======================================================
    // GET BY SCHOOL ID
    // ======================================================

    @Override
    public AboutSchoolDto getBySchoolId(Long schoolId) {

        AboutSchool aboutSchool =
                aboutSchoolRepository.findBySchoolId(schoolId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "AboutSchool not found"
                                ));

        return mapToDto(aboutSchool);
    }

    // ======================================================
    // MAP TO DTO
    // ======================================================

    private AboutSchoolDto mapToDto(
            AboutSchool aboutSchool
    ) {

        AboutSchoolDto dto = new AboutSchoolDto();

        dto.setId(aboutSchool.getId());

        dto.setSchoolId(
                aboutSchool.getSchool().getId()
        );

        // BRANDING
        dto.setTagline(aboutSchool.getTagline());
        dto.setLogo(aboutSchool.getLogo());

        // CONTENT
        dto.setAbout(aboutSchool.getAbout());
        dto.setMission(aboutSchool.getMission());
        dto.setVision(aboutSchool.getVision());
        dto.setPrincipalMessage(
                aboutSchool.getPrincipalMessage()
        );
        dto.setSchoolAdminMessage(
                aboutSchool.getSchoolAdminMessage()
        );

        // DETAILS
        dto.setBoard(aboutSchool.getBoard());
        dto.setSchoolType(
                aboutSchool.getSchoolType()
        );

        dto.setMedium(aboutSchool.getMedium());

        dto.setEstablishedYear(
                aboutSchool.getEstablishedYear()
        );

        // CONTACT
        dto.setWebsite(aboutSchool.getWebsite());

        // SOCIAL
        dto.setFacebookLink(
                aboutSchool.getFacebookLink()
        );

        dto.setInstagramLink(
                aboutSchool.getInstagramLink()
        );

        dto.setYoutubeLink(
                aboutSchool.getYoutubeLink()
        );

        return dto;
    }
}