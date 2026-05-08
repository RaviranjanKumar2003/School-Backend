//package com.example.stud_erp.service;
//
//import com.example.stud_erp.entity.Attendance;
//import com.example.stud_erp.entity.TeacherAttendance;
//import com.example.stud_erp.payload.ClassSessionDTO;
//
//import java.time.LocalDate;
//import java.util.List;
//
//public interface AttendanceService {
//
//    ClassSessionDTO saveAttendance(ClassSessionDTO dto);
//
//    List<ClassSessionDTO> getClassAttendance(Integer classNumber);
//
//    List<ClassSessionDTO> getStudentAttendance(Long studentId);
//
//    List<Attendance> getByDate(LocalDate date);
//
//    ClassSessionDTO getClassAttendanceByDate(Integer classNumber, LocalDate date);
//}



// updated


package com.example.stud_erp.service;

import com.example.stud_erp.entity.Attendance;
import com.example.stud_erp.payload.ClassSessionDTO;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {

    // ================= SAVE =================

    ClassSessionDTO saveAttendance(ClassSessionDTO dto);

    // ================= CLASS ATTENDANCE =================

    List<ClassSessionDTO> getClassAttendance(String className);

    // ================= STUDENT ATTENDANCE =================

    List<ClassSessionDTO> getStudentAttendance(Long studentId);

    // ================= DATE =================

    List<Attendance> getByDate(LocalDate date);

    // ================= CLASS + DATE =================

    ClassSessionDTO getClassAttendanceByDate(
            String className,
            LocalDate date
    );

    // ================= BULK SAVE =================

    void saveOrUpdateBulkAttendance(
            List<Attendance> attendanceList
    );

    // ================= PREFILL =================

    List<Attendance> getAttendanceByClassAndDate(
            String className,
            LocalDate date
    );
}