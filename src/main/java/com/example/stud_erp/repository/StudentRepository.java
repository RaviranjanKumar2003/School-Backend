
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

    boolean existsBySchoolIdAndClassNumberAndStudRollNoAndIsDeletedFalse(
            Long schoolId, Integer classNumber, Long rollNo
    );

    List<Student> findBySchoolIdAndIsDeletedFalse(Long schoolId);

    List<Student> findBySchoolIdAndClassNumberAndIsDeletedFalse(Long schoolId, Integer classNumber);

    Optional<Student> findByStudentId(String studentId);

    //========================================================================


    Optional<Student> findByUsernameAndPassword(String username, String password);



    Optional<Student> findByStudName(String studName);


    boolean existsByStudRollNo(Long studRollNo);





    boolean existsByStudentIdOrUsernameOrEmailOrStudRollNo(
            String studentId,
            String username,
            String email,
            Long studRollNo
    );


    @Query("""
        SELECT new com.example.stud_erp.payload.StudentDTO(
            s.username,
            s.email
        )
        FROM Student s
        WHERE s.id = :id
    """)
    Optional<StudentDTO> findStudentUsernameAndEmailById(Long id);

    // ✅ ACTIVE STUDENTS
    List<Student> findByIsDeletedFalse();

    // ✅ CLASS FILTER
    List<Student> findByClassNumberAndIsDeletedFalse(int classNumber);

    // ✅ ARCHIVED STUDENTS
    List<Student> findByIsDeletedTrue();

    List<Student> findByClassNumber(Integer classNumber);

    // ❌ OLD LOGIC (keep but avoid using for roll generation)
    @Query("SELECT MAX(s.studRollNo) FROM Student s WHERE s.classNumber = :classNumber")
    Long findLastRollNumberByClass(@Param("classNumber") int classNumber);


    // ============================================================
    // 🔥 NEW METHODS (IMPORTANT - DO NOT REMOVE)
    // ============================================================

    // ✅ GET ALL ACTIVE ROLLS (FOR GAP LOGIC)
    @Query("SELECT s.studRollNo FROM Student s WHERE s.classNumber = :classNumber AND s.isDeleted = false ORDER BY s.studRollNo ASC")
    List<Long> findActiveRollsByClass(@Param("classNumber") int classNumber);


    // ✅ CHECK ROLL EXISTS IN SAME CLASS (FOR RESTORE)
    boolean existsByClassNumberAndStudRollNoAndIsDeletedFalse(int classNumber, Long studRollNo);


    Optional<Student> findByClassNumberAndStudRollNo(int classNumber, Long studRollNo);


    List<Student> findByClassNameIgnoreCase(String className);


    @Query("SELECT MAX(s.id) FROM Student s")
    Long findMaxId();

    Long countByClassNumber(Integer classNumber);

    List<Student> findBySchoolIdAndClassNumber(
            Long schoolId,
            Integer classNumber
    );

}