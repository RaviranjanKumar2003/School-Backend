package com.example.stud_erp.service;

import com.example.stud_erp.entity.SupportTicket;
import com.example.stud_erp.payload.SupportTicketDTO;

import java.util.List;

public interface SupportTicketService {

    SupportTicket createTicket(
            SupportTicketDTO dto
    );

    List<SupportTicket> getAllTickets();

    List<SupportTicket> getMyTickets(
            Long schoolAdminId
    );

    SupportTicket startWork(
            Long ticketId
    );

    SupportTicket resolveTicket(
            Long ticketId,
            String note
    );

    SupportTicket closeTicket(
            Long ticketId
    );

    List<SupportTicket> getCreatorTickets(
            Long creatorId,
            String creatorRole
    );
}