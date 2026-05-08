<<<<<<< HEAD
=======
//package com.example.stud_erp.controller;
//
//import com.example.stud_erp.entity.Student;
//import com.example.stud_erp.payload.LoginRequest;
//import com.example.stud_erp.payload.StudentDTO;
//import com.example.stud_erp.service.ImageService;
//import com.example.stud_erp.service.StudentService;
//
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.StreamUtils;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.InputStream;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/api/students")
//@CrossOrigin(origins = "*")
//public class StudentController {
//
//    @Value("${project.image}")
//    private String path;
//
//    @Autowired
//    private ImageService imageService;
//
//    @Autowired
//    private StudentService studentService;
//
//
//
//// ✅ CREATE STUDENT (FINAL FIXED)
//@PostMapping("/add-student")
//public ResponseEntity<?> createStudent(
//        @ModelAttribute Student student,
//        @RequestParam(value = "image", required = false) MultipartFile image
//) {
//    try {
//
//        // ✅ VALIDATION
//        if (student.getStudName() == null || student.getStudName().isEmpty()) {
//            return ResponseEntity.badRequest().body("Student Name is required");
//        }
//
//        if (student.getClassNumber() <= 0) {
//            return ResponseEntity.badRequest().body("Class is required");
//        }
//
//        if (student.getEmail() == null || student.getEmail().isEmpty()) {
//            return ResponseEntity.badRequest().body("Email required");
//        }
//
//        // ✅ DUPLICATE CHECK (🔥 IMPORTANT)
//        if (studentService.existsByEmail(student.getEmail())) {
//            return ResponseEntity.badRequest().body("Email already exists");
//        }
//
//        // ✅ IMAGE UPLOAD
//        if (image != null && !image.isEmpty()) {
//            String fileName = imageService.uploadImage(image);
//            student.setImageUrl(fileName);
//        }
//
//        Student savedStudent = studentService.addStudent(student);
//
//        return ResponseEntity.ok(savedStudent);
//
//    } catch (Exception e) {
//        e.printStackTrace();
//        return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
//    }
//}
//
//
//// ✅ GET ALL
//    @GetMapping
//    public List<StudentDTO> getAllStudents() {
//        return studentService.findAll();
//    }
//
//
//// ✅ GET BY STUDENT ID
//    @GetMapping("/by-studentId/{studentId}")
//    public ResponseEntity<?> getStudentByStudentId(@PathVariable String studentId) {
//
//        Optional<Student> student = studentService.getByStudentId(studentId);
//
//        return student.map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//
//
//// ✅ GET BY DB ID
//    @GetMapping("/by-id/{id}")
//    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
//
//        Optional<Student> student = studentService.getById(id);
//
//        return student.map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//
//// ✅ GET BY CLASS (🔥 IMPORTANT)
//    @GetMapping("/class/{classNumber}")
//    public ResponseEntity<?> getStudentsByClass(@PathVariable int classNumber) {
//        return ResponseEntity.ok(studentService.getStudentsByClass(classNumber));
//    }
//
//
//
//// ✅ UPDATE
//@PutMapping("/update/{id}")
//public ResponseEntity<?> updateStudent(
//        @PathVariable Long id,
//        @RequestParam String studName,
//        @RequestParam String email,
//        @RequestParam String phone,
//        @RequestParam int classNumber,
//        @RequestParam Long rollNo,
//        @RequestParam(required = false) MultipartFile image
//) {
//    try {
//
//        if (studName == null || studName.isEmpty()) {
//            return ResponseEntity.badRequest().body("Name required");
//        }
//
//        if (classNumber <= 0) {
//            return ResponseEntity.badRequest().body("Invalid class number");
//        }
//
//        if (rollNo <= 0) {
//            return ResponseEntity.badRequest().body("Invalid roll number");
//        }
//
//        Student student = studentService.getById(id)
//                .orElseThrow(() -> new RuntimeException("Student not found"));
//
//        student.setStudName(studName);
//        student.setEmail(email);
//        student.setStudPhoneNumber(phone);
//        student.setClassNumber(classNumber);
//        student.setStudRollNo(rollNo);
//
//        // ✅ IMAGE UPDATE
//        if (image != null && !image.isEmpty()) {
//
//            if (student.getImageUrl() != null) {
//                imageService.deleteImage(student.getImageUrl());
//            }
//
//            String fileName = imageService.uploadImage(image);
//            student.setImageUrl(fileName);
//        }
//
//        // 🔥 FINAL FIX HERE
//        return ResponseEntity.ok(studentService.updateStudent(id, student));
//
//    } catch (Exception e) {
//        e.printStackTrace();
//        return ResponseEntity.internalServerError().body("Update failed: " + e.getMessage());
//    }
//}
//
//    // ===============================
//    // ✅ DELETE
//    // ===============================
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
//
//        studentService.deleteStudent(id);
//
//        return ResponseEntity.ok("Student deleted");
//    }
//
//
//// ✅ LOGIN
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
//
//        Student student = studentService.authenticateUser(request);
//
//        return ResponseEntity.ok(student);
//    }
//
//
//    @GetMapping("/deleted")
//    public List<StudentDTO> getDeletedStudents() {
//        return studentService.getDeletedStudents();
//    }
//
//// ✅ RESTORE
//    @PutMapping("/restore/{id}")
//    public String restoreStudent(@PathVariable Long id) {
//        studentService.restoreStudent(id);
//        return "Student restored successfully";
//    }
//
//// ❌ PERMANENT DELETE
//    @DeleteMapping("/permanent/{id}")
//    public String deletePermanently(@PathVariable Long id) {
//        studentService.deletePermanently(id);
//        return "Student permanently deleted";
//    }
//
////=============================================== IMAGE =================================
//
//    @PostMapping("/image/upload/{id}")
//    public ResponseEntity<?> uploadStudentImage(
//            @PathVariable Long id,
//            @RequestParam("image") MultipartFile image
//    ) {
//        try {
//
//            Student student = studentService.getById(id)
//                    .orElseThrow(() -> new RuntimeException("Student not found"));
//
//            if (student.getImageUrl() != null) {
//                imageService.deleteImage(student.getImageUrl());
//            }
//
//            String fileName = imageService.uploadImage(image);
//            student.setImageUrl(fileName);
//
//            return ResponseEntity.ok(studentService.addStudent(student));
//
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().body("Image upload failed");
//        }
//    }
//
//
//    @GetMapping("/image/get/{id}")
//    public void getStudentImage(
//            @PathVariable Long id,
//            HttpServletResponse response
//    ) throws Exception {
//
//        Student student = studentService.getById(id)
//                .orElseThrow(() -> new RuntimeException("Student not found"));
//
//        if (student.getImageUrl() == null) {
//            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//            return;
//        }
//
//        InputStream is = imageService.getResource(student.getImageUrl());
//
//        response.setContentType("image/jpeg");
//
//        StreamUtils.copy(is, response.getOutputStream());
//    }
//
//}





// upar sahi testing



>>>>>>> 5bf6a9a (work done)
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

<<<<<<< HEAD
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
=======
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
>>>>>>> 5bf6a9a (work done)

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

<<<<<<< HEAD
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
=======
    // ============================================================
    // 🔥 NEW API (IMPORTANT FOR ATTENDANCE FRONTEND)
    // ============================================================

    // ✅ GET STUDENTS BY CLASS (CLEAN RESPONSE FOR ATTENDANCE)
    @GetMapping("/attendance/{classNumber}")
    public List<StudentDTO> getStudentsForAttendance(@PathVariable int classNumber) {
        return studentService.getStudentsByClass(classNumber);
    }

    // ============================================================
    // ✅ CREATE STUDENT
    // ============================================================

    @PostMapping("/add-student")
    public ResponseEntity<?> createStudent(
            @ModelAttribute Student student,
            @RequestParam(value = "image", required = false) MultipartFile image
    ) {
        try {

            if (student.getStudName() == null || student.getStudName().isEmpty()) {
                return ResponseEntity.badRequest().body("Student Name is required");
            }
>>>>>>> 5bf6a9a (work done)

    ) throws IOException {

<<<<<<< HEAD
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

=======
            if (student.getEmail() == null || student.getEmail().isEmpty()) {
                return ResponseEntity.badRequest().body("Email required");
            }

            if (studentService.existsByEmail(student.getEmail())) {
                return ResponseEntity.badRequest().body("Email already exists");
            }

            if (image != null && !image.isEmpty()) {
                String fileName = imageService.uploadImage(image);
                student.setImageUrl(fileName);
            }

            Student savedStudent = studentService.addStudent(student);

            return ResponseEntity.ok(savedStudent);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    // ============================================================
    // ✅ GET ALL
    // ============================================================

    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return studentService.findAll();
    }

    // ============================================================
    // ✅ GET BY STUDENT ID
    // ============================================================

    @GetMapping("/by-studentId/{studentId}")
    public ResponseEntity<?> getStudentByStudentId(@PathVariable String studentId) {

        Optional<Student> student = studentService.getByStudentId(studentId);

        return student.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ============================================================
    // ✅ GET BY DB ID
    // ============================================================

    @GetMapping("/by-id/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {

        Optional<Student> student = studentService.getById(id);

        return student.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ============================================================
    // ✅ GET BY CLASS
    // ============================================================

    @GetMapping("/class/{classNumber}")
    public ResponseEntity<?> getStudentsByClass(@PathVariable int classNumber) {
        return ResponseEntity.ok(studentService.getStudentsByClass(classNumber));
    }

    // ============================================================
    // ✅ UPDATE
    // ============================================================

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStudent(
>>>>>>> 5bf6a9a (work done)
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

<<<<<<< HEAD
        Student student = new Student();
=======
            if (image != null && !image.isEmpty()) {
>>>>>>> 5bf6a9a (work done)

        student.setStudName(studName);

        student.setStudLastName(
                studLastName
        );

<<<<<<< HEAD
        student.setEmail(email);
=======
            return ResponseEntity.ok(studentService.updateStudent(id, student));
>>>>>>> 5bf6a9a (work done)

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

<<<<<<< HEAD
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
=======
    // ============================================================
    // ✅ DELETE
    // ============================================================

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted");
    }

    // ============================================================
    // ✅ LOGIN
    // ============================================================

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Student student = studentService.authenticateUser(request);
        return ResponseEntity.ok(student);
>>>>>>> 5bf6a9a (work done)
    }

    // ============================================================
    // ✅ SOFT DELETE FEATURES
    // ============================================================

    @GetMapping("/deleted")
    public List<StudentDTO> getDeleted(@RequestParam Long schoolId) {
        return studentService.getDeletedStudents(schoolId);
    }

    @PutMapping("/restore/{id}")
    public String restore(@PathVariable Long id) {
        studentService.restoreStudent(id);
        return "Restored Successfully";
    }

<<<<<<< HEAD

=======
>>>>>>> 5bf6a9a (work done)
    @DeleteMapping("/permanent/{id}")
    public String permanent(@PathVariable Long id) {
        studentService.permanentDelete(id);
        return "Deleted Permanently";
    }
<<<<<<< HEAD
=======

    // ============================================================
    // 🖼 IMAGE APIs
    // ============================================================

    @PostMapping("/image/upload/{id}")
    public ResponseEntity<?> uploadStudentImage(
            @PathVariable Long id,
            @RequestParam("image") MultipartFile image
    ) {
        try {

            Student student = studentService.getById(id)
                    .orElseThrow(() -> new RuntimeException("Student not found"));

            if (student.getImageUrl() != null) {
                imageService.deleteImage(student.getImageUrl());
            }

            String fileName = imageService.uploadImage(image);
            student.setImageUrl(fileName);

            return ResponseEntity.ok(studentService.updateStudent(id, student));

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Image upload failed");
        }
    }

    @GetMapping("/image/get/{id}")
    public void getStudentImage(
            @PathVariable Long id,
            HttpServletResponse response
    ) throws Exception {

        Student student = studentService.getById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (student.getImageUrl() == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        InputStream is = imageService.getResource(student.getImageUrl());

        response.setContentType("image/jpeg");

        StreamUtils.copy(is, response.getOutputStream());
    }

    // ============================================================
    // ✅ CLASS + ROLL
    // ============================================================

    @GetMapping("/class/{classNumber}/roll/{rollNo}")
    public ResponseEntity<?> getStudentByClassAndRoll(
            @PathVariable int classNumber,
            @PathVariable Long rollNo
    ) {
        try {

            Optional<Student> student = studentService
                    .findByClassNumberAndStudRollNo(classNumber, rollNo);

            if (student.isPresent()) {
                return ResponseEntity.ok(student.get());
            } else {
                return ResponseEntity.status(404).body("Student not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error fetching student");
        }
    }
>>>>>>> 5bf6a9a (work done)
}