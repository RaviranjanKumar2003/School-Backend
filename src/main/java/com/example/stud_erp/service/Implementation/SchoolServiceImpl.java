package com.example.stud_erp.service.Implementation;

import com.example.stud_erp.entity.School;
import com.example.stud_erp.entity.SchoolAdmin;
import com.example.stud_erp.payload.SchoolAdminDto;
import com.example.stud_erp.payload.SchoolDto;
import com.example.stud_erp.repository.SchoolRepository;
import com.example.stud_erp.service.ImageService;
import com.example.stud_erp.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;

    @Autowired
    private ImageService imageService;

    public SchoolServiceImpl(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    // ================= CREATE =================
    @Override
    public SchoolDto createSchool(SchoolDto dto) {

        School school = new School();

        // ================= SCHOOL =================
        school.setSchoolName(dto.getSchoolName());

        String slug = dto.getSchoolName()
                .toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-");

        school.setSlug(slug);

        school.setSchoolCode(dto.getSchoolCode());
        school.setAddress(dto.getAddress());
        school.setEmail(dto.getEmail());
        school.setPhone(dto.getPhone());

        // ================= SCHOOL ADMIN =================
        if (dto.getSchoolAdmin() != null) {

            SchoolAdmin admin = new SchoolAdmin();

            admin.setName(
                    dto.getSchoolAdmin().getName()
            );

            admin.setUsername(
                    dto.getSchoolAdmin().getUsername()
            );

            admin.setPassword(
                    dto.getSchoolAdmin().getPassword()
            );

            admin.setEmail(
                    dto.getSchoolAdmin().getEmail()
            );

            admin.setPhone(
                    dto.getSchoolAdmin().getPhone()
            );

            // IMPORTANT
            admin.setSchool(school);

            // IMPORTANT
            school.setSchoolAdmin(admin);
        }

        // ================= SAVE =================
        School savedSchool =
                schoolRepository.save(school);

        return mapToDto(savedSchool);
    }

    // ================= UPDATE =================
    @Override
    public SchoolDto updateSchool(Long id, SchoolDto dto) {

        School school = schoolRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("School not found"));

        // ================= SCHOOL UPDATE =================

        school.setSchoolName(dto.getSchoolName());
        school.setSchoolCode(dto.getSchoolCode());
        school.setAddress(dto.getAddress());
        school.setEmail(dto.getEmail());
        school.setPhone(dto.getPhone());

        // ================= SCHOOL ADMIN UPDATE =================

        if (dto.getSchoolAdmin() != null) {

            SchoolAdmin admin;

            // Existing admin hai to update karo
            if (school.getSchoolAdmin() != null) {

                admin = school.getSchoolAdmin();

            } else {

                // Naya admin create karo
                admin = new SchoolAdmin();
            }

            admin.setName(dto.getSchoolAdmin().getName());
            admin.setUsername(dto.getSchoolAdmin().getUsername());
            admin.setPassword(dto.getSchoolAdmin().getPassword());
            admin.setEmail(dto.getSchoolAdmin().getEmail());
            admin.setPhone(dto.getSchoolAdmin().getPhone());

            admin.setSchool(school);

            school.setSchoolAdmin(admin);
        }

        School updatedSchool = schoolRepository.save(school);

        return mapToDto(updatedSchool);
    }

    // ================= DELETE =================
    @Override
    public void deleteSchool(Long id) {

        School school = schoolRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("School not found"));

        schoolRepository.delete(school);
    }

    // ================= GET BY ID =================
    @Override
    public SchoolDto getSchoolById(Long id) {

        School school = schoolRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("School not found"));

        return mapToDto(school);
    }

    // ================= GET ALL =================
    @Override
    public List<SchoolDto> getAllSchools() {

        return schoolRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // ================= DTO MAPPER =================
    private SchoolDto mapToDto(School school) {

        SchoolDto dto = new SchoolDto();

        dto.setId(school.getId());
        dto.setSchoolName(school.getSchoolName());
        dto.setSchoolCode(school.getSchoolCode());
        dto.setAddress(school.getAddress());
        dto.setEmail(school.getEmail());
        dto.setPhone(school.getPhone());

        dto.setCoverImages(
                school.getCoverImages()
        );

        // ================= SCHOOL ADMINS =================

        List<SchoolAdminDto> adminDtos = new ArrayList<>();

        // ================= SCHOOL ADMIN =================
        if (school.getSchoolAdmin() != null) {

            SchoolAdminDto adminDto =
                    new SchoolAdminDto();

            adminDto.setId(
                    school.getSchoolAdmin().getId()
            );

            adminDto.setName(
                    school.getSchoolAdmin().getName()
            );

            adminDto.setUsername(
                    school.getSchoolAdmin().getUsername()
            );

            adminDto.setPassword(
                    school.getSchoolAdmin().getPassword()
            );

            adminDto.setEmail(
                    school.getSchoolAdmin().getEmail()
            );

            adminDto.setPhone(
                    school.getSchoolAdmin().getPhone()
            );

            dto.setSchoolAdmin(adminDto);
        }

        return dto;
    }


//============================================================================= COVER IMAGE

    @Override
    public School uploadCoverImages(
            Long schoolId,
            List<MultipartFile> images
    ) throws IOException {

        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() ->
                        new RuntimeException("School not found"));

        List<String> coverImages = school.getCoverImages();

        if (coverImages == null) {
            coverImages = new ArrayList<>();
        }

        for (MultipartFile file : images) {

            if (file != null && !file.isEmpty()) {

                String fileName =
                        imageService.uploadImage(file);

                coverImages.add(fileName);
            }
        }

        school.setCoverImages(coverImages);

        return schoolRepository.save(school);
    }
}



//package com.example.stud_erp.service.Implementation;
//
//import com.example.stud_erp.entity.School;
//import com.example.stud_erp.entity.SchoolAdmin;
//import com.example.stud_erp.payload.SchoolAdminDto;
//import com.example.stud_erp.payload.SchoolDto;
//import com.example.stud_erp.repository.SchoolRepository;
//import com.example.stud_erp.service.ImageService;
//import com.example.stud_erp.service.SchoolService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class SchoolServiceImpl
//        implements SchoolService {
//
//    // =====================================================
//    // REPOSITORY
//    // =====================================================
//
//    private final SchoolRepository schoolRepository;
//
//    @Autowired
//    private ImageService imageService;
//
//    // =====================================================
//    // CONSTRUCTOR
//    // =====================================================
//
//    public SchoolServiceImpl(
//            SchoolRepository schoolRepository
//    ) {
//
//        this.schoolRepository =
//                schoolRepository;
//    }
//
//    // =====================================================
//    // CREATE SCHOOL
//    // =====================================================
//
//    @Override
//    public SchoolDto createSchool(
//            SchoolDto dto
//    ) {
//
//        // ================= SCHOOL =================
//
//        School school = new School();
//
//        school.setSchoolName(
//                dto.getSchoolName()
//        );
//
//        // ================= SLUG =================
//
//        String slug =
//                dto.getSchoolName()
//                        .toLowerCase()
//                        .replaceAll(
//                                "[^a-z0-9\\s-]",
//                                ""
//                        )
//                        .replaceAll(
//                                "\\s+",
//                                "-"
//                        );
//
//        school.setSlug(slug);
//
//        // ================= BASIC =================
//
//        school.setSchoolCode(
//                dto.getSchoolCode()
//        );
//
//        school.setTagline(
//                dto.getTagline()
//        );
//
//        school.setLogo(
//                dto.getLogo()
//        );
//
//        school.setAbout(
//                dto.getAbout()
//        );
//
//        school.setMission(
//                dto.getMission()
//        );
//
//        school.setVision(
//                dto.getVision()
//        );
//
//        school.setPrincipalMessage(
//                dto.getPrincipalMessage()
//        );
//
//        school.setBoard(
//                dto.getBoard()
//        );
//
//        school.setSchoolType(
//                dto.getSchoolType()
//        );
//
//        school.setMedium(
//                dto.getMedium()
//        );
//
//        school.setEstablishedYear(
//                dto.getEstablishedYear()
//        );
//
//        // ================= CONTACT =================
//
//        school.setEmail(
//                dto.getEmail()
//        );
//
//        school.setPhone(
//                dto.getPhone()
//        );
//
//        school.setWebsite(
//                dto.getWebsite()
//        );
//
//        // ================= ADDRESS =================
//
//        school.setAddress(
//                dto.getAddress()
//        );
//
//        school.setCity(
//                dto.getCity()
//        );
//
//        school.setState(
//                dto.getState()
//        );
//
//        school.setCountry(
//                dto.getCountry()
//        );
//
//        school.setPincode(
//                dto.getPincode()
//        );
//
//        // ================= SOCIAL =================
//
//        school.setFacebookLink(
//                dto.getFacebookLink()
//        );
//
//        school.setInstagramLink(
//                dto.getInstagramLink()
//        );
//
//        school.setYoutubeLink(
//                dto.getYoutubeLink()
//        );
//
//        // ================= ACTIVE =================
//
//        school.setActive(
//                dto.getActive() != null
//                        ? dto.getActive()
//                        : true
//        );
//
//        // ================= COVER IMAGES =================
//
//        school.setCoverImages(
//                dto.getCoverImages()
//        );
//
//        // =====================================================
//        // SCHOOL ADMIN
//        // =====================================================
//
//        if (dto.getSchoolAdmin() != null) {
//
//            SchoolAdmin admin =
//                    new SchoolAdmin();
//
//            admin.setName(
//                    dto.getSchoolAdmin()
//                            .getName()
//            );
//
//            admin.setUsername(
//                    dto.getSchoolAdmin()
//                            .getUsername()
//            );
//
//            admin.setPassword(
//                    dto.getSchoolAdmin()
//                            .getPassword()
//            );
//
//            admin.setEmail(
//                    dto.getSchoolAdmin()
//                            .getEmail()
//            );
//
//            admin.setPhone(
//                    dto.getSchoolAdmin()
//                            .getPhone()
//            );
//
//            admin.setSchool(school);
//
//            school.setSchoolAdmin(admin);
//        }
//
//        // =====================================================
//        // SAVE
//        // =====================================================
//
//        School savedSchool =
//                schoolRepository.save(
//                        school
//                );
//
//        return mapToDto(savedSchool);
//    }
//
//    // =====================================================
//    // UPDATE SCHOOL
//    // =====================================================
//
//    @Override
//    public SchoolDto updateSchool(
//            Long id,
//            SchoolDto dto
//    ) {
//
//        School school =
//                schoolRepository.findById(id)
//                        .orElseThrow(() ->
//                                new RuntimeException(
//                                        "School not found"
//                                )
//                        );
//
//        // ================= BASIC =================
//
//        school.setSchoolName(
//                dto.getSchoolName()
//        );
//
//        school.setSchoolCode(
//                dto.getSchoolCode()
//        );
//
//        school.setTagline(
//                dto.getTagline()
//        );
//
//        school.setLogo(
//                dto.getLogo()
//        );
//
//        school.setAbout(
//                dto.getAbout()
//        );
//
//        school.setMission(
//                dto.getMission()
//        );
//
//        school.setVision(
//                dto.getVision()
//        );
//
//        school.setPrincipalMessage(
//                dto.getPrincipalMessage()
//        );
//
//        school.setBoard(
//                dto.getBoard()
//        );
//
//        school.setSchoolType(
//                dto.getSchoolType()
//        );
//
//        school.setMedium(
//                dto.getMedium()
//        );
//
//        school.setEstablishedYear(
//                dto.getEstablishedYear()
//        );
//
//        // ================= CONTACT =================
//
//        school.setEmail(
//                dto.getEmail()
//        );
//
//        school.setPhone(
//                dto.getPhone()
//        );
//
//        school.setWebsite(
//                dto.getWebsite()
//        );
//
//        // ================= ADDRESS =================
//
//        school.setAddress(
//                dto.getAddress()
//        );
//
//        school.setCity(
//                dto.getCity()
//        );
//
//        school.setState(
//                dto.getState()
//        );
//
//        school.setCountry(
//                dto.getCountry()
//        );
//
//        school.setPincode(
//                dto.getPincode()
//        );
//
//        // ================= SOCIAL =================
//
//        school.setFacebookLink(
//                dto.getFacebookLink()
//        );
//
//        school.setInstagramLink(
//                dto.getInstagramLink()
//        );
//
//        school.setYoutubeLink(
//                dto.getYoutubeLink()
//        );
//
//        // ================= ACTIVE =================
//
//        school.setActive(
//                dto.getActive()
//        );
//
//        // =====================================================
//        // SCHOOL ADMIN UPDATE
//        // =====================================================
//
//        if (dto.getSchoolAdmin() != null) {
//
//            SchoolAdmin admin;
//
//            if (school.getSchoolAdmin()
//                    != null) {
//
//                admin =
//                        school.getSchoolAdmin();
//
//            } else {
//
//                admin = new SchoolAdmin();
//            }
//
//            admin.setName(
//                    dto.getSchoolAdmin()
//                            .getName()
//            );
//
//            admin.setUsername(
//                    dto.getSchoolAdmin()
//                            .getUsername()
//            );
//
//            admin.setPassword(
//                    dto.getSchoolAdmin()
//                            .getPassword()
//            );
//
//            admin.setEmail(
//                    dto.getSchoolAdmin()
//                            .getEmail()
//            );
//
//            admin.setPhone(
//                    dto.getSchoolAdmin()
//                            .getPhone()
//            );
//
//            admin.setSchool(school);
//
//            school.setSchoolAdmin(admin);
//        }
//
//        School updatedSchool =
//                schoolRepository.save(
//                        school
//                );
//
//        return mapToDto(updatedSchool);
//    }
//
//    // =====================================================
//    // DELETE SCHOOL
//    // =====================================================
//
//    @Override
//    public void deleteSchool(
//            Long id
//    ) {
//
//        School school =
//                schoolRepository.findById(id)
//                        .orElseThrow(() ->
//                                new RuntimeException(
//                                        "School not found"
//                                )
//                        );
//
//        schoolRepository.delete(school);
//    }
//
//    // =====================================================
//    // GET SCHOOL BY ID
//    // =====================================================
//
//    @Override
//    public SchoolDto getSchoolById(
//            Long id
//    ) {
//
//        School school =
//                schoolRepository.findById(id)
//                        .orElseThrow(() ->
//                                new RuntimeException(
//                                        "School not found"
//                                )
//                        );
//
//        return mapToDto(school);
//    }
//
//    // =====================================================
//    // GET SCHOOL BY SLUG
//    // =====================================================
//
//    @Override
//    public SchoolDto getSchoolBySlug(
//            String slug
//    ) {
//
//        School school =
//                schoolRepository.findBySlug(slug)
//                        .orElseThrow(() ->
//                                new RuntimeException(
//                                        "School not found"
//                                )
//                        );
//
//        return mapToDto(school);
//    }
//
//    // =====================================================
//    // GET SCHOOL BY CODE
//    // =====================================================
//
//    @Override
//    public SchoolDto getSchoolBySchoolCode(
//            String schoolCode
//    ) {
//
//        School school =
//                schoolRepository
//                        .findBySchoolCode(
//                                schoolCode
//                        )
//                        .orElseThrow(() ->
//                                new RuntimeException(
//                                        "School not found"
//                                )
//                        );
//
//        return mapToDto(school);
//    }
//
//    // =====================================================
//    // GET ALL SCHOOLS
//    // =====================================================
//
//    @Override
//    public List<SchoolDto>
//    getAllSchools() {
//
//        return schoolRepository.findAll()
//                .stream()
//                .map(this::mapToDto)
//                .collect(Collectors.toList());
//    }
//
//    // =====================================================
//    // SEARCH SCHOOLS
//    // =====================================================
//
//    @Override
//    public List<SchoolDto> searchSchools(
//            String keyword
//    ) {
//
//        return schoolRepository
//                .findBySchoolNameContainingIgnoreCaseOrCityContainingIgnoreCase(
//                        keyword,
//                        keyword
//                )
//                .stream()
//                .map(this::mapToDto)
//                .collect(Collectors.toList());
//    }
//
//    // =====================================================
//    // ACTIVATE SCHOOL
//    // =====================================================
//
//    @Override
//    public SchoolDto activateSchool(
//            Long schoolId
//    ) {
//
//        School school =
//                schoolRepository.findById(
//                                schoolId
//                        )
//                        .orElseThrow(() ->
//                                new RuntimeException(
//                                        "School not found"
//                                )
//                        );
//
//        school.setActive(true);
//
//        return mapToDto(
//                schoolRepository.save(
//                        school
//                )
//        );
//    }
//
//    // =====================================================
//    // DEACTIVATE SCHOOL
//    // =====================================================
//
//    @Override
//    public SchoolDto deactivateSchool(
//            Long schoolId
//    ) {
//
//        School school =
//                schoolRepository.findById(
//                                schoolId
//                        )
//                        .orElseThrow(() ->
//                                new RuntimeException(
//                                        "School not found"
//                                )
//                        );
//
//        school.setActive(false);
//
//        return mapToDto(
//                schoolRepository.save(
//                        school
//                )
//        );
//    }
//
//    // =====================================================
//    // UPLOAD COVER IMAGES
//    // =====================================================
//
//    @Override
//    public School uploadCoverImages(
//            Long schoolId,
//            List<MultipartFile> images
//    ) throws IOException {
//
//        School school =
//                schoolRepository.findById(
//                                schoolId
//                        )
//                        .orElseThrow(() ->
//                                new RuntimeException(
//                                        "School not found"
//                                )
//                        );
//
//        List<String> coverImages =
//                school.getCoverImages();
//
//        if (coverImages == null) {
//
//            coverImages =
//                    new ArrayList<>();
//        }
//
//        for (MultipartFile file : images) {
//
//            if (file != null
//                    && !file.isEmpty()) {
//
//                String fileName =
//                        imageService.uploadImage(
//                                file
//                        );
//
//                coverImages.add(
//                        fileName
//                );
//            }
//        }
//
//        school.setCoverImages(
//                coverImages
//        );
//
//        return schoolRepository.save(
//                school
//        );
//    }
//
//    // =====================================================
//    // UPLOAD SCHOOL LOGO
//    // =====================================================
//
//    @Override
//    public School uploadSchoolLogo(
//            Long schoolId,
//            MultipartFile file
//    ) throws IOException {
//
//        School school =
//                schoolRepository.findById(
//                                schoolId
//                        )
//                        .orElseThrow(() ->
//                                new RuntimeException(
//                                        "School not found"
//                                )
//                        );
//
//        String fileName =
//                imageService.uploadImage(
//                        file
//                );
//
//        school.setLogo(fileName);
//
//        return schoolRepository.save(
//                school
//        );
//    }
//
//    // =====================================================
//    // DELETE COVER IMAGE
//    // =====================================================
//
//    @Override
//    public School deleteCoverImage(
//            Long schoolId,
//            String imageName
//    ) {
//
//        School school =
//                schoolRepository.findById(
//                                schoolId
//                        )
//                        .orElseThrow(() ->
//                                new RuntimeException(
//                                        "School not found"
//                                )
//                        );
//
//        List<String> images =
//                school.getCoverImages();
//
//        images.remove(imageName);
//
//        school.setCoverImages(images);
//
//        return schoolRepository.save(
//                school
//        );
//    }
//
//    // =====================================================
//    // DTO MAPPER
//    // =====================================================
//
//    private SchoolDto mapToDto(
//            School school
//    ) {
//
//        SchoolDto dto =
//                new SchoolDto();
//
//        // ================= BASIC =================
//
//        dto.setId(
//                school.getId()
//        );
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
//        // ================= CONTACT =================
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
//        // ================= ADDRESS =================
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
//        // ================= SOCIAL =================
//
//        dto.setFacebookLink(
//                school.getFacebookLink()
//        );
//
//        dto.setInstagramLink(
//                school.getInstagramLink()
//        );
//
//        dto.setYoutubeLink(
//                school.getYoutubeLink()
//        );
//
//        dto.setActive(
//                school.getActive()
//        );
//
//        dto.setCoverImages(
//                school.getCoverImages()
//        );
//
//        // =====================================================
//        // SCHOOL ADMIN DTO
//        // =====================================================
//
//        if (school.getSchoolAdmin()
//                != null) {
//
//            SchoolAdminDto adminDto =
//                    new SchoolAdminDto();
//
//            adminDto.setId(
//                    school.getSchoolAdmin()
//                            .getId()
//            );
//
//            adminDto.setName(
//                    school.getSchoolAdmin()
//                            .getName()
//            );
//
//            adminDto.setUsername(
//                    school.getSchoolAdmin()
//                            .getUsername()
//            );
//
//            adminDto.setPassword(
//                    school.getSchoolAdmin()
//                            .getPassword()
//            );
//
//            adminDto.setEmail(
//                    school.getSchoolAdmin()
//                            .getEmail()
//            );
//
//            adminDto.setPhone(
//                    school.getSchoolAdmin()
//                            .getPhone()
//            );
//
//            dto.setSchoolAdmin(
//                    adminDto
//            );
//        }
//
//        return dto;
//    }
//}