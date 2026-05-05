package com.example.stud_erp.controller;

import com.example.stud_erp.entity.StuAttendance;
import com.example.stud_erp.payload.StuAttendanceDTO;
import com.example.stud_erp.service.StuAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stu-attendance")
@CrossOrigin("*")
public class StuAttendanceController {

    @Autowired
    private StuAttendanceService service;

    @PostMapping("/save")
    public String save(
            @RequestParam Integer classNumber,
            @RequestParam String date,
            @RequestBody List<StuAttendanceDTO> list
    ) {
        return service.save(classNumber, LocalDate.parse(date), list);
    }

    @GetMapping
    public List<StuAttendanceDTO> get(
            @RequestParam Integer classNumber,
            @RequestParam String date
    ) {
        return service.getByClassAndDate(classNumber, LocalDate.parse(date));
    }

    @GetMapping("/summary")
    public Map<String, Object> getStudentSummary(@RequestParam String date) {

        LocalDate localDate = LocalDate.parse(date);

        List<StuAttendance> list = service.getByDate(localDate);

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
    public List<Map<String, Object>> getWeeklyStudentSummary() {

        List<Map<String, Object>> result = new ArrayList<>();

        for (int i = 6; i >= 0; i--) {
            LocalDate date = LocalDate.now().minusDays(i);

            List<StuAttendance> list = service.getByDate(date);

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