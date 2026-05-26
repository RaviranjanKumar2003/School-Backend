package com.example.stud_erp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "gallery")
public class Gallery {

    // =====================================================
    // ID
    // =====================================================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // =====================================================
    // TITLE
    // =====================================================

    private String title;

    // =====================================================
    // DESCRIPTION
    // =====================================================

    @Column(length = 5000)
    private String description;

    // =====================================================
    // TYPE
    // IMAGE / VIDEO
    // =====================================================

    private String type;

    // =====================================================
    // IMAGE FILE
    // =====================================================

    private String fileName;

    // =====================================================
    // VIDEO FILE
    // =====================================================

    private String videoUrl;

    // =====================================================
    // THUMBNAIL
    // =====================================================

    private String thumbnail;

    // =====================================================
    // ACTIVE
    // =====================================================

    private Boolean active = true;

    // =====================================================
    // SCHOOL
    // =====================================================

    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "school_id")

    @JsonIgnoreProperties({
            "gallery",
            "hibernateLazyInitializer",
            "handler"
    })

    private School school;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public Gallery() {
    }

    // =====================================================
    // GETTERS & SETTERS
    // =====================================================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}