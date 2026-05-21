package com.example.stud_erp.service;

import com.example.stud_erp.entity.Admission;

import java.util.List;

public interface AdmissionService {

    Admission createAdmission(Admission admission);

    List<Admission> getAllAdmissions();

    List<Admission> getByStatus(String status);

    Admission updateStatus(Long id, String status);
}