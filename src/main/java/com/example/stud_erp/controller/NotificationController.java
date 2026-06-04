
package com.example.stud_erp.controller;

import com.example.stud_erp.entity.Notification;
import com.example.stud_erp.payload.NotificationDTO;
import com.example.stud_erp.payload.NotificationResponse;
import com.example.stud_erp.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin("*")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/send")
    public String sendNotification(@RequestBody NotificationDTO dto) {
        return notificationService.sendNotification(dto);
    }

    @GetMapping("/student/{id}")
    public List<NotificationResponse> getStudentNotifications(@PathVariable Long id) {
        return notificationService.getNotificationsForStudent(id);
    }

    @PutMapping("/archive/{notificationId}/{studentId}")
    public String archive(@PathVariable Long notificationId, @PathVariable Long studentId) {
        notificationService.archiveNotification(notificationId, studentId);
        return "Archived";
    }

    @DeleteMapping("/delete/{notificationId}/{studentId}")
    public String delete(@PathVariable Long notificationId, @PathVariable Long studentId) {
        notificationService.deletePermanently(notificationId, studentId);
        return "Deleted";
    }

    @GetMapping("/unread/{studentId}")
    public int unread(@PathVariable Long studentId) {
        return notificationService.getUnreadCount(studentId);
    }

    @PutMapping("/read/{notificationId}/{userId}/{userType}")
    public String read(

            @PathVariable Long notificationId,

            @PathVariable Long userId,

            @PathVariable String userType
    ) {

        notificationService.markAsRead(

                notificationId,

                userId,

                userType
        );

        return "Read";
    }

    @PutMapping("/unarchive/{notificationId}/{studentId}")
    public String unarchive(@PathVariable Long notificationId, @PathVariable Long studentId) {
        notificationService.unarchiveNotification(notificationId, studentId);
        return "Restored";
    }

    @GetMapping("/archived/{studentId}")
    public List<NotificationResponse> getArchived(@PathVariable Long studentId) {
        return notificationService.getArchivedNotifications(studentId);
    }

    @GetMapping("/professor/{teacherId}")
    public List<NotificationResponse>
    getTeacherNotifications(
            @PathVariable Long teacherId
    ) {

        return notificationService
                .getTeacherNotifications(
                        teacherId
                );
    }




    // =====================================
    // HOD NOTIFICATIONS
    // =====================================

    @GetMapping("/hod/{hodId}")

    public List<NotificationResponse>
    getHodNotifications(

            @PathVariable Long hodId
    ) {

        return notificationService
                .getNotificationsByUserType(
                        hodId,
                        "HOD"
                );
    }


// =====================================
// ADMIN NOTIFICATIONS
// =====================================

    @GetMapping("/admin/{adminId}")

    public List<NotificationResponse>
    getAdminNotifications(

            @PathVariable Long adminId
    ) {

        return notificationService
                .getNotificationsByUserType(
                        adminId,
                        "ADMIN"
                );
    }



    // =====================================
// MY NOTICES
// =====================================

    @GetMapping("/my/{senderId}/{senderType}")

    public List<Notification>
    myNotifications(

            @PathVariable Long senderId,

            @PathVariable String senderType
    ) {

        return notificationService
                .getMyNotifications(

                        senderId,

                        senderType
                );
    }



// =====================================
// UPDATE NOTICE
// =====================================

    @PutMapping("/update/{notificationId}")

    public String updateNotification(

            @PathVariable Long notificationId,

            @RequestBody NotificationDTO dto
    ) {

        return notificationService
                .updateNotification(

                        notificationId,

                        dto
                );
    }



    // =====================================
    // DELETE NOTICE
    // =====================================

    @DeleteMapping(
            "/delete-notice/{notificationId}/{senderId}/{senderType}"
    )

    public String deleteNotification(

            @PathVariable Long notificationId,

            @PathVariable Long senderId,

            @PathVariable String senderType
    ) {

        return notificationService
                .deleteNotification(

                        notificationId,

                        senderId,

                        senderType
                );
    }
}