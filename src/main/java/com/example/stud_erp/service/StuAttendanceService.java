//package com.example.stud_erp.service;
//
<<<<<<< HEAD
//import com.example.stud_erp.entity.StuAttendance;
=======
>>>>>>> 5bf6a9a (work done)
//import com.example.stud_erp.payload.StuAttendanceDTO;
//
//import java.time.LocalDate;
//import java.util.List;
//
//public interface StuAttendanceService {
//
<<<<<<< HEAD
//    String save(Integer classNumber, LocalDate date, List<StuAttendanceDTO> list);
//
//    List<StuAttendanceDTO> getByClassAndDate(Integer classNumber, LocalDate date);
//
//    List<StuAttendance> getByDate(LocalDate date);
//
=======
//    // ✅ SAVE / UPDATE ATTENDANCE
//    String save(
//            String className,
//            LocalDate attendanceDate,
//            List<StuAttendanceDTO> list
//    );
//
//    // ✅ PREFILL ATTENDANCE
//    List<StuAttendanceDTO> getByClassAndDate(
//            String className,
//            LocalDate attendanceDate
//    );
>>>>>>> 5bf6a9a (work done)
//}



<<<<<<< HEAD
=======
//
//package com.example.stud_erp.service;
//
//import com.example.stud_erp.payload.StuAttendanceDTO;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Map;
//
//public interface StuAttendanceService {
//
//    // =========================================
//    // SAVE / UPDATE ATTENDANCE
//    // =========================================
//    String save(
//
//            String className,
//
//            LocalDate attendanceDate,
//
//            List<StuAttendanceDTO> list
//
//    );
//
//    // =========================================
//    // PREFILL ATTENDANCE
//    // =========================================
//    List<StuAttendanceDTO> getByClassAndDate(
//
//            String className,
//
//            LocalDate attendanceDate
//
//    );
//
//    // =========================================
//    // GET ALL ATTENDANCE
//    // =========================================
//    List<StuAttendanceDTO> getAllAttendance();
//
//    // =========================================
//    // GET CLASS ATTENDANCE
//    // =========================================
//    List<StuAttendanceDTO> getByClass(
//
//            String className
//
//    );
//
//    // =========================================
//    // MONTHLY ATTENDANCE
//    // =========================================
//    List<StuAttendanceDTO> getMonthlyAttendance(
//
//            String className,
//
//            int month,
//
//            int year
//
//    );
//
//    // =========================================
//    // YEARLY ATTENDANCE
//    // =========================================
//    List<StuAttendanceDTO> getYearlyAttendance(
//
//            String className,
//
//            int year
//
//    );
//
//    // =========================================
//    // MONTHLY SUMMARY
//    // =========================================
//    Map<String, Long> getMonthlySummary(
//
//            int year
//
//    );
//
//    // =========================================
//    // YEARLY SUMMARY
//    // =========================================
//    Map<Integer, Long> getYearlySummary();
//
//    // =========================================
//    // STUDENT ATTENDANCE %
//    // =========================================
//    double getStudentAttendancePercentage(
//
//            Long studentId
//
//    );
//}



//for view

>>>>>>> 5bf6a9a (work done)
package com.example.stud_erp.service;

import com.example.stud_erp.entity.StuAttendance;
import com.example.stud_erp.payload.StuAttendanceDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface StuAttendanceService {

<<<<<<< HEAD
    // SAVE
    String save(
            Long schoolId,
            Long classId,
            LocalDate date,
            Long takenById,
            String takenByName,
            String takenByRole,
            List<StuAttendanceDTO> list
    );

    // GET
    List<StuAttendanceDTO> getByClassAndDate(
            Long schoolId,
            Long classId,
            LocalDate date
    );

    // DAILY SUMMARY
    List<StuAttendance> getByDate(
            Long schoolId,
            LocalDate date
    );

=======
    // =========================================
    // SAVE / UPDATE ATTENDANCE
    // =========================================
    String save(

            String className,

            LocalDate attendanceDate,

            List<StuAttendanceDTO> list

    );

    // =========================================
    // PREFILL ATTENDANCE
    // =========================================
    List<StuAttendanceDTO> getByClassAndDate(

            String className,

            LocalDate attendanceDate

    );

    // =========================================
    // GET ALL ATTENDANCE
    // =========================================
    List<StuAttendanceDTO> getAllAttendance();

    // =========================================
    // GET ATTENDANCE BY CLASS
    // =========================================
    List<StuAttendanceDTO> getByClass(

            String className

    );

    // =========================================
    // GET MONTHLY ATTENDANCE
    // =========================================
    List<StuAttendanceDTO> getMonthlyAttendance(

            String className,

            int month,

            int year

    );

    // =========================================
    // GET YEARLY ATTENDANCE
    // =========================================
    List<StuAttendanceDTO> getYearlyAttendance(

            String className,

            int year

    );

    // =========================================
    // MONTHLY SUMMARY
    // =========================================
    Map<String, Long> getMonthlySummary(

            int year

    );

    // =========================================
    // YEARLY SUMMARY
    // =========================================
    Map<Integer, Long> getYearlySummary();

    // =========================================
    // STUDENT ATTENDANCE PERCENTAGE
    // =========================================
    double getStudentAttendancePercentage(

            Long studentId

    );

    // =========================================
// CHECK ATTENDANCE ALREADY EXISTS
// =========================================
    boolean attendanceAlreadyExists(

            String className,

            LocalDate attendanceDate

    );
>>>>>>> 5bf6a9a (work done)
}