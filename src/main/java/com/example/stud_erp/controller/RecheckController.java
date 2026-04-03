
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

    // ✅ 1. STUDENT REQUEST RECHECK (UPDATED 🔥)
    @PostMapping("/request")
    public RecheckRequest requestRecheck(@RequestBody RecheckRequest request) {

        RecheckRequest saved = recheckService.createRequest(request);

        // 🔔 Notify Admin
        NotificationDTO dto = new NotificationDTO();
        dto.setTitle("Recheck Request");
        dto.setMessage("Student requested recheck for subject: " + request.getSubject());
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
                + updated.getStudentId() + ", Subject: " + updated.getSubject());
        dto.setRecipientType("ALL_TEACHERS");
        dto.setSender("ADMIN");

        notificationService.sendNotification(dto);

        return updated;
    }

    // ❌ NEW (Reject added 🔥)
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

    // ✅ 3. TEACHER VIEW APPROVED LIST
    @GetMapping("/teacher")
    public List<RecheckRequest> getApprovedRequests() {
        return recheckService.getPendingRequests()
                .stream()
                .filter(r -> r.getStatus().equals("APPROVED"))
                .toList();
    }

    // ✅ 4. TEACHER UPDATE MARKS AFTER RECHECK (UPDATED 🔥)
    @PutMapping("/update/{id}")
    public String updateAfterRecheck(@PathVariable Long id, @RequestBody Result body) {

        RecheckRequest req = recheckService.approveRequest(id); // ensure exists

        int newMarks = body.getMarks();

        // 🔥 update via service (central logic)
        resultService.updateMarks(req.getStudentId(), req.getSubject(), newMarks);

        // 🔥 mark request completed
        req.setStatus("COMPLETED");

        // 🔔 Notification
        NotificationDTO dto = new NotificationDTO();
        dto.setTitle("Recheck Result");
        dto.setMessage("Your marks have been updated after recheck");
        dto.setRecipientType("INDIVIDUAL");
        dto.setRecipientId(req.getStudentId());
        dto.setSender("TEACHER");

        notificationService.sendNotification(dto);

        return "Recheck Process Completed ✅";
    }

    // ✅ 5. ADMIN VIEW ALL REQUESTS
    @GetMapping("/all")
    public List<RecheckRequest> getAllRequests() {
        return recheckService.getAllRequests();
    }
}