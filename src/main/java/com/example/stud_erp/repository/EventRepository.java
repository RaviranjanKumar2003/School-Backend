package com.example.stud_erp.repository;

import com.example.stud_erp.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    /* 🔥 ================= ADMIN ================= */

    // All events (latest first)
    List<Event> findAllByOrderByDateDesc();


    /* 🔥 ================= PUBLIC EVENTS ================= */

    // All published events
    List<Event> findByIsPublishedTrueOrderByDateDesc();


    /* 🔥 ================= TARGET BASED ================= */

    // Student / Teacher / All filter
    List<Event> findByIsPublishedTrueAndTargetInOrderByDateDesc(List<String> targets);


    /* 🔥 ================= OPTIONAL (PRO LEVEL) ================= */

    // Only TEACHER events
    List<Event> findByIsPublishedTrueAndTargetOrderByDateDesc(String target);

    // Only STUDENT events



    /* 🔥 ================= DATE FILTER (FIXED ✅) ================= */

    // Upcoming events
    List<Event> findByIsPublishedTrueAndDateGreaterThanEqualOrderByDateDesc(LocalDate date);

    // Past events
    List<Event> findByIsPublishedTrueAndDateLessThanOrderByDateDesc(LocalDate date);
}