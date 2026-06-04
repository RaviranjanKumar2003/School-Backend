package com.example.stud_erp.controller;

import com.example.stud_erp.payload.LiveClassAttendanceDto;
import com.example.stud_erp.service.LiveClassAttendanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/live-attendance")
@CrossOrigin("*")
public class LiveClassAttendanceController {

    private final LiveClassAttendanceService service;

    public LiveClassAttendanceController(
            LiveClassAttendanceService service
    ) {
        this.service = service;
    }

    @PostMapping("/join/{liveClassId}/{studentId}")
    public ResponseEntity<LiveClassAttendanceDto> join(
            @PathVariable Long liveClassId,
            @PathVariable Long studentId
    ) {

        return ResponseEntity.ok(
                service.joinClass(
                        liveClassId,
                        studentId
                )
        );
    }

    @PostMapping("/leave/{liveClassId}/{studentId}")
    public ResponseEntity<LiveClassAttendanceDto> leave(
            @PathVariable Long liveClassId,
            @PathVariable Long studentId
    ) {

        return ResponseEntity.ok(
                service.leaveClass(
                        liveClassId,
                        studentId
                )
        );
    }

    @GetMapping("/class/{liveClassId}")
    public ResponseEntity<List<LiveClassAttendanceDto>>
    getAttendance(
            @PathVariable Long liveClassId
    ) {

        return ResponseEntity.ok(
                service.getAttendanceByClass(
                        liveClassId
                )
        );
    }

    @GetMapping("/count/{liveClassId}")
    public ResponseEntity<Long> count(
            @PathVariable Long liveClassId
    ) {

        return ResponseEntity.ok(
                service.getAttendanceCount(
                        liveClassId
                )
        );
    }
}