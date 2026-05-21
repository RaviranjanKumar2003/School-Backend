package com.example.stud_erp.controller;

import com.example.stud_erp.entity.WhatsAppMessage;
import com.example.stud_erp.service.WhatsAppMessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/whatsapp")
@CrossOrigin("*")
public class WhatsAppMessageController {

    private final WhatsAppMessageService service;

    public WhatsAppMessageController(WhatsAppMessageService service) {
        this.service = service;
    }

    @PostMapping("/send")
    public WhatsAppMessage send(@RequestBody WhatsAppMessage message) {
        return service.sendMessage(message);
    }

    @GetMapping("/inquiry/{id}")
    public List<WhatsAppMessage> byInquiry(@PathVariable Long id) {
        return service.getByInquiryId(id);
    }

    @GetMapping("/phone/{phone}")
    public List<WhatsAppMessage> byPhone(@PathVariable String phone) {
        return service.getByPhone(phone);
    }
}