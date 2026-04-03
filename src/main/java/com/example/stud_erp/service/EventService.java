package com.example.stud_erp.service;

import com.example.stud_erp.entity.Event;
import com.example.stud_erp.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    /* ✅ CREATE (PRIVATE BY DEFAULT) */
    public Event createEvent(Event event) {
        event.setPublished(false);
        event.setTarget("ALL");
        return eventRepository.save(event);
    }

    /* ✅ ADMIN: ALL EVENTS */
    public List<Event> getAllEvents() {
        return eventRepository.findAllByOrderByDateDesc();
    }

    /* ✅ GET BY ID */
    public Event getById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    /* ✅ UPDATE EVENT */
    public Event updateEvent(Long id, Event updated) {

        Event event = getById(id);

        event.setTitle(updated.getTitle());
        event.setDescription(updated.getDescription());
        event.setDate(updated.getDate());

        return eventRepository.save(event);
    }

    /* ✅ DELETE */
    public String deleteEvent(Long id) {
        eventRepository.deleteById(id);
        return "Event Deleted ✅";
    }

    /* 🔥 PUBLISH EVENT (FIXED TARGET ✅) */
    public Event publishEvent(Long id, String target) {

        Event event = getById(id);

        // ✅ STANDARD TARGET (IMPORTANT FIX)
        if (!target.equals("ALL") &&
                !target.equals("TEACHERS") &&
                !target.equals("STUDENTS")) {
            throw new RuntimeException("Invalid target type");
        }

        event.setPublished(true);
        event.setTarget(target);

        return eventRepository.save(event);
    }

    /* 🔥 UNPUBLISH */
    public Event unpublishEvent(Long id) {
        Event event = getById(id);
        event.setPublished(false);
        return eventRepository.save(event);
    }

    /* 🔥 STUDENT VIEW (FIXED ✅) */
    public List<Event> getStudentEvents() {
        return eventRepository
                .findByIsPublishedTrueAndTargetInOrderByDateDesc(
                        List.of("STUDENTS", "ALL")
                );
    }

    /* 🔥 TEACHER VIEW (FIXED ✅) */
    public List<Event> getTeacherEvents() {
        return eventRepository
                .findByIsPublishedTrueAndTargetInOrderByDateDesc(
                        List.of("TEACHERS", "ALL")
                );
    }

    /* 🔥 UPCOMING EVENTS (NEW 🔥) */
    public List<Event> getUpcomingEvents() {
        return eventRepository
                .findByIsPublishedTrueAndDateGreaterThanEqualOrderByDateDesc(LocalDate.now());
    }

    /* 🔥 PAST EVENTS (NEW 🔥) */
    public List<Event> getPastEvents() {
        return eventRepository
                .findByIsPublishedTrueAndDateLessThanOrderByDateDesc(LocalDate.now());
    }

    /* 🔥 ROLE BASED */
    public List<Event> getPublicEventsByRole(String role) {

        if (role.equalsIgnoreCase("STUDENT")) {
            return getStudentEvents();
        }
        else if (role.equalsIgnoreCase("TEACHER")) {
            return getTeacherEvents();
        }
        else {
            return List.of();
        }
    }
}