package com.example.stud_erp.service;

import com.example.stud_erp.entity.SupportTicketMessage;
import com.example.stud_erp.payload.SupportTicketMessageDTO;

import java.util.List;

public interface SupportTicketMessageService {

    SupportTicketMessage sendMessage(
            SupportTicketMessageDTO dto
    );

    List<SupportTicketMessage> getMessages(
            Long ticketId
    );
}