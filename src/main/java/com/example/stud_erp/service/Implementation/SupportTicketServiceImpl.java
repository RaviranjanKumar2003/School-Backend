package com.example.stud_erp.service.Implementation;

import com.example.stud_erp.entity.SupportTicket;
import com.example.stud_erp.payload.NotificationDTO;
import com.example.stud_erp.payload.SupportTicketDTO;
import com.example.stud_erp.repository.SupportTicketRepository;
import com.example.stud_erp.service.NotificationService;
import com.example.stud_erp.service.SupportTicketService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SupportTicketServiceImpl
        implements SupportTicketService {

    private final SupportTicketRepository ticketRepo;

    private final NotificationService notificationService;

    public SupportTicketServiceImpl(
            SupportTicketRepository ticketRepo,
            NotificationService notificationService
    ) {

        this.ticketRepo = ticketRepo;
        this.notificationService = notificationService;
    }

    // ==========================================
    // CREATE TICKET
    // ==========================================

    @Override
    public SupportTicket createTicket(
            SupportTicketDTO dto
    ) {

        SupportTicket ticket =
                new SupportTicket();

        ticket.setSchoolId(
                dto.getSchoolId()
        );

        ticket.setSchoolName(
                dto.getSchoolName()
        );

        ticket.setSchoolAdminId(
                dto.getSchoolAdminId()
        );

        ticket.setSchoolAdminName(
                dto.getSchoolAdminName()
        );

        ticket.setCreatorId(
                dto.getCreatorId()
        );

        ticket.setCreatorName(
                dto.getCreatorName()
        );

        ticket.setCreatorRole(
                dto.getCreatorRole()
        );

        ticket.setSubject(
                dto.getSubject()
        );

        ticket.setDescription(
                dto.getDescription()
        );

        ticket.setCategory(
                dto.getCategory()
        );

        ticket.setPriority(
                dto.getPriority()
        );

        ticket.setStatus(
                "OPEN"
        );

        SupportTicket saved =
                ticketRepo.save(ticket);

        // ==========================================
        // NOTIFY SUPER ADMIN
        // ==========================================

        NotificationDTO notification =
                new NotificationDTO();

        notification.setTitle(
                "New Support Ticket"
        );

        notification.setMessage(
                "School: " + ticket.getSchoolName()
                        + "\nRole: " + ticket.getCreatorRole()
                        + "\nIssue: " + ticket.getSubject()
        );
        notification.setSender(
                ticket.getCreatorName()
        );

        notification.setSenderType(
                ticket.getCreatorRole()
        );

        notification.setSchoolId(
                ticket.getSchoolId()
        );

        notification.setRecipientType(
                "SUPER_ADMIN"
        );

        notificationService.sendNotification(
                notification
        );

        return saved;
    }

    // ==========================================
    // ALL TICKETS (SUPER ADMIN)
    // ==========================================

    @Override
    public List<SupportTicket> getAllTickets() {

        return ticketRepo.findAll();
    }

    // ==========================================
    // MY TICKETS (SCHOOL ADMIN)
    // ==========================================

    @Override
    public List<SupportTicket> getMyTickets(
            Long schoolAdminId
    ) {

        return ticketRepo
                .findBySchoolAdminIdOrderByCreatedAtDesc(
                        schoolAdminId
                );
    }

    // ==========================================
    // START WORK
    // ==========================================

    @Override
    public SupportTicket startWork(
            Long ticketId
    ) {

        SupportTicket ticket =
                ticketRepo.findById(ticketId)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Ticket Not Found"
                                )
                        );

        ticket.setStatus(
                "IN_PROGRESS"
        );

        sendStatusNotification(
                ticket,
                "Ticket In Progress",
                "Your ticket is being reviewed by Super Admin."
        );

        return ticketRepo.save(
                ticket
        );
    }

    // ==========================================
    // RESOLVE
    // ==========================================

    @Override
    public SupportTicket resolveTicket(
            Long ticketId,
            String note
    ) {

        SupportTicket ticket =
                ticketRepo.findById(ticketId)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Ticket Not Found"
                                )
                        );

        ticket.setStatus(
                "RESOLVED"
        );

        ticket.setResolutionNote(
                note
        );

        ticket.setResolvedAt(
                LocalDateTime.now()
        );
        sendStatusNotification(
                ticket,
                "Ticket Resolved",
                "Your ticket has been resolved.\n\n" + note
        );
        return ticketRepo.save(
                ticket
        );
    }

    // ==========================================
    // CLOSE
    // ==========================================

    @Override
    public SupportTicket closeTicket(
            Long ticketId
    ) {

        SupportTicket ticket =
                ticketRepo.findById(ticketId)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Ticket Not Found"
                                )
                        );

        ticket.setStatus(
                "CLOSED"
        );

        sendStatusNotification(
                ticket,
                "Ticket Closed",
                "Your ticket has been closed."
        );

        return ticketRepo.save(
                ticket
        );
    }


    @Override
    public List<SupportTicket> getCreatorTickets(
            Long creatorId,
            String creatorRole
    ) {

        return ticketRepo
                .findByCreatorIdAndCreatorRoleOrderByCreatedAtDesc(
                        creatorId,
                        creatorRole
                );
    }




    private void sendStatusNotification(
            SupportTicket ticket,
            String title,
            String message
    ) {

        NotificationDTO dto = new NotificationDTO();

        dto.setTitle(title);
        dto.setMessage(message);

        dto.setSender("SUPER ADMIN");
        dto.setSenderType("SUPER_ADMIN");

        dto.setSchoolId(ticket.getSchoolId());

        String role = ticket.getCreatorRole();

        if ("STUDENT".equalsIgnoreCase(role)) {

            dto.setRecipientType("SINGLE_STUDENT");
            dto.setStudentId(ticket.getCreatorId());
            dto.setRecipientId(ticket.getCreatorId());

        } else if (
                "TEACHER".equalsIgnoreCase(role)
                        || "PROFESSOR".equalsIgnoreCase(role)
        ) {

            dto.setRecipientType("SINGLE_TEACHER");
            dto.setTeacherId(ticket.getCreatorId());
            dto.setRecipientId(ticket.getCreatorId());

        } else if ("HOD".equalsIgnoreCase(role)) {

            dto.setRecipientType("SINGLE_HOD");
            dto.setRecipientId(ticket.getCreatorId());

        } else if ("RECEPTIONIST".equalsIgnoreCase(role)) {

            dto.setRecipientType(
                    "SINGLE_RECEPTIONIST"
            );

            dto.setRecipientId(
                    ticket.getCreatorId()
            );

        }
        else {

            dto.setRecipientType("SINGLE_ADMIN");
            dto.setRecipientId(ticket.getCreatorId());
        }

        notificationService.sendNotification(dto);
    }
}