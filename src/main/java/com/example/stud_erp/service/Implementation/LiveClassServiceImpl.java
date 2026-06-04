package com.example.stud_erp.service.Implementation;

import com.example.stud_erp.entity.*;
import com.example.stud_erp.enums.LiveClassStatus;
import com.example.stud_erp.payload.LiveClassDto;
import com.example.stud_erp.payload.LiveClassResponse;
import com.example.stud_erp.repository.*;
import com.example.stud_erp.service.LiveClassService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LiveClassServiceImpl implements LiveClassService {

    private final LiveClassRepository liveClassRepo;
    private final SchoolRepository schoolRepo;
    private final ProfessorRepository professorRepo;
    private final ClassRepository classRepo;
    private final StudentRepository studentRepo;
    private final NotificationRepository notificationRepo;
    private final SimpMessagingTemplate messagingTemplate;

    private final LiveClassAttendanceRepository attendanceRepo;

    public LiveClassServiceImpl(LiveClassRepository liveClassRepo, SchoolRepository schoolRepo, ProfessorRepository professorRepo, ClassRepository classRepo, StudentRepository studentRepo, NotificationRepository notificationRepo, SimpMessagingTemplate messagingTemplate, LiveClassAttendanceRepository attendanceRepo) {
        this.liveClassRepo = liveClassRepo;
        this.schoolRepo = schoolRepo;
        this.professorRepo = professorRepo;
        this.classRepo = classRepo;
        this.studentRepo = studentRepo;
        this.notificationRepo = notificationRepo;
        this.messagingTemplate = messagingTemplate;
        this.attendanceRepo = attendanceRepo;
    }

    @Override
    public LiveClass create(LiveClassDto dto) {

        School school = schoolRepo.findById(dto.getSchoolId())
                .orElseThrow(() -> new RuntimeException("School not found"));

        Professor professor = professorRepo.findById(dto.getProfessorId())
                .orElseThrow(() -> new RuntimeException("Professor not found"));

        ClassEntity classEntity = classRepo.findById(dto.getClassId())
                .orElseThrow(() -> new RuntimeException("Class not found"));

        LiveClass liveClass = new LiveClass();

        liveClass.setTopic(dto.getTopic());
        liveClass.setDescription(dto.getDescription());

        liveClass.setMeetingProvider(dto.getMeetingProvider());
        liveClass.setMeetingLink(dto.getMeetingLink());
        liveClass.setMeetingId(dto.getMeetingId());
        liveClass.setMeetingPassword(dto.getMeetingPassword());

        liveClass.setSchool(school);
        liveClass.setProfessor(professor);
        liveClass.setClassEntity(classEntity);

        liveClass.setCreatedBy(
                professor.getId()
        );

        liveClass.setUpdatedBy(
                professor.getId()
        );

        liveClass.setScheduledDate(dto.getScheduledDate());
        liveClass.setScheduledTime(dto.getScheduledTime());

        liveClass.setRecordingEnabled(dto.getRecordingEnabled());

        liveClass.setStatus(LiveClassStatus.SCHEDULED);

        LiveClass saved = liveClassRepo.save(liveClass);

        if (Boolean.TRUE.equals(dto.getNotifyStudents())) {

            List<Student> students =
                    studentRepo.findByClassEntity_IdAndIsDeletedFalse(
                            classEntity.getId()
                    );

            for (Student student : students) {

                Notification notification =
                        new Notification();

                notification.setTitle(
                        "Live Class Scheduled"
                );

                notification.setSubject(
                        saved.getTopic()
                );

                notification.setMessage(
                        professor.getName() +
                                " scheduled a live class."
                );

                notification.setStudent(student);

                notification.setSchoolId(
                        school.getId()
                );

                notification.setClassId(
                        classEntity.getId()
                );

                notification.setSender(
                        professor.getName()
                );

                notification.setSenderId(
                        professor.getId()
                );

                notification.setSenderType(
                        "PROFESSOR"
                );

                notification.setRecipientType(
                        "STUDENT"
                );

                notification.setSentAt(
                        LocalDateTime.now()
                );

                notificationRepo.save(notification);
            }
        }

        messagingTemplate.convertAndSend(
                "/topic/live/" + classEntity.getId(),
                saved
        );

        return saved;
    }

    @Override
    public LiveClass start(Long liveClassId) {

        LiveClass liveClass = getById(liveClassId);

        liveClass.setStatus(
                LiveClassStatus.LIVE
        );

        liveClass.setStartedAt(
                LocalDateTime.now()
        );

        LiveClass saved =
                liveClassRepo.save(liveClass);

        messagingTemplate.convertAndSend(
                "/topic/live/" +
                        liveClass.getClassEntity().getId(),
                saved
        );

        return saved;
    }

    @Override
    public LiveClass end(Long liveClassId) {

        LiveClass liveClass = getById(liveClassId);

        liveClass.setStatus(LiveClassStatus.ENDED);
        liveClass.setEndedAt(LocalDateTime.now());

        liveClass.setCurrentParticipants(0);

        // 🔥 AUTO CLOSE ALL ACTIVE ATTENDANCE
        List<LiveClassAttendance> list =
                attendanceRepo.findByLiveClassId(liveClassId);

        for (LiveClassAttendance att : list) {

            if (att.getLeftAt() == null) {
                att.setLeftAt(LocalDateTime.now());

                if (att.getJoinedAt() != null) {
                    long minutes = java.time.Duration.between(
                            att.getJoinedAt(),
                            att.getLeftAt()
                    ).toMinutes();

                    att.setDurationMinutes((long) minutes);
                }

                att.setAttended(false);
            }
        }

        attendanceRepo.saveAll(list);

        LiveClass saved = liveClassRepo.save(liveClass);

        messagingTemplate.convertAndSend(
                "/topic/live/" + liveClass.getClassEntity().getId(),
                saved
        );

        return saved;
    }

    @Override
    public LiveClass join(Long liveClassId, Long studentId) {

        LiveClass liveClass = getById(liveClassId);

        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        LiveClassAttendance attendance =
                attendanceRepo.findByStudentIdAndLiveClassId(
                        studentId,
                        liveClassId
                ).orElse(null);

        // FIRST TIME JOIN
        if (attendance == null) {

            attendance = new LiveClassAttendance();

            attendance.setLiveClass(liveClass);
            attendance.setStudent(student);

            attendance.setJoinedAt(LocalDateTime.now());
            attendance.setAttended(true);

            attendanceRepo.save(attendance);

            // TOTAL UNIQUE JOINED
            liveClass.setTotalParticipants(
                    liveClass.getTotalParticipants() + 1
            );

            // CURRENT ACTIVE
            liveClass.setCurrentParticipants(
                    liveClass.getCurrentParticipants() + 1
            );

            liveClassRepo.save(liveClass);
        }

        // REJOIN AFTER LEAVE
        else if(Boolean.FALSE.equals(attendance.getAttended())) {

            attendance.setAttended(true);

            attendance.setLeftAt(null);

            attendanceRepo.save(attendance);

            liveClass.setCurrentParticipants(
                    liveClass.getCurrentParticipants() + 1
            );

            liveClassRepo.save(liveClass);
        }

        return liveClass;
    }

    @Override
    public LiveClass leave(Long liveClassId, Long studentId) {

        LiveClassAttendance attendance =
                attendanceRepo.findByStudentIdAndLiveClassId(studentId, liveClassId)
                        .orElseThrow(() -> new RuntimeException("Attendance not found"));

        attendance.setLeftAt(LocalDateTime.now());

        if (attendance.getJoinedAt() != null) {

            long minutes = java.time.Duration.between(
                    attendance.getJoinedAt(),
                    attendance.getLeftAt()
            ).toMinutes();

            attendance.setDurationMinutes((long) minutes);
        }

        attendance.setAttended(false);

        LiveClass liveClass = getById(liveClassId);

        Integer current =
                liveClass.getCurrentParticipants();

        if(current != null && current > 0){
            liveClass.setCurrentParticipants(
                    current - 1
            );
        }

        liveClassRepo.save(liveClass);

        attendanceRepo.save(attendance);

        return getById(liveClassId);
    }

    @Override
    public LiveClass update(Long liveClassId,
                            LiveClassDto dto) {

        LiveClass liveClass =
                getById(liveClassId);

        liveClass.setTopic(dto.getTopic());
        liveClass.setDescription(dto.getDescription());

        liveClass.setMeetingProvider(
                dto.getMeetingProvider()
        );

        liveClass.setMeetingLink(
                dto.getMeetingLink()
        );

        liveClass.setMeetingId(
                dto.getMeetingId()
        );

        liveClass.setMeetingPassword(
                dto.getMeetingPassword()
        );

        liveClass.setScheduledDate(
                dto.getScheduledDate()
        );

        liveClass.setScheduledTime(
                dto.getScheduledTime()
        );

        liveClass.setRecordingEnabled(
                dto.getRecordingEnabled()
        );

        liveClass.setUpdatedBy(
                dto.getProfessorId()
        );

        return liveClassRepo.save(
                liveClass
        );
    }

    @Override
    public LiveClass updateRecording(
            Long liveClassId,
            String recordingUrl) {

        LiveClass liveClass =
                getById(liveClassId);

        liveClass.setRecordingUrl(
                recordingUrl
        );

        return liveClassRepo.save(
                liveClass
        );
    }

    @Override
    public void delete(Long liveClassId) {

        LiveClass liveClass =
                getById(liveClassId);

        liveClass.setDeleted(true);

        liveClass.setActive(false);

        liveClassRepo.save(liveClass);
    }

    @Override
    public LiveClass getById(Long liveClassId) {

        return liveClassRepo.findById(
                liveClassId
        ).orElseThrow(() ->
                new RuntimeException(
                        "Live Class Not Found"
                ));
    }

    @Override
    public LiveClass getCurrentClass(Long classId) {

        return liveClassRepo
                .findTopByClassEntity_IdAndStatusOrderByCreatedAtDesc(
                        classId,
                        LiveClassStatus.LIVE
                )
                .orElse(null);
    }

    @Override
    public LiveClass getCurrentProfessorClass(Long professorId) {

        return liveClassRepo
                .findTopByProfessor_IdAndStatusOrderByCreatedAtDesc(
                        professorId,
                        LiveClassStatus.LIVE
                )
                .orElse(null);
    }

    @Override
    public List<LiveClass> getSchoolClasses(Long schoolId) {

        return liveClassRepo
                .findBySchool_IdAndDeletedFalseOrderByCreatedAtDesc(
                        schoolId
                );
    }

    @Override
    public List<LiveClass> getSchoolClassesByStatus(
            Long schoolId,
            LiveClassStatus status) {

        return liveClassRepo
                .findBySchool_IdAndStatusOrderByCreatedAtDesc(
                        schoolId,
                        status
                );
    }

    @Override
    public List<LiveClass> getClassLiveHistory(Long classId) {

        return liveClassRepo
                .findByClassEntity_IdAndDeletedFalseOrderByCreatedAtDesc(
                        classId
                );
    }

    @Override
    public List<LiveClass> getClassLiveHistoryByStatus(
            Long classId,
            LiveClassStatus status) {

        return liveClassRepo
                .findByClassEntity_IdAndStatusOrderByCreatedAtDesc(
                        classId,
                        status
                );
    }

    @Override
    public List<LiveClassResponse> getProfessorClasses(Long professorId) {

        List<LiveClass> list =
                liveClassRepo
                        .findByProfessor_IdAndDeletedFalseOrderByCreatedAtDesc(
                                professorId
                        );

        return list.stream().map(lc -> {

            LiveClassResponse dto = new LiveClassResponse();

            dto.id = lc.getId();
            dto.topic = lc.getTopic();
            dto.description = lc.getDescription();
            dto.status = lc.getStatus().name();
            dto.meetingLink = lc.getMeetingLink();
            dto.meetingProvider = lc.getMeetingProvider();
            dto.scheduledDate = lc.getScheduledDate();
            dto.scheduledTime = lc.getScheduledTime();

            dto.totalMessages = lc.getTotalMessages();

            dto.className =
                    lc.getClassEntity() != null
                            ? lc.getClassEntity().getClassName()
                            : "N/A";

            // ================= FIXED =================

            long participants =
                    attendanceRepo.countByLiveClassId(lc.getId());

            dto.totalParticipants = Math.toIntExact(participants);

            dto.currentParticipants =
                    lc.getCurrentParticipants();

            long attendance =
                    attendanceRepo.countByLiveClassIdAndAttendedTrue(lc.getId());

            dto.totalAttendance = Math.toIntExact(attendance);

            return dto;

        }).toList();
    }

    @Override
    public List<LiveClass> getProfessorClassesByStatus(
            Long professorId,
            LiveClassStatus status) {

        return liveClassRepo
                .findByProfessor_IdAndStatusOrderByCreatedAtDesc(
                        professorId,
                        status
                );
    }

    @Override
    public List<LiveClass> getAllLiveClasses() {

        return liveClassRepo.findByStatus(
                LiveClassStatus.LIVE
        );
    }

    @Override
    public List<LiveClass> getSchoolLiveClasses(Long schoolId) {

        return liveClassRepo.findBySchool_IdAndStatus(
                schoolId,
                LiveClassStatus.LIVE
        );
    }

    @Override
    public List<LiveClass> getClassesByDate(LocalDate date) {

        return liveClassRepo.findByScheduledDate(date);
    }

    @Override
    public List<LiveClass> getSchoolClassesByDate(
            Long schoolId,
            LocalDate date) {

        return liveClassRepo.findBySchool_IdAndScheduledDate(
                schoolId,
                date
        );
    }

    @Override
    public long countSchoolClasses(Long schoolId) {

        return liveClassRepo.countBySchool_Id(schoolId);
    }

    @Override
    public long countSchoolLiveClasses(Long schoolId) {

        return liveClassRepo.countBySchool_IdAndStatus(
                schoolId,
                LiveClassStatus.LIVE
        );
    }

    @Override
    public long countProfessorClasses(Long professorId) {

        return liveClassRepo.countByProfessor_Id(professorId);
    }

    @Override
    public long countClassClasses(Long classId) {

        return liveClassRepo.countByClassEntity_Id(classId);
    }

    @Override
    public LiveClass getLatestSchoolClass(Long schoolId) {

        return liveClassRepo
                .findTopBySchool_IdOrderByCreatedAtDesc(schoolId)
                .orElse(null);
    }

    @Override
    public LiveClass getLatestProfessorClass(Long professorId) {

        return liveClassRepo
                .findTopByProfessor_IdOrderByCreatedAtDesc(professorId)
                .orElse(null);
    }

    @Override
    public LiveClass getLatestClassClass(Long classId) {

        return liveClassRepo
                .findTopByClassEntity_IdOrderByCreatedAtDesc(classId)
                .orElse(null);
    }

    @Override
    public List<LiveClassResponse> getLiveClassesForStudent(Long studentId) {

        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Long classId = student.getClassEntity().getId();

        List<LiveClass> list =
                liveClassRepo
                        .findByClassEntity_IdAndDeletedFalseOrderByCreatedAtDesc(
                                classId
                        );

        return list.stream().map(lc -> {

            LiveClassResponse dto = new LiveClassResponse();

            dto.id = lc.getId();
            dto.topic = lc.getTopic();
            dto.description = lc.getDescription();
            dto.status = lc.getStatus().name();
            dto.meetingLink = lc.getMeetingLink();
            dto.meetingProvider = lc.getMeetingProvider();
            dto.scheduledDate = lc.getScheduledDate();
            dto.scheduledTime = lc.getScheduledTime();

            dto.className = lc.getClassEntity() != null
                    ? lc.getClassEntity().getClassName()
                    : "N/A";

            // ================= FIXED =================
            long participants = 0;

            if (lc.getId() != null) {
                participants = attendanceRepo.countByLiveClassId(lc.getId());
            }

            dto.totalParticipants = Math.toIntExact(participants);

            dto.currentParticipants =
                    lc.getCurrentParticipants();

            return dto;

        }).toList();
    }
}