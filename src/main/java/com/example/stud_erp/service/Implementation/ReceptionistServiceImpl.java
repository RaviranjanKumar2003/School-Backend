package com.example.stud_erp.service.Implementation;

import com.example.stud_erp.entity.Receptionist;
import com.example.stud_erp.entity.School;
import com.example.stud_erp.payload.LoginRequest;
import com.example.stud_erp.payload.ReceptionistDto;
import com.example.stud_erp.repository.ActivityLogRepository;
import com.example.stud_erp.repository.ReceptionistRepository;
import com.example.stud_erp.repository.SchoolRepository;
import com.example.stud_erp.service.ImageService;
import com.example.stud_erp.service.ReceptionistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

import com.example.stud_erp.entity.ActivityLog;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class ReceptionistServiceImpl implements ReceptionistService {

    private final ReceptionistRepository receptionistRepository;
    private final SchoolRepository schoolRepository;
    private final ImageService imageService;

    @Autowired
    private ActivityLogRepository activityLogRepository;

    public ReceptionistServiceImpl(
            ReceptionistRepository receptionistRepository,
            SchoolRepository schoolRepository,
            ImageService imageService
    ) {
        this.receptionistRepository = receptionistRepository;
        this.schoolRepository = schoolRepository;
        this.imageService = imageService;
    }

    // ================= CREATE =================
    @Override
    public Receptionist createReceptionist(
            ReceptionistDto dto,
            MultipartFile file
    ) throws IOException {

        School school = schoolRepository.findById(dto.getSchoolId())
                .orElseThrow(() -> new RuntimeException("School not found"));

        Receptionist receptionist = new Receptionist();

        // ================= BASIC INFO =================

        receptionist.setName(dto.getName());

        receptionist.setEmail(dto.getEmail());

        receptionist.setPhone(dto.getPhone());

        receptionist.setSchool(school);

        // ================= AUTO USERNAME =================

        String cleanName = dto.getName()
                .trim()
                .replaceAll("\\s+", "")
                .toLowerCase();

        String username =
                cleanName + "_rec" + (100 + new Random().nextInt(900));

        receptionist.setUsername(username);

        // ================= AUTO PASSWORD =================

        String password =
                "REC@" + (1000 + new Random().nextInt(9000));

        receptionist.setPassword(password);

        // ================= IMAGE =================

        if (file != null && !file.isEmpty()) {

            String imageUrl = imageService.uploadImage(file);

            receptionist.setImageUrl(imageUrl);
        }

        receptionist.setCreatedAt(LocalDateTime.now());

        receptionist.setUpdatedAt(LocalDateTime.now());

        // ================= SAVE =================

        Receptionist saved =
                receptionistRepository.save(receptionist);

        // =====================================================
        // ACTIVITY LOG
        // =====================================================

        try {

            ActivityLog log = new ActivityLog();

            log.setSchoolId(
                    school.getId()
            );

            log.setTitle("New Receptionist Added");

            log.setDescription(
                    saved.getName()
                            + " joined as Receptionist"
            );

            log.setType("STAFF");

            log.setCreatedAt(LocalDateTime.now());

            activityLogRepository.save(log);

        } catch (Exception e) {

            System.out.println(
                    "Activity log error : "
                            + e.getMessage()
            );
        }

        // OPTIONAL LOG (for admin)

        System.out.println("=========== RECEPTIONIST LOGIN DETAILS ===========");

        System.out.println("USERNAME : " + saved.getUsername());

        System.out.println("PASSWORD : " + saved.getPassword());

        System.out.println("===================================================");

        return saved;
    }

    // ================= UPDATE =================
    @Override
    public Receptionist updateReceptionist(
            Long id,
            ReceptionistDto dto,
            MultipartFile file
    ) throws IOException {

        Receptionist receptionist = receptionistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receptionist not found"));

        School school = schoolRepository.findById(dto.getSchoolId())
                .orElseThrow(() -> new RuntimeException("School not found"));

        receptionist.setName(dto.getName());
        receptionist.setEmail(dto.getEmail());
        receptionist.setPhone(dto.getPhone());
        receptionist.setSchool(school);

        // NOTE: username/password NOT updated automatically (security best practice)
        // If needed later, add separate reset API

        // ================= IMAGE UPDATE =================
        if (file != null && !file.isEmpty()) {

            if (receptionist.getImageUrl() != null) {
                imageService.deleteImage(receptionist.getImageUrl());
            }

            String imageUrl = imageService.uploadImage(file);
            receptionist.setImageUrl(imageUrl);
        }

        receptionist.setUpdatedAt(LocalDateTime.now());

        return receptionistRepository.save(receptionist);
    }

    // ================= GET ALL =================
    @Override
    public List<Receptionist> getAllReceptionists() {
        return receptionistRepository.findAll();
    }

    // ================= GET BY ID =================
    @Override
    public Receptionist getReceptionistById(Long id) {
        return receptionistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receptionist not found"));
    }

    // ================= DELETE =================
    @Override
    public void deleteReceptionist(Long id) {

        Receptionist receptionist = getReceptionistById(id);

        if (receptionist.getImageUrl() != null) {
            imageService.deleteImage(receptionist.getImageUrl());
        }

        receptionistRepository.delete(receptionist);
    }

    // ================= LOGIN =================
    @Override
    public Receptionist authenticateUser(LoginRequest loginRequest) {

        Receptionist receptionist =
                receptionistRepository.findByUsername(loginRequest.getUsername());

        if (receptionist == null ||
                !loginRequest.getPassword().equals(receptionist.getPassword())) {

            throw new RuntimeException("Invalid username or password");
        }

        return receptionist;
    }

    // ================= GET BY SCHOOL =================
    @Override
    public List<Receptionist> getReceptionistsBySchool(Long schoolId) {
        return receptionistRepository.findBySchoolId(schoolId);
    }


    public Receptionist uploadImage(
            Long id,
            MultipartFile image
    ) throws IOException {

        Receptionist receptionist =
                receptionistRepository
                        .findById(id)
                        .orElseThrow();

        if (image != null && !image.isEmpty()) {

            String fileName =
                    UUID.randomUUID() +
                            "_" +
                            image.getOriginalFilename();

            Path path =
                    Paths.get("images");

            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            Files.copy(
                    image.getInputStream(),
                    path.resolve(fileName),
                    StandardCopyOption.REPLACE_EXISTING
            );

            receptionist.setImageUrl(fileName);
        }

        return receptionistRepository.save(
                receptionist
        );
    }
}