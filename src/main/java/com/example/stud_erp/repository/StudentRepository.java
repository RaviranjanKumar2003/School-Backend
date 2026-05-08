<<<<<<< HEAD
=======
//package com.example.stud_erp.repository;
//
//import com.example.stud_erp.entity.Student;
//import com.example.stud_erp.payload.StudentDTO;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import java.util.Optional;
//
//public interface StudentRepository extends JpaRepository<Student, Long> {
//
//    Student findByUsernameAndPassword(String username, String password);
//
//    Student findByUsername(String username);
//
//    Student findByEmail(String email);
//    boolean existsByStudentId(String studentId);
//    boolean existsByStudRollNo(Long rollNo);
//
//    boolean existsByUsername(String username);
//
//    boolean existsByEmail(String email);
//    Student findByStudName(String studName);
//
//    @Query("SELECT s.username, s.email FROM Student s WHERE s.id = :id")
//    Optional<StudentDTO> findStudentUsernameAndEmailById(Long id);
//
//    Student findByStudentId(String id);
//    boolean existsByStudentIdOrUsernameOrEmailOrStudRollNo(String studentId, String username, String email, Long rollNo);
//
//}



//package com.example.stud_erp.repository;
//
//import com.example.stud_erp.entity.Student;
//import com.example.stud_erp.payload.StudentDTO;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import java.util.Optional;
//
//public interface StudentRepository extends JpaRepository<Student, Long> {
//
//    Optional<Student> findByUsername(String username);
//
//    Optional<Student> findByUsernameAndPassword(String username, String password);
//
//    Optional<Student> findByEmail(String email);
//
//    Optional<Student> findByStudentId(String studentId); // 🔥 IMPORTANT
//
//    Optional<Student> findByStudName(String studName);
//
//    boolean existsByStudentId(String studentId);
//
//    boolean existsByStudRollNo(Long rollNo);
//
//    boolean existsByUsername(String username);
//
//    boolean existsByEmail(String email);
//
//
//    boolean existsByStudentIdOrUsernameOrEmailOrStudRollNo(
//            String studentId,
//            String username,
//            String email,
//            Long rollNo
//    );
//
//    @Query("SELECT new com.example.stud_erp.payload.StudentDTO(s.username, s.email) FROM Student s WHERE s.id = :id")
//    Optional<StudentDTO> findStudentUsernameAndEmailById(Long id);
//}


//package com.example.stud_erp.repository;
//
//import com.example.stud_erp.entity.Student;
//import com.example.stud_erp.payload.StudentDTO;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import java.util.Optional;
//
//public interface StudentRepository extends JpaRepository<Student, Long> {
//
//    // ===============================
//    // 🔐 LOGIN / AUTH
//    // ===============================
//
//    Optional<Student> findByUsername(String username);
//
//    // ⚠️ NOT RECOMMENDED (password check service me hi karo)
//    Optional<Student> findByUsernameAndPassword(String username, String password);
//
//    // ===============================
//    // 📧 EMAIL / OTP
//    // ===============================
//
//    Optional<Student> findByEmail(String email);
//
//    // ===============================
//    // 🎓 STUDENT FETCH
//    // ===============================
//
//    // 🔥 MAIN (frontend ke liye)
//    Optional<Student> findByStudentId(String studentId);
//
//    // ⚠️ NOT SAFE (name duplicate ho sakta hai)
//    Optional<Student> findByStudName(String studName);
//
//    // ===============================
//    // ✅ EXIST CHECKS
//    // ===============================
//
//    boolean existsByStudentId(String studentId);
//
//    boolean existsByStudRollNo(Long studRollNo);
//
//    boolean existsByUsername(String username);
//
//    boolean existsByEmail(String email);
//
//    boolean existsByStudentIdOrUsernameOrEmailOrStudRollNo(
//            String studentId,
//            String username,
//            String email,
//            Long studRollNo
//    );
//
//    // ===============================
//    // 📊 CUSTOM DTO QUERY
//    // ===============================
//
//    @Query("""
//        SELECT new com.example.stud_erp.payload.StudentDTO(
//            s.username,
//            s.email
//        )
//        FROM Student s
//        WHERE s.id = :id
//    """)
//    Optional<StudentDTO> findStudentUsernameAndEmailById(Long id);
//}
//
//
//





//package com.example.stud_erp.repository;
//
//import com.example.stud_erp.entity.Student;
//import com.example.stud_erp.payload.StudentDTO;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import java.util.Optional;
//import java.util.List; // ✅ NEW IMPORT
//
//public interface StudentRepository extends JpaRepository<Student, Long> {
//
//    // ===============================
//    // 🔐 LOGIN / AUTH
//    // ===============================
//
//    Optional<Student> findByUsername(String username);
//
//    // ⚠️ NOT RECOMMENDED (password check service me hi karo)
//    Optional<Student> findByUsernameAndPassword(String username, String password);
//
//    // ===============================
//    // 📧 EMAIL / OTP
//    // ===============================
//
//    Optional<Student> findByEmail(String email);
//
//    // ===============================
//    // 🎓 STUDENT FETCH
//    // ===============================
//
//    // 🔥 MAIN (frontend ke liye)
//    Optional<Student> findByStudentId(String studentId);
//
//    // ⚠️ NOT SAFE (name duplicate ho sakta hai)
//    Optional<Student> findByStudName(String studName);
//
//    // ===============================
//    // ✅ EXIST CHECKS
//    // ===============================
//
//    boolean existsByStudentId(String studentId);
//
//    boolean existsByStudRollNo(Long studRollNo);
//
//    boolean existsByUsername(String username);
//
//    boolean existsByEmail(String email);
//
//    boolean existsByStudentIdOrUsernameOrEmailOrStudRollNo(
//            String studentId,
//            String username,
//            String email,
//            Long studRollNo
//    );
//
//    // ===============================
//    // 📊 CUSTOM DTO QUERY
//    // ===============================
//
//    @Query("""
//        SELECT new com.example.stud_erp.payload.StudentDTO(
//            s.username,
//            s.email
//        )
//        FROM Student s
//        WHERE s.id = :id
//    """)
//    Optional<StudentDTO> findStudentUsernameAndEmailById(Long id);
//
//    // ===============================
//    // ✅ SOFT DELETE SUPPORT (NEW)
//    // ===============================
//
//    List<Student> findByIsDeletedFalse(); // ✅ ONLY ACTIVE STUDENTS
//}



// new update for class

//
//package com.example.stud_erp.repository;
//
//import com.example.stud_erp.entity.Student;
//import com.example.stud_erp.payload.StudentDTO;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.Optional;
//import java.util.List;
//
//public interface StudentRepository extends JpaRepository<Student, Long> {
//
//    Optional<Student> findByUsername(String username);
//
//    Optional<Student> findByUsernameAndPassword(String username, String password);
//
//    Optional<Student> findByEmail(String email);
//
//    Optional<Student> findByStudentId(String studentId);
//
//    Optional<Student> findByStudName(String studName);
//
//    boolean existsByStudentId(String studentId);
//
//    boolean existsByStudRollNo(Long studRollNo);
//
//    boolean existsByUsername(String username);
//
//    boolean existsByEmail(String email);
//
//    boolean existsByStudentIdOrUsernameOrEmailOrStudRollNo(
//            String studentId,
//            String username,
//            String email,
//            Long studRollNo
//    );
//
//    @Query("""
//        SELECT new com.example.stud_erp.payload.StudentDTO(
//            s.username,
//            s.email
//        )
//        FROM Student s
//        WHERE s.id = :id
//    """)
//    Optional<StudentDTO> findStudentUsernameAndEmailById(Long id);
//
//    // ✅ ACTIVE STUDENTS
//    List<Student> findByIsDeletedFalse();
//
//    // ✅ NEW: CLASS FILTER
//    List<Student> findByClassNumberAndIsDeletedFalse(int classNumber);
//    List<Student> findByIsDeletedTrue();
//
//    @Query("SELECT MAX(s.studRollNo) FROM Student s WHERE s.classNumber = :classNumber")
//    Long findLastRollNumberByClass(@Param("classNumber") int classNumber);
//}


//
//
//package com.example.stud_erp.repository;
//
//import com.example.stud_erp.entity.Student;
//import com.example.stud_erp.payload.StudentDTO;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.Optional;
//import java.util.List;
//
//public interface StudentRepository extends JpaRepository<Student, Long> {
//
//    Optional<Student> findByUsername(String username);
//
//    Optional<Student> findByUsernameAndPassword(String username, String password);
//
//    Optional<Student> findByEmail(String email);
//
//    Optional<Student> findByStudentId(String studentId);
//
//    Optional<Student> findByStudName(String studName);
//
//    boolean existsByStudentId(String studentId);
//
//    boolean existsByStudRollNo(Long studRollNo);
//
//    boolean existsByUsername(String username);
//
//    boolean existsByEmail(String email);
//
//    boolean existsByStudentIdOrUsernameOrEmailOrStudRollNo(
//            String studentId,
//            String username,
//            String email,
//            Long studRollNo
//    );
//
//    @Query("""
//        SELECT new com.example.stud_erp.payload.StudentDTO(
//            s.username,
//            s.email
//        )
//        FROM Student s
//        WHERE s.id = :id
//    """)
//    Optional<StudentDTO> findStudentUsernameAndEmailById(Long id);
//
//    // ✅ ACTIVE STUDENTS
//    List<Student> findByIsDeletedFalse();
//
//    // ✅ CLASS FILTER
//    List<Student> findByClassNumberAndIsDeletedFalse(int classNumber);
//
//    // ✅ ARCHIVED STUDENTS
//    List<Student> findByIsDeletedTrue();
//
//    // ❌ OLD LOGIC (keep but avoid using for roll generation)
//    @Query("SELECT MAX(s.studRollNo) FROM Student s WHERE s.classNumber = :classNumber")
//    Long findLastRollNumberByClass(@Param("classNumber") int classNumber);
//
//
//    // ============================================================
//    // 🔥 NEW METHODS (IMPORTANT - DO NOT REMOVE)
//    // ============================================================
//
//    // ✅ GET ALL ACTIVE ROLLS (FOR GAP LOGIC)
//    @Query("SELECT s.studRollNo FROM Student s WHERE s.classNumber = :classNumber AND s.isDeleted = false ORDER BY s.studRollNo ASC")
//    List<Long> findActiveRollsByClass(@Param("classNumber") int classNumber);
//
//
//    // ✅ CHECK ROLL EXISTS IN SAME CLASS (FOR RESTORE)
//    boolean existsByClassNumberAndStudRollNoAndIsDeletedFalse(int classNumber, Long studRollNo);
//
//}



// upar ka sahi testing




//
//package com.example.stud_erp.repository;
//
//import com.example.stud_erp.entity.Student;
//import com.example.stud_erp.payload.StudentDTO;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.Optional;
//import java.util.List;
//
//public interface StudentRepository extends JpaRepository<Student, Long> {
//
//    Optional<Student> findByUsername(String username);
//
//    Optional<Student> findByUsernameAndPassword(String username, String password);
//
//    Optional<Student> findByEmail(String email);
//
//
//    Optional<Student> findByStudName(String studName);
//
//    boolean existsByStudentId(String studentId);
//
//    boolean existsByStudRollNo(Long studRollNo);
//
//    boolean existsByUsername(String username);
//
//    boolean existsByEmail(String email);
//
//    boolean existsByStudentIdOrUsernameOrEmailOrStudRollNo(
//            String studentId,
//            String username,
//            String email,
//            Long studRollNo
//    );
//
//
//    @Query("""
//        SELECT new com.example.stud_erp.payload.StudentDTO(
//            s.username,
//            s.email
//        )
//        FROM Student s
//        WHERE s.id = :id
//    """)
//    Optional<StudentDTO> findStudentUsernameAndEmailById(Long id);
//
//    // ✅ ACTIVE STUDENTS
//    List<Student> findByIsDeletedFalse();
//
//    // ✅ CLASS FILTER
//    List<Student> findByClassNumberAndIsDeletedFalse(int classNumber);
//
//    // ✅ ARCHIVED STUDENTS
//    List<Student> findByIsDeletedTrue();
//
//    List<Student> findByClassNumber(Integer classNumber);
//
//    // ❌ OLD LOGIC (keep but avoid using for roll generation)
//    @Query("SELECT MAX(s.studRollNo) FROM Student s WHERE s.classNumber = :classNumber")
//    Long findLastRollNumberByClass(@Param("classNumber") int classNumber);
//
//
//    // ============================================================
//    // 🔥 NEW METHODS (IMPORTANT - DO NOT REMOVE)
//    // ============================================================
//
//    // ✅ GET ALL ACTIVE ROLLS (FOR GAP LOGIC)
//    @Query("SELECT s.studRollNo FROM Student s WHERE s.classNumber = :classNumber AND s.isDeleted = false ORDER BY s.studRollNo ASC")
//    List<Long> findActiveRollsByClass(@Param("classNumber") int classNumber);
//
//
//    // ✅ CHECK ROLL EXISTS IN SAME CLASS (FOR RESTORE)
//    boolean existsByClassNumberAndStudRollNoAndIsDeletedFalse(int classNumber, Long studRollNo);
//
//
//    Optional<Student> findByClassNumberAndStudRollNo(int classNumber, Long studRollNo);
//
//
//    Optional<Student> findByStudentId(String studentId);
//
//}



//updated





>>>>>>> 5bf6a9a (work done)

package com.example.stud_erp.repository;

import com.example.stud_erp.entity.Student;
import com.example.stud_erp.payload.StudentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {


    Optional<Student> findByUsername(String username);

    Optional<Student> findByEmail(String email);

<<<<<<< HEAD
    boolean existsByEmail(String email);
=======
    Optional<Student> findByStudName(String studName);

    boolean existsByStudentId(String studentId);

    boolean existsByStudRollNo(Long studRollNo);
>>>>>>> 5bf6a9a (work done)

    boolean existsByUsername(String username);

    boolean existsByStudentId(String studentId);

    Optional<Student> findByStudentId(String studentId);

    Optional<Student> findByUsernameAndPassword(
            String username,
            String password
    );

    Optional<Student> findByStudName(String studName);

    boolean existsByStudRollNo(Long studRollNo);

    boolean existsByStudentIdOrUsernameOrEmailOrStudRollNo(
            String studentId,
            String username,
            String email,
            Long studRollNo
    );

<<<<<<< HEAD
    // =========================================================
    // SCHOOL WISE
    // =========================================================

    List<Student> findBySchoolIdAndIsDeletedFalse(Long schoolId);

    List<Student> findBySchoolIdAndClassNumberAndIsDeletedFalse(
            Long schoolId,
            Long classNumber
    );

    Long countBySchoolIdAndIsDeletedFalse(Long schoolId);

    // =========================================================
    // ACTIVE / ARCHIVE
    // =========================================================

    List<Student> findByIsDeletedFalse();

    List<Student> findByIsDeletedTrue();

    List<Student> findByClassNumberAndIsDeletedFalse(
            Long classNumber
    );


    List<Student> findByClassNumber(Long classNumber);

    List<Student> findBySchoolIdAndIsDeletedTrue(Long schoolId);

    // =========================================================
    // ROLL NUMBER
    // =========================================================

    @Query("""
        SELECT s.studRollNo
        FROM Student s
        WHERE s.classNumber = :classNumber
        AND s.isDeleted = false
        ORDER BY s.studRollNo ASC
    """)
    List<Long> findActiveRollsByClass(
            @Param("classNumber") Long classNumber
    );

    boolean existsByClassNumberAndStudRollNoAndIsDeletedFalse(
            Long classNumber,
            Long studRollNo
    );

    Optional<Student> findByClassNumberAndStudRollNo(
            Long classNumber,
            Long studRollNo
    );

    List<Student> findByClassNameIgnoreCase(String className);

    @Query("SELECT MAX(s.id) FROM Student s")
    Long findMaxId();

    Long countByClassNumber(Long classNumber);

    // =========================================================
    // DTO
    // =========================================================

=======
>>>>>>> 5bf6a9a (work done)
    @Query("""
        SELECT new com.example.stud_erp.payload.StudentDTO(
            s.username,
            s.email
        )
        FROM Student s
        WHERE s.id = :id
    """)
<<<<<<< HEAD
    Optional<StudentDTO> findStudentUsernameAndEmailById(
            Long id
    );

=======
    Optional<StudentDTO> findStudentUsernameAndEmailById(Long id);

    // ✅ ACTIVE STUDENTS
    List<Student> findByIsDeletedFalse();

    // ✅ CLASS FILTER
    List<Student> findByClassNumberAndIsDeletedFalse(int classNumber);

    // ✅ 🔥 NEW (FOR ATTENDANCE UI - SORTED BY ROLL NO)
    List<Student> findByClassNumberAndIsDeletedFalseOrderByStudRollNoAsc(int classNumber);

    // ✅ ARCHIVED STUDENTS
    List<Student> findByIsDeletedTrue();

    List<Student> findByClassNumber(Integer classNumber);

    @Query("SELECT MAX(s.studRollNo) FROM Student s WHERE s.classNumber = :classNumber")
    Long findLastRollNumberByClass(@Param("classNumber") int classNumber);

    // ============================================================
    // 🔥 NEW METHODS (IMPORTANT - DO NOT REMOVE)
    // ============================================================

    @Query("SELECT s.studRollNo FROM Student s WHERE s.classNumber = :classNumber AND s.isDeleted = false ORDER BY s.studRollNo ASC")
    List<Long> findActiveRollsByClass(@Param("classNumber") int classNumber);

    boolean existsByClassNumberAndStudRollNoAndIsDeletedFalse(int classNumber, Long studRollNo);

    Optional<Student> findByClassNumberAndStudRollNo(int classNumber, Long studRollNo);

    Optional<Student> findByStudentId(String studentId);
>>>>>>> 5bf6a9a (work done)
}