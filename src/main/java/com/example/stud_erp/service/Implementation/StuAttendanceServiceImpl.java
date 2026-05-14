
//package com.example.stud_erp.service.Implementation;
//
//import com.example.stud_erp.entity.Student;
//import com.example.stud_erp.entity.StuAttendance;
//import com.example.stud_erp.payload.StuAttendanceDTO;
//import com.example.stud_erp.repository.StudentRepository;
//import com.example.stud_erp.repository.StuAttendanceRepository;
//import com.example.stud_erp.service.StuAttendanceService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.util.*;
//
//@Service
//@Transactional
//public class StuAttendanceServiceImpl implements StuAttendanceService {
//
//    @Autowired
//    private StuAttendanceRepository repo;
//
//    @Autowired
//    private StudentRepository studentRepo;
//
//    // =========================================================
//    // 🔥 SAVE OR UPDATE ATTENDANCE (UPSERT LOGIC)
//    // =========================================================
//    @Override
//    public String save(String className,
//                       LocalDate attendanceDate,
//                       List<StuAttendanceDTO> list) {
//
//        List<StuAttendance> records = new ArrayList<>();
//
//        for (StuAttendanceDTO dto : list) {
//
//            Student student = studentRepo.findById(dto.getStudentId())
//                    .orElseThrow(() ->
//                            new RuntimeException(
//                                    "Student not found: " + dto.getStudentId()
//                            ));
//
//            if (dto.getStatus() == null) {
//                throw new RuntimeException(
//                        "Status missing for student: "
//                                + student.getStudName()
//                );
//            }
//
//            // =====================================================
//            // ✅ CHECK EXISTING RECORD
//            // =====================================================
//            Optional<StuAttendance> existingOpt =
//                    repo.findByStudent_IdAndClassNameAndAttendanceDate(
//                            student.getId(),
//                            className,
//                            attendanceDate
//                    );
//
//            StuAttendance attendance;
//
//            // =========================
//            // UPDATE
//            // =========================
//            if (existingOpt.isPresent()) {
//
//                attendance = existingOpt.get();
//                attendance.setStatus(dto.getStatus());
//            }
//            // =========================
//            // NEW SAVE
//            // =========================
//            else {
//
//                attendance = new StuAttendance();
//                attendance.setStudent(student);
//                attendance.setClassName(className);
//                attendance.setAttendanceDate(attendanceDate);
//                attendance.setStatus(dto.getStatus());
//            }
//
//            records.add(attendance);
//        }
//
//        repo.saveAll(records);
//
//        return "✅ Attendance Saved / Updated Successfully";
//    }
//
//    // =========================================================
//    // 🔥 PREFILL ATTENDANCE
//    // =========================================================
//    @Override
//    public List<StuAttendanceDTO> getByClassAndDate(
//            String className,
//            LocalDate attendanceDate
//    ) {
//
//        return repo.findByClassNameAndAttendanceDate(
//                        className,
//                        attendanceDate
//                )
//                .stream()
//                .map(a -> {
//
//                    StuAttendanceDTO dto = new StuAttendanceDTO();
//
//                    dto.setStudentId(a.getStudent().getId());
//                    dto.setStudentName(a.getStudent().getStudName());
//                    dto.setStudentLastName(a.getStudent().getStudLastName());
//                    dto.setEmail(a.getStudent().getEmail());
//                    dto.setStudRollNo(a.getStudent().getStudRollNo());
//                    dto.setStatus(a.getStatus());
//
//                    return dto;
//                })
//                .toList();
//    }
//}


//
//package com.example.stud_erp.service.Implementation;
//
//import com.example.stud_erp.entity.Student;
//import com.example.stud_erp.entity.StuAttendance;
//import com.example.stud_erp.payload.StuAttendanceDTO;
//import com.example.stud_erp.repository.StudentRepository;
//import com.example.stud_erp.repository.StuAttendanceRepository;
//import com.example.stud_erp.service.StuAttendanceService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.util.*;
//
//@Service
//@Transactional
//public class StuAttendanceServiceImpl implements StuAttendanceService {
//
//    @Autowired
//    private StuAttendanceRepository repo;
//
//    @Autowired
//    private StudentRepository studentRepo;
//
//    @Override
//    public String save(String className, LocalDate attendanceDate, List<StuAttendanceDTO> list) {
//        try {
//            if (list == null || list.isEmpty()) {
//                throw new RuntimeException("Attendance list is empty!");
//            }
//
//            List<StuAttendance> records = new ArrayList<>();
//
//            for (StuAttendanceDTO dto : list) {
//                Student student = studentRepo.findById(dto.getStudentId())
//                        .orElseThrow(() -> new RuntimeException("Student not found ID: " + dto.getStudentId()));
//
//                // ✅ Exact matching with className and Date
//                Optional<StuAttendance> existingOpt = repo.findByStudent_IdAndClassNameAndAttendanceDate(
//                        student.getId(), className, attendanceDate);
//
//                StuAttendance attendance = existingOpt.orElse(new StuAttendance());
//
//                if (attendance.getId() == null) {
//                    attendance.setStudent(student);
//                    attendance.setClassName(className);
//                    attendance.setAttendanceDate(attendanceDate);
//                }
//
//                // ✅ SAFE ASSIGNMENT: trim() error se bachne ke liye toString() use kiya hai
//                String statusValue = "ABSENT";
//                if (dto.getStatus() != null) {
//                    String s = dto.getStatus().toString().trim();
//                    if (!s.isEmpty()) {
//                        statusValue = s;
//                    }
//                }
//
//                attendance.setStatus(statusValue);
//                records.add(attendance);
//            }
//
//            repo.saveAll(records);
//            return "✅ Attendance Processed Successfully";
//
//        } catch (Exception e) {
//            System.err.println("SAVE ERROR: " + e.getMessage());
//            throw new RuntimeException("Database Error: " + e.getMessage());
//        }
//    }
//
//    @Override
//    public List<StuAttendanceDTO> getByClassAndDate(String className, LocalDate attendanceDate) {
//        // ✅ Exact mapping for Prefill
//        return repo.findByClassNameAndAttendanceDate(className, attendanceDate)
//                .stream()
//                .map(a -> {
//                    StuAttendanceDTO dto = new StuAttendanceDTO();
//                    dto.setStudentId(a.getStudent().getId());
//                    dto.setStudentName(a.getStudent().getStudName());
//                    // Entity string return karegi
//                    dto.setStatus(a.getStatus().toString());
//                    return dto;
//                }).toList();
//    }
//}


//
//package com.example.stud_erp.service.Implementation;
//
//import com.example.stud_erp.entity.Student;
//import com.example.stud_erp.entity.StuAttendance;
//import com.example.stud_erp.payload.StuAttendanceDTO;
//import com.example.stud_erp.repository.StudentRepository;
//import com.example.stud_erp.repository.StuAttendanceRepository;
//import com.example.stud_erp.service.StuAttendanceService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@Transactional
//public class StuAttendanceServiceImpl implements StuAttendanceService {
//
//    @Autowired
//    private StuAttendanceRepository repo;
//
//    @Autowired
//    private StudentRepository studentRepo;
//
//    // =========================================
//    // SAVE ATTENDANCE
//    // =========================================
//    @Override
//    public String save(
//            String className,
//            LocalDate attendanceDate,
//            List<StuAttendanceDTO> list
//    ) {
//
//        try {
//
//            if (list == null || list.isEmpty()) {
//
//                throw new RuntimeException(
//                        "Attendance list is empty!"
//                );
//
//            }
//
//            for (StuAttendanceDTO dto : list) {
//
//                // =========================================
//                // FIND STUDENT
//                // =========================================
//                Student student =
//                        studentRepo.findById(
//                                        dto.getStudentId()
//                                )
//
//                                .orElseThrow(() ->
//                                        new RuntimeException(
//                                                "Student not found ID: "
//                                                        + dto.getStudentId()
//                                        )
//                                );
//
//                // =========================================
//                // CHECK EXISTING RECORD
//                // =========================================
//                Optional<StuAttendance> existingOpt =
//
//                        repo.findByStudent_IdAndClassNameAndAttendanceDate(
//
//                                student.getId(),
//
//                                className,
//
//                                attendanceDate
//
//                        );
//
//                StuAttendance attendance =
//                        existingOpt.orElse(
//                                new StuAttendance()
//                        );
//
//                // =========================================
//                // FIRST TIME INSERT
//                // =========================================
//                if (attendance.getId() == null) {
//
//                    attendance.setStudent(student);
//
//                    attendance.setClassName(
//                            className
//                    );
//
//                    attendance.setAttendanceDate(
//                            attendanceDate
//                    );
//
//                }
//
//                // =========================================
//                // STATUS
//                // =========================================
//                String statusValue = "ABSENT";
//
//                if (dto.getStatus() != null) {
//
//                    String s =
//                            dto.getStatus()
//                                    .trim();
//
//                    if (!s.isEmpty()) {
//
//                        statusValue = s;
//
//                    }
//
//                }
//
//                attendance.setStatus(
//                        statusValue
//                );
//
//                // =========================================
//                // SAVE STUDENT DETAILS
//                // =========================================
//                attendance.setStudentName(
//                        student.getStudName()
//                );
//
//                // =========================================
//                // SAVE TIME
//                // =========================================
//                attendance.setAttendanceTime(
//
//                        dto.getAttendanceTime() != null
//
//                                ? dto.getAttendanceTime()
//
//                                : LocalTime.now()
//
//                );
//
//                // =========================================
//                // SAVE PROFESSOR NAME
//                // =========================================
//                String profName =
//                        dto.getProfessorName();
//
//                System.out.println(
//                        "DTO PROFESSOR => "
//                                + profName
//                );
//
//                if (
//                        profName != null &&
//                                !profName.trim().isEmpty()
//                ) {
//
//                    attendance.setProfessorName(
//                            profName.trim()
//                    );
//
//                    System.out.println(
//                            "SETTING PROFESSOR => "
//                                    + profName.trim()
//                    );
//
//                } else {
//
//                    attendance.setProfessorName(
//                            "Unknown Professor"
//                    );
//
//                    System.out.println(
//                            "SETTING UNKNOWN PROFESSOR"
//                    );
//
//                }
//
//                // =========================================
//                // SAVE SUBJECT
//                // =========================================
//                if (
//                        dto.getSubjectName() != null &&
//                                !dto.getSubjectName()
//                                        .trim()
//                                        .isEmpty()
//                ) {
//
//                    attendance.setSubjectName(
//                            dto.getSubjectName()
//                                    .trim()
//                    );
//
//                } else {
//
//                    attendance.setSubjectName(
//                            "GENERAL"
//                    );
//
//                }
//
//                // =========================================
//                // FORCE SAVE
//                // =========================================
//                repo.save(attendance);
//
//            }
//
//            // =========================================
//            // FLUSH DB
//            // =========================================
//            repo.flush();
//
//            return "✅ Attendance Saved Successfully";
//
//        } catch (Exception e) {
//
//            System.err.println(
//                    "SAVE ERROR: "
//                            + e.getMessage()
//            );
//
//            throw new RuntimeException(
//                    "Database Error: "
//                            + e.getMessage()
//            );
//
//        }
//
//    }
//
//    // =========================================
//    // GET ATTENDANCE
//    // =========================================
//    @Override
//    public List<StuAttendanceDTO> getByClassAndDate(
//            String className,
//            LocalDate attendanceDate
//    ) {
//
//        return repo.findByClassNameAndAttendanceDate(
//
//                        className,
//
//                        attendanceDate
//
//                )
//
//                .stream()
//
//                .map(a -> {
//
//                    StuAttendanceDTO dto =
//                            new StuAttendanceDTO();
//
//                    // =========================================
//                    // STUDENT DETAILS
//                    // =========================================
//                    dto.setStudentId(
//                            a.getStudent().getId()
//                    );
//
//                    dto.setStudentName(
//                            a.getStudentName()
//                    );
//
//                    dto.setEmail(
//                            a.getStudent().getEmail()
//                    );
//
//                    dto.setStudRollNo(
//                            a.getStudent()
//                                    .getStudRollNo()
//                    );
//
//                    // =========================================
//                    // ATTENDANCE DETAILS
//                    // =========================================
//                    dto.setStatus(
//                            a.getStatus()
//                    );
//
//                    dto.setClassName(
//                            a.getClassName()
//                    );
//
//                    dto.setAttendanceDate(
//                            a.getAttendanceDate()
//                    );
//
//                    dto.setAttendanceTime(
//                            a.getAttendanceTime()
//                    );
//
//                    // =========================================
//                    // PROFESSOR DETAILS
//                    // =========================================
//                    dto.setProfessorName(
//                            a.getProfessorName()
//                    );
//
//                    // =========================================
//                    // SUBJECT
//                    // =========================================
//                    dto.setSubjectName(
//                            a.getSubjectName()
//                    );
//
//                    return dto;
//
//                }).toList();
//    }
//}



//for view



package com.example.stud_erp.service.Implementation;

import com.example.stud_erp.entity.Student;
import com.example.stud_erp.entity.StuAttendance;
import com.example.stud_erp.payload.StuAttendanceDTO;
import com.example.stud_erp.repository.StudentRepository;
import com.example.stud_erp.repository.StuAttendanceRepository;
import com.example.stud_erp.service.StuAttendanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
<<<<<<< HEAD
import java.util.Optional;
=======
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
>>>>>>> 5bf6a9a (work done)

@Service
@Transactional
public class StuAttendanceServiceImpl
        implements StuAttendanceService {

    @Autowired
    private StuAttendanceRepository repo;

    @Autowired
    private StudentRepository studentRepo;

<<<<<<< HEAD
    // ================= SAVE =================
    @Override
    public String save(
            Long schoolId,
            Long classId,
            LocalDate date,
            Long takenById,
            String takenByName,
            String takenByRole,
            List<StuAttendanceDTO> list
    ) {

        // 🔥 ROLE VALIDATION
        if (
                !takenByRole.equalsIgnoreCase("HOD")
                        &&
                        !takenByRole.equalsIgnoreCase("Teacher")
                        &&
                        !takenByRole.equalsIgnoreCase("SchoolAdmin")
        ) {

            throw new RuntimeException(
                    "Invalid role. Allowed: HOD, Teacher, SchoolAdmin"
            );
        }

        for (StuAttendanceDTO dto : list) {

            Student student =
                    studentRepo.findById(dto.getStudentId())
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Student not found"
                                    )
                            );

            // ================= SAME SCHOOL =================
            if (
                    !student.getSchoolId()
                            .equals(schoolId)
            ) {

                throw new RuntimeException(
                        "Student belongs to another school"
                );
            }

            // ================= SAME CLASS =================
            if (
                    !student.getClassNumber()
                            .equals(classId)
            ) {

                throw new RuntimeException(
                        "Student belongs to another class"
                );
            }

            // ================= CHECK EXISTING =================
            Optional<StuAttendance> existingOpt =
                    repo.findByStudent_IdAndDateAndClassId(
                            student.getId(),
                            date,
                            classId
                    );

            // ================= UPDATE =================
            if (existingOpt.isPresent()) {

                StuAttendance existing =
                        existingOpt.get();

                existing.setStatus(dto.getStatus());

                existing.setTakenById(takenById);

                existing.setTakenByName(takenByName);

                existing.setTakenByRole(takenByRole);

                repo.save(existing);

            }

            // ================= INSERT =================
            else {

                StuAttendance attendance =
                        new StuAttendance();

                attendance.setSchoolId(
                        student.getSchoolId()
                );

                attendance.setSchoolName(
                        student.getSchoolName()
                );

                attendance.setClassId(
                        student.getClassNumber()
                );

                attendance.setClassName(
                        student.getClassName()
                );

                attendance.setDate(date);

                attendance.setStudent(student);

                attendance.setStatus(dto.getStatus());

                // ================= TAKEN BY =================
                attendance.setTakenById(
                        takenById
                );

                attendance.setTakenByName(
                        takenByName
                );

                attendance.setTakenByRole(
                        takenByRole
                );

                repo.save(attendance);
            }
        }

        return "Attendance Saved Successfully";
    }

    // ================= GET =================
    @Override
    public List<StuAttendanceDTO> getByClassAndDate(
            Long schoolId,
            Long classId, LocalDate date
    ) {

        return repo
                .findBySchoolIdAndClassIdAndDate(
                        schoolId,
                        classId,
                        date
                )
                .stream()
                .map(a -> {

                    StuAttendanceDTO dto =
                            new StuAttendanceDTO();

                    dto.setStudentId(
                            a.getStudent().getId()
                    );

                    dto.setStudentName(
                            a.getStudent().getStudName()
                    );

                    dto.setStudentLastName(
                            a.getStudent().getStudLastName()
                    );

                    dto.setEmail(
                            a.getStudent().getEmail()
                    );

                    dto.setStudRollNo(
                            a.getStudent().getStudRollNo()
                    );

                    dto.setStatus(
                            a.getStatus()
                    );

                    // ================= SCHOOL =================
                    dto.setSchoolId(
                            a.getSchoolId()
                    );

                    dto.setSchoolName(
                            a.getSchoolName()
                    );

                    // ================= CLASS =================
                    dto.setClassId(
                            a.getClassId()
                    );

                    dto.setClassName(
                            a.getClassName()
                    );

                    // ================= TAKEN BY =================
                    dto.setTakenById(
                            a.getTakenById()
                    );

                    dto.setTakenByName(
                            a.getTakenByName()
                    );

                    dto.setTakenByRole(
                            a.getTakenByRole()
                    );

                    return dto;

                }).toList();
=======
    // =========================================
    // SAVE ATTENDANCE
    // =========================================
    @Override
    public String save(
            String className,
            LocalDate attendanceDate,
            List<StuAttendanceDTO> list
    ) {

        try {

            if (list == null || list.isEmpty()) {

                throw new RuntimeException(
                        "Attendance list is empty!"
                );

            }

            for (StuAttendanceDTO dto : list) {

                // =========================================
                // FIND STUDENT
                // =========================================
                Student student =
                        studentRepo.findById(
                                        dto.getStudentId()
                                )

                                .orElseThrow(() ->
                                        new RuntimeException(
                                                "Student not found ID: "
                                                        + dto.getStudentId()
                                        )
                                );

                // =========================================
                // CHECK EXISTING RECORD
                // =========================================
                Optional<StuAttendance> existingOpt =

                        repo.findByStudent_IdAndClassNameAndAttendanceDate(

                                student.getId(),

                                className,

                                attendanceDate

                        );

                StuAttendance attendance =
                        existingOpt.orElse(
                                new StuAttendance()
                        );

                // =========================================
                // FIRST TIME INSERT
                // =========================================
                if (attendance.getId() == null) {

                    attendance.setStudent(student);

                    attendance.setClassName(
                            className
                    );

                    attendance.setAttendanceDate(
                            attendanceDate
                    );

                }

                // =========================================
                // STATUS
                // =========================================
                String statusValue = "ABSENT";

                if (dto.getStatus() != null) {

                    String s =
                            dto.getStatus()
                                    .trim();

                    if (!s.isEmpty()) {

                        statusValue = s;

                    }

                }

                attendance.setStatus(
                        statusValue
                );

                // =========================================
                // SAVE STUDENT DETAILS
                // =========================================
                attendance.setStudentName(
                        student.getStudName()
                );

                // =========================================
                // SAVE TIME
                // =========================================
                attendance.setAttendanceTime(

                        dto.getAttendanceTime() != null

                                ? dto.getAttendanceTime()

                                : LocalTime.now()

                );

                // =========================================
                // SAVE PROFESSOR NAME
                // =========================================
                String profName =
                        dto.getProfessorName();

                System.out.println(
                        "DTO PROFESSOR => "
                                + profName
                );

                if (
                        profName != null &&
                                !profName.trim().isEmpty()
                ) {

                    attendance.setProfessorName(
                            profName.trim()
                    );

                    System.out.println(
                            "SETTING PROFESSOR => "
                                    + profName.trim()
                    );

                } else {

                    attendance.setProfessorName(
                            "Unknown Professor"
                    );

                    System.out.println(
                            "SETTING UNKNOWN PROFESSOR"
                    );

                }

                // =========================================
                // SAVE SUBJECT
                // =========================================
                if (
                        dto.getSubjectName() != null &&
                                !dto.getSubjectName()
                                        .trim()
                                        .isEmpty()
                ) {

                    attendance.setSubjectName(
                            dto.getSubjectName()
                                    .trim()
                    );

                } else {

                    attendance.setSubjectName(
                            "GENERAL"
                    );

                }

                // =========================================
                // FORCE SAVE
                // =========================================
                repo.save(attendance);

            }

            // =========================================
            // FLUSH DB
            // =========================================
            repo.flush();

            return "✅ Attendance Saved Successfully";

        } catch (Exception e) {

            System.err.println(
                    "SAVE ERROR: "
                            + e.getMessage()
            );

            throw new RuntimeException(
                    "Database Error: "
                            + e.getMessage()
            );

        }

    }

    // =========================================
    // CONVERT ENTITY TO DTO
    // =========================================
    private StuAttendanceDTO convertToDTO(
            StuAttendance a
    ) {

        StuAttendanceDTO dto =
                new StuAttendanceDTO();

        dto.setStudentId(
                a.getStudent().getId()
        );

        dto.setStudentName(
                a.getStudentName()
        );

        dto.setEmail(
                a.getStudent().getEmail()
        );

        dto.setStudRollNo(
                a.getStudent()
                        .getStudRollNo()
        );

        dto.setStatus(
                a.getStatus()
        );

        dto.setClassName(
                a.getClassName()
        );

        dto.setAttendanceDate(
                a.getAttendanceDate()
        );

        dto.setAttendanceTime(
                a.getAttendanceTime()
        );

        dto.setProfessorName(
                a.getProfessorName()
        );

        dto.setSubjectName(
                a.getSubjectName()
        );

        return dto;
    }

    // =========================================
    // GET ATTENDANCE BY CLASS & DATE
    // =========================================
    @Override
    public List<StuAttendanceDTO> getByClassAndDate(
            String className,
            LocalDate attendanceDate
    ) {

        return repo.findByClassNameAndAttendanceDate(

                        className,

                        attendanceDate

                )

                .stream()

                .map(this::convertToDTO)

                .toList();
    }

    // =========================================
    // GET ALL ATTENDANCE
    // =========================================
    @Override
    public List<StuAttendanceDTO> getAllAttendance() {

        return repo.findAll()

                .stream()

                .sorted((a, b) ->
                        b.getAttendanceDate()
                                .compareTo(
                                        a.getAttendanceDate()
                                )
                )

                .map(this::convertToDTO)

                .toList();
    }

    // =========================================
    // GET BY CLASS
    // =========================================
    @Override
    public List<StuAttendanceDTO> getByClass(
            String className
    ) {

        return repo.findAll()

                .stream()

                .filter(a ->

                        a.getClassName() != null &&

                                a.getClassName()
                                        .equalsIgnoreCase(
                                                className
                                        )
                )

                .sorted((a, b) ->
                        b.getAttendanceDate()
                                .compareTo(
                                        a.getAttendanceDate()
                                )
                )

                .map(this::convertToDTO)

                .toList();
    }

    // =========================================
    // GET MONTHLY ATTENDANCE
    // =========================================
    @Override
    public List<StuAttendanceDTO> getMonthlyAttendance(
            String className,
            int month,
            int year
    ) {

        return repo.findAll()

                .stream()

                .filter(a ->

                        a.getClassName() != null &&

                                a.getClassName()
                                        .equalsIgnoreCase(
                                                className
                                        ) &&

                                a.getAttendanceDate()
                                        .getMonthValue() == month &&

                                a.getAttendanceDate()
                                        .getYear() == year
                )

                .sorted((a, b) ->
                        b.getAttendanceDate()
                                .compareTo(
                                        a.getAttendanceDate()
                                )
                )

                .map(this::convertToDTO)

                .toList();
    }

    // =========================================
    // GET YEARLY ATTENDANCE
    // =========================================
    @Override
    public List<StuAttendanceDTO> getYearlyAttendance(
            String className,
            int year
    ) {

        return repo.findAll()

                .stream()

                .filter(a ->

                        a.getClassName() != null &&

                                a.getClassName()
                                        .equalsIgnoreCase(
                                                className
                                        ) &&

                                a.getAttendanceDate()
                                        .getYear() == year
                )

                .sorted((a, b) ->
                        b.getAttendanceDate()
                                .compareTo(
                                        a.getAttendanceDate()
                                )
                )

                .map(this::convertToDTO)

                .toList();
    }

    // =========================================
    // MONTHLY SUMMARY
    // =========================================
    @Override
    public Map<String, Long> getMonthlySummary(
            int year
    ) {

        List<StuAttendance> attendanceList =
                repo.findAll();

        return attendanceList.stream()

                .filter(a ->
                        a.getAttendanceDate()
                                .getYear() == year
                )

                .collect(
                        Collectors.groupingBy(

                                a -> a.getAttendanceDate()
                                        .getMonth()
                                        .toString(),

                                Collectors.counting()
                        )
                );
    }

    // =========================================
    // YEARLY SUMMARY
    // =========================================
    @Override
    public Map<Integer, Long> getYearlySummary() {

        List<StuAttendance> attendanceList =
                repo.findAll();

        return attendanceList.stream()

                .collect(
                        Collectors.groupingBy(

                                a -> a.getAttendanceDate()
                                        .getYear(),

                                Collectors.counting()
                        )
                );
    }

    // =========================================
    // STUDENT ATTENDANCE PERCENTAGE
    // =========================================
    @Override
    public double getStudentAttendancePercentage(
            Long studentId
    ) {

        List<StuAttendance> attendanceList =
                repo.findByStudent_Id(studentId);

        if (attendanceList.isEmpty()) {

            return 0.0;

        }

        long presentCount = attendanceList

                .stream()

                .filter(a ->

                        a.getStatus() != null &&

                                a.getStatus()
                                        .equalsIgnoreCase(
                                                "PRESENT"
                                        )
                )

                .count();

        return (
                (double) presentCount
                        / attendanceList.size()
        ) * 100.0;
    }


    // =========================================
// CHECK ATTENDANCE ALREADY EXISTS
// =========================================
    @Override
    public boolean attendanceAlreadyExists(

            String className,

            LocalDate attendanceDate

    ) {

        return repo.existsByClassNameAndAttendanceDate(

                className,

                attendanceDate

        );
>>>>>>> 5bf6a9a (work done)
    }

    // ================= DAILY =================
    @Override
    public List<StuAttendance> getByDate(
            Long schoolId,
            LocalDate date
    ) {

        return repo.findBySchoolIdAndDate(
                schoolId,
                date
        );
    }
}