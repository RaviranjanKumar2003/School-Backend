package com.example.stud_erp.service.Implementation;

import com.example.stud_erp.entity.TimeTable;
import com.example.stud_erp.payload.TimeTableDto;
import com.example.stud_erp.repository.TimeTableRepo;
import com.example.stud_erp.service.TimeTableService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeTableServiceImpl implements TimeTableService {

    @Autowired
    private TimeTableRepo repo;

    // ================= CREATE =================

    @Override
    public TimeTableDto createTimeTable(TimeTableDto dto) {

        // =========================================
        // CHECK TEACHER CONFLICT
        // =========================================

        boolean teacherBusy =
                repo.existsByTeacherIdAndDayNameAndPeriodNumber(
                        dto.getTeacherId(),
                        dto.getDayName(),
                        dto.getPeriodNumber()
                );

        if (teacherBusy) {

            throw new RuntimeException(
                    "Teacher already assigned in this period"
            );
        }

        // =========================================
        // CHECK CLASS CONFLICT
        // =========================================

        boolean classBusy =
                repo.existsByClassIdAndSectionNameAndDayNameAndPeriodNumber(
                        dto.getClassId(),
                        dto.getSectionName(),
                        dto.getDayName(),
                        dto.getPeriodNumber()
                );

        if (classBusy) {

            throw new RuntimeException(
                    "Class already has a subject in this period"
            );
        }

        TimeTable table = mapToEntity(dto);

        TimeTable saved = repo.save(table);

        return mapToDto(saved);
    }

    // ================= GET ALL =================

    @Override
    public List<TimeTableDto> getAllTimeTables() {

        return repo.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // ================= SCHOOL =================

    @Override
    public List<TimeTableDto> getBySchool(Long schoolId) {

        return repo.findBySchoolId(schoolId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // ================= CLASS =================

    @Override
    public List<TimeTableDto> getByClass(Long classId) {

        return repo.findByClassId(classId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // ================= CLASS + SECTION =================

    @Override
    public List<TimeTableDto> getByClassAndSection(
            Long classId,
            String sectionName
    ) {

        return repo.findByClassIdAndSectionName(
                        classId,
                        sectionName
                )
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // ================= TEACHER =================

    @Override
    public List<TimeTableDto> getByTeacher(Long teacherId) {

        return repo.findByTeacherId(teacherId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // ================= DELETE =================

    @Override
    public void deleteTimeTable(Long id) {

        repo.deleteById(id);
    }

    // ================= MAP ENTITY =================

    private TimeTable mapToEntity(TimeTableDto dto) {

        TimeTable t = new TimeTable();

        t.setId(dto.getId());
        t.setSchoolId(dto.getSchoolId());
        t.setClassId(dto.getClassId());
        t.setClassName(dto.getClassName());
        t.setSectionName(dto.getSectionName());
        t.setDayName(dto.getDayName());
        t.setPeriodNumber(dto.getPeriodNumber());
        t.setStartTime(dto.getStartTime());
        t.setEndTime(dto.getEndTime());
        t.setSubjectName(dto.getSubjectName());
        t.setTeacherId(dto.getTeacherId());
        t.setTeacherName(dto.getTeacherName());

        return t;
    }

    // ================= MAP DTO =================

    private TimeTableDto mapToDto(TimeTable t) {

        TimeTableDto dto = new TimeTableDto();

        dto.setId(t.getId());
        dto.setSchoolId(t.getSchoolId());
        dto.setClassId(t.getClassId());
        dto.setClassName(t.getClassName());
        dto.setSectionName(t.getSectionName());
        dto.setDayName(t.getDayName());
        dto.setPeriodNumber(t.getPeriodNumber());
        dto.setStartTime(t.getStartTime());
        dto.setEndTime(t.getEndTime());
        dto.setSubjectName(t.getSubjectName());
        dto.setTeacherId(t.getTeacherId());
        dto.setTeacherName(t.getTeacherName());

        return dto;
    }
}