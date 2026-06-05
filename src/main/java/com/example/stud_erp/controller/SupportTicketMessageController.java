package com.example.stud_erp.controller;

import com.example.stud_erp.entity.SupportTicketMessage;
import com.example.stud_erp.payload.SupportTicketMessageDTO;
import com.example.stud_erp.service.SupportTicketMessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/support/chat")
@CrossOrigin("*")
public class SupportTicketMessageController {

    private final SupportTicketMessageService service;

    public SupportTicketMessageController(
            SupportTicketMessageService service
    ) {
        this.service = service;
    }

    @PostMapping("/send")
    public SupportTicketMessage sendMessage(
            @RequestBody
            SupportTicketMessageDTO dto
    ) {

        return service.sendMessage(dto);
    }

    @GetMapping("/{ticketId}")
    public List<SupportTicketMessage> getMessages(
            @PathVariable Long ticketId
    ) {

        return service.getMessages(ticketId);
    }
}