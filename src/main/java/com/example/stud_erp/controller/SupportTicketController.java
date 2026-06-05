package com.example.stud_erp.controller;

import com.example.stud_erp.entity.SupportTicket;
import com.example.stud_erp.payload.SupportTicketDTO;
import com.example.stud_erp.service.SupportTicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/support")
@CrossOrigin("*")
public class SupportTicketController {

    private final SupportTicketService service;

    public SupportTicketController(
            SupportTicketService service
    ) {
        this.service = service;
    }

    // ==========================================
    // CREATE TICKET
    // ==========================================

    @PostMapping("/create")
    public SupportTicket createTicket(
            @RequestBody
            SupportTicketDTO dto
    ) {

        return service.createTicket(dto);
    }

    // ==========================================
    // SUPER ADMIN
    // ALL TICKETS
    // ==========================================

    @GetMapping("/all")
    public List<SupportTicket> getAllTickets() {

        return service.getAllTickets();
    }

    // ==========================================
    // SCHOOL ADMIN
    // MY TICKETS
    // ==========================================

    @GetMapping("/my/{schoolAdminId}")
    public List<SupportTicket> getMyTickets(

            @PathVariable
            Long schoolAdminId

    ) {

        return service.getMyTickets(
                schoolAdminId
        );
    }


    @GetMapping("/creator/{creatorId}/{role}")
    public List<SupportTicket> getCreatorTickets(

            @PathVariable
            Long creatorId,

            @PathVariable
            String role

    ) {

        return service.getCreatorTickets(
                creatorId,
                role
        );
    }
    // ==========================================
    // START WORK
    // ==========================================

    @PutMapping("/start/{ticketId}")
    public SupportTicket startWork(

            @PathVariable
            Long ticketId

    ) {

        return service.startWork(
                ticketId
        );
    }

    // ==========================================
    // RESOLVE
    // ==========================================

    @PutMapping("/resolve/{ticketId}")
    public SupportTicket resolveTicket(

            @PathVariable
            Long ticketId,

            @RequestParam
            String note

    ) {

        return service.resolveTicket(
                ticketId,
                note
        );
    }

    // ==========================================
    // CLOSE
    // ==========================================

    @PutMapping("/close/{ticketId}")
    public SupportTicket closeTicket(

            @PathVariable
            Long ticketId

    ) {

        return service.closeTicket(
                ticketId
        );
    }
}