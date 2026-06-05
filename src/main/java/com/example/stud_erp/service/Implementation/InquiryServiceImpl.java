//package com.example.stud_erp.service.Implementation;
//
//import com.example.stud_erp.entity.Inquiry;
//import com.example.stud_erp.payload.InquiryDto;
//import com.example.stud_erp.repository.InquiryRepository;
//import com.example.stud_erp.service.InquiryService;
//
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class InquiryServiceImpl implements InquiryService {
//
//    private final InquiryRepository repository;
//
//    public InquiryServiceImpl(InquiryRepository repository) {
//        this.repository = repository;
//    }
//
//    // =====================================
//    // CREATE
//    // =====================================
//
//    @Override
//    public Inquiry createInquiry(InquiryDto dto) {
//
//        Inquiry inquiry = new Inquiry();
//
//        inquiry.setStudentName(dto.getStudentName());
//        inquiry.setParentName(dto.getParentName());
//        inquiry.setPhone(dto.getPhone());
//        inquiry.setEmail(dto.getEmail());
//        inquiry.setMessage(dto.getMessage());
//        inquiry.setSchoolCode(dto.getSchoolCode());
//
//        // NEW FIELDS
//        inquiry.setAssignedTo(dto.getAssignedTo());
//        inquiry.setSource(dto.getSource());
//        inquiry.setPriority(dto.getPriority());
//        inquiry.setFollowUpDate(dto.getFollowUpDate());
//        inquiry.setLastAction(dto.getLastAction());
//
//        inquiry.setStatus("PENDING");
//
//        return repository.save(inquiry);
//    }
//
//    // =====================================
//    // GET ALL
//    // =====================================
//
//    @Override
//    public List<Inquiry> getAllInquiries() {
//        return repository.findAll();
//    }
//
//    // =====================================
//    // BY SCHOOL
//    // =====================================
//
//    @Override
//    public List<Inquiry> getBySchoolCode(String schoolCode) {
//        return repository.findBySchoolCode(schoolCode);
//    }
//
//    // =====================================
//    // UPDATE FULL INQUIRY
//    // =====================================
//
//    @Override
//    public Inquiry updateInquiry(Long id, InquiryDto dto) {
//
//        Inquiry inquiry = repository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Inquiry not found"));
//
//        inquiry.setStudentName(dto.getStudentName());
//        inquiry.setParentName(dto.getParentName());
//        inquiry.setPhone(dto.getPhone());
//        inquiry.setEmail(dto.getEmail());
//        inquiry.setMessage(dto.getMessage());
//
//        inquiry.setAssignedTo(dto.getAssignedTo());
//        inquiry.setSource(dto.getSource());
//        inquiry.setPriority(dto.getPriority());
//        inquiry.setFollowUpDate(dto.getFollowUpDate());
//        inquiry.setLastAction(dto.getLastAction());
//
//        return repository.save(inquiry);
//    }
//
//    // =====================================
//    // STATUS UPDATE ONLY
//    // =====================================
//
//    @Override
//    public Inquiry updateStatus(Long id, String status) {
//
//        Inquiry inquiry = repository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Inquiry not found"));
//
//        inquiry.setStatus(status);
//
//        return repository.save(inquiry);
//    }
//
//    @Override
//    public Inquiry getInquiryById(Long id) {
//
//        return repository
//                .findById(id)
//                .orElseThrow(() ->
//                        new RuntimeException("Inquiry Not Found"));
//    }
//}


package com.example.stud_erp.service.Implementation;

import com.example.stud_erp.entity.Inquiry;
import com.example.stud_erp.entity.Notification;
import com.example.stud_erp.entity.NotificationUser;
import com.example.stud_erp.entity.Receptionist;
import com.example.stud_erp.payload.InquiryDto;
import com.example.stud_erp.repository.*;
import com.example.stud_erp.service.InquiryService;
import com.example.stud_erp.entity.School;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InquiryServiceImpl implements InquiryService {

    private final InquiryRepository repository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private ReceptionistRepository receptionistRepository;

    @Autowired
    private NotificationUserRepository notificationUserRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    public InquiryServiceImpl(InquiryRepository repository) {
        this.repository = repository;
    }

    // =====================================
    // CREATE
    // =====================================

    @Override
    public Inquiry createInquiry(InquiryDto dto) {

        Inquiry inquiry = new Inquiry();

        inquiry.setStudentName(dto.getStudentName());
        inquiry.setParentName(dto.getParentName());
        inquiry.setPhone(dto.getPhone());
        inquiry.setEmail(dto.getEmail());
        inquiry.setMessage(dto.getMessage());
        inquiry.setSchoolCode(dto.getSchoolCode());

        inquiry.setAssignedTo(dto.getAssignedTo());
        inquiry.setSource(dto.getSource());
        inquiry.setPriority(dto.getPriority());
        inquiry.setFollowUpDate(dto.getFollowUpDate());
        inquiry.setLastAction(dto.getLastAction());

        inquiry.setStatus("PENDING");

        Inquiry savedInquiry =
                repository.save(inquiry);

        // ======================================
        // WEBSITE INQUIRY -> RECEPTIONIST BELL
        // ======================================

        if ("WEBSITE".equalsIgnoreCase(dto.getSource())) {

            try {

                School school =
                        schoolRepository.findBySchoolCode(
                                savedInquiry.getSchoolCode()
                        );

                if (school != null) {

                    List<Receptionist> receptionists =
                            receptionistRepository.findBySchoolId(
                                    school.getId()
                            );

                    Notification notification =
                            new Notification();

                    notification.setTitle(
                            "New Admission Inquiry"
                    );

                    notification.setSubject(
                            "Website Inquiry"
                    );

                    notification.setMessage(
                            savedInquiry.getStudentName()
                                    + " submitted a new admission inquiry."
                    );

                    notification.setSender(
                            "Website"
                    );

                    notification.setSenderType(
                            "SYSTEM"
                    );

                    notification.setRecipientType(
                            "RECEPTIONIST"
                    );

                    notification.setSentAt(
                            java.time.LocalDateTime.now()
                    );

                    Notification savedNotification =
                            notificationRepository.save(
                                    notification
                            );

                    for (Receptionist receptionist :
                            receptionists) {

                        NotificationUser user =
                                new NotificationUser();

                        user.setNotificationId(
                                savedNotification.getId()
                        );

                        user.setUserId(
                                receptionist.getId()
                        );

                        user.setUserType(
                                "RECEPTIONIST"
                        );

                        notificationUserRepository.save(
                                user
                        );
                    }
                }

            } catch (Exception e) {

                System.out.println(
                        "Inquiry Notification Error : "
                                + e.getMessage()
                );
            }
        }

        return savedInquiry;
    }
    // =====================================
    // GET ALL
    // =====================================

    @Override
    public List<Inquiry> getAllInquiries() {
        return repository.findAll();
    }

    // =====================================
    // BY SCHOOL
    // =====================================

    @Override
    public List<Inquiry> getBySchoolCode(String schoolCode) {
        return repository.findBySchoolCode(schoolCode);
    }

    // =====================================
    // UPDATE FULL INQUIRY
    // =====================================

    @Override
    public Inquiry updateInquiry(Long id, InquiryDto dto) {

        Inquiry inquiry = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inquiry not found"));

        inquiry.setStudentName(dto.getStudentName());
        inquiry.setParentName(dto.getParentName());
        inquiry.setPhone(dto.getPhone());
        inquiry.setEmail(dto.getEmail());
        inquiry.setMessage(dto.getMessage());

        inquiry.setAssignedTo(dto.getAssignedTo());
        inquiry.setSource(dto.getSource());
        inquiry.setPriority(dto.getPriority());
        inquiry.setFollowUpDate(dto.getFollowUpDate());
        inquiry.setLastAction(dto.getLastAction());

        return repository.save(inquiry);
    }

    // =====================================
    // STATUS UPDATE ONLY
    // =====================================

    @Override
    public Inquiry updateStatus(Long id, String status) {

        Inquiry inquiry = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inquiry not found"));

        inquiry.setStatus(status);

        return repository.save(inquiry);
    }

    @Override
    public Inquiry getInquiryById(Long id) {

        return repository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Inquiry Not Found"));
    }
}