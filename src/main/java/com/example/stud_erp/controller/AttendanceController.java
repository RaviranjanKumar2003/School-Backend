//package com.example.stud_erp.controller;
//
//import com.example.stud_erp.entity.Attendance;
//import com.example.stud_erp.entity.TeacherAttendance;
//import com.example.stud_erp.payload.ClassSessionDTO;
//import com.example.stud_erp.service.AttendanceService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/attendance")
//@CrossOrigin("*")
//public class AttendanceController {
//
//    @Autowired
//    private AttendanceService service;
//
//    @Autowired
//    private AttendanceService attendanceService;
//
//    // SAVE CLASS ATTENDANCE
//    @PostMapping("/save")
//    public ClassSessionDTO save(@RequestBody ClassSessionDTO dto) {
//        return service.saveAttendance(dto);
//    }
//
//    // CLASS VIEW
//    @GetMapping("/class/{classNumber}")
//    public List<ClassSessionDTO> getByClass(@PathVariable Integer classNumber) {
//        return service.getClassAttendance(classNumber);
//    }
//
//    // STUDENT VIEW
//    @GetMapping("/student/{id}")
//    public List<ClassSessionDTO> getByStudent(@PathVariable Long id) {
//        return service.getStudentAttendance(id);
//    }
//
//    @GetMapping
//    public List<Attendance> getByDate(@RequestParam String date) {
//        return attendanceService.getByDate(LocalDate.parse(date));
//    }
//
//    @GetMapping("/class/{classNumber}/date")
//    public ClassSessionDTO getByClassAndDate(
//            @PathVariable Integer classNumber,
//            @RequestParam String date) {
//
//        return service.getClassAttendanceByDate(
//                classNumber,
//                LocalDate.parse(date)
//        );
//    }
//}



// updated

package com.example.stud_erp.controller;

import com.example.stud_erp.entity.Attendance;
import com.example.stud_erp.payload.ClassSessionDTO;
import com.example.stud_erp.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/general-attendance") // Conflict se bachne ke liye path change kiya
@CrossOrigin("*")
public class AttendanceController {

    @Autowired
    private AttendanceService service;

    // ============================================================
    // ✅ SAVE ATTENDANCE
    // ============================================================
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ClassSessionDTO dto) {
        try {
            ClassSessionDTO savedData = service.saveAttendance(dto);
            return ResponseEntity.ok(savedData);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error saving attendance: " + e.getMessage());
        }
    }

    // ============================================================
    // ✅ CLASS + DATE VIEW (Prefill Logic)
    // ============================================================
    @GetMapping("/class/{className}/date/{date}")
    public ResponseEntity<?> getByClassAndDate(
            @PathVariable String className,
            @PathVariable String date) {
        try {
            ClassSessionDTO data = service.getClassAttendanceByDate(
                    className,
                    LocalDate.parse(date)
            );
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    // ============================================================
    // ✅ STUDENT VIEW (Individual Report)
    // ============================================================
    @GetMapping("/student/{id}")
    public ResponseEntity<?> getByStudent(@PathVariable Long id) {
        try {
            List<ClassSessionDTO> data = service.getStudentAttendance(id);
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error fetching student records");
        }
    }

    // ============================================================
    // ✅ BULK SAVE (For Admin / Large Data)
    // ============================================================
    @PostMapping("/bulk")
    public ResponseEntity<?> saveBulkAttendance(@RequestBody List<Attendance> attendanceList) {
        try {
            service.saveOrUpdateBulkAttendance(attendanceList);
            return ResponseEntity.ok(Map.of("message", "Attendance saved successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bulk save failed: " + e.getMessage());
        }
    }
}