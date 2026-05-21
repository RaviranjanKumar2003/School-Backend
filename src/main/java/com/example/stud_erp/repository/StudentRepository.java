
package com.example.stud_erp.repository;

import com.example.stud_erp.entity.Student;
import com.example.stud_erp.enums.StudentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // =========================================================
    // LOGIN / AUTH
    // =========================================================

    Optional<Student> findByUsername(String username);

    Optional<Student> findByEmail(String email);

    Optional<Student> findByStudentId(String studentId);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByStudentId(String studentId);

    // =========================================================
    // NAME
    // =========================================================

    Optional<Student> findByStudfirstName(String studfirstName);

    List<Student> findByStudfirstNameContainingIgnoreCase(
            String keyword
    );

    // =========================================================
    // SCHOOL WISE
    // =========================================================

    @Query("""
        SELECT s
        FROM Student s
        WHERE s.school.id = :schoolId
        AND s.isDeleted = false
        ORDER BY s.id DESC
    """)
    List<Student> findBySchoolIdAndIsDeletedFalse(
            @Param("schoolId") Long schoolId
    );

    @Query("""
        SELECT s
        FROM Student s
        WHERE s.school.id = :schoolId
        AND s.isDeleted = true
        ORDER BY s.id DESC
    """)
    List<Student> findBySchoolIdAndIsDeletedTrue(
            @Param("schoolId") Long schoolId
    );

    // =========================================================
    // CLASS WISE
    // =========================================================

    @Query("""
        SELECT s
        FROM Student s
        WHERE s.school.id = :schoolId
        AND s.classEntity.id = :classId
        AND s.isDeleted = false
        ORDER BY s.studRollNo ASC
    """)
    List<Student> findBySchoolIdAndClassEntity_IdAndIsDeletedFalse(
            @Param("schoolId") Long schoolId,
            @Param("classId") Long classId
    );


    Long countBySchoolIdAndClassEntity_IdAndIsDeletedFalse(
            Long schoolId,
            Long classId
    );

    // =========================================================
    // ROLL NUMBER
    // =========================================================

    boolean existsByStudRollNo(Long studRollNo);

    boolean existsByClassEntity_IdAndStudRollNoAndIsDeletedFalse(
            Long classId,
            Long studRollNo
    );

    Optional<Student> findByClassEntity_IdAndStudRollNo(
            Long classId,
            Long studRollNo
    );

    @Query("""
        SELECT s.studRollNo
        FROM Student s
        WHERE s.classEntity.id = :classId
        AND s.isDeleted = false
        ORDER BY s.studRollNo ASC
    """)
    List<Long> findActiveRollsByClass(
            @Param("classId") Long classId
    );

    Long countByClassEntity_IdAndIsDeletedFalse(
            Long classId
    );

    // =========================================================
    // STATUS
    // =========================================================

    Long countBySchoolIdAndStatusAndIsDeletedFalse(
            Long schoolId,
            StudentStatus status
    );

    List<Student> findByStatus(StudentStatus status);

    // =========================================================
    // TOTAL COUNT
    // =========================================================

    @Query("""
        SELECT COUNT(s)
        FROM Student s
        WHERE s.school.id = :schoolId
        AND s.isDeleted = false
    """)
    Long countBySchoolIdAndIsDeletedFalse(
            @Param("schoolId") Long schoolId
    );

    @Query("""
        SELECT COUNT(s)
        FROM Student s
        WHERE s.school.id = :schoolId
        AND s.isDeleted = true
    """)
    Long countBySchoolIdAndIsDeletedTrue(
            @Param("schoolId") Long schoolId
    );

    // =========================================================
    // SEARCH
    // =========================================================

    @Query("""
        SELECT s
        FROM Student s
        WHERE s.school.id = :schoolId
        AND s.isDeleted = false
        AND (
            LOWER(s.studfirstName) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR LOWER(s.studlastName) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR LOWER(s.studentId) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR LOWER(s.email) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR LOWER(s.classEntity.className) LIKE LOWER(CONCAT('%', :keyword, '%'))
        )
        ORDER BY s.id DESC
    """)
    List<Student> searchStudents(
            @Param("schoolId") Long schoolId,
            @Param("keyword") String keyword
    );

    // =========================================================
    // QR CODE
    // =========================================================

    Optional<Student> findByQrCodeUrl(String qrCodeUrl);

    // =========================================================
    // ACTIVE / DELETED
    // =========================================================

    List<Student> findByIsDeletedFalse();

    List<Student> findByIsDeletedTrue();

    // =========================================================
    // MAX ID
    // =========================================================

    @Query("""
        SELECT MAX(s.id)
        FROM Student s
    """)
    Long findMaxId();


    @Query("""
       SELECT MAX(s.studRollNo)
       FROM Student s
       WHERE s.classEntity.id = :classId
       AND s.section = :section
       """)
    Long findMaxRollNumber(

            @Param("classId") Long classId,

            @Param("section") String section
    );


}