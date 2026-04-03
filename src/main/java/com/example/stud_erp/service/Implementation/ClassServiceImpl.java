package com.example.stud_erp.service.Implementation;

import com.example.stud_erp.entity.ClassEntity;
import com.example.stud_erp.entity.Subject;
import com.example.stud_erp.payload.ClassDTO;
import com.example.stud_erp.payload.SubjectDTO;
import com.example.stud_erp.repository.ClassRepository;
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

    // ✅ CREATE CLASS
    @Override
    public ClassDTO createClass(ClassDTO dto) {
        ClassEntity cls = new ClassEntity();
        cls.setClassName(dto.getClassName());

        ClassEntity saved = classRepo.save(cls);

        dto.setId(saved.getId());
        return dto;
    }

    // ✅ GET ALL
    @Override
    public List<ClassDTO> getAllClasses() {
        return classRepo.findAll().stream().map(cls -> {
            ClassDTO dto = new ClassDTO();
            dto.setId(cls.getId());
            dto.setClassName(cls.getClassName());

            if (cls.getSubjects() != null) {
                dto.setSubjects(
                        cls.getSubjects()
                                .stream()
                                .map(sub -> {
                                    SubjectDTO s = new SubjectDTO();
                                    s.setId(sub.getId());
                                    s.setSubjectName(sub.getSubjectName());
                                    return s;
                                })
                                .collect(Collectors.toList())
                );
            }

            return dto;
        }).collect(Collectors.toList());
    }

    // ✅ DELETE CLASS
    @Override
    public void deleteClass(Long id) {
        classRepo.deleteById(id);
    }

    // ✅ ADD SUBJECT
    @Override
    public ClassDTO addSubject(Long classId, String subjectName) {
        ClassEntity cls = classRepo.findById(classId).orElseThrow();

        Subject sub = new Subject();
        sub.setSubjectName(subjectName);
        sub.setClassEntity(cls);

        subjectRepo.save(sub);

        return getAllClasses()
                .stream()
                .filter(c -> c.getId().equals(classId))
                .findFirst()
                .orElse(null);
    }

    // ✅ DELETE SUBJECT
    @Override
    public void deleteSubject(Long classId, String subjectName) {
        ClassEntity cls = classRepo.findById(classId).orElseThrow();

        List<Subject> updated =
                cls.getSubjects()
                        .stream()
                        .filter(s -> !s.getSubjectName().equals(subjectName))
                        .collect(Collectors.toList());

        cls.setSubjects(updated);
        classRepo.save(cls);
    }
}