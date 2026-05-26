package com.example.stud_erp.service.Implementation;

import com.example.stud_erp.entity.Gallery;
import com.example.stud_erp.entity.School;
import com.example.stud_erp.payload.GalleryDto;
import com.example.stud_erp.repository.GalleryRepository;
import com.example.stud_erp.repository.SchoolRepository;
import com.example.stud_erp.service.GalleryService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GalleryServiceImpl
        implements GalleryService {

    private final GalleryRepository galleryRepository;

    private final SchoolRepository schoolRepository;

    public GalleryServiceImpl(
            GalleryRepository galleryRepository,
            SchoolRepository schoolRepository
    ) {

        this.galleryRepository =
                galleryRepository;

        this.schoolRepository =
                schoolRepository;
    }

    // =====================================================
    // CREATE
    // =====================================================

    @Override
    public GalleryDto createGallery(
            GalleryDto dto
    ) {

        School school =
                schoolRepository.findById(
                        dto.getSchoolId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "School not found"
                        )
                );

        Gallery gallery =
                new Gallery();

        gallery.setTitle(
                dto.getTitle()
        );

        gallery.setDescription(
                dto.getDescription()
        );

        gallery.setType(
                dto.getType()
        );

        gallery.setFileName(
                dto.getFileName()
        );

        gallery.setVideoUrl(
                dto.getVideoUrl()
        );

        gallery.setThumbnail(
                dto.getThumbnail()
        );

        gallery.setActive(
                dto.getActive() != null
                        ? dto.getActive()
                        : true
        );

        gallery.setSchool(
                school
        );

        Gallery saved =
                galleryRepository.save(
                        gallery
                );

        return mapToDto(saved);
    }

    // =====================================================
    // UPDATE
    // =====================================================

    @Override
    public GalleryDto updateGallery(
            Long id,
            GalleryDto dto
    ) {

        Gallery gallery =
                galleryRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Gallery not found"
                                )
                        );

        gallery.setTitle(
                dto.getTitle()
        );

        gallery.setDescription(
                dto.getDescription()
        );

        gallery.setType(
                dto.getType()
        );

        gallery.setFileName(
                dto.getFileName()
        );

        gallery.setVideoUrl(
                dto.getVideoUrl()
        );

        gallery.setThumbnail(
                dto.getThumbnail()
        );

        gallery.setActive(
                dto.getActive()
        );

        Gallery updated =
                galleryRepository.save(
                        gallery
                );

        return mapToDto(updated);
    }

    // =====================================================
    // DELETE
    // =====================================================

    @Override
    public void deleteGallery(
            Long id
    ) {

        galleryRepository.deleteById(id);
    }

    // =====================================================
    // GET BY ID
    // =====================================================

    @Override
    public GalleryDto getGalleryById(
            Long id
    ) {

        Gallery gallery =
                galleryRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Gallery not found"
                                )
                        );

        return mapToDto(gallery);
    }

    // =====================================================
    // GET ALL
    // =====================================================

    @Override
    public List<GalleryDto>
    getAllGallery() {

        return galleryRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // =====================================================
    // GET BY SCHOOL
    // =====================================================

    @Override
    public List<GalleryDto>
    getGalleryBySchool(
            Long schoolId
    ) {

        return galleryRepository
                .findBySchoolIdAndActiveTrue(
                        schoolId
                )
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // =====================================================
    // DTO MAPPER
    // =====================================================

    private GalleryDto mapToDto(
            Gallery gallery
    ) {

        GalleryDto dto =
                new GalleryDto();

        dto.setId(
                gallery.getId()
        );

        dto.setTitle(
                gallery.getTitle()
        );

        dto.setDescription(
                gallery.getDescription()
        );

        dto.setType(
                gallery.getType()
        );

        dto.setFileName(
                gallery.getFileName()
        );

        dto.setVideoUrl(
                gallery.getVideoUrl()
        );

        dto.setThumbnail(
                gallery.getThumbnail()
        );

        dto.setActive(
                gallery.getActive()
        );

        if (gallery.getSchool() != null) {

            dto.setSchoolId(
                    gallery.getSchool().getId()
            );

            dto.setSchoolName(
                    gallery.getSchool().getSchoolName()
            );
        }

        return dto;
    }
}