package com.example.stud_erp.controller;

import com.example.stud_erp.payload.GalleryDto;
import com.example.stud_erp.service.GalleryService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gallery")
@CrossOrigin("*")
public class GalleryController {

    private final GalleryService galleryService;

    public GalleryController(
            GalleryService galleryService
    ) {

        this.galleryService =
                galleryService;
    }

    // =====================================================
    // CREATE
    // =====================================================

    @PostMapping
    public GalleryDto createGallery(

            @RequestBody GalleryDto dto

    ) {

        return galleryService
                .createGallery(dto);
    }

    // =====================================================
    // UPDATE
    // =====================================================

    @PutMapping("/{id}")
    public GalleryDto updateGallery(

            @PathVariable Long id,

            @RequestBody GalleryDto dto

    ) {

        return galleryService
                .updateGallery(
                        id,
                        dto
                );
    }

    // =====================================================
    // DELETE
    // =====================================================

    @DeleteMapping("/{id}")
    public String deleteGallery(

            @PathVariable Long id

    ) {

        galleryService.deleteGallery(id);

        return "Gallery deleted successfully";
    }

    // =====================================================
    // GET BY ID
    // =====================================================

    @GetMapping("/{id}")
    public GalleryDto getGalleryById(

            @PathVariable Long id

    ) {

        return galleryService
                .getGalleryById(id);
    }

    // =====================================================
    // GET ALL
    // =====================================================

    @GetMapping
    public List<GalleryDto> getAllGallery() {

        return galleryService
                .getAllGallery();
    }

    // =====================================================
    // GET BY SCHOOL
    // =====================================================

    @GetMapping("/school/{schoolId}")
    public List<GalleryDto>
    getGalleryBySchool(

            @PathVariable Long schoolId

    ) {

        return galleryService
                .getGalleryBySchool(
                        schoolId
                );
    }
}