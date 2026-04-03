//package com.example.stud_erp.controller;
//import com.example.stud_erp.entity.Attendance;
//import com.example.stud_erp.entity.ClassSession;
//import com.example.stud_erp.service.AttendanceService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.List;
//import java.util.Map;
//
//
//@RestController
//@RequestMapping("/api/attendance")
//public class AttendanceController {
//
//    @Autowired
//    private AttendanceService attendanceService;
//
//    @PostMapping("/save")
//    public ClassSession saveAttendance(@RequestBody Map<String, Object> request) {
//        String lecturer = (String) request.get("professor");  // Changed to "professor"
//        String subject = (String) request.get("subject");
//        LocalDate attendanceDate = LocalDate.parse((String) request.get("attendanceDate"));
//        LocalTime time = LocalTime.parse((String) request.get("time"));
//
//        // Check if students field is present
//        Map<String, String> students = (Map<String, String>) request.get("students");
//
//        if (students == null || students.isEmpty()) {
//            throw new IllegalArgumentException("Students list is required.");
//        }
//
//        return attendanceService.saveAttendance(lecturer, subject, attendanceDate, time, students);
//    }
//
//
//
//
//    @GetMapping("/lecturer/subject")
//    public ResponseEntity<Map<LocalDate, List<Attendance>>> getAttendance(
//            @RequestParam String lecturer,
//            @RequestParam String subject) {
//        Map<LocalDate, List<Attendance>> records = attendanceService.getAttendanceByLecturerAndSubject(lecturer, subject);
//        return ResponseEntity.ok(records);
//    }
//
//
//
//}


// after update




//package com.example.stud_erp.controller;
//
//import com.example.stud_erp.entity.Attendance;
//import com.example.stud_erp.entity.ClassSession;
//import com.example.stud_erp.entity.Student;
//import com.example.stud_erp.service.AttendanceService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/attendance")
//@CrossOrigin("*")
//public class AttendanceController {
//
//    @Autowired
//    private AttendanceService attendanceService;
//
//    // ===============================
//    // ✅ GET STUDENTS BY CLASS
//    // ===============================
//    @GetMapping("/students/{year}")
//    public ResponseEntity<List<Student>> getStudentsByClass(@PathVariable int year) {
//        return ResponseEntity.ok(attendanceService.getStudentsByClass(year));
//    }
//
//    // ===============================
//    // ✅ SAVE ATTENDANCE
//    // ===============================
//    @PostMapping("/save")
//    public ClassSession saveAttendance(@RequestBody Map<String, Object> request) {
//
//        String lecturer = (String) request.get("professor");
//        String subject = (String) request.get("subject");
//        int year = (int) request.get("year");
//
//        LocalDate attendanceDate = LocalDate.parse((String) request.get("attendanceDate"));
//        LocalTime time = LocalTime.parse((String) request.get("time"));
//
//        Map<String, String> rawStudents = (Map<String, String>) request.get("students");
//
//        Map<Long, String> students = new HashMap<>();
//
//        for (Map.Entry<String, String> entry : rawStudents.entrySet()) {
//            students.put(Long.parseLong(entry.getKey()), entry.getValue());
//        }
//
//        return attendanceService.saveAttendance(lecturer, subject, attendanceDate, time, students);
//    }
//}

//update for school



package com.example.stud_erp.controller;

import com.example.stud_erp.entity.ClassSession;
import com.example.stud_erp.entity.Student;
import com.example.stud_erp.service.AttendanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin("*")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    // ===============================
    // ✅ GET STUDENTS BY CLASS (FIXED)
    // ===============================
    @GetMapping("/students/{classNumber}")
    public ResponseEntity<List<Student>> getStudentsByClass(@PathVariable int classNumber) {
        return ResponseEntity.ok(attendanceService.getStudentsByClass(classNumber));
    }

    // ===============================
    // ✅ SAVE ATTENDANCE (FIXED)
    // ===============================
    @PostMapping("/save")
    public ClassSession saveAttendance(@RequestBody Map<String, Object> request) {

        String lecturer = (String) request.get("professor");
        String subject = (String) request.get("subject");

        // ❌ OLD: year
        // int year = (int) request.get("year");

        // ✅ FIX
        int classNumber = (int) request.get("classNumber");

        LocalDate attendanceDate = LocalDate.parse((String) request.get("attendanceDate"));
        LocalTime time = LocalTime.parse((String) request.get("time"));

        Map<String, String> rawStudents = (Map<String, String>) request.get("students");

        Map<Long, String> students = new HashMap<>();

        for (Map.Entry<String, String> entry : rawStudents.entrySet()) {
            students.put(Long.parseLong(entry.getKey()), entry.getValue());
        }

        return attendanceService.saveAttendance(
                lecturer,
                subject,
                attendanceDate,
                time,
                students
        );
    }
}