//package com.example.stud_erp.controller;
//
<<<<<<< HEAD
//import com.example.stud_erp.entity.StuAttendance;
=======
>>>>>>> 5bf6a9a (work done)
//import com.example.stud_erp.payload.StuAttendanceDTO;
//import com.example.stud_erp.service.StuAttendanceService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDate;
<<<<<<< HEAD
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
=======
//import java.util.List;
>>>>>>> 5bf6a9a (work done)
//
//@RestController
//@RequestMapping("/api/stu-attendance")
//@CrossOrigin("*")
//public class StuAttendanceController {
//
//    @Autowired
//    private StuAttendanceService service;
//
//    @PostMapping("/save")
//    public String save(
//            @RequestParam Integer classNumber,
//            @RequestParam String date,
//            @RequestBody List<StuAttendanceDTO> list
//    ) {
//        return service.save(classNumber, LocalDate.parse(date), list);
//    }
//
//    @GetMapping
//    public List<StuAttendanceDTO> get(
//            @RequestParam Integer classNumber,
//            @RequestParam String date
//    ) {
//        return service.getByClassAndDate(classNumber, LocalDate.parse(date));
//    }
<<<<<<< HEAD
//
//    @GetMapping("/summary")
//    public Map<String, Object> getStudentSummary(@RequestParam String date) {
//
//        LocalDate localDate = LocalDate.parse(date);
//
//        List<StuAttendance> list = service.getByDate(localDate);
//
//        long total = list.size();
//
//        long present = list.stream()
//                .filter(a -> "P".equalsIgnoreCase(a.getStatus()))
//                .count();
//
//        long absent = total - present;
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("total", total);
//        map.put("present", present);
//        map.put("absent", absent);
//
//        return map;
//    }
//
//
//
//    @GetMapping("/weekly-summary")
//    public List<Map<String, Object>> getWeeklyStudentSummary() {
//
//        List<Map<String, Object>> result = new ArrayList<>();
//
//        for (int i = 6; i >= 0; i--) {
//            LocalDate date = LocalDate.now().minusDays(i);
//
//            List<StuAttendance> list = service.getByDate(date);
//
//            long present = list.stream()
//                    .filter(a -> "P".equalsIgnoreCase(a.getStatus()))
//                    .count();
//
//            Map<String, Object> map = new HashMap<>();
//            map.put("date", date.toString());
//            map.put("present", present);
//
//            result.add(map);
//        }
//
//        return result;
//    }
//
//}


=======
//}



//update

//package com.example.stud_erp.controller;
//
//import com.example.stud_erp.payload.StuAttendanceDTO;
//import com.example.stud_erp.service.StuAttendanceService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/attendance")
//@CrossOrigin("*")
//public class StuAttendanceController {
//
//    @Autowired
//    private StuAttendanceService service;
//
//    // =====================================================
//    // ✅ SAVE / UPDATE ATTENDANCE (Using className)
//    // =====================================================
//    @PostMapping("/save")
//    public ResponseEntity<?> save(@RequestBody Map<String, Object> payload) {
//        try {
//            // Frontend se 'classNumber' aa raha hai, use 'className' variable mein store karenge
//            // Agar aapke frontend se value "1", "2" aa rahi hai toh wo yahan string ban jayegi
//            String className = payload.get("classNumber").toString();
//            String dateStr = (String) payload.get("date");
//
//            // Students ki list nikaalna
//            List<Map<String, Object>> studentsRaw = (List<Map<String, Object>>) payload.get("students");
//
//            // Service ko call karna (Ab 'className' define ho chuka hai)
//            String result = service.save(
//                    className,
//                    LocalDate.parse(dateStr),
//                    null // Yahan aapka students conversion logic aayega agar DTO list chahiye
//            );
//
//            return ResponseEntity.ok(Map.of("message", result));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
//        }
//    }
//
//    // =====================================================
//    // ✅ GET ATTENDANCE (Using className in Path)
//    // =====================================================
//    @GetMapping("/class/{className}/date/{date}")
//    public ResponseEntity<?> get(
//            @PathVariable String className, // Ab ye String className lega
//            @PathVariable String date
//    ) {
//        try {
//            List<StuAttendanceDTO> data = service.getByClassAndDate(
//                    className,
//                    LocalDate.parse(date)
//            );
//
//            return ResponseEntity.ok(data);
//
//        } catch (Exception e) {
//            return ResponseEntity
//                    .internalServerError()
//                    .body("Error fetching attendance for " + className);
//        }
//    }
//}


// update it



//package com.example.stud_erp.controller;
//
//import com.example.stud_erp.payload.StuAttendanceDTO;
//import com.example.stud_erp.service.StuAttendanceService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/api/stu-attendance") // Mapping unique rakhi hai taaki crash na ho
//@CrossOrigin("*")
//public class StuAttendanceController {
//
//    @Autowired
//    private StuAttendanceService service;
//
//    // =====================================================
//    // ✅ SAVE / UPDATE ATTENDANCE
//    // =====================================================
//    @PostMapping("/save")
//    public ResponseEntity<?> save(@RequestBody Map<String, Object> payload) {
//        try {
//            // 1. Extract Basic Details
//            String className = payload.get("classNumber").toString();
//            String dateStr = (String) payload.get("date");
//
//            // 2. RAW List ko DTO List mein convert karna (CRITICAL STEP)
//            List<Map<String, Object>> studentsRaw = (List<Map<String, Object>>) payload.get("students");
//
//            List<StuAttendanceDTO> dtoList = studentsRaw.stream().map(s -> {
//                StuAttendanceDTO dto = new StuAttendanceDTO();
//                // Frontend se studentId aa raha hai
//                dto.setStudentId(Long.valueOf(s.get("studentId").toString()));
//                dto.setStatus(s.get("status").toString());
//                return dto;
//            }).collect(Collectors.toList());
//
//            // 3. Service ko full list bhej rahe hain (null nahi)
//            String result = service.save(
//                    className,
//                    LocalDate.parse(dateStr),
//                    dtoList
//            );
//
//            return ResponseEntity.ok(Map.of("message", result));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.badRequest().body("❌ Error: " + e.getMessage());
//        }
//    }
//
//    // =====================================================
//    // ✅ GET / PREFILL ATTENDANCE
//    // =====================================================
//    @GetMapping("/class/{className}/date/{date}")
//    public ResponseEntity<?> get(
//            @PathVariable String className,
//            @PathVariable String date
//    ) {
//        try {
//            List<StuAttendanceDTO> data = service.getByClassAndDate(
//                    className,
//                    LocalDate.parse(date)
//            );
//            return ResponseEntity.ok(data);
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body("❌ Error fetching data: " + e.getMessage());
//        }
//    }
//}





//package com.example.stud_erp.controller;
//
//import com.example.stud_erp.payload.StuAttendanceDTO;
//import com.example.stud_erp.service.StuAttendanceService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/api/stu-attendance")
//@CrossOrigin("*")
//public class StuAttendanceController {
//
//    @Autowired
//    private StuAttendanceService service;
//
//    // =========================================
//    // SAVE / UPDATE ATTENDANCE
//    // =========================================
//    @PostMapping("/save")
//    public ResponseEntity<?> save(@RequestBody Map<String, Object> payload) {
//
//        try {
//
//            // ✅ FRONTEND KE ACCORDING FIELD NAMES
//            String className = payload.get("className").toString();
//            String dateStr = payload.get("attendanceDate").toString();
//
//            // ✅ STUDENTS LIST
//            List<Map<String, Object>> studentsRaw =
//                    (List<Map<String, Object>>) payload.get("students");
//
//            List<StuAttendanceDTO> dtoList = studentsRaw.stream().map(s -> {
//
//                StuAttendanceDTO dto = new StuAttendanceDTO();
//
//                dto.setStudentId(
//                        Long.valueOf(s.get("studentId").toString())
//                );
//
//                dto.setStatus(
//                        s.get("status").toString()
//                );
//
//                return dto;
//
//            }).collect(Collectors.toList());
//
//            // ✅ SAVE SERVICE
//            String result = service.save(
//                    className,
//                    LocalDate.parse(dateStr),
//                    dtoList
//            );
//
//            return ResponseEntity.ok(
//                    Map.of("message", result)
//            );
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//            return ResponseEntity.badRequest().body(
//                    Map.of(
//                            "success", false,
//                            "message", e.getMessage()
//                    )
//            );
//        }
//    }
//
//    // =========================================
//    // GET ATTENDANCE
//    // =========================================
//    @GetMapping("/class/{className}/date/{date}")
//    public ResponseEntity<?> get(
//            @PathVariable String className,
//            @PathVariable String date
//    ) {
//
//        try {
//
//            List<StuAttendanceDTO> data =
//                    service.getByClassAndDate(
//                            className,
//                            LocalDate.parse(date)
//                    );
//
//            return ResponseEntity.ok(data);
//
//        } catch (Exception e) {
//
//            return ResponseEntity.status(500).body(
//                    Map.of(
//                            "success", false,
//                            "message", e.getMessage()
//                    )
//            );
//        }
//    }
//}

//
//package com.example.stud_erp.controller;
//
//import com.example.stud_erp.payload.StuAttendanceDTO;
//import com.example.stud_erp.service.StuAttendanceService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/api/stu-attendance")
//@CrossOrigin("*")
//public class StuAttendanceController {
//
//    @Autowired
//    private StuAttendanceService service;
//
//    // =========================================
//    // SAVE / UPDATE ATTENDANCE
//    // =========================================
//    @PostMapping("/save")
//    public ResponseEntity<?> save(
//            @RequestBody Map<String, Object> payload
//    ) {
//
//        try {
//
//            // =========================================
//            // BASIC DETAILS
//            // =========================================
//            final String className =
//                    payload.get("className").toString();
//
//            final String dateStr =
//                    payload.get("attendanceDate").toString();
//
//            // =========================================
//            // OPTIONAL FIELDS
//            // =========================================
//            final String professorName =
//                    payload.get("professorName") != null
//                            ? payload.get("professorName").toString()
//                            : "";
//
//            final String subjectName =
//                    payload.get("subjectName") != null
//                            ? payload.get("subjectName").toString()
//                            : "";
//
//            final String attendanceTime =
//                    payload.get("attendanceTime") != null
//                            ? payload.get("attendanceTime").toString()
//                            : null;
//
//            // =========================================
//            // STUDENTS LIST
//            // =========================================
//            List<Map<String, Object>> studentsRaw =
//                    (List<Map<String, Object>>)
//                            payload.get("students");
//
//            // =========================================
//            // CONVERT TO DTO
//            // =========================================
//            List<StuAttendanceDTO> dtoList =
//
//                    studentsRaw.stream().map(s -> {
//
//                        StuAttendanceDTO dto =
//                                new StuAttendanceDTO();
//
//                        // =========================================
//                        // STUDENT ID
//                        // =========================================
//                        dto.setStudentId(
//
//                                Long.valueOf(
//                                        s.get("studentId")
//                                                .toString()
//                                )
//                        );
//
//                        // =========================================
//                        // STATUS
//                        // =========================================
//                        dto.setStatus(
//
//                                s.get("status")
//                                        .toString()
//                        );
//
//                        // =========================================
//                        // EXTRA DETAILS
//                        // =========================================
//                        dto.setProfessorName(
//                                professorName
//                        );
//
//                        dto.setSubjectName(
//                                subjectName
//                        );
//
//                        dto.setClassName(
//                                className
//                        );
//
//                        dto.setAttendanceDate(
//                                LocalDate.parse(dateStr)
//                        );
//
//                        // =========================================
//                        // ATTENDANCE TIME
//                        // =========================================
//                        if (attendanceTime != null
//                                && !attendanceTime.isEmpty()) {
//
//                            dto.setAttendanceTime(
//
//                                    LocalTime.parse(
//                                            attendanceTime
//                                    )
//                            );
//
//                        }
//
//                        return dto;
//
//                    }).collect(Collectors.toList());
//
//            // =========================================
//            // SAVE
//            // =========================================
//            String result = service.save(
//
//                    className,
//
//                    LocalDate.parse(dateStr),
//
//                    dtoList
//
//            );
//
//            return ResponseEntity.ok(
//
//                    Map.of(
//
//                            "success", true,
//
//                            "message", result
//
//                    )
//
//            );
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//            return ResponseEntity.badRequest().body(
//
//                    Map.of(
//
//                            "success", false,
//
//                            "message", e.getMessage()
//
//                    )
//
//            );
//        }
//    }
//
//    // =========================================
//    // GET ATTENDANCE
//    // =========================================
//    @GetMapping("/class/{className}/date/{date}")
//    public ResponseEntity<?> get(
//
//            @PathVariable String className,
//
//            @PathVariable String date
//
//    ) {
//
//        try {
//
//            List<StuAttendanceDTO> data =
//
//                    service.getByClassAndDate(
//
//                            className,
//
//                            LocalDate.parse(date)
//
//                    );
//
//            return ResponseEntity.ok(data);
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//            return ResponseEntity.status(500).body(
//
//                    Map.of(
//
//                            "success", false,
//
//                            "message", e.getMessage()
//
//                    )
//
//            );
//        }
//    }
//}




>>>>>>> 5bf6a9a (work done)
package com.example.stud_erp.controller;

import com.example.stud_erp.entity.StuAttendance;
import com.example.stud_erp.payload.StuAttendanceDTO;
import com.example.stud_erp.service.StuAttendanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
<<<<<<< HEAD
import java.util.*;
=======
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
>>>>>>> 5bf6a9a (work done)

@RestController
@RequestMapping("/api/stu-attendance")
@CrossOrigin("*")
public class StuAttendanceController {

    @Autowired
    private StuAttendanceService service;

<<<<<<< HEAD
    // ================= SAVE =================
    @PostMapping("/save")
    public String save(

            @RequestParam Long schoolId,

            @RequestParam Long classId,

            @RequestParam String date,

            @RequestParam Long takenById,

            @RequestParam String takenByName,

            @RequestParam String takenByRole,

            @RequestBody List<StuAttendanceDTO> list
    ) {

        return service.save(
                schoolId,
                classId,
                LocalDate.parse(date),
                takenById,
                takenByName,
                takenByRole,
                list
        );
    }

    // ================= GET =================
    @GetMapping
    public List<StuAttendanceDTO> get(

            @RequestParam Long schoolId,

            @RequestParam Long classId,

            @RequestParam String date
    ) {

        return service.getByClassAndDate(
                schoolId,
                classId,
                LocalDate.parse(date)
        );
=======
    // =========================================
    // SAVE / UPDATE ATTENDANCE
    // =========================================
    @PostMapping("/save")
    public ResponseEntity<?> save(
            @RequestBody Map<String, Object> payload
    ) {

        try {

            // =========================================
            // BASIC DETAILS
            // =========================================
            final String className =
                    payload.get("className").toString();

            final String dateStr =
                    payload.get("attendanceDate").toString();

            // =========================================
            // OPTIONAL FIELDS
            // =========================================
            final String professorName =
                    payload.get("professorName") != null
                            ? payload.get("professorName")
                            .toString()
                            .trim()
                            : "Unknown Professor";

            final String subjectName =
                    payload.get("subjectName") != null
                            ? payload.get("subjectName")
                            .toString()
                            .trim()
                            : "GENERAL";

            final String attendanceTime =
                    payload.get("attendanceTime") != null
                            ? payload.get("attendanceTime")
                            .toString()
                            : null;

            System.out.println(
                    "CONTROLLER PROFESSOR => "
                            + professorName
            );

            // =========================================
            // STUDENTS LIST
            // =========================================
            List<Map<String, Object>> studentsRaw =
                    (List<Map<String, Object>>)
                            payload.get("students");

            // =========================================
            // CONVERT TO DTO
            // =========================================
            List<StuAttendanceDTO> dtoList =

                    studentsRaw.stream().map(s -> {

                        StuAttendanceDTO dto =
                                new StuAttendanceDTO();

                        // =========================================
                        // STUDENT ID
                        // =========================================
                        dto.setStudentId(

                                Long.valueOf(
                                        s.get("studentId")
                                                .toString()
                                )
                        );

                        // =========================================
                        // STATUS
                        // =========================================
                        dto.setStatus(

                                s.get("status")
                                        .toString()
                        );

                        // =========================================
                        // CLASS DETAILS
                        // =========================================
                        dto.setClassName(
                                className
                        );

                        dto.setAttendanceDate(
                                LocalDate.parse(dateStr)
                        );

                        // =========================================
                        // PROFESSOR DETAILS
                        // =========================================
                        dto.setProfessorName(
                                professorName
                        );

                        // =========================================
                        // SUBJECT
                        // =========================================
                        dto.setSubjectName(
                                subjectName
                        );

                        // =========================================
                        // ATTENDANCE TIME
                        // =========================================
                        if (
                                attendanceTime != null
                                        && !attendanceTime.isEmpty()
                        ) {

                            dto.setAttendanceTime(

                                    LocalTime.parse(
                                            attendanceTime
                                    )
                            );

                        } else {

                            dto.setAttendanceTime(
                                    LocalTime.now()
                            );

                        }

                        System.out.println(
                                "DTO PROFESSOR => "
                                        + dto.getProfessorName()
                        );

                        return dto;

                    }).collect(Collectors.toList());

            // =========================================
            // SAVE
            // =========================================
            String result = service.save(

                    className,

                    LocalDate.parse(dateStr),

                    dtoList

            );

            return ResponseEntity.ok(

                    Map.of(

                            "success", true,

                            "message", result

                    )

            );

        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity.badRequest().body(

                    Map.of(

                            "success", false,

                            "message", e.getMessage()

                    )

            );
        }
    }

    // =========================================
    // GET ATTENDANCE BY CLASS + DATE
    // =========================================
    @GetMapping("/class/{className}/date/{date}")
    public ResponseEntity<?> get(

            @PathVariable String className,

            @PathVariable String date

    ) {

        try {

            List<StuAttendanceDTO> data =

                    service.getByClassAndDate(

                            className,

                            LocalDate.parse(date)

                    );

            return ResponseEntity.ok(data);

        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity.status(500).body(

                    Map.of(

                            "success", false,

                            "message", e.getMessage()

                    )

            );
        }
    }

    // =========================================
    // GET ALL ATTENDANCE OF CLASS
    // =========================================
    @GetMapping("/class/{className}")
    public ResponseEntity<?> getClassAttendance(

            @PathVariable String className

    ) {

        try {

            List<StuAttendanceDTO> data =
                    service.getByClass(className);

            return ResponseEntity.ok(data);

        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity.status(500).body(

                    Map.of(

                            "success", false,

                            "message", e.getMessage()

                    )

            );
        }
    }

    // =========================================
    // GET MONTHLY REPORT
    // =========================================
    @GetMapping("/monthly-report/{className}")
    public ResponseEntity<?> getMonthlyReport(

            @PathVariable String className,

            @RequestParam int month,

            @RequestParam int year

    ) {

        try {

            List<StuAttendanceDTO> data =

                    service.getMonthlyAttendance(

                            className,

                            month,

                            year

                    );

            return ResponseEntity.ok(data);

        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity.status(500).body(

                    Map.of(

                            "success", false,

                            "message", e.getMessage()

                    )

            );
        }
    }

    // =========================================
    // GET YEARLY REPORT
    // =========================================
    @GetMapping("/yearly-report/{className}")
    public ResponseEntity<?> getYearlyReport(

            @PathVariable String className,

            @RequestParam int year

    ) {

        try {

            List<StuAttendanceDTO> data =

                    service.getYearlyAttendance(

                            className,

                            year

                    );

            return ResponseEntity.ok(data);

        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity.status(500).body(

                    Map.of(

                            "success", false,

                            "message", e.getMessage()

                    )

            );
        }
    }

    // =========================================
    // GET ALL ATTENDANCE
    // =========================================
    @GetMapping("/all")
    public ResponseEntity<?> getAllAttendance() {

        try {

            List<StuAttendanceDTO> data =
                    service.getAllAttendance();

            return ResponseEntity.ok(data);

        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity.status(500).body(

                    Map.of(

                            "success", false,

                            "message", e.getMessage()

                    )

            );
        }
    }

    // =========================================
    // GET MONTHLY SUMMARY
    // =========================================
    @GetMapping("/monthly-summary/{year}")
    public ResponseEntity<?> getMonthlySummary(

            @PathVariable int year

    ) {

        try {

            return ResponseEntity.ok(

                    service.getMonthlySummary(year)

            );

        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity.status(500).body(

                    Map.of(

                            "success", false,

                            "message", e.getMessage()

                    )

            );
        }
    }

    // =========================================
    // GET YEARLY SUMMARY
    // =========================================
    @GetMapping("/yearly-summary")
    public ResponseEntity<?> getYearlySummary() {

        try {

            return ResponseEntity.ok(

                    service.getYearlySummary()

            );

        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity.status(500).body(

                    Map.of(

                            "success", false,

                            "message", e.getMessage()

                    )

            );
        }
    }

    // =========================================
    // GET STUDENT ATTENDANCE %
    // =========================================
    @GetMapping("/student-percentage/{studentId}")
    public ResponseEntity<?> getStudentPercentage(

            @PathVariable Long studentId

    ) {

        try {

            double percentage =
                    service.getStudentAttendancePercentage(
                            studentId
                    );

            return ResponseEntity.ok(

                    Map.of(

                            "studentId", studentId,

                            "attendancePercentage",
                            percentage

                    )

            );

        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity.status(500).body(

                    Map.of(

                            "success", false,

                            "message", e.getMessage()

                    )

            );
        }
>>>>>>> 5bf6a9a (work done)
    }

    // ================= DAILY SUMMARY =================
    @GetMapping("/summary/{schoolId}")
    public Map<String, Object> getStudentSummary(

            @PathVariable Long schoolId,

            @RequestParam String date
    ) {

        LocalDate localDate =
                LocalDate.parse(date);

        List<StuAttendance> list =
                service.getByDate(
                        schoolId,
                        localDate
                );

        long total = list.size();

        long present = list.stream()
                .filter(a ->
                        "P".equalsIgnoreCase(
                                a.getStatus()
                        )
                )
                .count();

        long absent = total - present;

        Map<String, Object> map =
                new HashMap<>();

        map.put("total", total);

        map.put("present", present);

        map.put("absent", absent);

        return map;
    }

    // ================= WEEKLY SUMMARY =================
    @GetMapping("/weekly-summary/{schoolId}")
    public List<Map<String, Object>>
    getWeeklyStudentSummary(

            @PathVariable Long schoolId
    ) {

        List<Map<String, Object>> result =
                new ArrayList<>();

        for (int i = 6; i >= 0; i--) {

            LocalDate date =
                    LocalDate.now().minusDays(i);

            List<StuAttendance> list =
                    service.getByDate(
                            schoolId,
                            date
                    );

            long present = list.stream()
                    .filter(a ->
                            "P".equalsIgnoreCase(
                                    a.getStatus()
                            )
                    )
                    .count();

            long absent = list.size() - present;

            Map<String, Object> map =
                    new HashMap<>();

            map.put("date", date.toString());

            map.put("present", present);

            map.put("absent", absent);

            result.add(map);
        }

        return result;
    }

}