// =============================
// ReceptionistController.java
// =============================

package com.example.stud_erp.controller;

import com.example.stud_erp.entity.Receptionist;
import com.example.stud_erp.payload.LoginRequest;
import com.example.stud_erp.payload.ReceptionistDto;
import com.example.stud_erp.service.ReceptionistService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/receptionists")

@CrossOrigin("*")
public class ReceptionistController {

    private final ReceptionistService receptionistService;

    public ReceptionistController(
            ReceptionistService receptionistService
    ) {

        this.receptionistService =
                receptionistService;
    }

    // ================= CREATE =================
    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public Receptionist createReceptionist(

            @RequestPart("data")
            ReceptionistDto dto,

            @RequestPart(value = "image",
                    required = false)
            MultipartFile image

    ) throws IOException {

        return receptionistService
                .createReceptionist(dto, image);
    }

    // ================= UPDATE =================
    @PutMapping(
            value = "/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public Receptionist updateReceptionist(

            @PathVariable Long id,

            @RequestPart("data")
            ReceptionistDto dto,

            @RequestPart(
                    value = "image",
                    required = false
            )
            MultipartFile image

    ) throws IOException {

        return receptionistService
                .updateReceptionist(
                        id,
                        dto,
                        image
                );
    }

    // ================= GET ALL =================
    @GetMapping
    public List<Receptionist> getAllReceptionists() {

        return receptionistService
                .getAllReceptionists();
    }

    // ================= GET BY ID =================
    @GetMapping("/{id}")
    public Receptionist getReceptionistById(
            @PathVariable Long id
    ) {

        return receptionistService
                .getReceptionistById(id);
    }

    // ================= DELETE =================
    @DeleteMapping("/{id}")
    public void deleteReceptionist(
            @PathVariable Long id
    ) {

        receptionistService
                .deleteReceptionist(id);
    }

    // ================= LOGIN =================
    @PostMapping("/login")
    public Receptionist login(

            @RequestBody
            LoginRequest request

    ) {

        return receptionistService
                .authenticateUser(request);
    }

    // ================= GET BY SCHOOL =================
    @GetMapping("/school/{schoolId}")
    public List<Receptionist> getBySchool(

            @PathVariable Long schoolId

    ) {

        return receptionistService
                .getReceptionistsBySchool(
                        schoolId
                );
    }


    // ================= GET IMAGE =================
    @GetMapping("/image/{fileName}")
    public ResponseEntity<Resource> getImage(
            @PathVariable String fileName
    ) {

        try {

            Path path = Paths.get(
                    "images",
                    fileName
            );

            Resource resource =
                    new UrlResource(path.toUri());

            if (!resource.exists()) {

                return ResponseEntity.notFound().build();
            }

            String contentType =
                    Files.probeContentType(path);

            if (contentType == null) {

                contentType =
                        "application/octet-stream";
            }

            return ResponseEntity.ok()

                    .header(
                            HttpHeaders.CONTENT_DISPOSITION,
                            "inline; fileName=\"" +
                                    resource.getFilename() + "\""
                    )

                    .contentType(
                            MediaType.parseMediaType(contentType)
                    )

                    .body(resource);

        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(
            value = "/image/upload/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public Receptionist uploadImage(

            @PathVariable Long id,

            @RequestParam("image")
            MultipartFile image

    ) throws IOException {

        return receptionistService
                .uploadImage(id, image);
    }
}