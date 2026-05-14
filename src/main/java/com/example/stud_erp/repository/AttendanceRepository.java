//
//package com.example.stud_erp.repository;
//
//import com.example.stud_erp.entity.Attendance;
//import com.example.stud_erp.entity.TeacherAttendance;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Repository
//public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
//
//    // ✅ FIXED QUERY (date ab ClassSession se aayegi)
//    @Query("""
//        SELECT ar FROM Attendance ar
//        WHERE ar.classSession.lecturer = :lecturer
//        AND ar.classSession.subject = :subject
//        ORDER BY ar.classSession.date ASC
//    """)
//    List<Attendance> findByClassSessionLecturerAndClassSessionSubject(
//            String lecturer,
//            String subject
//    );
//
//    // ✅ Student wise attendance
//    List<Attendance> findByStudent_Id(Long studentId);
//
//    // ✅ Already correct
//    List<Attendance> findByClassSession_LecturerAndClassSession_SubjectOrderByClassSession_DateAsc(
//            String lecturer,
//            String subject
//    );
//
//    List<Attendance> findByDate(LocalDate date);
//
//    List<Attendance> findByClassNumberAndDate(Integer classNumber, LocalDate date);
//}



//updated



//
//
//package com.example.stud_erp.repository;
//
//import com.example.stud_erp.entity.Attendance;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
//
//    // ✅ FIXED QUERY
//    @Query("""
//        SELECT ar FROM Attendance ar
//        WHERE ar.classSession.lecturer = :lecturer
//        AND ar.classSession.subject = :subject
//        ORDER BY ar.classSession.date ASC
//    """)
//    List<Attendance> findByClassSessionLecturerAndClassSessionSubject(
//            String lecturer,
//            String subject
//    );
//
//    // ✅ Student wise attendance
//    List<Attendance> findByStudent_Id(Long studentId);
//
//    List<Attendance> findByClassSession_LecturerAndClassSession_SubjectOrderByClassSession_DateAsc(
//            String lecturer,
//            String subject
//    );
//
//    List<Attendance> findByDate(LocalDate date);
//
//    // ✅ CLASS + DATE (already good)
//    List<Attendance> findByClassNumberAndDate(Integer classNumber, LocalDate date);
//
//
//    // ============================================================
//    // 🔥 NEW METHODS (VERY IMPORTANT FOR ATTENDANCE FLOW)
//    // ============================================================
//
//    // ✅ CHECK EXISTING ATTENDANCE (avoid duplicate / for update)
//    Optional<Attendance> findByStudent_IdAndDateAndClassNumber(
//            Long studentId,
//            LocalDate date,
//            int classNumber
//    );
//
//    // ✅ FETCH ALL ATTENDANCE FOR CLASS ON DATE (for UI pre-fill)
//    List<Attendance> findByClassNumberAndDateOrderByStudent_StudRollNoAsc(
//            int classNumber,
//            LocalDate date
//    );
//}


// update 06/05/26

package com.example.stud_erp.repository;

import com.example.stud_erp.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    // ============================================================
    // LECTURER + SUBJECT
    // ============================================================

    @Query("""
        SELECT ar FROM Attendance ar
        WHERE ar.classSession.lecturer = :lecturer
        AND ar.classSession.subject = :subject
        ORDER BY ar.classSession.attendanceDate ASC
    """)
    List<Attendance> findByClassSessionLecturerAndClassSessionSubject(
            String lecturer,
            String subject
    );

    List<Attendance>
    findByClassSession_LecturerAndClassSession_SubjectOrderByClassSession_AttendanceDateAsc(
            String lecturer,
            String subject
    );

    // ============================================================
    // STUDENT
    // ============================================================

    List<Attendance> findByStudent_Id(Long studentId);

    // ============================================================
    // DATE
    // ============================================================

    List<Attendance> findByAttendanceDate(LocalDate attendanceDate);

    // ============================================================
    // ✅ CLASS NAME + DATE
    // ============================================================

    List<Attendance> findByClassNameAndAttendanceDate(
            String className,
            LocalDate attendanceDate
    );

    // ============================================================
    // PREFILL SORTED
    // ============================================================

    List<Attendance>
    findByClassNameAndAttendanceDateOrderByStudent_StudRollNoAsc(
            String className,
            LocalDate attendanceDate
    );

    // ============================================================
    // DUPLICATE CHECK
    // ============================================================

    boolean existsByStudent_IdAndAttendanceDateAndClassName(
            Long studentId,
            LocalDate attendanceDate,
            String className
    );

    Optional<Attendance> findByStudent_IdAndAttendanceDateAndClassName(
            Long studentId,
            LocalDate attendanceDate,
            String className
    );

    // ============================================================
    // ✅ DASHBOARD COUNT (FIXED: Status now uses String)
    // ============================================================

    long countByClassNameAndAttendanceDateAndStatus(
            String className,
            LocalDate attendanceDate,
            String status // "PRESENT" ya "ABSENT" as a String
    );
}