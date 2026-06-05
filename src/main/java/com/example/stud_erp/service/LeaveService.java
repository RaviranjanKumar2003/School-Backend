package com.example.stud_erp.service;

import com.example.stud_erp.entity.*;
import com.example.stud_erp.payload.LeaveDTO;
import com.example.stud_erp.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stud_erp.entity.SchoolAdmin;
import com.example.stud_erp.payload.NotificationDTO;
import com.example.stud_erp.repository.SchoolAdminRepository;

import java.util.List;

@Service
public class LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private HODRepository hodRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private SchoolAdminRepository schoolAdminRepository;

    @Autowired
    private ReceptionistRepository receptionistRepository;

    // =====================================================
    // APPLY LEAVE
    // =====================================================

    // =====================================================
    // APPLY LEAVE
    // =====================================================

    public Leave applyLeave(LeaveDTO dto) {

        Leave leave = new Leave();

        // =====================================================
        // BASIC DETAILS
        // =====================================================

        leave.setReason(dto.getReason());

        leave.setFromDate(dto.getFromDate());

        leave.setToDate(dto.getToDate());

        leave.setSendTo(dto.getSendTo());

        leave.setLeaveType(dto.getLeaveType());

        leave.setStatus("PENDING");

        // =====================================================
        // SENDER INFO
        // =====================================================

        leave.setSenderType(
                dto.getSenderType()
        );

        leave.setSenderId(
                dto.getSenderId()
        );

        // =====================================================
        // STUDENT FLOW
        // =====================================================

        if ("STUDENT".equals(dto.getSenderType())) {

            Student student = studentRepository
                    .findById(dto.getStudentId())
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Student not found"
                            ));

            // =========================
            // STUDENT SET
            // =========================

            leave.setStudent(student);

            // =========================
            // CLASS NAME
            // =========================

            if (student.getClassEntity() != null) {

                leave.setClassName(

                        student.getClassEntity()
                                .getClassName()
                );
            }

            // =========================
            // SCHOOL
            // =========================

            leave.setSchoolId(

                    student.getSchool().getId()
            );

            // =========================
            // TEACHER TARGET
            // =========================

            if ("TEACHER".equals(dto.getSendTo())) {

                Professor teacher =
                        professorRepository
                                .findById(dto.getTeacherId())
                                .orElseThrow(() ->
                                        new RuntimeException(
                                                "Teacher not found"
                                        ));

                // SAME SCHOOL SECURITY

                if (!teacher.getSchool().getId().equals(

                        student.getSchool().getId()

                )) {

                    throw new RuntimeException(

                            "Cross school leave not allowed"
                    );
                }

                leave.setTeacherId(
                        teacher.getId()
                );
            }
        }

        // =====================================================
        // TEACHER FLOW
        // =====================================================

        else if ("TEACHER".equals(dto.getSenderType())) {

            Professor teacher =
                    professorRepository
                            .findById(dto.getSenderId())
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Teacher not found"
                                    ));

            // =========================
            // SCHOOL
            // =========================

            leave.setSchoolId(

                    teacher.getSchool().getId()
            );
        }

        // =====================================================
        // HOD FLOW
        // =====================================================

        else if ("HOD".equals(dto.getSenderType())) {

            HOD hod = hodRepository
                    .findById(dto.getSenderId())
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "HOD not found"
                            ));

            // =========================
            // SCHOOL
            // =========================

            leave.setSchoolId(

                    hod.getSchool().getId()
            );
        }

        else if ("RECEPTIONIST".equals(dto.getSenderType())) {

            Receptionist receptionist =
                    receptionistRepository
                            .findById(dto.getSenderId())
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Receptionist not found"
                                    ));

            leave.setSchoolId(
                    receptionist.getSchool().getId()
            );
        }

        // =====================================================
        // ADMIN FLOW
        // =====================================================

        else if ("ADMIN".equals(dto.getSenderType())) {

            // future admin leave workflow
        }

        // =====================================================
        // SAVE
        // =====================================================

        Leave savedLeave =
                leaveRepository.save(leave);

        // =====================================================
        // STUDENT -> TEACHER
        // =====================================================

        if (

                "STUDENT".equals(
                        savedLeave.getSenderType()
                )

                        &&

                        "TEACHER".equals(
                                savedLeave.getSendTo()
                        )

        ) {

            sendLeaveNotification(

                    savedLeave,

                    "New Leave Request",

                    "Student applied for leave",

                    "SINGLE_TEACHER",

                    savedLeave.getTeacherId()
            );
        }



        // =====================================================
        // STUDENT -> HOD
        // =====================================================

        else if (

                "STUDENT".equals(
                        savedLeave.getSenderType()
                )

                        &&

                        "HOD".equals(
                                savedLeave.getSendTo()
                        )

        ) {

            List<HOD> hods =

                    hodRepository
                            .findBySchool_Id(

                                    savedLeave
                                            .getSchoolId()
                            );

            for (HOD hod : hods) {

                sendLeaveNotification(

                        savedLeave,

                        "New Leave Request",

                        "Student applied for leave",

                        "SINGLE_HOD",

                        hod.getId()
                );
            }
        }



        // =====================================================
        // TEACHER -> HOD
        // =====================================================

        else if (

                "TEACHER".equals(
                        savedLeave.getSenderType()
                )

                        &&

                        "HOD".equals(
                                savedLeave.getSendTo()
                        )

        ) {

            List<HOD> hods =

                    hodRepository
                            .findBySchool_Id(

                                    savedLeave
                                            .getSchoolId()
                            );

            for (HOD hod : hods) {

                sendLeaveNotification(

                        savedLeave,

                        "Teacher Leave Request",

                        "Teacher applied for leave",

                        "SINGLE_HOD",

                        hod.getId()
                );
            }
        }



        // =====================================================
        // TEACHER -> ADMIN
        // =====================================================

        else if (

                "TEACHER".equals(
                        savedLeave.getSenderType()
                )

                        &&

                        "ADMIN".equals(
                                savedLeave.getSendTo()
                        )

        ) {

            List<SchoolAdmin> admins =

                    schoolAdminRepository
                            .findBySchoolId(

                                    savedLeave
                                            .getSchoolId()
                            );

            for (SchoolAdmin admin : admins) {

                sendLeaveNotification(

                        savedLeave,

                        "Teacher Leave Request",

                        "Teacher applied for leave",

                        "SINGLE_ADMIN",

                        admin.getId()
                );
            }
        }

        // =====================================================
        // HOD -> ADMIN
        // =====================================================

        else if (

                "HOD".equals(
                        savedLeave.getSenderType()
                )

        ) {

            List<SchoolAdmin> admins =

                    schoolAdminRepository
                            .findBySchoolId(

                                    savedLeave
                                            .getSchoolId()
                            );

            for (SchoolAdmin admin : admins) {

                sendLeaveNotification(

                        savedLeave,

                        "HOD Leave Request",

                        "HOD applied for leave",

                        "SINGLE_ADMIN",

                        admin.getId()
                );
            }
        }

        else if (

                "RECEPTIONIST".equals(
                        savedLeave.getSenderType()
                )

                        &&

                        "ADMIN".equals(
                                savedLeave.getSendTo()
                        )

        ) {

            List<SchoolAdmin> admins =

                    schoolAdminRepository
                            .findBySchoolId(
                                    savedLeave.getSchoolId()
                            );

            for (SchoolAdmin admin : admins) {

                sendLeaveNotification(

                        savedLeave,

                        "Receptionist Leave Request",

                        "Receptionist applied for leave",

                        "SINGLE_ADMIN",

                        admin.getId()
                );
            }
        }
        return savedLeave;
    }




    // =====================================================
    // STUDENT LEAVES
    // =====================================================

    public List<Leave> getStudentLeaves(
            Long studentId
    ) {

        return leaveRepository
                .findByStudent_IdOrderByCreatedAtDesc(
                        studentId
                );
    }

    // =====================================================
    // HOD REQUESTS
    // =====================================================

    public List<Leave> getHodLeaves(
            Long schoolId
    ) {

        return leaveRepository
                .findBySendToAndSchoolIdOrderByCreatedAtDesc(
                        "HOD",
                        schoolId
                );
    }

    // =====================================================
    // TEACHER REQUESTS
    // =====================================================

    public List<Leave> getTeacherLeaves(

            Long teacherId,

            Long schoolId
    ) {
        return leaveRepository
                .findByTeacherIdAndSchoolIdOrderByCreatedAtDesc(

                        teacherId,

                        schoolId
                );
    }

    // =====================================================
    // APPROVE
    // =====================================================

    public void approveLeave(

            Long leaveId,

            Long actionById,

            String actionByType

    ) {

        Leave leave = leaveRepository.findById(leaveId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Leave not found"
                        ));

        leave.setStatus("APPROVED");

        leave.setActionById(actionById);

        leave.setActionByType(actionByType);

        leaveRepository.save(leave);
        // =====================================
// STUDENT
// =====================================

        if ("STUDENT".equals(
                leave.getSenderType()
        )) {

            sendLeaveNotification(

                    leave,

                    "Leave Approved",

                    "Your leave approved",

                    "SINGLE_STUDENT",

                    leave.getStudent().getId()
            );
        }

// =====================================
// TEACHER
// =====================================

        else if ("TEACHER".equals(
                leave.getSenderType()
        )) {

            sendLeaveNotification(

                    leave,

                    "Leave Approved",

                    "Your leave approved",

                    "SINGLE_TEACHER",

                    leave.getSenderId()
            );
        }

        // =====================================
        // HOD
        // =====================================

        else if ("HOD".equals(
                leave.getSenderType()
        )) {

            sendLeaveNotification(

                    leave,

                    "Leave Approved",

                    "Your leave approved",

                    "SINGLE_HOD",

                    leave.getSenderId()
            );
        }

        else if ("RECEPTIONIST".equals(
                leave.getSenderType()
        )) {

            sendLeaveNotification(

                    leave,

                    "Leave Approved",

                    "Your leave approved",

                    "SINGLE_RECEPTIONIST",

                    leave.getSenderId()
            );
        }
    }


    // =====================================================
    // REJECT
    // =====================================================

    public void rejectLeave(

            Long leaveId,

            Long actionById,

            String actionByType,

            String responseMessage

    ) {

        Leave leave = leaveRepository.findById(leaveId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Leave not found"
                        ));

        leave.setStatus("REJECTED");
        leave.setActionById(actionById);
        leave.setActionByType(actionByType);
        leave.setResponseMessage(responseMessage);
        leaveRepository.save(leave);

        // =====================================
// STUDENT
// =====================================

        if ("STUDENT".equals(
                leave.getSenderType()
        )) {

            sendLeaveNotification(

                    leave,

                    "Leave Rejected",

                    responseMessage,

                    "SINGLE_STUDENT",

                    leave.getStudent().getId()
            );
        }

// =====================================
// TEACHER
// =====================================

        else if ("TEACHER".equals(
                leave.getSenderType()
        )) {

            sendLeaveNotification(

                    leave,

                    "Leave Rejected",

                    responseMessage,

                    "SINGLE_TEACHER",

                    leave.getSenderId()
            );
        }

// =====================================
// HOD
// =====================================

        else if ("HOD".equals(
                leave.getSenderType()
        )) {

            sendLeaveNotification(

                    leave,

                    "Leave Rejected",

                    responseMessage,

                    "SINGLE_HOD",

                    leave.getSenderId()
            );
        }


        else if ("RECEPTIONIST".equals(
                leave.getSenderType()
        )) {

            sendLeaveNotification(

                    leave,

                    "Leave Rejected",

                    responseMessage,

                    "SINGLE_RECEPTIONIST",

                    leave.getSenderId()
            );
        }
    }



    // =====================================================
    // MY LEAVES Teacher keliye hai
    // =====================================================

    public List<Leave> getMyLeaves(

            Long senderId,

            String senderType

    ) {

        return leaveRepository
                .findBySenderIdAndSenderTypeOrderByCreatedAtDesc(

                        senderId,

                        senderType
                );
    }



    // =====================================================
    // ADMIN LEAVES
    // =====================================================

    public List<Leave> getAdminLeaves(Long schoolId) {

        return leaveRepository
                .findBySendToAndSchoolIdAndSenderTypeInOrderByCreatedAtDesc(

                        "ADMIN",

                        schoolId,

                        List.of("HOD", "TEACHER","RECEPTIONIST")
                );
    }


    private void sendLeaveNotification(

            Leave leave,

            String title,

            String message,

            String recipientType,

            Long recipientId

    ) {

        NotificationDTO dto =
                new NotificationDTO();

        dto.setTitle(title);

        dto.setSubject("Leave");

        dto.setMessage(message);

        if (leave.getActionByType() != null) {

            dto.setSenderType(
                    leave.getActionByType()
            );

            dto.setSenderId(
                    leave.getActionById()
            );

        } else {

            dto.setSenderType(
                    leave.getSenderType()
            );

            dto.setSenderId(
                    leave.getSenderId()
            );

        }

        dto.setSchoolId(
                leave.getSchoolId()
        );

        dto.setRecipientType(
                recipientType
        );

        // STUDENT

        if ("SINGLE_STUDENT".equals(
                recipientType
        )) {

            dto.setStudentId(
                    recipientId
            );
        }

        // TEACHER

        else if ("SINGLE_TEACHER".equals(
                recipientType
        )) {

            dto.setTeacherId(
                    recipientId
            );
        }

        // HOD / ADMIN

        else {

            dto.setRecipientId(
                    recipientId
            );
        }

        notificationService
                .sendNotification(dto);
    }
}