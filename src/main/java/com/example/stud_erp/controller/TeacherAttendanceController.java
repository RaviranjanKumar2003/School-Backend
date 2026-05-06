package com.example.stud_erp.controller;

import com.example.stud_erp.entity.TeacherAttendance;
import com.example.stud_erp.service.Implementation.TeacherAttendanceServiceImpl;
import com.example.stud_erp.service.TeacherAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/attendance/teacher")
@CrossOrigin("*")
public class TeacherAttendanceController {

    @Autowired
    private TeacherAttendanceServiceImpl service;

    @Autowired
    private TeacherAttendanceService teacherAttendanceService;

    // SAVE
    @PostMapping("/save")
    public String save(@RequestBody List<TeacherAttendance> list) {
        return service.saveOrUpdate(list);
    }


    @GetMapping
    public List<TeacherAttendance> getByDate(@RequestParam String date) {
        return teacherAttendanceService.getByDate(LocalDate.parse(date));
    }


    @GetMapping("/summary")
    public Map<String, Object> getTeacherSummary(@RequestParam String date) {

        LocalDate localDate = LocalDate.parse(date);

        List<TeacherAttendance> list =
                teacherAttendanceService.getByDate(localDate);

        long total = list.size();

        long present = list.stream()
                .filter(a -> "P".equalsIgnoreCase(a.getStatus()))
                .count();

        long absent = total - present;

        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("present", present);
        map.put("absent", absent);

        return map;
    }


    @GetMapping("/weekly-summary")
    public List<Map<String, Object>> getWeeklyTeacherSummary() {

        List<Map<String, Object>> result = new ArrayList<>();

        for (int i = 6; i >= 0; i--) {
            LocalDate date = LocalDate.now().minusDays(i);

            List<TeacherAttendance> list =
                    teacherAttendanceService.getByDate(date);

            long present = list.stream()
                    .filter(a -> "P".equalsIgnoreCase(a.getStatus()))
                    .count();

            Map<String, Object> map = new HashMap<>();
            map.put("date", date.toString());
            map.put("present", present);

            result.add(map);
        }

        return result;
    }
}