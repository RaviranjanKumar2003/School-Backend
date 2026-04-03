//package com.example.stud_erp.controller;
//
//import com.example.stud_erp.entity.Professor;
//import com.example.stud_erp.exception.OTPExpiredException;
//import com.example.stud_erp.payload.ForgotPasswordRequest;
//import com.example.stud_erp.payload.LoginRequest;
//import com.example.stud_erp.payload.ResetPasswordRequest;
//import com.example.stud_erp.service.ImageService;
//import com.example.stud_erp.service.ProfessorService;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/professors")
//public class ProfessorController {
//
//    @Autowired
//    private ProfessorService professorService;
//
//    @Autowired
//    private ImageService imageService;
//
////    @PostMapping("/add-prof")
////    public ResponseEntity<Professor> createProfessor(@RequestBody Professor professor) {
////        Professor savedProfessor = professorService.saveProfessor(professor);
////        return ResponseEntity.ok(savedProfessor);
////    }
//
//    @PostMapping("/add-prof")
//    public ResponseEntity<String> createProfessor(@RequestParam("file") MultipartFile multipartFile,
//                                                  @RequestParam("professorId") String professorId,
//                                                  @RequestParam("name") String name,
//                                                  @RequestParam("username") String username,
//                                                  @RequestParam("password") String password,
//                                                  @RequestParam("email") String email,
//                                                  @RequestParam("subject") String subject,
//                                                  @RequestParam("departmentName") String departmentName) {
//        try {
//            Professor professor = new Professor();
//            professor.setProfessorId(professorId);
//            professor.setName(name);
//            professor.setUsername(username);
//            professor.setPassword(password);
//            professor.setEmail(email);
//            professor.setSubject(subject);
//            professor.setDepartmentName(departmentName);
//
//
//            // Handle the image upload
//            String imageUrl = imageService.uploadProfData(multipartFile, professor);
//            professor.setImageUrl(imageUrl);
//
//            Professor savedProfessor = professorService.saveProfessor(professor);
//            return ResponseEntity.ok("Professor data successfully uploaded");
//        } catch (DataIntegrityViolationException e) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("A professor with the same ID, Username, or Email already exists.");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading professor data");
//        }
//    }
//
//
//    @GetMapping("/get-prof")
//    public ResponseEntity<List<Professor>> getAllProfessors() {
//        List<Professor> professors = professorService.getAllProfessors();
//        return ResponseEntity.ok(professors);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Professor> getProfessorById(@PathVariable String id) {
//        Professor professor = professorService.getProfessorById(id);
//        return professor != null ? ResponseEntity.ok(professor) : ResponseEntity.notFound().build();
//    }
//
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteProfessor(@PathVariable Long id) {
//        professorService.deleteProfessor(id);
//        return ResponseEntity.noContent().build();
//    }
//
//
//    @PostMapping("/login")
//    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
//        try {
//            Professor authenticatedUser = professorService.authenticateUser(loginRequest);
//            return ResponseEntity.ok(authenticatedUser);
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed: " + ex.getMessage());
//        }
//    }
//
//    @PostMapping("/forgot-password")
//    public ResponseEntity<?> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {
//        try {
//            professorService.sendForgotPasswordEmail(request.getEmail());
//            return ResponseEntity.ok("OTP sent to your email successfully");
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
//        }
//    }
//
//    @PostMapping("/verify-otp")
//    public ResponseEntity<?> verifyOTP(@RequestParam String email, @RequestParam String otp) {
//        try {
//            professorService.verifyOTP(email, otp);
//            return ResponseEntity.ok("OTP verified successfully");
//        } catch (OTPExpiredException ex) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("OTP verification failed: " + ex.getMessage());
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
//        }
//    }
//
//    @PostMapping("/reset-password")
//    public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPasswordRequest request) {
//        try {
//            professorService.resetPassword(request);
//            return ResponseEntity.ok("Password reset successfully");
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
//        }
//    }
//}



// after upadte




package com.example.stud_erp.controller;

import com.example.stud_erp.entity.Professor;
import com.example.stud_erp.payload.LoginRequest;
import com.example.stud_erp.repository.ProfessorRepository;
import com.example.stud_erp.service.ImageService;
import com.example.stud_erp.service.ProfessorService;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Paths;
import java.util.*;
import java.io.IOException;

@RestController
@RequestMapping("/api/professors")
@CrossOrigin
public class ProfessorController {

    @Value("${project.image}")
    private String path;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private ImageService imageService;

    // ==============================
    // CREATE TEACHER
    // ==============================

    @PostMapping("/createTeacher")
    public Map<String, String> createTeacher(

            @RequestParam("schoolCode") String schoolCode,
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("department") String department,
            @RequestParam("subject") String subject,
            @RequestParam("designation") String designation,
            @RequestParam("qualification") String qualification,
            @RequestParam("experience") String experience,
            @RequestParam("joiningDate") String joiningDate,
            @RequestParam(value = "image", required = false) MultipartFile image

    ) throws IOException {

        Professor professor = new Professor();

        String professorId = schoolCode + "-" + System.currentTimeMillis();
        String username = name.toLowerCase().replaceAll(" ", "");
        String password = UUID.randomUUID().toString().substring(0, 8);

        professor.setProfessorId(professorId);
        professor.setSchoolCode(schoolCode);
        professor.setName(name);
        professor.setEmail(email);
        professor.setPhone(phone);
        professor.setDepartmentName(department);
        professor.setSubject(subject);
        professor.setDesignation(designation);
        professor.setQualification(qualification);
        professor.setExperience(experience);
        professor.setJoiningDate(joiningDate);
        professor.setUsername(username);
        professor.setPassword(password);

        if (image != null && !image.isEmpty()) {
            String fileName = imageService.uploadImage(image);
            professor.setImageUrl(fileName);
        }

        professorRepository.save(professor);

        Map<String, String> response = new HashMap<>();
        response.put("username", username);
        response.put("password", password);

        return response;
    }

    // ==============================
    // GET ALL TEACHERS
    // ==============================

    @GetMapping
    public List<Professor> getAllTeachers() {
        return professorRepository.findAll();
    }

    // ==============================
    // GET TEACHER BY ID
    // ==============================

    @GetMapping("/{id}")
    public Professor getTeacherById(@PathVariable Long id) {

        return professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
    }

    // ==============================
    // UPDATE TEACHER
    // ==============================

    @PutMapping("/update/{id}")
    public Professor updateTeacher(
            @PathVariable Long id,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("department") String department,
            @RequestParam("subject") String subject
    ) throws IOException {

        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        professor.setName(name);
        professor.setEmail(email);
        professor.setPhone(phone);
        professor.setDepartmentName(department);
        professor.setSubject(subject);

        // image update
        if (image != null && !image.isEmpty()) {

            if (professor.getImageUrl() != null) {
                imageService.deleteImage(professor.getImageUrl());
            }

            String fileName = imageService.uploadImage(image);
            professor.setImageUrl(fileName);
        }

        return professorRepository.save(professor);
    }

    // ==============================
    // DELETE TEACHER
    // ==============================

    @DeleteMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable Long id) {

        professorRepository.deleteById(id);

        return "Teacher deleted successfully";
    }

    // ==============================
    // TEACHER LOGIN
    // ==============================

    @PostMapping("/login")
    public Professor login(@RequestBody LoginRequest loginRequest) {

        return professorService.authenticateUser(loginRequest);

    }

//========================================================= IMAGE

    @PostMapping("/image/upload/{profId}")
    public Professor uploadProfessorImage(
            @RequestParam("image") MultipartFile image,
            @PathVariable Long profId
    ) throws IOException {

        Professor professor = professorRepository.findById(profId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        // old image delete
        if (professor.getImageUrl() != null) {
            imageService.deleteImage(professor.getImageUrl());
        }

        String fileName = imageService.uploadImage(image);
        professor.setImageUrl(fileName);

        return professorRepository.save(professor);
    }


    @GetMapping("/image/get/{profId}")
    public void getProfessorImage(
            @PathVariable Long profId,
            HttpServletResponse response
    ) throws IOException {

        Professor professor = professorRepository.findById(profId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        if (professor.getImageUrl() == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("Image not found");
            return;
        }

        String imageName = Paths.get(professor.getImageUrl()).getFileName().toString();

        try (InputStream is = imageService.getResource(imageName)) {

            String contentType = URLConnection.guessContentTypeFromName(imageName);

            response.setContentType(
                    contentType != null ? contentType : "application/octet-stream"
            );

            StreamUtils.copy(is, response.getOutputStream());
        }
    }

}