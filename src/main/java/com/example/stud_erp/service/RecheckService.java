package com.example.stud_erp.service;

import com.example.stud_erp.entity.RecheckRequest;
import com.example.stud_erp.repository.RecheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecheckService {

    @Autowired
    private RecheckRepository recheckRepository;

    // ✅ 1. CREATE REQUEST (Student)
    public RecheckRequest createRequest(RecheckRequest request) {

        List<RecheckRequest> existing =
                recheckRepository.findByStudentIdAndSubject(
                        request.getStudentId(),
                        request.getSubject()
                );

        if (!existing.isEmpty()) {
            throw new RuntimeException("Recheck already requested for this subject");
        }

        request.setStatus("PENDING");

        return recheckRepository.save(request);
    }

    // ✅ 2. GET ALL PENDING REQUESTS (Admin)
    public List<RecheckRequest> getPendingRequests() {
        return recheckRepository.findByStatus("PENDING");
    }

    // ✅ 3. APPROVE REQUEST (Admin)
    public RecheckRequest approveRequest(Long id) {

        RecheckRequest req = recheckRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        req.setStatus("APPROVED");

        return recheckRepository.save(req);
    }

    // ✅ 4. REJECT REQUEST (Admin)
    public RecheckRequest rejectRequest(Long id) {

        RecheckRequest req = recheckRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        req.setStatus("REJECTED");

        return recheckRepository.save(req);
    }

    // ✅ 5. STUDENT HISTORY
    public List<RecheckRequest> getStudentRequests(Long studentId) {
        return recheckRepository.findByStudentId(studentId);
    }

    // 🔥 6. GET ALL REQUESTS (FIX FOR ERROR)
    public List<RecheckRequest> getAllRequests() {
        return recheckRepository.findAll();
    }

    // 🔥 7. GET BY ID (IMPORTANT FIX)
    public RecheckRequest getById(Long id) {
        return recheckRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));
    }
}