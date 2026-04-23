//package com.example.stud_erp.service;

//package com.example.stud_erp.service;
//
//import com.example.stud_erp.payload.EventResponseDTO;
//import com.example.stud_erp.entity.EventResponse;
//import com.example.stud_erp.repository.EventResponseRepository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class EventResponseService {
//
//    @Autowired
//    private EventResponseRepository repository;
//
//    // 🔥 SAVE / UPDATE RESPONSE
//    public void saveResponse(EventResponseDTO dto) {
//
//        // 🔥 check same teacher already responded
//        Optional<EventResponse> existing =
//                repository.findByEventIdAndTeacherId(dto.getEventId(), dto.getTeacherId());
//
//        EventResponse response;
//
//        if (existing.isPresent()) {
//            // 🔁 UPDATE
//            response = existing.get();
//        } else {
//            // 🆕 NEW
//            response = new EventResponse();
//            response.setEventId(dto.getEventId());
//            response.setTeacherId(dto.getTeacherId());
//
//            // 🔥 TEMP NAME (later DB से ले सकते हो)
//            response.setTeacherName("Teacher " + dto.getTeacherId());
//        }
//
//        response.setStatus(dto.getStatus());
//
//        repository.save(response);
//    }
//
//    // 🔥 GET ALL RESPONSES (ADMIN PANEL)
//    public List<EventResponse> getAllResponses() {
//        return repository.findAll();
//    }
//}




package com.example.stud_erp.service;
import com.example.stud_erp.payload.EventResponseDTO;
import com.example.stud_erp.entity.EventResponse;
import com.example.stud_erp.repository.EventResponseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EventResponseService {

    @Autowired
    private EventResponseRepository repository;

    /* 🔥 SAVE / UPDATE */
    public void saveResponse(EventResponseDTO dto) {

        EventResponse response = repository
                .findByEventIdAndTeacherId(dto.getEventId(), dto.getTeacherId())
                .orElse(null);

        if (response == null) {
            response = new EventResponse();
            response.setEventId(dto.getEventId());
            response.setTeacherId(dto.getTeacherId());

            response.setTeacherName(
                    dto.getTeacherName() != null
                            ? dto.getTeacherName()
                            : "Teacher " + dto.getTeacherId()
            );
        }

        response.setStatus(dto.getStatus());

        repository.save(response);
    }

    /* 🔥 ALL RESPONSES */
    public List<EventResponse> getAllResponses() {
        return repository.findAll();
    }

    /* 🔥 EVENT WISE */
    public List<EventResponseDTO> getResponsesByEvent(Long eventId) {

        List<EventResponse> responses = repository.findByEventId(eventId);
        List<EventResponseDTO> list = new ArrayList<>();

        for (EventResponse r : responses) {

            EventResponseDTO dto = new EventResponseDTO();
            dto.setEventId(r.getEventId());
            dto.setTeacherId(r.getTeacherId());
            dto.setTeacherName(r.getTeacherName());
            dto.setStatus(r.getStatus());

            list.add(dto);
        }

        return list;
    }

    /* 🔥 ANALYTICS */
    public Map<String, Long> getResponseStats(Long eventId) {

        List<EventResponse> responses = repository.findByEventId(eventId);

        long accepted = responses.stream()
                .filter(r -> r.getStatus().equalsIgnoreCase("ACCEPTED"))
                .count();

        long rejected = responses.stream()
                .filter(r -> r.getStatus().equalsIgnoreCase("REJECTED"))
                .count();

        long maybe = responses.stream()
                .filter(r -> r.getStatus().equalsIgnoreCase("MAYBE"))
                .count();

        Map<String, Long> map = new HashMap<>();
        map.put("ACCEPTED", accepted);
        map.put("REJECTED", rejected);
        map.put("MAYBE", maybe);
        map.put("TOTAL", (long) responses.size());

        return map;
    }
}