package com.example.stud_erp.service;

import com.example.stud_erp.payload.GalleryDto;

import java.util.List;

public interface GalleryService {

    // =====================================================
    // CREATE
    // =====================================================

    GalleryDto createGallery(
            GalleryDto dto
    );

    // =====================================================
    // UPDATE
    // =====================================================

    GalleryDto updateGallery(
            Long id,
            GalleryDto dto
    );

    // =====================================================
    // DELETE
    // =====================================================

    void deleteGallery(
            Long id
    );

    // =====================================================
    // GET BY ID
    // =====================================================

    GalleryDto getGalleryById(
            Long id
    );

    // =====================================================
    // GET ALL
    // =====================================================

    List<GalleryDto> getAllGallery();

    // =====================================================
    // GET BY SCHOOL
    // =====================================================

    List<GalleryDto> getGalleryBySchool(
            Long schoolId
    );
}