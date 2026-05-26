package com.example.stud_erp.controller;

import com.example.stud_erp.entity.School;
import com.example.stud_erp.payload.*;
import com.example.stud_erp.repository.*;
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




//========================================================================================================
//package com.example.stud_erp.controller;
//
//import com.example.stud_erp.entity.School;
//import com.example.stud_erp.payload.FacilityDto;
//import com.example.stud_erp.payload.GalleryDto;
//import com.example.stud_erp.payload.PublicSchoolResponseDto;
//import com.example.stud_erp.payload.SchoolStatisticsDto;
//import com.example.stud_erp.payload.TestimonialDto;
//import com.example.stud_erp.repository.FacilityRepository;
//import com.example.stud_erp.repository.GalleryRepository;
//import com.example.stud_erp.repository.SchoolRepository;
//import com.example.stud_erp.repository.SchoolStatisticsRepository;
//import com.example.stud_erp.repository.TestimonialRepository;
//
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/public/schools")
//@CrossOrigin("*")
//public class PublicSchoolController {
//
//    // =====================================================
//    // REPOSITORIES
//    // =====================================================
//
//    private final SchoolRepository schoolRepository;
//
//    private final FacilityRepository facilityRepository;
//
//    private final GalleryRepository galleryRepository;
//
//    private final SchoolStatisticsRepository statisticsRepository;
//
//    private final TestimonialRepository testimonialRepository;
//
//    // =====================================================
//    // CONSTRUCTOR
//    // =====================================================
//
//    public PublicSchoolController(
//
//            SchoolRepository schoolRepository,
//
//            FacilityRepository facilityRepository,
//
//            GalleryRepository galleryRepository,
//
//            SchoolStatisticsRepository statisticsRepository,
//
//            TestimonialRepository testimonialRepository
//
//    ) {
//
//        this.schoolRepository = schoolRepository;
//
//        this.facilityRepository = facilityRepository;
//
//        this.galleryRepository = galleryRepository;
//
//        this.statisticsRepository = statisticsRepository;
//
//        this.testimonialRepository = testimonialRepository;
//    }
//
//    // =====================================================
//    // GET SCHOOL PUBLIC PAGE
//    // =====================================================
//
//    @GetMapping("/{slug}")
//    public PublicSchoolResponseDto getSchoolBySlug(
//            @PathVariable String slug
//    ) {
//
//        // =====================================================
//        // FIND SCHOOL
//        // =====================================================
//
//        School school =
//                schoolRepository.findBySlug(slug)
//                        .orElseThrow(() ->
//                                new RuntimeException(
//                                        "School not found"
//                                )
//                        );
//
//        if (school == null) {
//
//            throw new RuntimeException(
//                    "School not found"
//            );
//        }
//
//        // =====================================================
//        // DTO
//        // =====================================================
//
//        PublicSchoolResponseDto dto =
//                new PublicSchoolResponseDto();
//
//        // =====================================================
//        // BASIC INFO
//        // =====================================================
//
//        dto.setId(school.getId());
//
//        dto.setSchoolName(
//                school.getSchoolName()
//        );
//
//        dto.setSchoolCode(
//                school.getSchoolCode()
//        );
//
//        dto.setSlug(
//                school.getSlug()
//        );
//
//        dto.setTagline(
//                school.getTagline()
//        );
//
//        dto.setLogo(
//                school.getLogo()
//        );
//
//        // =====================================================
//        // SCHOOL DETAILS
//        // =====================================================
//
//        dto.setAbout(
//                school.getAbout()
//        );
//
//        dto.setMission(
//                school.getMission()
//        );
//
//        dto.setVision(
//                school.getVision()
//        );
//
//        dto.setPrincipalMessage(
//                school.getPrincipalMessage()
//        );
//
//        dto.setBoard(
//                school.getBoard()
//        );
//
//        dto.setSchoolType(
//                school.getSchoolType()
//        );
//
//        dto.setMedium(
//                school.getMedium()
//        );
//
//        dto.setEstablishedYear(
//                school.getEstablishedYear()
//        );
//
//        // =====================================================
//        // CONTACT INFO
//        // =====================================================
//
//        dto.setEmail(
//                school.getEmail()
//        );
//
//        dto.setPhone(
//                school.getPhone()
//        );
//
//        dto.setWebsite(
//                school.getWebsite()
//        );
//
//        // =====================================================
//        // ADDRESS
//        // =====================================================
//
//        dto.setAddress(
//                school.getAddress()
//        );
//
//        dto.setCity(
//                school.getCity()
//        );
//
//        dto.setState(
//                school.getState()
//        );
//
//        dto.setCountry(
//                school.getCountry()
//        );
//
//        dto.setPincode(
//                school.getPincode()
//        );
//
//        // =====================================================
//        // SOCIAL LINKS
//        // =====================================================
//
//        dto.setFacebook(
//                school.getFacebookLink()
//        );
//
//        dto.setInstagram(
//                school.getInstagramLink()
//        );
//
//        dto.setYoutube(
//                school.getYoutubeLink()
//        );
//
//        // =====================================================
//        // STATUS
//        // =====================================================
//
//        dto.setActive(
//                school.getActive()
//        );
//
//        // =====================================================
//        // COVER IMAGES
//        // =====================================================
//
//        dto.setCoverImages(
//                school.getCoverImages()
//        );
//
//        // =====================================================
//        // FACILITIES
//        // =====================================================
//
//        dto.setFacilities(
//
//                facilityRepository
//                        .findBySchoolId(
//                                school.getId()
//                        )
//                        .stream()
//                        .map(facility -> {
//
//                            FacilityDto f =
//                                    new FacilityDto();
//
//                            f.setId(
//                                    facility.getId()
//                            );
//
//                            f.setTitle(
//                                    facility.getTitle()
//                            );
//
//                            f.setDescription(
//                                    facility.getDescription()
//                            );
//
//                            f.setIcon(
//                                    facility.getIcon()
//                            );
//
//                            return f;
//                        })
//                        .toList()
//        );
//
//        // =====================================================
//        // GALLERY
//        // =====================================================
//
//        dto.setGallery(
//
//                galleryRepository
//                        .findBySchoolId(
//                                school.getId()
//                        )
//                        .stream()
//                        .map(gallery -> {
//
//                            GalleryDto g =
//                                    new GalleryDto();
//
//                            g.setId(
//                                    gallery.getId()
//                            );
//
//                            g.setTitle(
//                                    gallery.getTitle()
//                            );
//
//                            g.setDescription(
//                                    gallery.getDescription()
//                            );
//
//                            g.setType(
//                                    gallery.getType()
//                            );
//
//                            g.setFileName(
//                                    gallery.getFileName()
//                            );
//
//                            g.setVideoUrl(
//                                    gallery.getVideoUrl()
//                            );
//
//                            g.setThumbnail(
//                                    gallery.getThumbnail()
//                            );
//
//                            return g;
//                        })
//                        .toList()
//        );
//
//        // =====================================================
//        // STATISTICS
//        // =====================================================
//
//        var stats =
//                statisticsRepository
//                        .findBySchoolId(
//                                school.getId()
//                        );
//
//        if (stats != null) {
//
//            SchoolStatisticsDto s =
//                    new SchoolStatisticsDto();
//
//            s.setId(
//                    stats.getId()
//            );
//
//            s.setTotalStudents(
//                    stats.getTotalStudents()
//            );
//
//            s.setTotalTeachers(
//                    stats.getTotalTeachers()
//            );
//
//            s.setTotalClasses(
//                    stats.getTotalClasses()
//            );
//
//            s.setTotalLabs(
//                    stats.getTotalLabs()
//            );
//
//            s.setTotalLibraries(
//                    stats.getTotalLibraries()
//            );
//
//            s.setTotalComputers(
//                    stats.getTotalComputers()
//            );
//
//            s.setBoardResultPercentage(
//                    stats.getBoardResultPercentage()
//            );
//
//            dto.setStatistics(s);
//        }
//
//        // =====================================================
//        // TESTIMONIALS
//        // =====================================================
//
//        dto.setTestimonials(
//
//                testimonialRepository
//                        .findBySchoolIdAndActiveTrue(
//                                school.getId()
//                        )
//                        .stream()
//                        .map(testimonial -> {
//
//                            TestimonialDto t =
//                                    new TestimonialDto();
//
//                            t.setId(
//                                    testimonial.getId()
//                            );
//
//                            t.setName(
//                                    testimonial.getName()
//                            );
//
//                            t.setRole(
//                                    testimonial.getRole()
//                            );
//
//                            t.setMessage(
//                                    testimonial.getMessage()
//                            );
//
//                            t.setImage(
//                                    testimonial.getImage()
//                            );
//
//                            t.setRating(
//                                    testimonial.getRating()
//                            );
//
//                            return t;
//                        })
//                        .toList()
//        );
//
//        // =====================================================
//        // RETURN
//        // =====================================================
//
//        return dto;
//    }
//}