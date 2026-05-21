package com.example.stud_erp.repository;

import com.example.stud_erp.entity.Admission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdmissionRepository extends JpaRepository<Admission, Long> {

    List<Admission> findByAdmissionStatus(String status);

    List<Admission> findByInquiryId(Long inquiryId);
}