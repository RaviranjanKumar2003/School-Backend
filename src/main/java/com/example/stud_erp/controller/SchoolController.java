package com.example.stud_erp.controller;

import com.example.stud_erp.entity.School;
import com.example.stud_erp.payload.SchoolDto;
import com.example.stud_erp.service.ImageService;
import com.example.stud_erp.service.SchoolService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.List;

@RestController
@RequestMapping("/api/schools")
@CrossOrigin("*")
public class SchoolController {

    private final SchoolService schoolService;

    @Autowired
    private ImageService imageService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    // ================= CREATE SCHOOL + SCHOOL ADMIN =================
    @PostMapping
    public SchoolDto createSchool(
            @RequestBody SchoolDto dto
    ) {

        return schoolService.createSchool(dto);
    }

    // ================= UPDATE SCHOOL =================
    @PutMapping("/{id}")
    public SchoolDto updateSchool(
            @PathVariable Long id,
            @RequestBody SchoolDto dto
    ) {

        return schoolService.updateSchool(id, dto);
    }

    // ================= DELETE SCHOOL =================
    @DeleteMapping("/{id}")
    public String deleteSchool(
            @PathVariable Long id
    ) {

        schoolService.deleteSchool(id);

        return "School deleted successfully";
    }

    // ================= GET SCHOOL BY ID =================
    @GetMapping("/{id}")
    public SchoolDto getSchoolById(
            @PathVariable Long id
    ) {

        return schoolService.getSchoolById(id);
    }

    // ================= GET ALL SCHOOLS =================
    @GetMapping
    public List<SchoolDto> getAllSchools() {

        return schoolService.getAllSchools();
    }


//======================================================================================= COVER IMAGE

    @PostMapping("/cover/upload-multiple/{schoolId}")
    public ResponseEntity<?> uploadMultipleCoverImages(

            @PathVariable Long schoolId,

            @RequestParam("images")
            List<MultipartFile> images

    ) {

        try {

            School updated =
                    schoolService.uploadCoverImages(
                            schoolId,
                            images
                    );

            return ResponseEntity.ok(updated);

        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }


    @GetMapping("/cover/get-file/{fileName}")
    public void getCoverImage(

            @PathVariable String fileName,

            HttpServletResponse response

    ) throws IOException {

        InputStream resource =
                imageService.getResource(fileName);

        String contentType =
                URLConnection.guessContentTypeFromName(fileName);

        response.setContentType(
                contentType != null
                        ? contentType
                        : "application/octet-stream"
        );

        StreamUtils.copy(
                resource,
                response.getOutputStream()
        );
    }
}



//package com.example.stud_erp.controller;
//
//import com.example.stud_erp.entity.School;
//import com.example.stud_erp.payload.SchoolDto;
//import com.example.stud_erp.service.ImageService;
//import com.example.stud_erp.service.SchoolService;
//
//import jakarta.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.StreamUtils;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URLConnection;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/schools")
//@CrossOrigin("*")
//public class SchoolController {
//
//    // =====================================================
//    // SERVICES
//    // =====================================================
//
//    private final SchoolService schoolService;
//
//    @Autowired
//    private ImageService imageService;
//
//    // =====================================================
//    // CONSTRUCTOR
//    // =====================================================
//
//    public SchoolController(
//            SchoolService schoolService
//    ) {
//
//        this.schoolService =
//                schoolService;
//    }
//
//    // =====================================================
//    // CREATE SCHOOL
//    // =====================================================
//
//    @PostMapping
//    public SchoolDto createSchool(
//            @RequestBody SchoolDto dto
//    ) {
//
//        return schoolService
//                .createSchool(dto);
//    }
//
//    // =====================================================
//    // UPDATE SCHOOL
//    // =====================================================
//
//    @PutMapping("/{id}")
//    public SchoolDto updateSchool(
//
//            @PathVariable Long id,
//
//            @RequestBody SchoolDto dto
//
//    ) {
//
//        return schoolService
//                .updateSchool(
//                        id,
//                        dto
//                );
//    }
//
//    // =====================================================
//    // DELETE SCHOOL
//    // =====================================================
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteSchool(
//            @PathVariable Long id
//    ) {
//
//        schoolService.deleteSchool(id);
//
//        return ResponseEntity.ok(
//                "School deleted successfully"
//        );
//    }
//
//    // =====================================================
//    // GET SCHOOL BY ID
//    // =====================================================
//
//    @GetMapping("/{id}")
//    public SchoolDto getSchoolById(
//            @PathVariable Long id
//    ) {
//
//        return schoolService
//                .getSchoolById(id);
//    }
//
//    // =====================================================
//    // GET SCHOOL BY SLUG
//    // =====================================================
//
//    @GetMapping("/slug/{slug}")
//    public SchoolDto getSchoolBySlug(
//            @PathVariable String slug
//    ) {
//
//        return schoolService
//                .getSchoolBySlug(slug);
//    }
//
//    // =====================================================
//    // GET SCHOOL BY SCHOOL CODE
//    // =====================================================
//
//    @GetMapping("/code/{schoolCode}")
//    public SchoolDto getSchoolBySchoolCode(
//            @PathVariable String schoolCode
//    ) {
//
//        return schoolService
//                .getSchoolBySchoolCode(
//                        schoolCode
//                );
//    }
//
//    // =====================================================
//    // GET ALL SCHOOLS
//    // =====================================================
//
//    @GetMapping
//    public List<SchoolDto>
//    getAllSchools() {
//
//        return schoolService
//                .getAllSchools();
//    }
//
//    // =====================================================
//    // SEARCH SCHOOLS
//    // =====================================================
//
//    @GetMapping("/search")
//    public List<SchoolDto> searchSchools(
//
//            @RequestParam String keyword
//
//    ) {
//
//        return schoolService
//                .searchSchools(keyword);
//    }
//
//    // =====================================================
//    // ACTIVATE SCHOOL
//    // =====================================================
//
//    @PutMapping("/activate/{schoolId}")
//    public SchoolDto activateSchool(
//
//            @PathVariable Long schoolId
//
//    ) {
//
//        return schoolService
//                .activateSchool(
//                        schoolId
//                );
//    }
//
//    // =====================================================
//    // DEACTIVATE SCHOOL
//    // =====================================================
//
//    @PutMapping("/deactivate/{schoolId}")
//    public SchoolDto deactivateSchool(
//
//            @PathVariable Long schoolId
//
//    ) {
//
//        return schoolService
//                .deactivateSchool(
//                        schoolId
//                );
//    }
//
//    // =====================================================
//    // UPLOAD MULTIPLE COVER IMAGES
//    // =====================================================
//
//    @PostMapping(
//            "/cover/upload-multiple/{schoolId}"
//    )
//
//    public ResponseEntity<?> uploadMultipleCoverImages(
//
//            @PathVariable Long schoolId,
//
//            @RequestParam("images")
//            List<MultipartFile> images
//
//    ) {
//
//        try {
//
//            School updatedSchool =
//                    schoolService
//                            .uploadCoverImages(
//                                    schoolId,
//                                    images
//                            );
//
//            return ResponseEntity.ok(
//                    updatedSchool
//            );
//
//        } catch (Exception e) {
//
//            return ResponseEntity
//                    .status(
//                            HttpStatus.BAD_REQUEST
//                    )
//                    .body(
//                            e.getMessage()
//                    );
//        }
//    }
//
//    // =====================================================
//    // UPLOAD SCHOOL LOGO
//    // =====================================================
//
//    @PostMapping(
//            "/logo/upload/{schoolId}"
//    )
//
//    public ResponseEntity<?> uploadSchoolLogo(
//
//            @PathVariable Long schoolId,
//
//            @RequestParam("file")
//            MultipartFile file
//
//    ) {
//
//        try {
//
//            School updatedSchool =
//                    schoolService
//                            .uploadSchoolLogo(
//                                    schoolId,
//                                    file
//                            );
//
//            return ResponseEntity.ok(
//                    updatedSchool
//            );
//
//        } catch (Exception e) {
//
//            return ResponseEntity
//                    .status(
//                            HttpStatus.BAD_REQUEST
//                    )
//                    .body(
//                            e.getMessage()
//                    );
//        }
//    }
//
//    // =====================================================
//    // DELETE COVER IMAGE
//    // =====================================================
//
//    @DeleteMapping(
//            "/cover/delete/{schoolId}"
//    )
//
//    public ResponseEntity<?> deleteCoverImage(
//
//            @PathVariable Long schoolId,
//
//            @RequestParam String imageName
//
//    ) {
//
//        try {
//
//            School updatedSchool =
//                    schoolService
//                            .deleteCoverImage(
//                                    schoolId,
//                                    imageName
//                            );
//
//            return ResponseEntity.ok(
//                    updatedSchool
//            );
//
//        } catch (Exception e) {
//
//            return ResponseEntity
//                    .status(
//                            HttpStatus.BAD_REQUEST
//                    )
//                    .body(
//                            e.getMessage()
//                    );
//        }
//    }
//
//    // =====================================================
//    // GET IMAGE FILE
//    // =====================================================
//
//    @GetMapping(
//            "/image/get-file/{fileName}"
//    )
//
//    public void getImage(
//
//            @PathVariable String fileName,
//
//            HttpServletResponse response
//
//    ) throws IOException {
//
//        InputStream resource =
//                imageService
//                        .getResource(fileName);
//
//        String contentType =
//                URLConnection
//                        .guessContentTypeFromName(
//                                fileName
//                        );
//
//        response.setContentType(
//
//                contentType != null
//                        ? contentType
//                        : "application/octet-stream"
//        );
//
//        StreamUtils.copy(
//                resource,
//                response.getOutputStream()
//        );
//    }
//}