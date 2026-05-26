package com.example.stud_erp.repository;

import com.example.stud_erp.entity.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeTableRepo extends JpaRepository<TimeTable, Long> {

    List<TimeTable> findBySchoolId(Long schoolId);

    List<TimeTable> findByClassId(Long classId);

    List<TimeTable> findByClassIdAndSectionName(
            Long classId,
            String sectionName
    );

    List<TimeTable> findByTeacherId(Long teacherId);

    List<TimeTable> findByClassIdAndDayName(
            Long classId,
            String dayName
    );

    boolean existsByTeacherIdAndDayNameAndPeriodNumber(
            Long teacherId,
            String dayName,
            int periodNumber
    );

    boolean existsByClassIdAndSectionNameAndDayNameAndPeriodNumber(
            Long classId,
            String sectionName,
            String dayName,
            int periodNumber
    );
}