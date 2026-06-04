package com.example.stud_erp.controller;

import com.example.stud_erp.entity.Leave;
import com.example.stud_erp.enums.LeaveType;
import com.example.stud_erp.payload.LeaveDTO;
import com.example.stud_erp.service.LeaveService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave")
@CrossOrigin("*")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    // =====================================================
    // APPLY LEAVE
    // =====================================================

    @PostMapping("/apply")
    public Leave applyLeave(
            @RequestBody LeaveDTO dto
    ) {

        return leaveService.applyLeave(dto);
    }

    // =====================================================
    // STUDENT LEAVES
    // =====================================================

    @GetMapping("/student/{studentId}")
    public List<Leave> studentLeaves(
            @PathVariable Long studentId
    ) {

        return leaveService
                .getStudentLeaves(studentId);
    }

    // =====================================================
    // HOD LEAVES
    // =====================================================

    @GetMapping("/hod/{schoolId}")
    public List<Leave> hodLeaves(
            @PathVariable Long schoolId
    ) {

        return leaveService
                .getHodLeaves(schoolId);
    }

    // =====================================================
    // TEACHER LEAVES
    // =====================================================

    @GetMapping("/teacher/{teacherId}/{schoolId}")
    public List<Leave> teacherLeaves(

            @PathVariable Long teacherId,

            @PathVariable Long schoolId
    ) {

        return leaveService
                .getTeacherLeaves(
                        teacherId,
                        schoolId
                );
    }

    // =====================================================
    // APPROVE
    // =====================================================

    @PutMapping("/approve/{leaveId}/{actionById}/{actionByType}")
    public String approveLeave(

            @PathVariable Long leaveId,

            @PathVariable Long actionById,

            @PathVariable String actionByType

    ) {

        leaveService.approveLeave(
                leaveId,
                actionById,
                actionByType
        );

        return "APPROVED";
    }

    // =====================================================
    // REJECT
    // =====================================================

    @PutMapping("/reject/{leaveId}/{actionById}/{actionByType}")
    public String rejectLeave(

            @PathVariable Long leaveId,

            @PathVariable Long actionById,

            @PathVariable String actionByType,

            @RequestParam String responseMessage

    ) {

        leaveService.rejectLeave(
                leaveId,
                actionById,
                actionByType,
                responseMessage
        );

        return "REJECTED";
    }

    // =====================================================
    // LEAVE TYPES
    // =====================================================
    @GetMapping("/types")
    public LeaveType[] getLeaveTypes() {

        return LeaveType.values();
    }


    // =====================================================
    // MY LEAVES Teacher keliye apna dekh sakta hai
    // =====================================================

    @GetMapping("/my/{senderId}/{senderType}")
    public List<Leave> myLeaves(

            @PathVariable Long senderId,

            @PathVariable String senderType

    ) {

        return leaveService.getMyLeaves(

                senderId,

                senderType
        );
    }


    // =====================================================
    // ADMIN LEAVES
    // =====================================================

    @GetMapping("/admin/{schoolId}")
    public List<Leave> adminLeaves(
            @PathVariable Long schoolId
    ) {

        return leaveService
                .getAdminLeaves(schoolId);
    }
}