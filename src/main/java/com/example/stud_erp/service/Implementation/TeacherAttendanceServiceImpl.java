package com.example.stud_erp.service.Implementation;

import com.example.stud_erp.entity.SchoolAdmin;
import com.example.stud_erp.entity.TeacherAttendance;
import com.example.stud_erp.payload.TeacherAttendanceDTO;
import com.example.stud_erp.repository.SchoolAdminRepository;
import com.example.stud_erp.repository.TeacherAttendanceRepository;
import com.example.stud_erp.service.TeacherAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherAttendanceServiceImpl implements TeacherAttendanceService {

    @Autowired
    private TeacherAttendanceRepository repository;

    @Autowired
    private SchoolAdminRepository adminRepository;

    // ================= SAVE =================
    @Override
    public String saveOrUpdate(Long schoolId, List<TeacherAttendance> list) {

        for (TeacherAttendance ta : list) {

            ta.setSchoolId(schoolId);

            // ================= DEFAULT AUDIT =================
            if (ta.getCreatedBy() == null) {
                throw new RuntimeException("createdBy is required");
            }

            if (ta.getCreatedByRole() == null ||
                    ta.getCreatedByRole().isEmpty()) {

                throw new RuntimeException("createdByRole is required");
            }

            // ================= CREATED BY NAME =================
            if (ta.getCreatedByName() == null ||
                    ta.getCreatedByName().isEmpty()) {

                SchoolAdmin admin = adminRepository
                        .findById(ta.getCreatedBy())
                        .orElse(null);

                if (admin != null) {
                    ta.setCreatedByName(admin.getName());
                } else {
                    ta.setCreatedByName("Unknown");
                }
            }

            TeacherAttendance existing =
                    repository.findByTeacherIdAndDate(
                            ta.getTeacherId(),
                            ta.getDate()
                    ).orElse(null);

            // ================= UPDATE =================
            if (existing != null) {

                existing.setStatus(ta.getStatus());

                existing.setSchoolId(schoolId);

                existing.setCreatedBy(ta.getCreatedBy());
                existing.setCreatedByRole(ta.getCreatedByRole());
                existing.setCreatedByName(ta.getCreatedByName());

                repository.save(existing);

            } else {

                // ================= INSERT =================
                ta.setSchoolId(schoolId);

                repository.save(ta);
            }
        }

        return "Saved Successfully";
    }

    // ================= GET BY DATE =================
    @Override
    public List<TeacherAttendanceDTO> getByDate(
            Long schoolId,
            LocalDate date
    ) {

        List<TeacherAttendance> list =
                repository.findBySchoolIdAndDate(
                        schoolId,
                        date
                );

        List<TeacherAttendanceDTO> result =
                new ArrayList<>();

        for (TeacherAttendance t : list) {
            result.add(toDTO(t));
        }

        return result;
    }

    // ================= WEEKLY =================
    @Override
    public List<TeacherAttendanceDTO> getWeekly(
            Long schoolId
    ) {

        List<TeacherAttendanceDTO> result =
                new ArrayList<>();

        for (int i = 6; i >= 0; i--) {

            LocalDate date =
                    LocalDate.now().minusDays(i);

            List<TeacherAttendance> list =
                    repository.findBySchoolIdAndDate(
                            schoolId,
                            date
                    );

            for (TeacherAttendance t : list) {
                result.add(toDTO(t));
            }
        }

        return result;
    }

    // ================= DTO =================
    private TeacherAttendanceDTO toDTO(
            TeacherAttendance t
    ) {

        TeacherAttendanceDTO dto =
                new TeacherAttendanceDTO();

        dto.setId(t.getId());

        dto.setSchoolId(t.getSchoolId());

        dto.setTeacherId(t.getTeacherId());

        dto.setStatus(t.getStatus());

        dto.setDate(t.getDate());

        // ================= AUDIT =================
        dto.setCreatedBy(t.getCreatedBy());

        dto.setCreatedByRole(
                t.getCreatedByRole()
        );

        dto.setCreatedByName(
                t.getCreatedByName()
        );

        // ================= FUTURE JOIN =================
        dto.setTeacherName("N/A");

        dto.setEmail("N/A");

        return dto;
    }
}