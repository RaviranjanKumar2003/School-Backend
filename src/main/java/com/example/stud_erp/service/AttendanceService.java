//package com.example.stud_erp.service;
//
//
//import com.example.stud_erp.entity.Attendance;
//import com.example.stud_erp.entity.ClassSession;
//import com.example.stud_erp.entity.Student;
//import com.example.stud_erp.repository.AttendanceRepository;
//import com.example.stud_erp.repository.ClassRepository;
//import com.example.stud_erp.repository.StudentRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Service
//public class AttendanceService {
//
//    @Autowired
//    private ClassRepository classSessionRepository;
//
//    @Autowired
//    private AttendanceRepository attendanceRecordRepository;
//
//    @Autowired
//    private StudentRepository studentRepository;
//
//    public ClassSession saveAttendance(String lecturer, String subject, LocalDate attendanceDate, LocalTime time, Map<String, String> students) {
//        ClassSession classSession = new ClassSession();
//        classSession.setLecturer(lecturer);
//        classSession.setSubject(subject);
//        classSession.setTime(time);
//
//        List<Attendance> attendanceRecords = new ArrayList<>();
//
//        for (Map.Entry<String, String> entry : students.entrySet()) {
//            Attendance record = new Attendance();
//            record.setStudentName(entry.getKey());
//            record.setStatus(entry.getValue());
//            record.setAttendanceDate(attendanceDate);
//            record.setClassSession(classSession);
//
//            // Find the student by name or another unique identifier
//            Student student = studentRepository.findByStudName(entry.getKey());
//            if (student != null) {
//                record.setStudent(student);  // Set the Student entity in the Attendance
//            } else {
//                throw new IllegalArgumentException("Student not found: " + entry.getKey());
//            }
//
//            attendanceRecords.add(record);
//        }
//
//        classSession.setAttendance(attendanceRecords);
//
//        return classSessionRepository.save(classSession);
//    }
//
//
//    public Map<LocalDate, List<Attendance>> getAttendanceByLecturerAndSubject(String lecturer, String subject) {
//        List<Attendance> records = attendanceRecordRepository.findByClassSessionLecturerAndClassSessionSubject(lecturer, subject);
//
//        return records.stream().collect(Collectors.groupingBy(Attendance::getAttendanceDate));
//    }
//
//}






//package com.example.stud_erp.service;
//
//import com.example.stud_erp.entity.Attendance;
//import com.example.stud_erp.entity.ClassSession;
//import com.example.stud_erp.entity.Student;
//import com.example.stud_erp.repository.AttendanceRepository;
//import com.example.stud_erp.repository.ClassRepository;
//import com.example.stud_erp.repository.StudentRepository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.*;
//import java.util.stream.Collectors;
//
//@Service
//public class AttendanceService {
//
//    @Autowired
//    private ClassRepository classSessionRepository;
//
//    @Autowired
//    private AttendanceRepository attendanceRecordRepository;
//
//    @Autowired
//    private StudentRepository studentRepository;
//
//    // ===============================
//    // SAVE ATTENDANCE
//    // ===============================
//
//    public ClassSession saveAttendance(
//            String lecturer,
//            String subject,
//            LocalDate attendanceDate,
//            LocalTime time,
//            Map<String, String> students
//    ) {
//
//        ClassSession classSession = new ClassSession();
//        classSession.setLecturer(lecturer);
//        classSession.setSubject(subject);
//        classSession.setTime(time);
//
//        List<Attendance> attendanceRecords = new ArrayList<>();
//
//        for (Map.Entry<String, String> entry : students.entrySet()) {
//
//            Attendance record = new Attendance();
//            record.setStudentName(entry.getKey());
//            record.setStatus(entry.getValue());
//            record.setAttendanceDate(attendanceDate);
//            record.setClassSession(classSession);
//
//            // ✅ SAFE FETCH
//            Optional<Student> optionalStudent =
//                    studentRepository.findByStudName(entry.getKey());
//
//            if (optionalStudent.isPresent()) {
//                record.setStudent(optionalStudent.get());
//            } else {
//                System.out.println("⚠️ Student not found: " + entry.getKey());
//            }
//
//            attendanceRecords.add(record);
//        }
//
//        classSession.setAttendance(attendanceRecords);
//
//        return classSessionRepository.save(classSession);
//    }
//
//    // ===============================
//    // GET ATTENDANCE
//    // ===============================
//
//    public Map<LocalDate, List<Attendance>> getAttendanceByLecturerAndSubject(
//            String lecturer,
//            String subject
//    ) {
//
//        List<Attendance> records =
//                attendanceRecordRepository
//                        .findByClassSessionLecturerAndClassSessionSubject(
//                                lecturer,
//                                subject
//                        );
//
//        return records.stream()
//                .collect(Collectors.groupingBy(Attendance::getAttendanceDate));
//    }
//
//    // ===============================
//    // STUDENT ATTENDANCE %
//    // ===============================
//
//    public double getAttendancePercentage(Long studentId) {
//
//        // ✅ FIXED METHOD NAME
//        List<Attendance> records =
//                attendanceRecordRepository.findByStudent_Id(studentId);
//
//        if (records == null || records.isEmpty()) return 0;
//
//        long presentCount =
//                records.stream()
//                        .filter(r -> "PRESENT".equalsIgnoreCase(r.getStatus()))
//                        .count();
//
//        return (presentCount * 100.0) / records.size();
//    }
//}




//update




//package com.example.stud_erp.service;
//
//import com.example.stud_erp.entity.Attendance;
//import com.example.stud_erp.entity.ClassSession;
//import com.example.stud_erp.entity.Student;
//import com.example.stud_erp.repository.AttendanceRepository;
//import com.example.stud_erp.repository.ClassRepository;
//import com.example.stud_erp.repository.StudentRepository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.*;
//
//@Service
//public class AttendanceService {
//
//    @Autowired
//    private ClassRepository classSessionRepository;
//
//    @Autowired
//    private AttendanceRepository attendanceRecordRepository;
//
//    @Autowired
//    private StudentRepository studentRepository;
//
//    // ===============================
//    // ✅ GET STUDENTS BY CLASS (year)
//    // ===============================
//    public List<Student> getStudentsByClass(int year) {
//        return studentRepository.findAll()
//                .stream()
//                .filter(s -> s.getYear() == year && !s.isDeleted())
//                .toList();
//    }
//
//    // ===============================
//    // ✅ SAVE ATTENDANCE (UPDATED)
//    // ===============================
//    public ClassSession saveAttendance(
//            String lecturer,
//            String subject,
//            LocalDate attendanceDate,
//            LocalTime time,
//            Map<Long, String> students
//    ) {
//
//        ClassSession classSession = new ClassSession();
//        classSession.setLecturer(lecturer);
//        classSession.setSubject(subject);
//        classSession.setTime(time);
//
//        List<Attendance> attendanceRecords = new ArrayList<>();
//
//        for (Map.Entry<Long, String> entry : students.entrySet()) {
//
//            Optional<Student> optionalStudent =
//                    studentRepository.findById(entry.getKey());
//
//            if (optionalStudent.isEmpty()) continue;
//
//            Student student = optionalStudent.get();
//
//            Attendance record = new Attendance();
//            record.setStudentName(student.getStudName());
//            record.setStatus(entry.getValue());
//            record.setAttendanceDate(attendanceDate);
//            record.setClassSession(classSession);
//            record.setStudent(student);
//
//            attendanceRecords.add(record);
//        }
//
//        classSession.setAttendance(attendanceRecords);
//
//        return classSessionRepository.save(classSession);
//    }
//}



// update for school



package com.example.stud_erp.service;

import com.example.stud_erp.entity.Attendance;
import com.example.stud_erp.entity.ClassSession;
import com.example.stud_erp.entity.Student;
import com.example.stud_erp.repository.AttendanceRepository;
import com.example.stud_erp.repository.ClassRepository;
import com.example.stud_erp.repository.ClassSessionRepository;
import com.example.stud_erp.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class AttendanceService {

    @Autowired
    private ClassSessionRepository classSessionRepository;

    @Autowired
    private AttendanceRepository attendanceRecordRepository;

    @Autowired
    private StudentRepository studentRepository;

    // ===============================
    // ✅ GET STUDENTS BY CLASS (FIXED)
    // ===============================
    public List<Student> getStudentsByClass(int classNumber) {
        return studentRepository.findAll()
                .stream()
                .filter(s -> s.getClassNumber() == classNumber && !s.isDeleted())
                .toList();
    }

    // ===============================
    // ✅ SAVE ATTENDANCE
    // ===============================
    public ClassSession saveAttendance(
            String lecturer,
            String subject,
            LocalDate attendanceDate,
            LocalTime time,
            Map<Long, String> students
    ) {

        ClassSession classSession = new ClassSession();
        classSession.setLecturer(lecturer);
        classSession.setSubject(subject);
        classSession.setTime(time);

        List<Attendance> attendanceRecords = new ArrayList<>();

        for (Map.Entry<Long, String> entry : students.entrySet()) {

            Optional<Student> optionalStudent =
                    studentRepository.findById(entry.getKey());

            if (optionalStudent.isEmpty()) continue;

            Student student = optionalStudent.get();

            Attendance record = new Attendance();
            record.setStudentName(student.getStudName());
            record.setStatus(entry.getValue());
            record.setAttendanceDate(attendanceDate);
            record.setClassSession(classSession);
            record.setStudent(student);

            attendanceRecords.add(record);
        }

        classSession.setAttendance(attendanceRecords);

        return classSessionRepository.save(classSession);
    }
}