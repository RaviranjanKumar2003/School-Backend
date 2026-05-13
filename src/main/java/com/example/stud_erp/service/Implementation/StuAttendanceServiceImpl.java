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
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StuAttendanceServiceImpl
        implements StuAttendanceService {

    @Autowired
    private StuAttendanceRepository repo;

    @Autowired
    private StudentRepository studentRepo;

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