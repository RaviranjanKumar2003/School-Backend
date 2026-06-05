package com.example.stud_erp.repository;

import com.example.stud_erp.entity.SupportTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupportTicketRepository
        extends JpaRepository<SupportTicket, Long> {

    List<SupportTicket>
    findBySchoolAdminIdOrderByCreatedAtDesc(
            Long schoolAdminId
    );

    List<SupportTicket>
    findBySchoolIdOrderByCreatedAtDesc(
            Long schoolId
    );

    List<SupportTicket>
    findByStatusOrderByCreatedAtDesc(
            String status
    );

    List<SupportTicket>
    findByCreatorIdAndCreatorRoleOrderByCreatedAtDesc(
            Long creatorId,
            String creatorRole
    );
}