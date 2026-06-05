package com.example.stud_erp.service;

import com.example.stud_erp.entity.SchoolAdmin;
import com.example.stud_erp.payload.NotificationDTO;
import com.example.stud_erp.payload.SuperAdminNoticeDTO;
import com.example.stud_erp.repository.SchoolAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuperAdminNoticeService {

    @Autowired
    private SchoolAdminRepository schoolAdminRepository;

    @Autowired
    private NotificationService notificationService;

    public void sendNotice(
            SuperAdminNoticeDTO dto
    ) {

        // ALL SCHOOLS

        if ("ALL_SCHOOLS".equals(
                dto.getRecipientType()
        )) {

            List<SchoolAdmin> admins =
                    schoolAdminRepository.findAll();

            for (SchoolAdmin admin : admins) {

                NotificationDTO n =
                        new NotificationDTO();

                n.setTitle(dto.getTitle());

                n.setMessage(dto.getMessage());

                n.setSender("SUPER ADMIN");

                n.setSenderType("SUPER_ADMIN");

                n.setRecipientType("SINGLE_ADMIN");

                n.setRecipientId(admin.getId());

                n.setSchoolId(
                        admin.getSchool().getId()
                );

                notificationService
                        .sendNotification(n);
            }
        }

        // SINGLE SCHOOL

        else if (

                "SINGLE_SCHOOL".equals(
                        dto.getRecipientType()
                )

        ) {

            List<SchoolAdmin> admins =

                    schoolAdminRepository
                            .findBySchoolId(
                                    dto.getSchoolId()
                            );

            for (SchoolAdmin admin : admins) {

                NotificationDTO n =
                        new NotificationDTO();

                n.setTitle(dto.getTitle());

                n.setMessage(dto.getMessage());

                n.setSender("SUPER ADMIN");

                n.setSenderType("SUPER_ADMIN");

                n.setRecipientType("SINGLE_ADMIN");

                n.setRecipientId(admin.getId());

                n.setSchoolId(
                        admin.getSchool().getId()
                );

                notificationService
                        .sendNotification(n);
            }
        }
    }
}