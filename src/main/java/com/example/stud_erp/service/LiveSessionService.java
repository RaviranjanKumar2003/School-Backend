//package com.example.stud_erp.service;
//
//import com.example.stud_erp.entity.LiveSession;
//import com.example.stud_erp.repository.LiveSessionRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Service
//public class LiveSessionService {
//
//    @Autowired
//    private LiveSessionRepository repository;
//
//    // ================= CREATE LIVE SESSION =================
//
//    public LiveSession createSession(LiveSession session) {
//
//        // ================= PROFESSOR ACTIVE CHECK =================
//
//        LiveSession existingProfessorSession =
//                repository.findByProfessorNameAndActiveTrue(
//                        session.getProfessorName()
//                ).orElse(null);
//
//        if (existingProfessorSession != null) {
//
//            throw new RuntimeException(
//                    "Professor already has an active live class"
//            );
//        }
//
//        // ================= CLASS ACTIVE CHECK =================
//
//        LiveSession existingClassSession =
//                repository.findByClassNameAndActiveTrue(
//                        session.getClassName()
//                ).orElse(null);
//
//        if (existingClassSession != null) {
//
//            throw new RuntimeException(
//                    "This class already has an active live session"
//            );
//        }
//
//        // ================= DEFAULT VALUES =================
//
//        session.setActive(true);
//
//        session.setEnded(false);
//
//        session.setParticipants(0);
//
//        session.setStartedAt(LocalDateTime.now());
//
//        return repository.save(session);
//    }
//
//    // ================= END SESSION =================
//
//    public LiveSession endSession(Long id) {
//
//        LiveSession session = repository.findById(id)
//                .orElseThrow(() ->
//                        new RuntimeException("Live session not found")
//                );
//
//        session.setActive(false);
//
//        session.setEnded(true);
//
//        session.setEndedAt(LocalDateTime.now());
//
//        return repository.save(session);
//    }
//
//    // ================= GET CURRENT CLASS SESSION =================
//
//    public LiveSession getActiveSession(String className) {
//
//        return repository
//                .findTopByClassNameAndActiveTrueOrderByStartedAtDesc(
//                        className
//                )
//                .orElse(null);
//    }
//
//    // ================= GET ALL ACTIVE SESSIONS =================
//
//    public List<LiveSession> getAllActiveSessions() {
//
//        return repository.findByActiveTrue();
//    }
//
//    // ================= CLASS HISTORY =================
//
//    public List<LiveSession> getClassHistory(String className) {
//
//        return repository.findByClassNameOrderByStartedAtDesc(
//                className
//        );
//    }
//
//    // ================= PROFESSOR HISTORY =================
//
//    public List<LiveSession> getProfessorHistory(
//            String professorName
//    ) {
//
//        return repository
//                .findByProfessorNameOrderByStartedAtDesc(
//                        professorName
//                );
//    }
//
//    // ================= PARTICIPANTS COUNT =================
//
//    public LiveSession incrementParticipants(Long id) {
//
//        LiveSession session = repository.findById(id)
//                .orElseThrow(() ->
//                        new RuntimeException("Live session not found")
//                );
//
//        Integer currentParticipants =
//                session.getParticipants();
//
//        if (currentParticipants == null) {
//
//            currentParticipants = 0;
//        }
//
//        session.setParticipants(currentParticipants + 1);
//
//        return repository.save(session);
//    }
//}



// updated



package com.example.stud_erp.service;

import com.example.stud_erp.entity.LiveSession;
import com.example.stud_erp.repository.LiveSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LiveSessionService {

    @Autowired
    private LiveSessionRepository repository;

    // ================= CREATE LIVE SESSION =================

    public LiveSession createSession(LiveSession session) {

        // ================= CHECK OLD PROFESSOR SESSION =================

        LiveSession existingProfessorSession =
                repository.findByProfessorNameAndActiveTrue(
                        session.getProfessorName()
                ).orElse(null);

        // AUTO END OLD SESSION

        if (existingProfessorSession != null) {

            existingProfessorSession.setActive(false);

            existingProfessorSession.setEnded(true);

            existingProfessorSession.setEndedAt(
                    LocalDateTime.now()
            );

            repository.save(existingProfessorSession);
        }

        // ================= CHECK OLD CLASS SESSION =================

        LiveSession existingClassSession =
                repository.findByClassNameAndActiveTrue(
                        session.getClassName()
                ).orElse(null);

        // AUTO END OLD CLASS SESSION

        if (existingClassSession != null) {

            existingClassSession.setActive(false);

            existingClassSession.setEnded(true);

            existingClassSession.setEndedAt(
                    LocalDateTime.now()
            );

            repository.save(existingClassSession);
        }

        // ================= CREATE NEW SESSION =================

        session.setActive(true);

        session.setEnded(false);

        session.setParticipants(0);

        session.setStartedAt(
                LocalDateTime.now()
        );

        return repository.save(session);
    }

    // ================= END SESSION =================

    public LiveSession endSession(Long id) {

        LiveSession session = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Live session not found"
                        )
                );

        session.setActive(false);

        session.setEnded(true);

        session.setEndedAt(LocalDateTime.now());

        return repository.save(session);
    }

    // ================= GET CURRENT CLASS SESSION =================

    public LiveSession getActiveSession(String className) {

        return repository
                .findTopByClassNameAndActiveTrueOrderByStartedAtDesc(
                        className
                )
                .orElse(null);
    }

    // ================= GET ALL ACTIVE SESSIONS =================

    public List<LiveSession> getAllActiveSessions() {

        return repository.findByActiveTrue();
    }

    // ================= CLASS HISTORY =================

    public List<LiveSession> getClassHistory(String className) {

        return repository.findByClassNameOrderByStartedAtDesc(
                className
        );
    }

    // ================= PROFESSOR HISTORY =================

    public List<LiveSession> getProfessorHistory(
            String professorName
    ) {

        return repository
                .findByProfessorNameOrderByStartedAtDesc(
                        professorName
                );
    }

    // ================= PARTICIPANTS COUNT =================

    public LiveSession incrementParticipants(Long id) {

        LiveSession session = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Live session not found"
                        )
                );

        Integer currentParticipants =
                session.getParticipants();

        if (currentParticipants == null) {

            currentParticipants = 0;
        }

        session.setParticipants(
                currentParticipants + 1
        );

        return repository.save(session);
    }
}