package com.example.stud_erp.service;

import com.example.stud_erp.entity.Notification;
import com.example.stud_erp.entity.Professor;
import com.example.stud_erp.entity.Student;
import com.example.stud_erp.payload.NotificationDTO;
import com.example.stud_erp.repository.NotificationRepository;
import com.example.stud_erp.repository.ProfessorRepository;
import com.example.stud_erp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    // ✅ SEND NOTIFICATION (UNCHANGED)
    public String sendNotification(NotificationDTO dto) {

        String type = dto.getRecipientType();

        switch (type) {

            case "STUDENT":
            case "INDIVIDUAL":

                if (dto.getRecipientId() == null) {
                    throw new RuntimeException("Recipient ID required");
                }

                Student student = studentRepository.findById(dto.getRecipientId()).orElse(null);

                if (student != null) {
                    Notification n = new Notification(dto);
                    n.setStudent(student);
                    notificationRepository.save(n);
                    return "Sent to Student";
                }

                Professor professor = professorRepository.findById(dto.getRecipientId()).orElse(null);

                if (professor != null) {
                    Notification n = new Notification(dto);
                    n.setProfessor(professor);
                    notificationRepository.save(n);
                    return "Sent to Professor";
                }

                throw new RuntimeException("User not found");

            case "ALL_STUDENTS":

                List<Student> students = studentRepository.findAll();

                for (Student s : students) {
                    Notification n = new Notification(dto);
                    n.setStudent(s);
                    notificationRepository.save(n);
                }

                return "Sent to all students";

            case "ALL_TEACHERS":

                List<Professor> professors = professorRepository.findAll();

                for (Professor p : professors) {
                    Notification n = new Notification(dto);
                    n.setProfessor(p);
                    notificationRepository.save(n);
                }

                return "Sent to all teachers";

            case "ALL":

                studentRepository.findAll().forEach(s -> {
                    Notification n = new Notification(dto);
                    n.setStudent(s);
                    notificationRepository.save(n);
                });

                professorRepository.findAll().forEach(p -> {
                    Notification n = new Notification(dto);
                    n.setProfessor(p);
                    notificationRepository.save(n);
                });

                return "Sent to all users";

            default:
                throw new RuntimeException("Invalid Recipient Type");
        }
    }

    // ✅ FIXED (only active notifications)
    public List<Notification> getNotificationsForStudent(Long studentId) {
        return notificationRepository.findActiveByStudentId(studentId);
    }

    public List<Notification> getNotificationsForProfessor(Long professorId) {
        return notificationRepository.findByProfessorId(professorId);
    }

    // ✅ MARK AS READ
    public void markAsRead(Long id) {
        Notification n = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        n.setReadStatus(true);
        notificationRepository.save(n);
    }

    // ✅ ARCHIVE (SOFT DELETE)
    public void archiveNotification(Long id) {
        Notification n = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        if (!n.getReadStatus()) {
            throw new RuntimeException("पहले read करो");
        }

        n.setIsArchived(true);
        notificationRepository.save(n);
    }

    // ✅ GET ARCHIVED
    public List<Notification> getArchivedNotifications(Long studentId) {
        return notificationRepository.findArchivedByStudentId(studentId);
    }

    // ✅ PERMANENT DELETE
    public void deletePermanently(Long id){
        notificationRepository.deleteById(id);
    }

   //  Unarchive
    public void unarchiveNotification(Long id) {
        Notification n = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        n.setIsArchived(false);
        notificationRepository.save(n);
    }
}

