package com.example.stud_erp.service;

import com.example.stud_erp.payload.TimeTableDto;

import java.util.List;

public interface TimeTableService {

    TimeTableDto createTimeTable(TimeTableDto dto);

    List<TimeTableDto> getAllTimeTables();

    List<TimeTableDto> getBySchool(Long schoolId);

    List<TimeTableDto> getByClass(Long classId);

    List<TimeTableDto> getByClassAndSection(
            Long classId,
            String sectionName
    );

    List<TimeTableDto> getByTeacher(Long teacherId);

    void deleteTimeTable(Long id);
}