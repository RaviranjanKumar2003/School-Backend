package com.example.stud_erp.service;

import com.example.stud_erp.entity.*;
import com.example.stud_erp.payload.ExamNoticeDTO;
import com.example.stud_erp.payload.NotificationDTO;
import com.example.stud_erp.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamNoticeService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private ExamNoticeRepository noticeRepo;

    @Autowired
    private ClassRepository classRepo;

    @Autowired
    private NotificationService notificationService;

    public void createExamNotice( ExamNotice req) {

        Long classId =
                req.getClassId();

        String examType =
                req.getExamType();

        String message =
                req.getMessage();

        Long schoolId =
                req.getSchoolId();

        String schoolCode =
                req.getSchoolCode();

        String createdBy =
                req.getCreatedBy();

        List<Subject> subjects =
                subjectRepository
                        .findByClassEntityIdAndSchoolId(
                                classId,
                                schoolId
                        );

        for (Subject sub : subjects) {

            String subjectName = sub.getSubjectName().trim().toLowerCase();

            List<Professor> teachers =
                    professorRepository
                            .findBySchool_Id(schoolId)
                            .stream()
                            .filter(p ->
                                    p.getAssignments() != null &&
                                            p.getAssignments().stream().anyMatch(a ->

                                                    // SUBJECT MATCH
                                                    a.getSubjectName() != null &&
                                                            a.getSubjectName().trim()
                                                                    .equalsIgnoreCase(subjectName.trim())

                                                            // CLASS MATCH
                                                            &&
                                                            a.getClassName() != null &&
                                                            a.getClassName().trim()
                                                                    .equalsIgnoreCase(
                                                                            classRepo.findById(classId)
                                                                                    .get()
                                                                                    .getClassName()
                                                                                    .trim()
                                                                    )
                                            )
                            )
                            .toList();


            if (teachers.isEmpty()) {

                ExamNotice notice = new ExamNotice();

                notice.setClassId(classId);
                notice.setExamType(examType);

                notice.setSubjectName(sub.getSubjectName());

                notice.setSchoolId(schoolId);

                notice.setSchoolCode(schoolCode);

                notice.setMessage(message);

                notice.setStatus("CREATED");

                notice.setCreatedBy(createdBy);

                notice.setTeacherAssigned(false);

                noticeRepo.save(notice);
            }

            for (Professor teacher : teachers) {

                ExamNotice notice = new ExamNotice();

                notice.setClassId(classId);
                notice.setExamType(examType);
                notice.setSubjectName(sub.getSubjectName());
                notice.setTeacherId(teacher.getId());
                notice.setSchoolId(schoolId);
                notice.setSchoolCode(schoolCode);

                notice.setMessage(message);
                notice.setStatus("CREATED");
                notice.setCreatedBy(createdBy);

                notice.setTeacherAssigned(true);

                noticeRepo.save(notice);
                NotificationDTO dto =
                        new NotificationDTO();

                dto.setTitle(
                        "Exam Assigned"
                );

                dto.setSubject(
                        examType + " Exam"
                );

                dto.setMessage(
                        message
                );

                dto.setSender(
                        createdBy
                );

                dto.setSenderId(
                        req.getSenderId()
                );

                dto.setSenderType(
                        req.getSenderType()
                );

                dto.setRecipientType(
                        "SINGLE_TEACHER"
                );

                dto.setTeacherId(
                        teacher.getId()
                );

                dto.setSchoolId(
                        schoolId
                );

                notificationService
                        .sendNotification(dto);
            }
        }
    }

    // TEACHER NOTICES
    public List<ExamNoticeDTO> getTeacherNotices(Long teacherId) {

        List<ExamNotice> list = noticeRepo.findByTeacherId(teacherId);

        return list.stream().map(n -> {

            ExamNoticeDTO dto = new ExamNoticeDTO();

            dto.setId(n.getId());
            dto.setSubjectName(n.getSubjectName());
            dto.setExamType(n.getExamType());
            dto.setMessage(n.getMessage());
            dto.setCreatedAt(n.getCreatedAt().toString());
            dto.setTeacherAssigned(
                    n.getTeacherAssigned()
            );

            // CLASS NAME
            dto.setClassName("Unknown");

            if (n.getClassId() != null) {
                classRepo.findById(n.getClassId())
                        .ifPresent(c -> dto.setClassName(c.getClassName()));
            }

            return dto;

        }).toList();
    }

    // ALL NOTICES
    public List<ExamNoticeDTO> getAll() {

        List<ExamNotice> list = noticeRepo.findAll();

        return list.stream().map(n -> {

            ExamNoticeDTO dto = new ExamNoticeDTO();

            dto.setId(n.getId());
            dto.setExamType(n.getExamType());
            dto.setSubjectName(n.getSubjectName());
            dto.setMessage(n.getMessage());
            dto.setStatus(n.getStatus());
            dto.setCreatedAt(n.getCreatedAt().toString());
            dto.setTeacherAssigned(
                    n.getTeacherAssigned()
            );

            // CLASS NAME
            dto.setClassName("Unknown");

            if (n.getClassId() != null) {
                classRepo.findById(n.getClassId())
                        .ifPresent(c -> dto.setClassName(c.getClassName()));
            }

            return dto;

        }).toList();
    }


    // ✅ SCHOOL WISE NOTICES
    public List<ExamNoticeDTO> getBySchool(Long schoolId) {

        List<ExamNotice> list =
                noticeRepo.findBySchoolId(schoolId);

        return list.stream().map(n -> {

            ExamNoticeDTO dto =
                    new ExamNoticeDTO();

            dto.setId(n.getId());
            dto.setExamType(n.getExamType());
            dto.setSubjectName(n.getSubjectName());
            dto.setMessage(n.getMessage());
            dto.setStatus(n.getStatus());
            dto.setCreatedAt(n.getCreatedAt().toString());
            dto.setTeacherAssigned(
                    n.getTeacherAssigned()
            );

            // CLASS NAME
            dto.setClassName("Unknown");

            if (n.getClassId() != null) {

                classRepo.findById(n.getClassId())
                        .ifPresent(c ->
                                dto.setClassName(
                                        c.getClassName()
                                ));
            }

            return dto;

        }).toList();
    }


    public void delete(Long id) {
        noticeRepo.deleteById(id);
    }

    public void updateExamNotice(List<Long> ids, String message) {

        List<ExamNotice> notices = noticeRepo.findAllById(ids);

        for (ExamNotice n : notices) {
            n.setMessage(message);
        }

        noticeRepo.saveAll(notices);
    }
}