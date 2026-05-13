package com.example.stud_erp.controller;

import com.example.stud_erp.entity.Student;
import com.example.stud_erp.payload.LoginRequest;
import com.example.stud_erp.payload.LoginResponse;
import com.example.stud_erp.payload.StudentDTO;
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
    // CREATE
    // =========================================================

    @PostMapping(
            value = "/add-student",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public Student create(

            @RequestPart("student")
            Student student,

            @RequestPart(
                    value = "image",
                    required = false
            )
            MultipartFile image

    ) throws IOException {

        return studentService.createStudent(
                student,
                image
        );
    }

    // =========================================================
    // UPDATE
    // =========================================================

    @PutMapping(
            value = "/update/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public Student update(

            @PathVariable Long id,

            @RequestParam String studName,

            @RequestParam(required = false)
            String studLastName,

            @RequestParam String email,

            @RequestParam String studPhoneNumber,

            @RequestParam Long classNumber,

            @RequestParam Long studRollNo,

            @RequestPart(
                    value = "image",
                    required = false
            )
            MultipartFile image

    ) throws IOException {

        Student student = new Student();

        student.setStudName(studName);

        student.setStudLastName(
                studLastName
        );

        student.setEmail(email);

        student.setStudPhoneNumber(
                studPhoneNumber
        );

        student.setClassNumber(
                classNumber
        );

        student.setStudRollNo(
                studRollNo
        );

        return studentService.updateStudent(
                id,
                student,
                image
        );
    }

    // =========================================================
    // DELETE
    // =========================================================

    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable Long id
    ) {

        studentService.deleteStudent(id);

        return "Student deleted successfully";
    }

    // =========================================================
    // GET ALL STUDENTS BY SCHOOL
    // =========================================================

    @GetMapping("/school/{schoolId}")
    public List<StudentDTO> getAll(

            @PathVariable Long schoolId
    ) {

        return studentService.getAllStudents(
                schoolId
        );
    }

    // =========================================================
    // TOTAL COUNT
    // =========================================================

    @GetMapping("/count/{schoolId}")
    public Long getTotalStudents(

            @PathVariable Long schoolId
    ) {

        return studentService.getTotalStudents(
                schoolId
        );
    }

    // =========================================================
    // CLASS WISE
    // =========================================================

    @GetMapping("/school/{schoolId}/class/{classNumber}")
    public List<StudentDTO> getByClass(

            @PathVariable Long schoolId,

            @PathVariable Long classNumber
    ) {

        return studentService.getStudentsByClass(
                schoolId,
                classNumber
        );
    }

    // =========================================================
    // GET SINGLE
    // =========================================================

    @GetMapping("/{id}")
    public Student getById(

            @PathVariable Long id
    ) {

        return studentService
                .getStudentById(id)

                .orElseThrow(() ->
                        new RuntimeException(
                                "Student not found"
                        )
                );
    }

    // =========================================================
    // GET BY STUDENT ID
    // =========================================================

    @GetMapping("/student-id/{studentId}")
    public Student getByStudentId(

            @PathVariable String studentId
    ) {

        return studentService
                .getByStudentId(studentId)

                .orElseThrow(() ->
                        new RuntimeException(
                                "Student not found"
                        )
                );
    }

    // =========================================================
    // LOGIN
    // =========================================================

    @PostMapping("/login")
    public ResponseEntity<?> login(

            @RequestBody LoginRequest request
    ) {

        LoginResponse response =
                studentService.authenticateUser(
                        request
                );

        return ResponseEntity.ok(response);
    }

    // ================= IMAGE GET =================
    @GetMapping("/image/get/{id}")
    public ResponseEntity<Resource> getImage(
            @PathVariable Long id
    ) throws IOException {

        // ================= STUDENT =================
        Student student =
                studentService.getById(id);

        // ================= IMAGE NULL CHECK =================
        if (
                student == null ||
                        student.getImageUrl() == null
        ) {

            throw new RuntimeException(
                    "Image not found"
            );
        }

        // ================= IMAGE PATH =================
        Path path = Paths.get(
                "uploads/students/"
                        + student.getImageUrl()
        );

        // ================= FILE CHECK =================
        if (!Files.exists(path)) {

            throw new RuntimeException(
                    "File does not exist : "
                            + path
            );
        }

        // ================= RESOURCE =================
        Resource resource =
                new UrlResource(
                        path.toUri()
                );

        // ================= CONTENT TYPE =================
        String contentType =
                Files.probeContentType(path);

        if (contentType == null) {

            contentType =
                    "application/octet-stream";
        }

        // ================= RESPONSE =================
        return ResponseEntity.ok()
                .contentType(
                        MediaType.parseMediaType(
                                contentType
                        )
                )
                .header(
                        HttpHeaders.CACHE_CONTROL,
                        "no-cache, no-store, must-revalidate"
                )
                .header(
                        HttpHeaders.PRAGMA,
                        "no-cache"
                )
                .header(
                        HttpHeaders.EXPIRES,
                        "0"
                )
                .body(resource);
    }


    @GetMapping("/deleted")
    public List<StudentDTO> getDeleted(@RequestParam Long schoolId) {
        return studentService.getDeletedStudents(schoolId);
    }

    @PutMapping("/restore/{id}")
    public String restore(@PathVariable Long id) {
        studentService.restoreStudent(id);
        return "Restored Successfully";
    }


    @DeleteMapping("/permanent/{id}")
    public String permanent(@PathVariable Long id) {
        studentService.permanentDelete(id);
        return "Deleted Permanently";
    }
}