package com.example.stud_erp.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    private LocalDate date;

    private String createdBy;

    // 🔥 EVENT STATUS (PRIVATE → PUBLIC)
    @Column(nullable = false)
    private boolean isPublished = false;

    // 🔥 TARGET AUDIENCE
    @Column(nullable = false)
    private String target = "ALL";
    // values: ALL / TEACHER / STUDENT

    // 🔥 TRACKING (VERY IMPORTANT FOR REAL PROJECT)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 🔥 Constructor
    public Event() {}

    // 🔥 Auto set timestamps
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // 🔥 Getters & Setters

    public Long getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }

    public boolean isPublished() { return isPublished; }
    public void setPublished(boolean published) { isPublished = published; }

    public String getTarget() { return target; }
    public void setTarget(String target) { this.target = target; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}