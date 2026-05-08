//package com.example.stud_erp.service.Implementation;
//
//import com.example.stud_erp.entity.Attendance;
//import com.example.stud_erp.entity.ClassSession;
//import com.example.stud_erp.entity.Student;
//import com.example.stud_erp.entity.TeacherAttendance;
//import com.example.stud_erp.payload.AttendanceDTO;
//import com.example.stud_erp.payload.ClassSessionDTO;
//import com.example.stud_erp.repository.AttendanceRepository;
//import com.example.stud_erp.repository.ClassSessionRepository;
//import com.example.stud_erp.repository.StudentRepository;
//import com.example.stud_erp.service.AttendanceService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class AttendanceServiceImpl implements AttendanceService {
//
//    @Autowired
//    private ClassSessionRepository sessionRepo;
//
//    @Autowired
//    private StudentRepository studentRepo;
//
//    @Autowired
//    private AttendanceRepository attendanceRepo;
//
//    @Autowired
//    private AttendanceRepository attendanceRepository;
//
//    // 🔥 SAVE ATTENDANCE
//    @Override
//    public ClassSessionDTO saveAttendance(ClassSessionDTO dto) {
//
//        // 🔥 Duplicate check
//        ClassSession existing = sessionRepo
//                .findByClassNumberAndDateAndSubject(
//                        dto.getClassNumber(),
//                        dto.getDate(),
//                        dto.getSubject()
//                ).orElse(null);
//
//        ClassSession session = (existing != null) ? existing : new ClassSession();
//
//        session.setLecturer(dto.getLecturer());
//        session.setSubject(dto.getSubject());
//        session.setClassNumber(dto.getClassNumber());
//        session.setDate(dto.getDate());
//        session.setTime(dto.getTime());
//
//        List<Attendance> attendanceList = new ArrayList<>();
//
//        for (AttendanceDTO a : dto.getStudents()) {
//
//            Student student = studentRepo.findById(a.getStudentId())
//                    .orElseThrow(() -> new RuntimeException("Student not found"));
//
//            Attendance att = new Attendance();
//            att.setStudent(student);
//            att.setStatus(a.getStatus());
//            att.setClassSession(session);
//
//            attendanceList.add(att);
//        }
//
//        session.setAttendance(attendanceList);
//
//        ClassSession saved = sessionRepo.save(session);
//
//        return mapToDTO(saved);
//    }
//
//    // 🔥 CLASS ATTENDANCE
//    @Override
//    public List<ClassSessionDTO> getClassAttendance(Integer classNumber) {
//
//        return sessionRepo.findByClassNumber(classNumber)
//                .stream()
//                .map(this::mapToDTO)
//                .toList();
//    }
//
//    // 🔥 STUDENT ATTENDANCE
//    @Override
//    public List<ClassSessionDTO> getStudentAttendance(Long studentId) {
//
//        List<Attendance> list = attendanceRepo.findByStudent_Id(studentId);
//
//        return list.stream()
//                .map(att -> mapToDTO(att.getClassSession()))
//                .distinct()
//                .toList();
//    }
//
//    // ================= MAPPER =================
//
//    private ClassSessionDTO mapToDTO(ClassSession session) {
//
//        ClassSessionDTO dto = new ClassSessionDTO();
//
//        dto.setId(session.getId());
//        dto.setLecturer(session.getLecturer());
//        dto.setSubject(session.getSubject());
//        dto.setClassNumber(session.getClassNumber());
//        dto.setDate(session.getDate());
//        dto.setTime(session.getTime());
//
//        List<AttendanceDTO> students = session.getAttendance().stream().map(a -> {
//            AttendanceDTO ad = new AttendanceDTO();
//            ad.setStudentId(a.getStudent().getId());
//            ad.setStudentName(a.getStudent().getStudName());
//            ad.setStatus(a.getStatus());
//            return ad;
//        }).toList();
//
//        dto.setStudents(students);
//
//        return dto;
//    }
//
//    @Override
//    public List<Attendance> getByDate(LocalDate date) {
//        return attendanceRepository.findByDate(date);
//    }
//
//    @Override
//    public ClassSessionDTO getClassAttendanceByDate(Integer classNumber, LocalDate date) {
//
//        List<Attendance> records = attendanceRepository
//                .findByClassNumberAndDate(classNumber, date);
//
//        if (records.isEmpty()) {
//            return null;
//        }
//
//        ClassSessionDTO dto = new ClassSessionDTO();
//        dto.setClassNumber(classNumber);
//        dto.setDate(date);
//
//        List<AttendanceDTO> students = records.stream().map(a -> {
//            AttendanceDTO s = new AttendanceDTO();
//
//            s.setStudentId(a.getStudent().getId());
//            s.setStudentName(a.getStudent().getStudName());
//            s.setStudRollNo(a.getStudent().getStudRollNo());
//            s.setStatus(a.getStatus());
//
//            return s;
//        }).toList();
//
//        dto.setStudents(students);
//
//        return dto;
//    }
//
//}



// updated



//
//package com.example.stud_erp.service.Implementation;
//
//import com.example.stud_erp.entity.Attendance;
//import com.example.stud_erp.entity.AttendanceStatus;
//import com.example.stud_erp.entity.ClassSession;
//import com.example.stud_erp.entity.Student;
//import com.example.stud_erp.payload.AttendanceDTO;
//import com.example.stud_erp.payload.ClassSessionDTO;
//import com.example.stud_erp.repository.AttendanceRepository;
//import com.example.stud_erp.repository.ClassSessionRepository;
//import com.example.stud_erp.repository.StudentRepository;
//import com.example.stud_erp.service.AttendanceService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class AttendanceServiceImpl implements AttendanceService {
//
//    @Autowired
//    private ClassSessionRepository sessionRepo;
//
//    @Autowired
//    private StudentRepository studentRepo;
//
//    @Autowired
//    private AttendanceRepository attendanceRepo;
//
//    // ============================================================
//    // 🔥 EXISTING LOGIC (FIXED)
//    // ============================================================
//
//    @Override
//    public ClassSessionDTO saveAttendance(ClassSessionDTO dto) {
//
//        ClassSession existing = sessionRepo
//                .findByClassNumberAndDateAndSubject(
//                        dto.getClassNumber(),
//                        dto.getDate(),
//                        dto.getSubject()
//                ).orElse(null);
//
//        ClassSession session = (existing != null) ? existing : new ClassSession();
//
//        session.setLecturer(dto.getLecturer());
//        session.setSubject(dto.getSubject());
//        session.setClassNumber(dto.getClassNumber());
//        session.setDate(dto.getDate());
//        session.setTime(dto.getTime());
//
//        List<Attendance> attendanceList = new ArrayList<>();
//
//        for (AttendanceDTO a : dto.getStudents()) {
//
//            Student student = studentRepo.findById(a.getStudentId())
//                    .orElseThrow(() -> new RuntimeException("Student not found"));
//
//            Attendance att = new Attendance();
//            att.setStudent(student);
//
//            // ✅ FIX (String → Enum)
//            att.setStatus(AttendanceStatus.valueOf(a.getStatus().toUpperCase()));
//
//            att.setClassSession(session);
//
//            attendanceList.add(att);
//        }
//
//        session.setAttendance(attendanceList);
//
//        ClassSession saved = sessionRepo.save(session);
//
//        return mapToDTO(saved);
//    }
//
//    @Override
//    public List<ClassSessionDTO> getClassAttendance(Integer classNumber) {
//        return sessionRepo.findByClassNumber(classNumber)
//                .stream()
//                .map(this::mapToDTO)
//                .toList();
//    }
//
//    @Override
//    public List<ClassSessionDTO> getStudentAttendance(Long studentId) {
//
//        List<Attendance> list = attendanceRepo.findByStudent_Id(studentId);
//
//        return list.stream()
//                .map(att -> mapToDTO(att.getClassSession()))
//                .distinct()
//                .toList();
//    }
//
//    @Override
//    public List<Attendance> getByDate(LocalDate date) {
//        return attendanceRepo.findByDate(date);
//    }
//
//    @Override
//    public ClassSessionDTO getClassAttendanceByDate(Integer classNumber, LocalDate date) {
//
//        List<Attendance> records = attendanceRepo
//                .findByClassNumberAndDate(classNumber, date);
//
//        if (records.isEmpty()) {
//            return null;
//        }
//
//        ClassSessionDTO dto = new ClassSessionDTO();
//        dto.setClassNumber(classNumber);
//        dto.setDate(date);
//
//        List<AttendanceDTO> students = records.stream().map(a -> {
//            AttendanceDTO s = new AttendanceDTO();
//
//            s.setStudentId(a.getStudent().getId());
//            s.setStudentName(a.getStudent().getStudName());
//            s.setStudRollNo(a.getStudent().getStudRollNo());
//
//            // ✅ FIX (Enum → String)
//            s.setStatus(a.getStatus().name());
//
//            return s;
//        }).toList();
//
//        dto.setStudents(students);
//
//        return dto;
//    }
//
//    // ============================================================
//    // 🔥 BULK LOGIC (FINAL FIXED)
//    // ============================================================
//
//    @Override
//    public void saveOrUpdateBulkAttendance(List<Attendance> attendanceList) {
//
//        for (Attendance incoming : attendanceList) {
//
//            Long studentId = incoming.getStudent().getId();
//            LocalDate date = incoming.getDate();
//            int classNumber = incoming.getClassNumber();
//
//            Optional<Attendance> existing = attendanceRepo
//                    .findByStudent_IdAndDateAndClassNumber(
//                            studentId,
//                            date,
//                            classNumber
//                    );
//
//            if (existing.isPresent()) {
//                // ✅ UPDATE
//                Attendance old = existing.get();
//                old.setStatus(incoming.getStatus());
//                attendanceRepo.save(old);
//
//            } else {
//                // ✅ INSERT
//                Student student = studentRepo.findById(studentId)
//                        .orElseThrow(() -> new RuntimeException("Student not found"));
//
//                Attendance newAtt = new Attendance();
//                newAtt.setStudent(student);
//                newAtt.setStatus(incoming.getStatus());
//                newAtt.setDate(date);
//                newAtt.setClassNumber(classNumber);
//
//                attendanceRepo.save(newAtt);
//            }
//        }
//    }
//
//    // ============================================================
//    // 🔥 PREFILL DATA
//    // ============================================================
//
//    @Override
//    public List<Attendance> getAttendanceByClassAndDate(int classNumber, LocalDate date) {
//
//        return attendanceRepo
//                .findByClassNumberAndDateOrderByStudent_StudRollNoAsc(
//                        classNumber,
//                        date
//                );
//    }
//
//    // ============================================================
//    // 🔥 MAPPER (FINAL FIXED)
//    // ============================================================
//
//    private ClassSessionDTO mapToDTO(ClassSession session) {
//
//        ClassSessionDTO dto = new ClassSessionDTO();
//
//        dto.setId(session.getId());
//        dto.setLecturer(session.getLecturer());
//        dto.setSubject(session.getSubject());
//        dto.setClassNumber(session.getClassNumber());
//        dto.setDate(session.getDate());
//        dto.setTime(session.getTime());
//
//        List<AttendanceDTO> students = session.getAttendance().stream().map(a ->
//        {
//            AttendanceDTO ad = new AttendanceDTO();
//            ad.setStudentId(a.getStudent().getId());
//            ad.setStudentName(a.getStudent().getStudName());
//
//            // ✅ FIX (Enum → String)
//            ad.setStatus(a.getStatus().name());
//
//            return ad;
//        }).toList();
//
//        dto.setStudents(students);
//
//        return dto;
//    }
//}



// update 06/5/26
//
//package com.example.stud_erp.service.Implementation;
//
//import com.example.stud_erp.entity.*;
//import com.example.stud_erp.payload.AttendanceDTO;
//import com.example.stud_erp.payload.ClassSessionDTO;
//import com.example.stud_erp.repository.AttendanceRepository;
//import com.example.stud_erp.repository.ClassSessionRepository;
//import com.example.stud_erp.repository.StudentRepository;
//import com.example.stud_erp.service.AttendanceService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.util.*;
//
//@Service
//public class AttendanceServiceImpl implements AttendanceService {
//
//    @Autowired
//    private ClassSessionRepository sessionRepo;
//
//    @Autowired
//    private StudentRepository studentRepo;
//
//    @Autowired
//    private AttendanceRepository attendanceRepo;
//
//    // ============================================================
//    // ✅ SAVE ATTENDANCE (FINAL FIXED AS PER REQUIREMENT)
//    // ============================================================
//
//    @Override
//    public ClassSessionDTO saveAttendance(ClassSessionDTO dto) {
//
//        // 🔥 BLOCK DUPLICATE: CLASS + DATE
//        List<Attendance> existingRecords = attendanceRepo
//                .findByClassNumberAndDate(dto.getClassNumber(), dto.getDate());
//
//        if (!existingRecords.isEmpty()) {
//            throw new RuntimeException("Attendance already marked for this class on this date");
//        }
//
//        ClassSession session = new ClassSession();
//
//        session.setLecturer(dto.getLecturer());
//        session.setSubject(dto.getSubject());
//        session.setClassNumber(dto.getClassNumber());
//        session.setDate(dto.getDate());
//        session.setTime(dto.getTime());
//
//        List<Attendance> attendanceList = new ArrayList<>();
//
//        for (AttendanceDTO a : dto.getStudents()) {
//
//            Student student = studentRepo.findById(a.getStudentId())
//                    .orElseThrow(() -> new RuntimeException("Student not found"));
//
//            Attendance att = new Attendance();
//            att.setStudent(student);
//            att.setStatus(AttendanceStatus.valueOf(a.getStatus().toUpperCase()));
//            att.setDate(dto.getDate());
//            att.setClassNumber(dto.getClassNumber());
//            att.setClassSession(session);
//
//            attendanceList.add(att);
//        }
//
//        session.setAttendance(attendanceList);
//
//        ClassSession saved = sessionRepo.save(session);
//
//        return mapToDTO(saved);
//    }
//
//    // ============================================================
//    // ✅ GET CLASS ATTENDANCE
//    // ============================================================
//
//    @Override
//    public List<ClassSessionDTO> getClassAttendance(Integer classNumber) {
//        return sessionRepo.findByClassNumber(classNumber)
//                .stream()
//                .map(this::mapToDTO)
//                .toList();
//    }
//
//    // ============================================================
//    // ✅ STUDENT ATTENDANCE
//    // ============================================================
//
//    @Override
//    public List<ClassSessionDTO> getStudentAttendance(Long studentId) {
//
//        List<Attendance> list = attendanceRepo.findByStudent_Id(studentId);
//
//        return list.stream()
//                .map(att -> mapToDTO(att.getClassSession()))
//                .distinct()
//                .toList();
//    }
//
//    // ============================================================
//    // ✅ BY DATE
//    // ============================================================
//
//    @Override
//    public List<Attendance> getByDate(LocalDate date) {
//        return attendanceRepo.findByDate(date);
//    }
//
//    // ============================================================
//    // ✅ CLASS + DATE
//    // ============================================================
//
//    @Override
//    public ClassSessionDTO getClassAttendanceByDate(Integer classNumber, LocalDate date) {
//
//        List<Attendance> records = attendanceRepo
//                .findByClassNumberAndDate(classNumber, date);
//
//        if (records.isEmpty()) return null;
//
//        ClassSessionDTO dto = new ClassSessionDTO();
//        dto.setClassNumber(classNumber);
//        dto.setDate(date);
//
//        List<AttendanceDTO> students = records.stream().map(a -> {
//            AttendanceDTO s = new AttendanceDTO();
//
//            s.setStudentId(a.getStudent().getId());
//            s.setStudentName(a.getStudent().getStudName());
//            s.setStudRollNo(a.getStudent().getStudRollNo());
//            s.setStatus(a.getStatus().name());
//
//            return s;
//        }).toList();
//
//        dto.setStudents(students);
//
//        return dto;
//    }
//
//    // ============================================================
//    // 🔥 BULK SAVE (NO UPDATE - STRICT MODE)
//    // ============================================================
//
//    @Override
//    public void saveOrUpdateBulkAttendance(List<Attendance> attendanceList) {
//
//        for (Attendance incoming : attendanceList) {
//
//            Long studentId = incoming.getStudent().getId();
//
//            boolean exists = attendanceRepo
//                    .existsByStudent_IdAndDateAndClassNumber(
//                            studentId,
//                            incoming.getDate(),
//                            incoming.getClassNumber()
//                    );
//
//            if (exists) {
//                throw new RuntimeException("Attendance already exists for this class and date");
//            }
//
//            Student student = studentRepo.findById(studentId)
//                    .orElseThrow(() -> new RuntimeException("Student not found"));
//
//            incoming.setStudent(student);
//            attendanceRepo.save(incoming);
//        }
//    }
//
//    // ============================================================
//    // ✅ PREFILL
//    // ============================================================
//
//    @Override
//    public List<Attendance> getAttendanceByClassAndDate(int classNumber, LocalDate date) {
//
//        return attendanceRepo
//                .findByClassNumberAndDateOrderByStudent_StudRollNoAsc(
//                        classNumber,
//                        date
//                );
//    }
//
//    // ============================================================
//    // 🔥 MAPPER
//    // ============================================================
//
//    private ClassSessionDTO mapToDTO(ClassSession session) {
//
//        ClassSessionDTO dto = new ClassSessionDTO();
//
//        dto.setId(session.getId());
//        dto.setLecturer(session.getLecturer());
//        dto.setSubject(session.getSubject());
//        dto.setClassNumber(session.getClassNumber());
//        dto.setDate(session.getDate());
//        dto.setTime(session.getTime());
//
//        List<AttendanceDTO> students = session.getAttendance().stream().map(a -> {
//            AttendanceDTO ad = new AttendanceDTO();
//            ad.setStudentId(a.getStudent().getId());
//            ad.setStudentName(a.getStudent().getStudName());
//            ad.setStatus(a.getStatus().name());
//            return ad;
//        }).toList();
//
//        dto.setStudents(students);
//
//        return dto;
//    }
//}




package com.example.stud_erp.service.Implementation;

import com.example.stud_erp.entity.Attendance;
import com.example.stud_erp.entity.ClassSession;
import com.example.stud_erp.entity.Student;

import com.example.stud_erp.payload.AttendanceDTO;
import com.example.stud_erp.payload.ClassSessionDTO;

import com.example.stud_erp.repository.AttendanceRepository;
import com.example.stud_erp.repository.ClassSessionRepository;
import com.example.stud_erp.repository.StudentRepository;

import com.example.stud_erp.service.AttendanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private ClassSessionRepository sessionRepo;

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private AttendanceRepository attendanceRepo;

    // ============================================================
    // SAVE ATTENDANCE
    // ============================================================

    @Override
    public ClassSessionDTO saveAttendance(ClassSessionDTO dto) {

        // =========================================
        // DUPLICATE CHECK
        // =========================================
        List<Attendance> existingRecords =
                attendanceRepo.findByClassNameAndAttendanceDate(
                        dto.getClassName(),
                        dto.getAttendanceDate()
                );

        if (!existingRecords.isEmpty()) {
            throw new RuntimeException(
                    "Attendance already marked for this class on this date"
            );
        }

        // =========================================
        // CREATE SESSION
        // =========================================
        ClassSession session = new ClassSession();

        session.setLecturer(dto.getLecturer());

        session.setSubject(dto.getSubject());

        session.setClassName(dto.getClassName());

        session.setAttendanceDate(dto.getAttendanceDate());

        session.setTime(dto.getTime());

        // =========================================
        // ATTENDANCE LIST
        // =========================================
        List<Attendance> attendanceList = new ArrayList<>();

        for (AttendanceDTO a : dto.getStudents()) {

            Student student = studentRepo.findById(a.getStudentId())
                    .orElseThrow(() ->
                            new RuntimeException("Student not found")
                    );

            Attendance attendance = new Attendance();

            attendance.setStudent(student);

            attendance.setStatus(a.getStatus());

            attendance.setAttendanceDate(dto.getAttendanceDate());

            attendance.setClassName(dto.getClassName());

            attendance.setClassSession(session);

            attendanceList.add(attendance);
        }

        session.setAttendance(attendanceList);

        ClassSession saved = sessionRepo.save(session);

        return mapToDTO(saved);
    }

    // ============================================================
    // GET CLASS ATTENDANCE
    // ============================================================

    @Override
    public List<ClassSessionDTO> getClassAttendance(String className) {

        return sessionRepo.findByClassName(className)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    // ============================================================
    // GET STUDENT ATTENDANCE
    // ============================================================

    @Override
    public List<ClassSessionDTO> getStudentAttendance(Long studentId) {

        List<Attendance> list =
                attendanceRepo.findByStudent_Id(studentId);

        return list.stream()
                .map(att -> mapToDTO(att.getClassSession()))
                .distinct()
                .toList();
    }

    // ============================================================
    // GET BY DATE
    // ============================================================

    @Override
    public List<Attendance> getByDate(LocalDate date) {

        return attendanceRepo.findByAttendanceDate(date);
    }

    // ============================================================
    // CLASS + DATE
    // ============================================================

    @Override
    public ClassSessionDTO getClassAttendanceByDate(
            String className,
            LocalDate attendanceDate
    ) {

        List<Attendance> records =
                attendanceRepo.findByClassNameAndAttendanceDate(
                        className,
                        attendanceDate
                );

        if (records.isEmpty()) {
            return null;
        }

        ClassSessionDTO dto = new ClassSessionDTO();

        dto.setClassName(className);

        dto.setAttendanceDate(attendanceDate);

        List<AttendanceDTO> students = records.stream().map(a -> {

            AttendanceDTO s = new AttendanceDTO();

            s.setStudentId(a.getStudent().getId());

            s.setStudentName(a.getStudent().getStudName());

            s.setStudRollNo(a.getStudent().getStudRollNo());

            s.setStatus(a.getStatus());

            return s;

        }).toList();

        dto.setStudents(students);

        return dto;
    }

    // ============================================================
    // BULK SAVE
    // ============================================================

    @Override
    public void saveOrUpdateBulkAttendance(
            List<Attendance> attendanceList
    ) {

        for (Attendance incoming : attendanceList) {

            Long studentId = incoming.getStudent().getId();

            boolean exists =
                    attendanceRepo
                            .existsByStudent_IdAndAttendanceDateAndClassName(
                                    studentId,
                                    incoming.getAttendanceDate(),
                                    incoming.getClassName()
                            );

            if (exists) {

                throw new RuntimeException(
                        "Attendance already exists for this class and date"
                );
            }

            Student student = studentRepo.findById(studentId)
                    .orElseThrow(() ->
                            new RuntimeException("Student not found")
                    );

            incoming.setStudent(student);

            attendanceRepo.save(incoming);
        }
    }

    // ============================================================
    // PREFILL
    // ============================================================

    @Override
    public List<Attendance> getAttendanceByClassAndDate(
            String className,
            LocalDate attendanceDate
    ) {

        return attendanceRepo
                .findByClassNameAndAttendanceDateOrderByStudent_StudRollNoAsc(
                        className,
                        attendanceDate
                );
    }

    // ============================================================
    // MAPPER
    // ============================================================

    private ClassSessionDTO mapToDTO(ClassSession session) {

        ClassSessionDTO dto = new ClassSessionDTO();

        dto.setId(session.getId());

        dto.setLecturer(session.getLecturer());

        dto.setSubject(session.getSubject());

        dto.setClassName(session.getClassName());

        dto.setAttendanceDate(session.getAttendanceDate());

        dto.setTime(session.getTime());

        List<AttendanceDTO> students =
                session.getAttendance()
                        .stream()
                        .map(a -> {

                            AttendanceDTO ad =
                                    new AttendanceDTO();

                            ad.setStudentId(
                                    a.getStudent().getId()
                            );

                            ad.setStudentName(
                                    a.getStudent().getStudName()
                            );

                            ad.setStudRollNo(
                                    a.getStudent().getStudRollNo()
                            );

                            ad.setStatus(a.getStatus());

                            return ad;

                        }).toList();

        dto.setStudents(students);

        return dto;
    }
}