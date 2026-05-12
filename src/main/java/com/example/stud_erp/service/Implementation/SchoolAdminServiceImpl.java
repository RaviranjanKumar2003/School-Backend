package com.example.stud_erp.service.Implementation;

import com.example.stud_erp.entity.SchoolAdmin;
import com.example.stud_erp.payload.LoginResponse;
import com.example.stud_erp.repository.SchoolAdminRepository;
import com.example.stud_erp.service.SchoolAdminService;
import org.springframework.stereotype.Service;

@Service
public class SchoolAdminServiceImpl
        implements SchoolAdminService {

    private final SchoolAdminRepository repo;

    public SchoolAdminServiceImpl(
            SchoolAdminRepository repo
    ) {

        this.repo = repo;
    }

    // ================= CREATE =================
    @Override
    public SchoolAdmin create(
            SchoolAdmin admin
    ) {

        return repo.save(admin);
    }

    // ================= UPDATE =================
    @Override
    public SchoolAdmin update(
            Long id,
            SchoolAdmin admin
    ) {

        SchoolAdmin existing =
                repo.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Not found"
                                ));

        existing.setName(admin.getName());

        existing.setUsername(admin.getUsername());

        existing.setPassword(admin.getPassword());

        existing.setEmail(admin.getEmail());

        existing.setPhone(admin.getPhone());

        existing.setSchool(admin.getSchool());

        return repo.save(existing);
    }

    // ================= GET BY ID =================
    @Override
    public SchoolAdmin getById(Long id) {

        return repo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Not found"
                        ));
    }

    // ================= DELETE =================
    @Override
    public void delete(Long id) {

        repo.deleteById(id);
    }

    // ================= LOGIN =================
    @Override
    public LoginResponse login(
            String username,
            String password
    ) {

        SchoolAdmin admin =
                repo.findByUsername(username);

        // ================= VALIDATION =================
        if (admin == null ||
                !admin.getPassword().equals(password)) {

            throw new RuntimeException(
                    "Invalid credentials"
            );
        }

        // ================= RESPONSE =================
        LoginResponse res =
                new LoginResponse();

        res.setId(admin.getId());

        res.setName(admin.getName());

        res.setUsername(admin.getUsername());

        res.setEmail(admin.getEmail());

        res.setRole("schooladmin");

        // ================= IMPORTANT FIX =================
        if (admin.getSchool() != null) {

            // 🔥 SAVE SCHOOL ID
            res.setSchoolId(
                    admin.getSchool().getId()
            );

            // 🔥 SAVE SCHOOL NAME
            res.setSchoolName(
                    admin.getSchool().getSchoolName()
            );

            // 🔥 SAVE SCHOOL CODE
            res.setSchoolCode(
                    admin.getSchool().getSchoolCode()
            );
        }

        return res;
    }
}