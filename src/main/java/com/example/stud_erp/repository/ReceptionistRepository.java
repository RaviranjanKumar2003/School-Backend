// =============================
// ReceptionistRepository.java
// =============================

package com.example.stud_erp.repository;

import com.example.stud_erp.entity.Receptionist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceptionistRepository
        extends JpaRepository<Receptionist, Long> {

    // ================= LOGIN =================
    Receptionist findByUsername(String username);

    // ================= EMAIL =================
    Receptionist findByEmail(String email);

    // ================= SCHOOL =================
    List<Receptionist> findBySchoolId(Long schoolId);

    // ================= EXISTS =================
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}