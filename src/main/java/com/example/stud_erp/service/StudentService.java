package com.example.stud_erp.service;

import com.example.stud_erp.entity.Student;
import com.example.stud_erp.payload.LoginRequest;
import com.example.stud_erp.payload.LoginResponse;
import com.example.stud_erp.payload.StudentDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface StudentService {

    // ================= CREATE =================
    Student createStudent(
            Student student,
            MultipartFile image
    ) throws IOException;

    // ================= UPDATE =================
    Student updateStudent(
            Long id,
            Student student,
            MultipartFile image
    ) throws IOException;

    // ================= DELETE =================
    void deleteStudent(Long id);

    // ================= GET =================
    List<StudentDTO> getAllStudents(Long schoolId);

    List<StudentDTO> getStudentsByClass(
            Long schoolId,
            Long classNumber
    );

    Optional<Student> getStudentById(Long id);

    Optional<Student> getByStudentId(String studentId);

    public List<StudentDTO> getDeletedStudents(Long schoolId);

    void restoreStudent(Long id);

    void permanentDelete(Long id);

    // ================= COUNT =================
    Long getTotalStudents(Long schoolId);

    // ================= LOGIN =================
    LoginResponse authenticateUser(LoginRequest request);

    Student getById(Long id);
}