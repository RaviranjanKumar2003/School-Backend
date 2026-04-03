package com.example.stud_erp.controller;

import com.example.stud_erp.entity.Event;
import com.example.stud_erp.service.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin("*")
public class EventController {

    @Autowired
    private EventService eventService;

    // ✅ CREATE (PRIVATE BY DEFAULT)
    @PostMapping
    public Event create(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    // ✅ ADMIN: ALL EVENTS
    @GetMapping
    public List<Event> getAll() {
        return eventService.getAllEvents();
    }

    // ✅ GET ONE
    @GetMapping("/{id}")
    public Event getOne(@PathVariable Long id) {
        return eventService.getById(id);
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public Event update(@PathVariable Long id, @RequestBody Event event) {
        return eventService.updateEvent(id, event);
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return eventService.deleteEvent(id);
    }

    // 🔥 PUBLISH EVENT
    @PutMapping("/publish/{id}")
    public Event publishEvent(
            @PathVariable Long id,
            @RequestParam String target // ALL / TEACHER / STUDENT
    ) {
        return eventService.publishEvent(id, target);
    }

    // 🔥 UNPUBLISH EVENT (NEW 🔥)
    @PutMapping("/unpublish/{id}")
    public Event unpublishEvent(@PathVariable Long id) {
        return eventService.unpublishEvent(id);
    }

    // 🔥 STUDENT EVENTS
    @GetMapping("/student")
    public List<Event> getStudentEvents() {
        return eventService.getStudentEvents();
    }

    // 🔥 TEACHER EVENTS
    @GetMapping("/teacher")
    public List<Event> getTeacherEvents() {
        return eventService.getTeacherEvents();
    }

    // 🔥 ROLE BASED
    @GetMapping("/public")
    public List<Event> getByRole(@RequestParam String role) {
        return eventService.getPublicEventsByRole(role);
    }
}