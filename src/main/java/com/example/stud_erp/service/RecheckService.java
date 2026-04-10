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

    // ✅ 1. CREATE REQUEST (MULTI SUBJECT FIXED 🔥)
    public RecheckRequest createRequest(RecheckRequest request) {

        // 🔥 check duplicate for each subject
        for (String subject : request.getSubjects()) {

            List<RecheckRequest> existing =
                    recheckRepository.findByStudentIdAndSubjectsContaining(
                            request.getStudentId(),
                            subject
                    );

            if (!existing.isEmpty()) {
                throw new RuntimeException("Recheck already requested for subject: " + subject);
            }
        }

        request.setStatus("PENDING");

        return recheckRepository.save(request);
    }

    // ✅ 2. GET ALL PENDING REQUESTS
    public List<RecheckRequest> getPendingRequests() {
        return recheckRepository.findByStatus("PENDING");
    }

    // ✅ 3. APPROVE REQUEST
    public RecheckRequest approveRequest(Long id) {

        RecheckRequest req = getById(id);

        req.setStatus("APPROVED");

        return recheckRepository.save(req);
    }

    // ❌ 4. REJECT REQUEST
    public RecheckRequest rejectRequest(Long id) {

        RecheckRequest req = getById(id);

        req.setStatus("REJECTED");

        return recheckRepository.save(req);
    }

    // ✅ 5. STUDENT HISTORY
    public List<RecheckRequest> getStudentRequests(Long studentId) {
        return recheckRepository.findByStudentId(studentId);
    }

    // ✅ 6. GET ALL REQUESTS
    public List<RecheckRequest> getAllRequests() {
        return recheckRepository.findAll();
    }

    // ✅ 7. GET BY ID
    public RecheckRequest getById(Long id) {
        return recheckRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));
    }

    // 🔥 8. SAVE (for controller use)
    public RecheckRequest save(RecheckRequest req) {
        return recheckRepository.save(req);
    }
}