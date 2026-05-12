package com.example.stud_erp.service.Implementation;

import com.example.stud_erp.entity.ClassEntity;
import com.example.stud_erp.entity.Student;
import com.example.stud_erp.payload.LoginRequest;
import com.example.stud_erp.payload.LoginResponse;
import com.example.stud_erp.payload.StudentDTO;
import com.example.stud_erp.repository.ClassRepository;
import com.example.stud_erp.repository.StudentRepository;
import com.example.stud_erp.service.ImageService;
import com.example.stud_erp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Year;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private ImageService imageService;

    // ================= CREATE =================
    @Override
    public Student createStudent(
            Student student,
            MultipartFile image
    ) throws IOException, IOException {

        // ================= EMAIL VALIDATION =================
        if (student.getEmail() == null ||
                student.getEmail().trim().isEmpty()) {

            throw new RuntimeException("Email required");
        }

        // LOWERCASE EMAIL
        student.setEmail(
                student.getEmail().trim().toLowerCase()
        );

        // ================= DUPLICATE EMAIL =================
        if (studentRepository.existsByEmail(student.getEmail())) {

            throw new RuntimeException("Email already exists");
        }

        // ================= PHONE VALIDATION =================
        if (student.getStudPhoneNumber() == null ||
                student.getStudPhoneNumber().length() < 10) {

            throw new RuntimeException("Valid phone number required");
        }

        // ================= CLASS FETCH =================
        ClassEntity cls = classRepository
                .findById(student.getClassNumber().longValue())
                .orElseThrow(() ->
                        new RuntimeException("Class not found")
                );

        // ================= SET CLASS NAME =================
        student.setClassName(cls.getClassName());

        // ================= AUTO STUDENT ID =================
        String regNo =
                "SCH-"
                        + Year.now().getValue()
                        + "-"
                        + System.currentTimeMillis();

        student.setStudentId(regNo);

        // ================= AUTO USERNAME =================
        String username =
                student.getEmail()
                        .split("@")[0]
                        + "_"
                        + cls.getClassName()
                        .replaceAll("\\s+", "")
                        .toLowerCase();

        student.setUsername(username);

        // ================= AUTO PASSWORD =================
        String password =
                "STUD@"
                        + student.getStudPhoneNumber().substring(
                        student.getStudPhoneNumber().length() - 4
                );

        student.setPassword(password);

        // ================= AUTO ROLL NUMBER =================

        Long totalStudents =
                studentRepository.countByClassNumber(
                        student.getClassNumber()
                );

        Long nextRollNo = totalStudents + 1;

        student.setStudRollNo(nextRollNo);

        // ================= IMAGE UPLOAD =================
        if (image != null && !image.isEmpty()) {

            String imageUrl =
                    imageService.uploadImage(image);

            student.setImageUrl(imageUrl);
        }

        // ================= DEFAULT DELETE FLAG =================
        student.setDeleted(false);

        // ================= SAVE =================
        return studentRepository.save(student);
    }

    // ================= UPDATE =================
    @Override
    public Student updateStudent(Long id, Student updated) {

        Student st = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        st.setStudName(updated.getStudName());
        st.setEmail(updated.getEmail());
        st.setStudPhoneNumber(updated.getStudPhoneNumber());

        st.setClassNumber(updated.getClassNumber());

        ClassEntity cls = classRepository.findById(updated.getClassNumber().longValue())
                .orElseThrow(() -> new RuntimeException("Class not found"));

        st.setClassName(cls.getClassName());

        return studentRepository.save(st);
    }

    // ================= DELETE =================
    @Override
    public void deleteStudent(Long id) {

        Student st = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        st.setDeleted(true);

        studentRepository.save(st);
    }

    // ================= GET ALL =================
    @Override
    public List<StudentDTO> getAllStudents(Long schoolId) {

        return studentRepository
                .findBySchoolIdAndIsDeletedFalse(schoolId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // ================= CLASS WISE =================
    @Override
    public List<StudentDTO> getStudentsByClass(Long schoolId, int classNumber) {

        return studentRepository
                .findBySchoolIdAndClassNumberAndIsDeletedFalse(schoolId, classNumber)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // ================= GET BY ID =================
    @Override
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Optional<Student> getByStudentId(String studentId) {
        return studentRepository.findByStudentId(studentId);
    }

    // ================= DTO MAPPER =================
    private StudentDTO toDTO(Student s) {

        StudentDTO dto = new StudentDTO();

        dto.setId(s.getId());

        dto.setSchoolId(s.getSchoolId());
        dto.setSchoolCode(s.getSchoolCode());
        dto.setSchoolName(s.getSchoolName());

        dto.setStudentId(s.getStudentId());
        dto.setUsername(s.getUsername());
        dto.setEmail(s.getEmail());

        dto.setClassNumber(s.getClassNumber());
        dto.setClassName(s.getClassName());

        dto.setStudRollNo(s.getStudRollNo());
        dto.setStudName(s.getStudName());
        dto.setStudPhoneNumber(s.getStudPhoneNumber());

        dto.setImageUrl(s.getImageUrl());

        return dto;
    }


    // ================= LOGIN =================
    @Override
    public LoginResponse authenticateUser(LoginRequest request) {

        Student student = studentRepository
                .findByUsername(request.getUsername())

                .orElseThrow(() ->
                        new RuntimeException("Student not found")
                );

        if (!student.getPassword().equals(request.getPassword())) {

            throw new RuntimeException("Invalid password");
        }

        // ================= RESPONSE =================
        LoginResponse response = new LoginResponse();

        response.setId(student.getId());

        response.setName(student.getStudName());

        response.setUsername(student.getUsername());

        response.setEmail(student.getEmail());

        response.setRole("STUDENT");

        response.setSchoolId(student.getSchoolId());

        response.setSchoolName(student.getSchoolName());

        response.setSchoolCode(student.getSchoolCode());

        return response;
    }
}