package com.example.stud_erp.controller;

import com.example.stud_erp.enums.LiveClassStatus;
import com.example.stud_erp.payload.LiveClassDto;
import com.example.stud_erp.service.LiveClassService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/live-class")
@CrossOrigin("*")
public class LiveClassController {

    private final LiveClassService service;

    public LiveClassController(LiveClassService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> create(
            @RequestBody LiveClassDto dto
    ) {

        service.create(dto);

        return ResponseEntity.ok(
                "Live Class Created Successfully"
        );
    }

    @PutMapping("/start/{id}")
    public ResponseEntity<?> start(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.start(id));
    }

    @PutMapping("/end/{id}")
    public ResponseEntity<?> end(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.end(id));
    }

    @PutMapping("/join/{id}/{studentId}")
    public ResponseEntity<?> join(
            @PathVariable Long id,
            @PathVariable Long studentId
    ) {
        return ResponseEntity.ok(service.join(id, studentId));
    }

    @PutMapping("/leave/{liveClassId}/{studentId}")
    public ResponseEntity<?> leave(
            @PathVariable Long liveClassId,
            @PathVariable Long studentId
    ) {
        return ResponseEntity.ok(service.leave(liveClassId, studentId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody LiveClassDto dto
    ) {
        return ResponseEntity.ok(
                service.update(id, dto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long id
    ) {
        service.delete(id);
        return ResponseEntity.ok("Deleted Successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                service.getById(id)
        );
    }

    @GetMapping("/school/{schoolId}")
    public ResponseEntity<?> schoolClasses(
            @PathVariable Long schoolId
    ) {
        return ResponseEntity.ok(
                service.getSchoolClasses(schoolId)
        );
    }

    @GetMapping("/class/{classId}")
    public ResponseEntity<?> classHistory(
            @PathVariable Long classId
    ) {
        return ResponseEntity.ok(
                service.getClassLiveHistory(classId)
        );
    }

    @GetMapping("/professor/{professorId}")
    public ResponseEntity<?> professorClasses(
            @PathVariable Long professorId
    ) {
        try {
            return ResponseEntity.ok(
                    service.getProfessorClasses(professorId)
            );
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/live")
    public ResponseEntity<?> allLiveClasses() {
        return ResponseEntity.ok(
                service.getAllLiveClasses()
        );
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<?> byDate(
            @PathVariable LocalDate date
    ) {
        return ResponseEntity.ok(
                service.getClassesByDate(date)
        );
    }

    @GetMapping("/status/{schoolId}/{status}")
    public ResponseEntity<?> schoolStatus(
            @PathVariable Long schoolId,
            @PathVariable LiveClassStatus status
    ) {
        return ResponseEntity.ok(
                service.getSchoolClassesByStatus(
                        schoolId,
                        status
                )
        );
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<?> getStudentLiveClasses(@PathVariable Long studentId) {
        return ResponseEntity.ok(service.getLiveClassesForStudent(studentId));
    }
}