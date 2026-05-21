package com.example.stud_erp.service.Implementation;

import com.example.stud_erp.entity.WhatsAppMessage;
import com.example.stud_erp.repository.WhatsAppMessageRepository;
import com.example.stud_erp.service.WhatsAppMessageService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WhatsAppMessageServiceImpl implements WhatsAppMessageService {

    private final WhatsAppMessageRepository repository;

    public WhatsAppMessageServiceImpl(WhatsAppMessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public WhatsAppMessage sendMessage(WhatsAppMessage message) {

        message.setSentAt(LocalDateTime.now());

        // future: integrate WhatsApp API here (Twilio / Gupshup / Meta API)

        if (message.getStatus() == null) {
            message.setStatus("SENT");
        }

        return repository.save(message);
    }

    @Override
    public List<WhatsAppMessage> getByInquiryId(Long inquiryId) {
        return repository.findByInquiryId(inquiryId);
    }

    @Override
    public List<WhatsAppMessage> getByPhone(String phone) {
        return repository.findByPhone(phone);
    }
}