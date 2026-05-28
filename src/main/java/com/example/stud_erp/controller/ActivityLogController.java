package com.example.stud_erp.controller;

import com.example.stud_erp.entity.ActivityLog;
import com.example.stud_erp.repository.ActivityLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activity")
@CrossOrigin("*")
public class ActivityLogController {

    @Autowired
    private ActivityLogRepository repo;

    @GetMapping("/{schoolId}")
    public List<ActivityLog> getActivities(
            @PathVariable Long schoolId
    ) {

        return repo
                .findTop10BySchoolIdOrderByCreatedAtDesc(
                        schoolId
                );
    }
}