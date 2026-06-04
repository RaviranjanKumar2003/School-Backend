


//=================================================================================== NEW

package com.example.stud_erp.controller;

import com.example.stud_erp.payload.LoginRequest;
import com.example.stud_erp.payload.LoginResponse;
import com.example.stud_erp.payload.StudentDto;
import com.example.stud_erp.payload.StudentPromotionRequest;
import com.example.stud_erp.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/students")

@CrossOrigin(
        origins = "*",
        allowedHeaders = "*",
        methods = {
                RequestMethod.GET,
                RequestMethod.POST,
                RequestMethod.PUT,
                RequestMethod.DELETE,
                RequestMethod.OPTIONS
        }
)

public class StudentController {

    @Autowired
    private StudentService studentService;

    // =========================================================
    // CREATE STUDENT
    // =========================================================

    @PostMapping(
            value = "/add-student",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<StudentDto> createStudent(

            @RequestPart("student")
            StudentDto studentDto,

            @RequestPart(
                    value = "image",
                    required = false
            )
            MultipartFile image

    ) throws IOException {

        StudentDto createdStudent =
                studentService.createStudent(
                        studentDto,
                        image
                );

        return ResponseEntity.ok(createdStudent);
    }

    // =========================================================
    // UPDATE STUDENT
    // =========================================================

    @PutMapping(
            value = "/update/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<StudentDto> updateStudent(

            @PathVariable Long id,

            @RequestPart("student")
            StudentDto studentDto,

            @RequestPart(
                    value = "image",
                    required = false
            )
            MultipartFile image

    ) throws IOException {

        StudentDto updatedStudent =
                studentService.updateStudent(
                        id,
                        studentDto,
                        image
                );

        return ResponseEntity.ok(updatedStudent);
    }

    // =========================================================
    // DELETE STUDENT
    // =========================================================

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(
            @PathVariable Long id
    ) {

        studentService.deleteStudent(id);

        return ResponseEntity.ok(
                "Student deleted successfully"
        );
    }

    // =========================================================
    // RESTORE STUDENT
    // =========================================================

    @PutMapping("/restore/{id}")
    public ResponseEntity<String> restoreStudent(
            @PathVariable Long id
    ) {

        studentService.restoreStudent(id);

        return ResponseEntity.ok(
                "Student restored successfully"
        );
    }

    // =========================================================
    // PERMANENT DELETE
    // =========================================================

    @DeleteMapping("/permanent/{id}")
    public ResponseEntity<String> permanentDelete(
            @PathVariable Long id
    ) {

        studentService.permanentDelete(id);

        return ResponseEntity.ok(
                "Student permanently deleted"
        );
    }

    // =========================================================
    // GET ALL STUDENTS
    // =========================================================

    @GetMapping("/school/{schoolId}")
    public ResponseEntity<List<StudentDto>> getAllStudents(

            @PathVariable Long schoolId

    ) {

        List<StudentDto> students =
                studentService.getAllStudents(
                        schoolId
                );

        return ResponseEntity.ok(students);
    }

    // =========================================================
    // GET DELETED STUDENTS
    // =========================================================

    @GetMapping("/deleted/{schoolId}")
    public ResponseEntity<List<StudentDto>> getDeletedStudents(

            @PathVariable Long schoolId

    ) {

        List<StudentDto> students =
                studentService.getDeletedStudents(
                        schoolId
                );

        return ResponseEntity.ok(students);
    }

    // =========================================================
// GET STUDENTS BY CLASS
// =========================================================

    @GetMapping("/school/{schoolId}/class/{classId}")
    public ResponseEntity<List<StudentDto>> getStudentsByClass(

            @PathVariable Long schoolId,

            @PathVariable Long classId

    ) {

        List<StudentDto> students =
                studentService.getStudentsByClass(
                        schoolId,
                        classId
                );

        return ResponseEntity.ok(students);
    }

    // =========================================================
    // GET SINGLE STUDENT
    // =========================================================

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(

            @PathVariable Long id

    ) {

        StudentDto student =
                studentService.getStudentById(id);

        return ResponseEntity.ok(student);
    }

    // =========================================================
    // GET BY STUDENT ID
    // =========================================================

    @GetMapping("/student-id/{studentId}")
    public ResponseEntity<StudentDto> getByStudentId(

            @PathVariable String studentId

    ) {

        StudentDto student =
                studentService.getByStudentId(
                        studentId
                );

        return ResponseEntity.ok(student);
    }

    // =========================================================
    // SEARCH STUDENTS
    // =========================================================

    @GetMapping("/search")
    public ResponseEntity<List<StudentDto>> searchStudents(

            @RequestParam Long schoolId,

            @RequestParam String keyword

    ) {

        List<StudentDto> students =
                studentService.searchStudents(
                        schoolId,
                        keyword
                );

        return ResponseEntity.ok(students);
    }

    // =========================================================
    // TOTAL STUDENTS
    // =========================================================

    @GetMapping("/count/{schoolId}")
    public ResponseEntity<Long> getTotalStudents(

            @PathVariable Long schoolId

    ) {

        Long total =
                studentService.getTotalStudents(
                        schoolId
                );

        return ResponseEntity.ok(total);
    }

    // =========================================================
    // TOTAL ACTIVE STUDENTS
    // =========================================================

    @GetMapping("/count/active/{schoolId}")
    public ResponseEntity<Long> getTotalActiveStudents(

            @PathVariable Long schoolId

    ) {

        Long total =
                studentService.getTotalActiveStudents(
                        schoolId
                );

        return ResponseEntity.ok(total);
    }

    // =========================================================
    // TOTAL DELETED STUDENTS
    // =========================================================

    @GetMapping("/count/deleted/{schoolId}")
    public ResponseEntity<Long> getTotalDeletedStudents(

            @PathVariable Long schoolId

    ) {

        Long total =
                studentService.getTotalDeletedStudents(
                        schoolId
                );

        return ResponseEntity.ok(total);
    }

    // =========================================================
    // UPDATE STATUS
    // =========================================================

    @PutMapping("/status/{studentId}")
    public ResponseEntity<String> updateStatus(

            @PathVariable Long studentId,

            @RequestParam String status

    ) {

        studentService.updateStudentStatus(
                studentId,
                status
        );

        return ResponseEntity.ok(
                "Student status updated successfully"
        );
    }

    // =========================================================
    // LOGIN
    // =========================================================

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(

            @RequestBody LoginRequest request

    ) {

        LoginResponse response =
                studentService.authenticateUser(
                        request
                );

        return ResponseEntity.ok(response);
    }

    // =========================================================
    // GET PROFILE IMAGE
    // =========================================================

    @GetMapping("/image/get/{id}")
    public ResponseEntity<Resource> getProfileImage(

            @PathVariable Long id

    ) throws IOException {

        StudentDto student =
                studentService.getStudentById(id);

        if (
                student == null ||
                        student.getProfileImage() == null ||
                        student.getProfileImage().isEmpty()
        ) {

            return ResponseEntity.notFound().build();
        }

        // ✅ FIXED PATH
        Path path = Paths.get(
                System.getProperty("user.dir"),
                "images",
                student.getProfileImage()
        );

        System.out.println("IMAGE PATH : " + path);

        System.out.println(
                "FILE EXISTS : " +
                        Files.exists(path)
        );

        if (!Files.exists(path)) {

            return ResponseEntity.notFound().build();
        }

        Resource resource =
                new UrlResource(path.toUri());

        String contentType =
                Files.probeContentType(path);

        if (contentType == null) {

            contentType =
                    "application/octet-stream";
        }

        return ResponseEntity.ok()

                .contentType(
                        MediaType.parseMediaType(
                                contentType
                        )
                )

                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "inline; fileName=\"" +
                                resource.getFilename() + "\""
                )

                .body(resource);
    }

    // =========================================================
    // GET QR CODE IMAGE
    // =========================================================

    @GetMapping("/qrcode/{studentId}")
    public ResponseEntity<Resource> getQrCode(

            @PathVariable String studentId

    ) throws IOException {

        StudentDto student =
                studentService.getByStudentId(
                        studentId
                );

        if (
                student == null ||
                        student.getQrCodeUrl() == null
        ) {

            throw new RuntimeException(
                    "QR Code not found"
            );
        }

        Path path = Paths.get(
                student.getQrCodeUrl()
        );

        if (!Files.exists(path)) {

            throw new RuntimeException(
                    "QR file not found"
            );
        }

        Resource resource =
                new UrlResource(
                        path.toUri()
                );

        return ResponseEntity.ok()

                .contentType(
                        MediaType.IMAGE_PNG
                )

                .body(resource);
    }

    // =========================================================
    // QR SCAN STUDENT DETAILS
    // =========================================================

    @GetMapping("/scan/{qrCode}")
    public ResponseEntity<StudentDto> scanQrCode(

            @PathVariable String qrCode

    ) {

        StudentDto student =
                studentService.getStudentByQrCode(
                        qrCode
                );

        return ResponseEntity.ok(student);
    }


    // =========================================================
// PROMOTE STUDENTS
// =========================================================

    @PostMapping("/promote")
    public ResponseEntity<?> promoteStudents(

            @RequestBody StudentPromotionRequest req

    ) {

        studentService.promoteStudents(req);

        return ResponseEntity.ok(
                "Students promoted successfully"
        );
    }


    // =========================================================
// TOTAL BOYS
// =========================================================

    @GetMapping("/count/boys/{schoolId}")
    public ResponseEntity<Long> getTotalBoys(

            @PathVariable Long schoolId

    ) {

        Long total =
                studentService.getTotalBoys(
                        schoolId
                );

        return ResponseEntity.ok(total);
    }

// =========================================================
// TOTAL GIRLS
// =========================================================

    @GetMapping("/count/girls/{schoolId}")
    public ResponseEntity<Long> getTotalGirls(

            @PathVariable Long schoolId

    ) {

        Long total =
                studentService.getTotalGirls(
                        schoolId
                );

        return ResponseEntity.ok(total);
    }

    @PostMapping(
            value = "/image/upload/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<?> uploadStudentImage(

            @PathVariable Long id,

            @RequestParam("file")
            MultipartFile file

    ) throws Exception {

        StudentDto student =
                studentService.uploadProfileImage(
                        id,
                        file
                );

        return ResponseEntity.ok(student);
    }
}