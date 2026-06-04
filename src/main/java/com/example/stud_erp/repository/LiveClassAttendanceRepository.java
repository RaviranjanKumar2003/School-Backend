package com.example.stud_erp.repository;

import com.example.stud_erp.entity.LiveClassAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LiveClassAttendanceRepository
        extends JpaRepository<LiveClassAttendance, Long> {

    boolean existsByStudentIdAndLiveClassId(
            Long studentId,
            Long liveClassId
    );

    Optional<LiveClassAttendance>
    findByStudentIdAndLiveClassId(
            Long studentId,
            Long liveClassId
    );

    List<LiveClassAttendance>
    findByLiveClassId(
            Long liveClassId
    );


    @Query("""
SELECT COUNT(a)
FROM LiveClassAttendance a
WHERE a.liveClass.id = :liveClassId
""")

    long countByLiveClassIdAndAttendedTrue(Long liveClassId);


    // ================= CHECK EXIST =================
    boolean existsByStudent_IdAndLiveClass_Id(Long studentId, Long liveClassId);

    Optional<LiveClassAttendance>
    findByStudent_IdAndLiveClass_Id(Long studentId, Long liveClassId);

    List<LiveClassAttendance>
    findByLiveClass_Id(Long liveClassId);

    // ================= COUNT PRESENT STUDENTS =================
    @Query("""
        SELECT COUNT(a)
        FROM LiveClassAttendance a
        WHERE a.liveClass.id = :liveClassId
        AND a.attended = true
    """)
    long countPresent(@Param("liveClassId") Long liveClassId);

    @Query("SELECT COUNT(a) FROM LiveClassAttendance a WHERE a.liveClass.id = :liveClassId")
    long countByLiveClassId(@Param("liveClassId") Long liveClassId);

}