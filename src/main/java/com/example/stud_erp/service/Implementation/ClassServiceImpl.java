package com.example.stud_erp.service.Implementation;

import com.example.stud_erp.entity.ClassEntity;
import com.example.stud_erp.entity.Subject;
import com.example.stud_erp.payload.ClassDTO;
import com.example.stud_erp.payload.SubjectDTO;
import com.example.stud_erp.repository.ClassRepository;
import com.example.stud_erp.repository.StudentRepository;
import com.example.stud_erp.repository.SubjectRepository;
import com.example.stud_erp.service.ClassService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassRepository classRepo;

    @Autowired
    private SubjectRepository subjectRepo;

    @Autowired
    private StudentRepository studentRepo;

    // =====================================================
    // CREATE CLASS
    // =====================================================

    @Override
    public ClassDTO createClass(Long schoolId, ClassDTO dto) {

        ClassEntity cls = new ClassEntity();

        cls.setClassName(dto.getClassName());

        cls.setSchoolId(schoolId);

        ClassEntity saved = classRepo.save(cls);

        return convertToDTO(saved);
    }

    // =====================================================
    // GET ALL CLASSES
    // =====================================================

    @Override
    public List<ClassDTO> getAllClasses() {

        return classRepo.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // =====================================================
    // GET CLASSES BY SCHOOL
    // =====================================================

    @Override
    public List<ClassDTO> getClassesBySchool(Long schoolId) {

        return classRepo.findAll()
                .stream()

                .filter(c ->

                        c.getSchoolId() != null
                                &&
                                schoolId.equals(c.getSchoolId())
                )

                .map(this::convertToDTO)

                .collect(Collectors.toList());
    }

    // =====================================================
    // GET SINGLE CLASS
    // =====================================================

    @Override
    public ClassDTO getClassById(
            Long schoolId,
            Long classId
    ) {

        ClassEntity cls = classRepo.findById(classId)

                .orElseThrow(() ->
                        new RuntimeException("Class not found"));

        // ================= SECURITY =================

        if (
                cls.getSchoolId() == null
                        ||
                        !schoolId.equals(cls.getSchoolId())
        ) {
            throw new RuntimeException(
                    "Unauthorized access"
            );
        }

        return convertToDTO(cls);
    }

    // =====================================================
    // DELETE CLASS
    // =====================================================

    @Override
    public void deleteClass(
            Long schoolId,
            Long classId
    ) {

        ClassEntity cls = classRepo.findById(classId)

                .orElseThrow(() ->
                        new RuntimeException("Class not found"));

        // ================= SECURITY =================

        if (
                cls.getSchoolId() == null
                        ||
                        !schoolId.equals(cls.getSchoolId())
        ) {
            throw new RuntimeException(
                    "Unauthorized delete attempt"
            );
        }

        // ================= CHECK STUDENTS =================

        boolean hasStudents =
                studentRepo.existsByClassEntityId(classId);

        if (hasStudents) {

            throw new RuntimeException(
                    "Cannot delete class because students are assigned to it."
            );
        }

        // ================= DELETE SUBJECTS =================

        List<Subject> subjects =
                subjectRepo.findByClassEntityId(classId);

        if (!subjects.isEmpty()) {

            subjectRepo.deleteAll(subjects);
        }

        // ================= DELETE CLASS =================

        classRepo.delete(cls);
    }

    // =====================================================
    // ADD SUBJECT
    // =====================================================

    @Override
    public ClassDTO addSubject(
            Long schoolId,
            Long classId,
            String subjectName
    ) {

        ClassEntity cls = classRepo.findById(classId)

                .orElseThrow(() ->
                        new RuntimeException("Class not found"));

        // ================= SECURITY =================

        if (
                cls.getSchoolId() == null
                        ||
                        !schoolId.equals(cls.getSchoolId())
        ) {
            throw new RuntimeException(
                    "Unauthorized access"
            );
        }

        // ================= CREATE SUBJECT =================

        Subject sub = new Subject();

        sub.setSubjectName(subjectName);

        sub.setClassEntity(cls);

        sub.setSchoolId(schoolId);

        int count =
                cls.getSubjects() == null
                        ? 0
                        : cls.getSubjects().size();

        sub.setNumber(count + 1);

        subjectRepo.save(sub);

        // ================= UPDATE LOCAL LIST =================

        if (cls.getSubjects() != null) {

            cls.getSubjects().add(sub);
        }

        return convertToDTO(cls);
    }

    // =====================================================
    // DELETE SUBJECT
    // =====================================================

    @Override
    public void deleteSubject(
            Long schoolId,
            Long classId,
            String subjectName
    ) {

        ClassEntity cls = classRepo.findById(classId)

                .orElseThrow(() ->
                        new RuntimeException("Class not found"));

        // ================= SECURITY =================

        if (
                cls.getSchoolId() == null
                        ||
                        !schoolId.equals(cls.getSchoolId())
        ) {
            throw new RuntimeException(
                    "Unauthorized access"
            );
        }

        // ================= FIND SUBJECT =================

        List<Subject> subjects =
                subjectRepo.findByClassEntityId(classId);

        Subject subject = subjects.stream()

                .filter(s ->

                        s.getSubjectName() != null
                                &&
                                s.getSubjectName()
                                        .equalsIgnoreCase(subjectName)
                )

                .findFirst()

                .orElseThrow(() ->
                        new RuntimeException("Subject not found"));

        // ================= DELETE FROM DB =================

        subjectRepo.delete(subject);
    }

    // =====================================================
    // DTO MAPPER
    // =====================================================

    private ClassDTO convertToDTO(ClassEntity cls) {

        ClassDTO dto = new ClassDTO();

        dto.setId(cls.getId());

        dto.setClassName(cls.getClassName());

        dto.setSchoolId(cls.getSchoolId());

        // ================= SUBJECTS =================

        if (cls.getSubjects() != null) {

            List<SubjectDTO> subjectList =

                    cls.getSubjects()

                            .stream()

                            .map(sub -> {

                                SubjectDTO s =
                                        new SubjectDTO();

                                s.setId(sub.getId());

                                s.setSubjectName(
                                        sub.getSubjectName()
                                );

                                s.setClassId(cls.getId());

                                s.setSchoolId(
                                        sub.getSchoolId()
                                );

                                s.setNumber(

                                        sub.getNumber() == null
                                                ? 0
                                                : sub.getNumber()
                                );

                                return s;

                            })

                            .collect(Collectors.toList());

            dto.setSubjects(subjectList);
        }

        return dto;
    }
}