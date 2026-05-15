package com.example.stud_erp.service;

import com.example.stud_erp.entity.Professor;
import com.example.stud_erp.payload.LoginRequest;
import com.example.stud_erp.payload.ProfessorDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProfessorService {

    Professor createProfessor(ProfessorDTO dto, MultipartFile image) throws IOException;

    Professor updateProfessor(Long id, ProfessorDTO dto, MultipartFile image) throws IOException;

    Professor getProfessorById(Long id);

    List<Professor> getAllProfessors();

    void deleteProfessor(Long id);

    Professor saveProfessor(Professor professor);

    Professor authenticateUser(LoginRequest request);

    // ================= FILTER APIs =================
    List<Professor> getBySchoolId(Long schoolId);
    List<Professor> getBySchoolIdAndHodId(Long schoolId, Long hodId);
    List<ProfessorDTO> getBySchoolIdAndHodIdDTO(Long schoolId, Long hodId);
    Professor updateProfessorBasic(Long id, ProfessorDTO dto, MultipartFile image) throws Exception;
}