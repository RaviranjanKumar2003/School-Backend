
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

    boolean existsByEmail(String email);

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

    @Query("""
        SELECT new com.example.stud_erp.payload.StudentDTO(
            s.username,
            s.email
        )
        FROM Student s
        WHERE s.id = :id
    """)
    Optional<StudentDTO> findStudentUsernameAndEmailById(
            Long id
    );

}