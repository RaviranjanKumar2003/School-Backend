package com.example.stud_erp.repository;

import com.example.stud_erp.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRepository
        extends JpaRepository<Leave, Long> {

    // =====================================================
    // STUDENT
    // =====================================================

    List<Leave> findByStudent_IdOrderByCreatedAtDesc(
            Long studentId
    );
    // =====================================================
    // HOD
    // =====================================================

    List<Leave> findBySendToAndSchoolIdOrderByCreatedAtDesc(
            String sendTo,
            Long schoolId
    );

    // =====================================================
    // TEACHER
    // =====================================================

    List<Leave>
    findByTeacherIdAndSchoolIdOrderByCreatedAtDesc(
            Long teacherId,
            Long schoolId
    );

    List<Leave>
    findBySenderIdAndSenderTypeOrderByCreatedAtDesc(

            Long senderId,

            String senderType
    );

    List<Leave> findBySendToAndSchoolIdAndSenderTypeInOrderByCreatedAtDesc(
            String sendTo,
            Long schoolId,
            List<String> senderTypes
    );


}