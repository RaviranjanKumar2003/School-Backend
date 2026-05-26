package com.example.stud_erp.service;

import com.example.stud_erp.entity.School;
import com.example.stud_erp.payload.SchoolDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SchoolService {

    SchoolDto createSchool(SchoolDto dto);

    SchoolDto updateSchool(Long id, SchoolDto dto);

    void deleteSchool(Long id);

    SchoolDto getSchoolById(Long id);

    List<SchoolDto> getAllSchools();

    School uploadCoverImages(
            Long schoolId,
            List<MultipartFile> images
    ) throws IOException;
}



//package com.example.stud_erp.service;
//
//import com.example.stud_erp.entity.School;
//import com.example.stud_erp.payload.SchoolDto;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.List;
//
//public interface SchoolService {
//
//    // =====================================================
//    // CREATE SCHOOL
//    // =====================================================
//
//    SchoolDto createSchool(
//            SchoolDto dto
//    );
//
//    // =====================================================
//    // UPDATE SCHOOL
//    // =====================================================
//
//    SchoolDto updateSchool(
//            Long id,
//            SchoolDto dto
//    );
//
//    // =====================================================
//    // DELETE SCHOOL
//    // =====================================================
//
//    void deleteSchool(
//            Long id
//    );
//
//    // =====================================================
//    // GET SCHOOL BY ID
//    // =====================================================
//
//    SchoolDto getSchoolById(
//            Long id
//    );
//
//    // =====================================================
//    // GET SCHOOL BY SLUG
//    // =====================================================
//
//    SchoolDto getSchoolBySlug(
//            String slug
//    );
//
//    // =====================================================
//    // GET SCHOOL BY SCHOOL CODE
//    // =====================================================
//
//    SchoolDto getSchoolBySchoolCode(
//            String schoolCode
//    );
//
//    // =====================================================
//    // GET ALL SCHOOLS
//    // =====================================================
//
//    List<SchoolDto> getAllSchools();
//
//    // =====================================================
//    // SEARCH SCHOOLS
//    // =====================================================
//
//    List<SchoolDto> searchSchools(
//            String keyword
//    );
//
//    // =====================================================
//    // UPLOAD MULTIPLE COVER IMAGES
//    // =====================================================
//
//    School uploadCoverImages(
//            Long schoolId,
//            List<MultipartFile> images
//    ) throws IOException;
//
//    // =====================================================
//    // UPLOAD SCHOOL LOGO
//    // =====================================================
//
//    School uploadSchoolLogo(
//            Long schoolId,
//            MultipartFile file
//    ) throws IOException;
//
//    // =====================================================
//    // DELETE COVER IMAGE
//    // =====================================================
//
//    School deleteCoverImage(
//            Long schoolId,
//            String imageName
//    );
//
//    // =====================================================
//    // ACTIVATE SCHOOL
//    // =====================================================
//
//    SchoolDto activateSchool(
//            Long schoolId
//    );
//
//    // =====================================================
//    // DEACTIVATE SCHOOL
//    // =====================================================
//
//    SchoolDto deactivateSchool(
//            Long schoolId
//    );
//}