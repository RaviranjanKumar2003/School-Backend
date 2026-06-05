package com.example.stud_erp.service;

import com.example.stud_erp.entity.*;
import com.example.stud_erp.payload.NotificationDTO;
import com.example.stud_erp.payload.NotificationResponse;
import com.example.stud_erp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private NotificationUserRepository notificationUserRepository;

    @Autowired
    private HODRepository hodRepository;

    public String sendNotification(NotificationDTO dto) {

        switch (dto.getRecipientType()) {

            // =====================================
            // ALL STUDENTS
            // =====================================

            case "ALL_STUDENTS":

                Notification allStudentNotification =
                        notificationRepository.save(
                                new Notification(dto)
                        );

                List<Student> students =
                        studentRepository.findBySchool_Id(
                                dto.getSchoolId()
                        );

                for (Student s : students) {

                    NotificationUser nu =
                            new NotificationUser();

                    nu.setNotificationId(
                            allStudentNotification.getId()
                    );

                    nu.setUserId(s.getId());

                    nu.setUserType("STUDENT");

                    notificationUserRepository.save(nu);
                }

                return "Sent to all students";

            // =====================================
            // ALL TEACHERS
            // =====================================

            case "ALL_TEACHERS":

                Notification allTeacherNotification =
                        notificationRepository.save(
                                new Notification(dto)
                        );

                List<Professor> teachers =
                        professorRepository.findBySchoolId(
                                dto.getSchoolId()
                        );

                for (Professor p : teachers) {

                    NotificationUser nu =
                            new NotificationUser();

                    nu.setNotificationId(
                            allTeacherNotification.getId()
                    );

                    nu.setUserId(p.getId());

                    nu.setUserType("TEACHER");

                    notificationUserRepository.save(nu);
                }

                return "Sent to all teachers";

            // =====================================
            // ALL
            // =====================================

            case "ALL":

                Notification allNotification =
                        notificationRepository.save(
                                new Notification(dto)
                        );

                List<Student> allStudents =
                        studentRepository.findBySchool_Id(
                                dto.getSchoolId()
                        );

                for (Student s : allStudents) {

                    NotificationUser nu =
                            new NotificationUser();

                    nu.setNotificationId(
                            allNotification.getId()
                    );

                    nu.setUserId(s.getId());

                    nu.setUserType("STUDENT");

                    notificationUserRepository.save(nu);
                }

                List<Professor> allTeachers =
                        professorRepository.findBySchoolId(
                                dto.getSchoolId()
                        );

                for (Professor p : allTeachers) {

                    NotificationUser nu =
                            new NotificationUser();

                    nu.setNotificationId(
                            allNotification.getId()
                    );

                    nu.setUserId(p.getId());

                    nu.setUserType("TEACHER");

                    notificationUserRepository.save(nu);
                }

                return "Sent to all";

            // =====================================
            // SINGLE STUDENT
            // =====================================

            case "SINGLE_STUDENT":

                Notification studentNotification =
                        notificationRepository.save(
                                new Notification(dto)
                        );

                NotificationUser studentUser =
                        new NotificationUser();

                studentUser.setNotificationId(
                        studentNotification.getId()
                );

                studentUser.setUserId(
                        dto.getStudentId()
                );

                studentUser.setUserType("STUDENT");

                notificationUserRepository.save(studentUser);

                return "Sent to student";

            // =====================================
            // SINGLE TEACHER
            // =====================================

            case "SINGLE_TEACHER":

                Notification teacherNotification =
                        notificationRepository.save(
                                new Notification(dto)
                        );

                NotificationUser teacherUser =
                        new NotificationUser();

                teacherUser.setNotificationId(
                        teacherNotification.getId()
                );

                teacherUser.setUserId(
                        dto.getTeacherId()
                );

                teacherUser.setUserType("TEACHER");

                notificationUserRepository.save(teacherUser);

                return "Sent to teacher";

            // =====================================
            // CLASS STUDENTS
            // =====================================

            case "CLASS_STUDENTS":

                Notification classNotification =
                        notificationRepository.save(
                                new Notification(dto)
                        );

                List<Student> classStudents =
                        studentRepository
                                .findBySchool_IdAndClassEntity_Id(
                                        dto.getSchoolId(),
                                        dto.getClassId()
                                );

                for (Student s : classStudents) {

                    NotificationUser nu =
                            new NotificationUser();

                    nu.setNotificationId(
                            classNotification.getId()
                    );

                    nu.setUserId(s.getId());

                    nu.setUserType("STUDENT");

                    notificationUserRepository.save(nu);
                }

                return "Sent to class students";

            // =====================================
            // ALL HOD
            // =====================================

            case "ALL_HOD":

                Notification hodNotificationAll =
                        notificationRepository.save(
                                new Notification(dto)
                        );

                List<HOD> hods =
                        hodRepository.findBySchoolId(
                                dto.getSchoolId()
                        );

                for (HOD h : hods) {

                    NotificationUser nu =
                            new NotificationUser();

                    nu.setNotificationId(
                            hodNotificationAll.getId()
                    );

                    nu.setUserId(
                            h.getId()
                    );

                    nu.setUserType(
                            "HOD"
                    );

                    nu.setReadStatus(false);

                    nu.setArchived(false);

                    notificationUserRepository.save(nu);
                }

                return "Sent to all HOD";



            // =====================================
            // SINGLE HOD
            // =====================================

            case "SINGLE_HOD":

                Notification hodNotification =
                        notificationRepository.save(
                                new Notification(dto)
                        );

                NotificationUser hodUser =
                        new NotificationUser();

                hodUser.setNotificationId(
                        hodNotification.getId()
                );

                hodUser.setUserId(
                        dto.getRecipientId()
                );

                hodUser.setUserType(
                        "HOD"
                );

                notificationUserRepository.save(
                        hodUser
                );

                return "Sent to HOD";


            // =====================================
            // SINGLE ADMIN
            // =====================================

            case "SINGLE_ADMIN":

                Notification adminNotification =
                        notificationRepository.save(
                                new Notification(dto)
                        );

                NotificationUser adminUser =
                        new NotificationUser();

                adminUser.setNotificationId(
                        adminNotification.getId()
                );

                adminUser.setUserId(
                        dto.getRecipientId()
                );

                adminUser.setUserType(
                        "ADMIN"
                );

                notificationUserRepository.save(
                        adminUser
                );

                return "Sent to Admin";


            case "SUPER_ADMIN":
                Notification superAdminNotification =
                        notificationRepository.save(
                                new Notification(dto)
                        );

                NotificationUser superAdminUser =
                        new NotificationUser();

                superAdminUser.setNotificationId(
                        superAdminNotification.getId()
                );

                superAdminUser.setUserId(
                        1L
                );

                superAdminUser.setUserType(
                        "SUPER_ADMIN"
                );

                notificationUserRepository.save(
                        superAdminUser
                );

                return "Sent to Super Admin";



            case "SINGLE_RECEPTIONIST":

                Notification receptionistNotification =
                        notificationRepository.save(
                                new Notification(dto)
                        );

                NotificationUser receptionistUser =
                        new NotificationUser();

                receptionistUser.setNotificationId(
                        receptionistNotification.getId()
                );

                receptionistUser.setUserId(
                        dto.getRecipientId()
                );

                receptionistUser.setUserType(
                        "RECEPTIONIST"
                );

                notificationUserRepository.save(
                        receptionistUser
                );

                return "Sent to Receptionist";


            default:
                throw new RuntimeException(
                        "Invalid recipient type"
                );
        }
    }

    public List<NotificationResponse> getArchivedNotifications(Long studentId) {

        List<NotificationUser> list =
                notificationUserRepository.findByUserIdAndUserTypeAndIsArchivedTrue(studentId, "STUDENT");

        return list.stream().map(nu -> {

            Notification n = notificationRepository
                    .findById(nu.getNotificationId())
                    .orElse(null);

            NotificationResponse res = new NotificationResponse();
            res.setNotificationUserId(nu.getId());
            res.setNotificationId(n.getId());
            res.setTitle(n.getTitle());
            res.setMessage(n.getMessage());
            res.setSender(n.getSender());
            res.setSenderType(n.getSenderType());
            res.setReadStatus(nu.isReadStatus());
            res.setSentAt(n.getSentAt());

            return res;

        }).toList();
    }

    public void archiveNotification(Long notificationId, Long studentId) {

        NotificationUser nu = notificationUserRepository
                .findByNotificationIdAndUserIdAndUserType(notificationId, studentId, "STUDENT");

        if (nu == null) {
            throw new RuntimeException("Not found");
        }

        nu.setArchived(true);
        notificationUserRepository.save(nu);
    }

    public void deletePermanently(Long notificationId, Long studentId){

        NotificationUser nu = notificationUserRepository
                .findByNotificationIdAndUserIdAndUserType(notificationId, studentId, "STUDENT");

        if (nu != null) {
            notificationUserRepository.delete(nu);
        }
    }

    public int getUnreadCount(Long studentId) {
        return notificationUserRepository
                .findByUserIdAndUserTypeAndReadStatusFalseAndIsArchivedFalse(studentId, "STUDENT")
                .size();
    }

    public void markAsRead(

            Long notificationId,

            Long userId,

            String userType
    ) {

        NotificationUser nu =
                notificationUserRepository
                        .findByNotificationIdAndUserIdAndUserType(

                                notificationId,

                                userId,

                                userType
                        );

        if (nu == null) {
            return;
        }

        nu.setReadStatus(true);

        notificationUserRepository.save(nu);
    }

    public void unarchiveNotification(Long notificationId, Long studentId) {

        NotificationUser nu = notificationUserRepository
                .findByNotificationIdAndUserIdAndUserType(notificationId, studentId, "STUDENT");

        if (nu == null) {
            throw new RuntimeException("Not found");
        }

        nu.setArchived(false);
        notificationUserRepository.save(nu);
    }

    public List<NotificationResponse> getNotificationsForStudent(Long studentId) {

        List<NotificationUser> users =
                notificationUserRepository
                        .findByUserIdAndUserTypeAndIsArchivedFalse(
                                studentId,
                                "STUDENT"
                        );

        List<NotificationResponse> responses =
                new ArrayList<>();

        for (NotificationUser nu : users) {

            Optional<Notification> optionalNotification =
                    notificationRepository.findById(
                            nu.getNotificationId()
                    );

            if (optionalNotification.isEmpty()) {
                continue;
            }

            Notification n =
                    optionalNotification.get();

            NotificationResponse res =
                    new NotificationResponse();

            res.setNotificationUserId(
                    nu.getId()
            );

            res.setNotificationId(
                    n.getId()
            );

            res.setTitle(
                    n.getTitle()
            );

            res.setMessage(
                    n.getMessage()
            );

            res.setSender(
                    n.getSender()
            );

            // 🔥 IMPORTANT
            res.setSenderType(
                    n.getSenderType()
            );

            res.setReadStatus(
                    nu.isReadStatus()
            );

            res.setSentAt(
                    n.getSentAt()
            );

            responses.add(res);
        }

        return responses;
    }






    public List<NotificationResponse>
    getTeacherNotifications(Long teacherId) {

        List<NotificationUser> list =

                notificationUserRepository
                        .findByUserIdAndUserTypeAndIsArchivedFalse(
                                teacherId,
                                "TEACHER"
                        );

        return list.stream().map(nu -> {

            Notification n =
                    notificationRepository
                            .findById(
                                    nu.getNotificationId()
                            )
                            .orElse(null);

            NotificationResponse res =
                    new NotificationResponse();

            if (n != null) {

                res.setNotificationUserId(
                        nu.getId()
                );

                res.setNotificationId(
                        n.getId()
                );

                res.setTitle(
                        n.getTitle()
                );

                res.setMessage(
                        n.getMessage()
                );

                res.setSender(
                        n.getSender()
                );

                res.setSenderType(
                        n.getSenderType()
                );

                res.setReadStatus(
                        nu.isReadStatus()
                );

                res.setSentAt(
                        n.getSentAt()
                );
            }

            return res;

        }).toList();
    }




    public List<NotificationResponse>
    getNotificationsByUserType(

            Long userId,

            String userType
    ) {

        List<NotificationUser> list =

                notificationUserRepository
                        .findByUserIdAndUserTypeAndIsArchivedFalse(

                                userId,

                                userType
                        );

        return list.stream().map(nu -> {

            Notification n =
                    notificationRepository
                            .findById(
                                    nu.getNotificationId()
                            )
                            .orElse(null);

            NotificationResponse res =
                    new NotificationResponse();

            if (n != null) {

                res.setNotificationUserId(
                        nu.getId()
                );

                res.setNotificationId(
                        n.getId()
                );

                res.setTitle(
                        n.getTitle()
                );

                res.setMessage(
                        n.getMessage()
                );

                res.setSender(
                        n.getSender()
                );

                res.setSenderType(
                        n.getSenderType()
                );

                res.setReadStatus(
                        nu.isReadStatus()
                );

                res.setSentAt(
                        n.getSentAt()
                );
            }

            return res;

        }).toList();
    }



    // =====================================
// MY NOTIFICATIONS
// =====================================

    public List<Notification>
    getMyNotifications(

            Long senderId,

            String senderType
    ) {

        return notificationRepository
                .findBySenderIdAndSenderTypeOrderBySentAtDesc(

                        senderId,

                        senderType
                );
    }


// =====================================
// UPDATE NOTIFICATION
// =====================================

    public String updateNotification(

            Long notificationId,

            NotificationDTO dto
    ) {

        Notification n =
                notificationRepository
                        .findById(notificationId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Notification not found"
                                )
                        );

        // SECURITY

        if (
                !n.getSenderId().equals(dto.getSenderId())

                        ||

                        !n.getSenderType().equals(dto.getSenderType())
        ) {

            throw new RuntimeException(
                    "You can update only your notifications"
            );
        }

        n.setTitle(dto.getTitle());

        n.setSubject(dto.getSubject());

        n.setMessage(dto.getMessage());

        notificationRepository.save(n);

        return "Notification updated";
    }



// =====================================
// DELETE NOTIFICATION
// =====================================

    public String deleteNotification(

            Long notificationId,

            Long senderId,

            String senderType
    ) {

        Notification n =
                notificationRepository
                        .findById(notificationId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Notification not found"
                                )
                        );

        // SECURITY

        if (
                !n.getSenderId().equals(senderId)

                        ||

                        !n.getSenderType().equals(senderType)
        ) {

            throw new RuntimeException(
                    "You can delete only your notifications"
            );
        }

        notificationRepository.delete(n);

        return "Notification deleted";
    }
}