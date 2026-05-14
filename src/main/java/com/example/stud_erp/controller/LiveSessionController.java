package com.example.stud_erp.controller;

import com.example.stud_erp.entity.LiveSession;
import com.example.stud_erp.service.LiveSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/live")
public class LiveSessionController {

    @Autowired
    private LiveSessionService service;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // ================= CREATE LIVE SESSION =================

    @PostMapping("/create")
    public LiveSession create(
            @RequestBody LiveSession session
    ) {

        System.out.println("LIVE SESSION PAYLOAD = " + session.getTopic());

        LiveSession created =
                service.createSession(session);

        // ================= REALTIME NOTIFICATION =================

        messagingTemplate.convertAndSend(
                "/topic/live/" + created.getClassName(),
                created
        );

        return created;
    }

    // ================= GET CURRENT ACTIVE SESSION =================

    @GetMapping("/current/{className}")
    public LiveSession current(
            @PathVariable String className
    ) {

        return service.getActiveSession(className);
    }

    // ================= END LIVE SESSION =================

    @PutMapping("/end/{id}")
    public LiveSession end(
            @PathVariable Long id
    ) {

        LiveSession endedSession =
                service.endSession(id);

        // ================= REALTIME END NOTIFICATION =================

        messagingTemplate.convertAndSend(
                "/topic/live/" + endedSession.getClassName(),
                endedSession
        );

        return endedSession;
    }

    // ================= GET ALL ACTIVE SESSIONS =================

    @GetMapping("/all")
    public List<LiveSession> allActiveSessions() {

        return service.getAllActiveSessions();
    }

    // ================= GET CLASS LIVE HISTORY =================

    @GetMapping("/history/{className}")
    public List<LiveSession> classHistory(
            @PathVariable String className
    ) {

        return service.getClassHistory(className);
    }

    // ================= GET PROFESSOR LIVE HISTORY =================

    @GetMapping("/professor/{professorName}")
    public List<LiveSession> professorHistory(
            @PathVariable String professorName
    ) {

        return service.getProfessorHistory(professorName);
    }

    // ================= JOIN LIVE CLASS =================

    @PutMapping("/join/{id}")
    public LiveSession joinLiveClass(
            @PathVariable Long id
    ) {

        return service.incrementParticipants(id);
    }
}