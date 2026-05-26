package com.example.stud_erp.service.Implementation;

import com.example.stud_erp.entity.Professor;
import com.example.stud_erp.entity.TeacherAssignment;
import com.example.stud_erp.payload.TeacherAssignmentDto;
import com.example.stud_erp.repository.ProfessorRepository;
import com.example.stud_erp.repository.TeacherAssignmentRepo;
import com.example.stud_erp.service.TeacherAssignmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherAssignmentServiceImpl
        implements TeacherAssignmentService {

    @Autowired
    private TeacherAssignmentRepo repo;

    @Autowired
    private ProfessorRepository professorRepository;

    // =====================================================
    // CREATE
    // =====================================================

    @Override
    public TeacherAssignmentDto createAssignment(
            TeacherAssignmentDto dto
    ) {

        Professor professor =
                professorRepository
                        .findById(dto.getProfessorId())
                        .orElseThrow(() ->
                                new RuntimeException("Teacher not found"));

        TeacherAssignment assignment =
                new TeacherAssignment();

        assignment.setProfessor(professor);

        assignment.setSchoolId(dto.getSchoolId());

        assignment.setClassId(dto.getClassId());

        assignment.setClassName(dto.getClassName());

        assignment.setSection(dto.getSection());

        assignment.setSubjectName(dto.getSubjectName());

        assignment.setWeeklyPeriods(dto.getWeeklyPeriods());

        assignment.setActive(
                dto.getActive() != null
                        ? dto.getActive()
                        : true
        );

        TeacherAssignment saved =
                repo.save(assignment);

        return mapToDto(saved);
    }

    // =====================================================
    // GET ALL
    // =====================================================

    @Override
    public List<TeacherAssignmentDto> getAllAssignments() {

        return repo.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // =====================================================
    // GET BY SCHOOL
    // =====================================================

    @Override
    public List<TeacherAssignmentDto> getBySchool(
            Long schoolId
    ) {

        return repo.findBySchoolId(schoolId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // =====================================================
    // GET BY CLASS
    // =====================================================

    @Override
    public List<TeacherAssignmentDto> getByClass(
            Long classId
    ) {

        return repo.findByClassId(classId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // =====================================================
    // GET BY PROFESSOR
    // =====================================================

    @Override
    public List<TeacherAssignmentDto> getByProfessor(
            Long professorId
    ) {

        return repo.findByProfessorId(professorId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // =====================================================
    // DELETE
    // =====================================================

    @Override
    public void deleteAssignment(Long id) {

        repo.deleteById(id);
    }

    // =====================================================
    // DTO MAPPER
    // =====================================================

    private TeacherAssignmentDto mapToDto(
            TeacherAssignment assignment
    ) {

        TeacherAssignmentDto dto =
                new TeacherAssignmentDto();

        dto.setId(assignment.getId());

        dto.setProfessorId(
                assignment.getProfessor().getId()
        );

        dto.setProfessorName(
                assignment.getProfessor().getName()
        );

        dto.setSchoolId(
                assignment.getSchoolId()
        );

        dto.setClassId(
                assignment.getClassId()
        );

        dto.setClassName(
                assignment.getClassName()
        );

        dto.setSection(
                assignment.getSection()
        );

        dto.setSubjectName(
                assignment.getSubjectName()
        );

        dto.setWeeklyPeriods(
                assignment.getWeeklyPeriods()
        );

        dto.setActive(
                assignment.getActive()
        );

        return dto;
    }
}