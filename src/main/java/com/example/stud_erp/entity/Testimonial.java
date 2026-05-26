package com.example.stud_erp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "testimonials")
public class Testimonial {

    // =====================================================
    // ID
    // =====================================================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // =====================================================
    // STUDENT / PARENT NAME
    // =====================================================

    private String name;

    // =====================================================
    // ROLE
    // Parent / Student / Alumni
    // =====================================================

    private String role;

    // =====================================================
    // MESSAGE
    // =====================================================

    @Column(length = 5000)
    private String message;

    // =====================================================
    // PROFILE IMAGE
    // =====================================================

    private String image;

    // =====================================================
    // RATING
    // =====================================================

    private Integer rating;

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
            "testimonials",
            "hibernateLazyInitializer",
            "handler"
    })

    private School school;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public Testimonial() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
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