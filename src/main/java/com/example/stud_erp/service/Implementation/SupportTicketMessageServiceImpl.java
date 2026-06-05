package com.example.stud_erp.service.Implementation;

import com.example.stud_erp.entity.SupportTicketMessage;
import com.example.stud_erp.payload.SupportTicketMessageDTO;
import com.example.stud_erp.repository.SupportTicketMessageRepository;
import com.example.stud_erp.service.SupportTicketMessageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupportTicketMessageServiceImpl
        implements SupportTicketMessageService {

    private final SupportTicketMessageRepository repo;

    public SupportTicketMessageServiceImpl(
            SupportTicketMessageRepository repo
    ) {
        this.repo = repo;
    }

    @Override
    public SupportTicketMessage sendMessage(
            SupportTicketMessageDTO dto
    ) {

        SupportTicketMessage msg =
                new SupportTicketMessage();

        msg.setTicketId(
                dto.getTicketId()
        );

        msg.setSenderId(
                dto.getSenderId()
        );

        msg.setSenderName(
                dto.getSenderName()
        );

        msg.setSenderRole(
                dto.getSenderRole()
        );

        msg.setMessage(
                dto.getMessage()
        );
        msg.setImageUrl(
                dto.getImageUrl()
        );

        return repo.save(msg);
    }

    @Override
    public List<SupportTicketMessage> getMessages(
            Long ticketId
    ) {

        return repo
                .findByTicketIdOrderByCreatedAtAsc(
                        ticketId
                );
    }
}