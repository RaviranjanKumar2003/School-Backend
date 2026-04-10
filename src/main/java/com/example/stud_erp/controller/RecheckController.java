package com.example.stud_erp.controller;

import com.example.stud_erp.entity.RecheckRequest;
import com.example.stud_erp.entity.Result;
import com.example.stud_erp.payload.NotificationDTO;
import com.example.stud_erp.service.NotificationService;
import com.example.stud_erp.service.RecheckService;
import com.example.stud_erp.service.ResultService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recheck")
@CrossOrigin("*")
public class RecheckController {

    @Autowired
    private RecheckService recheckService;

    @Autowired
    private ResultService resultService;

    @Autowired
    private NotificationService notificationService;

    // ✅ 1. STUDENT REQUEST RECHECK
    @PostMapping("/request")
    public RecheckRequest requestRecheck(@RequestBody RecheckRequest request) {

        RecheckRequest saved = recheckService.createRequest(request);

        // 🔔 Notify Admin/Teachers
        NotificationDTO dto = new NotificationDTO();
        dto.setTitle("Recheck Request");
        dto.setMessage("Student requested recheck for subjects: "
                + String.join(", ", request.getSubjects()));
        dto.setRecipientType("ALL_TEACHERS");
        dto.setSender("STUDENT");

        notificationService.sendNotification(dto);

        return saved;
    }

    // ✅ 2. ADMIN APPROVE RECHECK
    @PutMapping("/approve/{id}")
    public RecheckRequest approveRecheck(@PathVariable Long id) {

        RecheckRequest updated = recheckService.approveRequest(id);

        // 🔔 Notify Teacher
        NotificationDTO dto = new NotificationDTO();
        dto.setTitle("Recheck Approved");
        dto.setMessage("Recheck approved for studentId: "
                + updated.getStudentId()
                + ", Subjects: " + String.join(", ", updated.getSubjects()));
        dto.setRecipientType("ALL_TEACHERS");
        dto.setSender("ADMIN");

        notificationService.sendNotification(dto);

        return updated;
    }

    // ❌ 3. ADMIN REJECT
    @PutMapping("/reject/{id}")
    public RecheckRequest rejectRecheck(@PathVariable Long id) {

        RecheckRequest updated = recheckService.rejectRequest(id);

        // 🔔 Notify Student
        NotificationDTO dto = new NotificationDTO();
        dto.setTitle("Recheck Rejected");
        dto.setMessage("Your recheck request has been rejected");
        dto.setRecipientType("INDIVIDUAL");
        dto.setRecipientId(updated.getStudentId());
        dto.setSender("ADMIN");

        notificationService.sendNotification(dto);

        return updated;
    }

    // ✅ 4. TEACHER VIEW APPROVED LIST
    @GetMapping("/teacher")
    public List<RecheckRequest> getApprovedRequests() {
        return recheckService.getAllRequests()
                .stream()
                .filter(r -> "APPROVED".equals(r.getStatus()))
                .toList();
    }

    // ✅ 5. TEACHER UPDATE MARKS (MULTI SUBJECT 🔥)
    @PutMapping("/update/{id}")
    public String updateAfterRecheck(@PathVariable Long id, @RequestBody Result body) {

        // 🔍 get request
        RecheckRequest req = recheckService.getById(id);

        int newMarks = body.getMarks();

        // 🔥 loop all subjects
        for (String subject : req.getSubjects()) {
            resultService.updateMarks(req.getStudentId(), subject, newMarks);
        }

        // 🔥 mark completed
        req.setStatus("COMPLETED");
        recheckService.save(req);

        // 🔔 Notify Student
        NotificationDTO dto = new NotificationDTO();
        dto.setTitle("Recheck Completed");
        dto.setMessage("Your marks have been updated after recheck");
        dto.setRecipientType("INDIVIDUAL");
        dto.setRecipientId(req.getStudentId());
        dto.setSender("TEACHER");

        notificationService.sendNotification(dto);

        return "Recheck Process Completed ✅";
    }

    // ✅ 6. ADMIN VIEW ALL
    @GetMapping("/all")
    public List<RecheckRequest> getAllRequests() {
        return recheckService.getAllRequests();
    }
}