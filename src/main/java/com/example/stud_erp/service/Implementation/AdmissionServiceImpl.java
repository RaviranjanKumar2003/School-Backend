package com.example.stud_erp.service.Implementation;

import com.example.stud_erp.entity.Admission;
import com.example.stud_erp.repository.AdmissionRepository;
import com.example.stud_erp.service.AdmissionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdmissionServiceImpl implements AdmissionService {

    private final AdmissionRepository repository;

    public AdmissionServiceImpl(AdmissionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Admission createAdmission(Admission admission) {

        if (admission.getAdmissionDate() == null) {
            admission.setAdmissionDate(LocalDateTime.now());
        }

        if (admission.getAdmissionStatus() == null) {
            admission.setAdmissionStatus("PENDING");
        }

        return repository.save(admission);
    }

    @Override
    public List<Admission> getAllAdmissions() {
        return repository.findAll();
    }

    @Override
    public List<Admission> getByStatus(String status) {
        return repository.findByAdmissionStatus(status);
    }

    @Override
    public Admission updateStatus(Long id, String status) {

        Admission admission = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admission not found"));

        admission.setAdmissionStatus(status);
        return repository.save(admission);
    }
}