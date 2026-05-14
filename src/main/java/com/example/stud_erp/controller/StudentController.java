package com.example.stud_erp.controller;

import com.example.stud_erp.entity.Student;
import com.example.stud_erp.payload.LoginRequest;
import com.example.stud_erp.payload.LoginResponse;
import com.example.stud_erp.payload.StudentDTO;
import com.example.stud_erp.service.StudentService;

import com.example.stud_erp.service.ImageService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/students")

// ✅ CORS FIX
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
    private ImageService imageService;

    @Autowired
    private StudentService studentService;

    // ================= CREATE STUDENT =================
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

    // ================= UPDATE STUDENT =================
    @PutMapping("/{id}")
    public Student update(

            @PathVariable Long id,

            @RequestBody Student student
    ) {

        return studentService.updateStudent(id, student);
    }

    // ================= DELETE STUDENT =================
    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable Long id
    ) {

        studentService.deleteStudent(id);

        return "Student deleted successfully";
    }

    // ================= GET ALL STUDENTS BY SCHOOL =================
    @GetMapping("/school/{schoolId}")
    public List<StudentDTO> getAll(

            @PathVariable Long schoolId
    ) {

        return studentService.getAllStudents(schoolId);
    }

    // ================= GET STUDENTS BY CLASS =================
    @GetMapping("/school/{schoolId}/class/{classNumber}")
    public List<StudentDTO> getByClass(

            @PathVariable Long schoolId,

            @PathVariable int classNumber
    ) {

        return studentService.getStudentsByClass(
                schoolId,
                classNumber
        );
    }

    // ================= GET SINGLE STUDENT =================
    @GetMapping("/{id}")
    public Student getById(

            @PathVariable Long id
    ) {

        return studentService
                .getStudentById(id)

                .orElseThrow(() ->
                        new RuntimeException("Student not found")
                );
    }

    // ================= GET BY STUDENT ID =================
    @GetMapping("/student-id/{studentId}")
    public Student getByStudentId(

            @PathVariable String studentId
    ) {

        return studentService
                .getByStudentId(studentId)

                .orElseThrow(() ->
                        new RuntimeException("Student not found")
                );
    }


    // ================= LOGIN =================
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request
    ) {

        LoginResponse response =
                studentService.authenticateUser(request);

        return ResponseEntity.ok(response);
    }

    // ================= IMAGE GET =================
    @GetMapping("/image/get/{id}")
    public void getStudentImage(
            @PathVariable Long id,
            HttpServletResponse response
    ) throws Exception {

        Student student = studentService
                .getStudentById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found")
                );

        if (student.getImageUrl() == null) {

            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

            response.getWriter().write("Image not found");

            return;
        }

        String imageName = Paths
                .get(student.getImageUrl())
                .getFileName()
                .toString();

        try (
                InputStream inputStream =
                        imageService.getResource(imageName)
        ) {

            String contentType =
                    URLConnection.guessContentTypeFromName(imageName);

            response.setContentType(
                    contentType != null
                            ? contentType
                            : "application/octet-stream"
            );

            StreamUtils.copy(
                    inputStream,
                    response.getOutputStream()
            );
        }
    }


    // ================= IMAGE UPLOAD =================
    @PostMapping(
            value = "/image/upload/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public Student uploadStudentImage(

            @PathVariable Long id,

            @RequestPart("image")
            MultipartFile image

    ) throws Exception {

        Student student = studentService
                .getStudentById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found")
                );

        String imageUrl =
                imageService.uploadImage(image);

        student.setImageUrl(imageUrl);

        return studentService.updateStudent(id, student);
    }
}