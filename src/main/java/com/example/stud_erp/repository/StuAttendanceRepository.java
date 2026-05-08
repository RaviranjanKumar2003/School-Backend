//package com.example.stud_erp.repository;
//
//import com.example.stud_erp.entity.StuAttendance;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.time.LocalDate;
//import java.util.List;
//
//public interface StuAttendanceRepository extends JpaRepository<StuAttendance, Long> {
//
//    List<StuAttendance> findByClassNumberAndDate(Integer classNumber, LocalDate date);
//}



//updated
//
//package com.example.stud_erp.repository;
//
//import com.example.stud_erp.entity.StuAttendance;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//public interface StuAttendanceRepository
//        extends JpaRepository<StuAttendance, Long> {
//
//    // ✅ CLASS + DATE
//    List<StuAttendance> findByClassNameAndAttendanceDate(
//            String className,
//            LocalDate attendanceDate
//    );
//
//    // ✅ DUPLICATE CHECK
//    Optional<StuAttendance>
//    findByStudent_IdAndClassNameAndAttendanceDate(
//            Long studentId,
//            String className,
//            LocalDate attendanceDate
//    );
//}




//
//
//package com.example.stud_erp.repository;
//
//import com.example.stud_erp.entity.StuAttendance;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public interface StuAttendanceRepository
//        extends JpaRepository<StuAttendance, Long> {
//
//    // =========================================
//    // FIND BY CLASS + DATE
//    // =========================================
//    List<StuAttendance> findByClassNameAndAttendanceDate(
//
//            String className,
//
//            LocalDate attendanceDate
//
//    );
//
//    // =========================================
//    // CHECK DUPLICATE RECORD
//    // =========================================
//    Optional<StuAttendance>
//    findByStudent_IdAndClassNameAndAttendanceDate(
//
//            Long studentId,
//
//            String className,
//
//            LocalDate attendanceDate
//
//    );
//
//    // =========================================
//    // FIND BY STUDENT
//    // =========================================
//    List<StuAttendance> findByStudent_Id(
//
//            Long studentId
//
//    );
//
//    // =========================================
//    // FIND BY DATE
//    // =========================================
//    List<StuAttendance> findByAttendanceDate(
//
//            LocalDate attendanceDate
//
//    );
//
//    // =========================================
//    // DELETE CLASS ATTENDANCE
//    // =========================================
//    void deleteByClassNameAndAttendanceDate(
//
//            String className,
//
//            LocalDate attendanceDate
//
//    );
//}





package com.example.stud_erp.repository;

import com.example.stud_erp.entity.StuAttendance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StuAttendanceRepository
        extends JpaRepository<StuAttendance, Long> {

<<<<<<< HEAD

    // ================= CLASS + DATE =================
    List<StuAttendance> findBySchoolIdAndClassIdAndDate(
            Long schoolId,
            Long classId,
            LocalDate date
    );

    // ================= STUDENT =================
    List<StuAttendance> findByStudent_Id(Long studentId);

    // ================= DATE =================
    List<StuAttendance> findBySchoolIdAndDate(
            Long schoolId,
            LocalDate date
    );

    // ================= DUPLICATE CHECK =================
    Optional<StuAttendance>
    findByStudent_IdAndDateAndClassId(
            Long studentId,
            LocalDate date,
            Long classId
    );

    // ================= WEEKLY =================
    List<StuAttendance> findBySchoolId(Long schoolId);


=======
    // =========================================
    // FIND BY CLASS + DATE
    // =========================================
    List<StuAttendance> findByClassNameAndAttendanceDate(

            String className,

            LocalDate attendanceDate

    );

    // =========================================
    // CHECK DUPLICATE RECORD
    // =========================================
    Optional<StuAttendance>
    findByStudent_IdAndClassNameAndAttendanceDate(

            Long studentId,

            String className,

            LocalDate attendanceDate

    );

    // =========================================
    // FIND BY STUDENT
    // =========================================
    List<StuAttendance> findByStudent_Id(

            Long studentId

    );

    // =========================================
    // FIND BY DATE
    // =========================================
    List<StuAttendance> findByAttendanceDate(

            LocalDate attendanceDate

    );

    // =========================================
    // DELETE CLASS ATTENDANCE
    // =========================================
    void deleteByClassNameAndAttendanceDate(

            String className,

            LocalDate attendanceDate

    );

    // =========================================
    // NEW: GET ALL ORDER BY DATE
    // =========================================
    List<StuAttendance>
    findAllByOrderByAttendanceDateDesc();

    // =========================================
    // NEW: FILTER BY MONTH
    // =========================================
    List<StuAttendance>
    findByAttendanceDateBetween(

            LocalDate startDate,

            LocalDate endDate

    );

    // =========================================
    // NEW: FILTER BY CLASS
    // =========================================
    List<StuAttendance>
    findByClassName(

            String className

    );

    // =========================================
    // NEW: FILTER BY PROFESSOR
    // =========================================
    List<StuAttendance>
    findByProfessorName(

            String professorName

    );

    // =========================================
    // NEW: FILTER BY STATUS
    // =========================================
    List<StuAttendance>
    findByStatus(

            String status

    );

    // =========================================
// CHECK CLASS ATTENDANCE EXISTS
// =========================================
    boolean existsByClassNameAndAttendanceDate(

            String className,

            LocalDate attendanceDate

    );
>>>>>>> 5bf6a9a (work done)
}