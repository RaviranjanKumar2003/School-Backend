
package com.example.stud_erp.service;

import com.example.stud_erp.entity.Receptionist;
import com.example.stud_erp.payload.LoginRequest;
import com.example.stud_erp.payload.ReceptionistDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ReceptionistService {

    // ================= CREATE =================
    Receptionist createReceptionist(
            ReceptionistDto dto,
            MultipartFile file
    ) throws IOException;

    // ================= UPDATE =================
    Receptionist updateReceptionist(
            Long id,
            ReceptionistDto dto,
            MultipartFile file
    ) throws IOException;

    // ================= GET ALL =================
    List<Receptionist> getAllReceptionists();

    // ================= GET BY ID =================
    Receptionist getReceptionistById(Long id);

    // ================= DELETE =================
    void deleteReceptionist(Long id);

    // ================= LOGIN =================
    Receptionist authenticateUser(LoginRequest loginRequest);

    // ================= GET BY SCHOOL =================
    List<Receptionist> getReceptionistsBySchool(Long schoolId);

    Receptionist uploadImage(
            Long id,
            MultipartFile image
    ) throws IOException;
}