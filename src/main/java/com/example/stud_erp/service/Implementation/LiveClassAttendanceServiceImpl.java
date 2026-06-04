package com.example.stud_erp.service.Implementation;

import com.example.stud_erp.entity.LiveClass;
import com.example.stud_erp.entity.LiveClassAttendance;
import com.example.stud_erp.entity.Student;
import com.example.stud_erp.payload.LiveClassAttendanceDto;
import com.example.stud_erp.repository.LiveClassAttendanceRepository;
import com.example.stud_erp.repository.LiveClassRepository;
import com.example.stud_erp.repository.StudentRepository;
import com.example.stud_erp.service.LiveClassAttendanceService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LiveClassAttendanceServiceImpl
        implements LiveClassAttendanceService {

    private final LiveClassAttendanceRepository attendanceRepo;
    private final LiveClassRepository liveClassRepo;
    private final StudentRepository studentRepo;

    public LiveClassAttendanceServiceImpl(
            LiveClassAttendanceRepository attendanceRepo,
            LiveClassRepository liveClassRepo,
            StudentRepository studentRepo
    ) {
        this.attendanceRepo = attendanceRepo;
        this.liveClassRepo = liveClassRepo;
        this.studentRepo = studentRepo;
    }

    @Override
    public LiveClassAttendanceDto joinClass(
            Long liveClassId,
            Long studentId
    ) {

        if (attendanceRepo.existsByStudentIdAndLiveClassId(
                studentId,
                liveClassId
        )) {

            LiveClassAttendance attendance =
                    attendanceRepo.findByStudentIdAndLiveClassId(
                            studentId,
                            liveClassId
                    ).orElseThrow();

            return convert(attendance);
        }

        LiveClass liveClass =
                liveClassRepo.findById(liveClassId)
                        .orElseThrow(() ->
                                new RuntimeException("Live Class Not Found"));

        Student student =
                studentRepo.findById(studentId)
                        .orElseThrow(() ->
                                new RuntimeException("Student Not Found"));

        LiveClassAttendance attendance =
                new LiveClassAttendance();

        attendance.setLiveClass(liveClass);
        attendance.setStudent(student);
        attendance.setJoinedAt(LocalDateTime.now());
        attendance.setAttended(true);

        attendance = attendanceRepo.save(attendance);

        return convert(attendance);
    }

    @Override
    public LiveClassAttendanceDto leaveClass(
            Long liveClassId,
            Long studentId
    ) {

        LiveClassAttendance attendance =
                attendanceRepo.findByStudentIdAndLiveClassId(
                        studentId,
                        liveClassId
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Attendance Not Found"
                        ));

        attendance.setLeftAt(LocalDateTime.now());

        if (attendance.getJoinedAt() != null) {

            long duration =
                    Duration.between(
                            attendance.getJoinedAt(),
                            attendance.getLeftAt()
                    ).toMinutes();

            attendance.setDurationMinutes(duration);
        }

        attendance = attendanceRepo.save(attendance);

        return convert(attendance);
    }

    @Override
    public List<LiveClassAttendanceDto>
    getAttendanceByClass(Long liveClassId) {

        return attendanceRepo
                .findByLiveClassId(liveClassId)
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    @Override
    public Long getAttendanceCount(
            Long liveClassId
    ) {
        return attendanceRepo.countByLiveClassId(
                liveClassId
        );
    }

    private LiveClassAttendanceDto convert(
            LiveClassAttendance attendance
    ) {

        LiveClassAttendanceDto dto =
                new LiveClassAttendanceDto();

        dto.setId(attendance.getId());

        dto.setLiveClassId(
                attendance.getLiveClass().getId()
        );

        dto.setStudentId(
                attendance.getStudent().getId()
        );

        dto.setStudentName(
                attendance.getStudent().getFullName()
        );

        dto.setJoinedAt(
                attendance.getJoinedAt()
        );

        dto.setLeftAt(
                attendance.getLeftAt()
        );

        dto.setDurationMinutes(
                attendance.getDurationMinutes()
        );

        dto.setAttended(
                attendance.getAttended()
        );

        return dto;
    }
}