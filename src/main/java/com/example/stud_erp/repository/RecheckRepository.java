package com.example.stud_erp.repository;

import com.example.stud_erp.entity.RecheckRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecheckRepository extends JpaRepository<RecheckRequest, Long> {

    // ✅ Student ke sabhi recheck request
    List<RecheckRequest> findByStudentId(Long studentId);

    // ✅ Pending requests (Admin panel ke liye)
    List<RecheckRequest> findByStatus(String status);

    // ✅ Specific subject ka request check karne ke liye
    List<RecheckRequest> findByStudentIdAndSubject(Long studentId, String subject);
}