package com.example.stud_erp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "schools")
public class School {

    // ================= ID =================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ================= BASIC INFO =================
    private String schoolName;

    @Column(unique = true)
    private String schoolCode;

    private String address;

    private String email;

    private String phone;

    // ================= AUDIT =================
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // ================= SCHOOL ADMIN =================
    @OneToOne(
            mappedBy = "school",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )

    @JsonIgnoreProperties({
            "school",
            "hibernateLazyInitializer",
            "handler"
    })

    private SchoolAdmin schoolAdmin;
    // ================= HODS =================
    @OneToMany(
            mappedBy = "school",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )

    @JsonIgnoreProperties({
            "school",
            "hibernateLazyInitializer",
            "handler"
    })

    private List<HOD> hods = new ArrayList<>();

    // ================= LIFECYCLE =================
    @PrePersist
    public void onCreate() {

        createdAt = LocalDateTime.now();

        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {

        updatedAt = LocalDateTime.now();
    }

    // ================= COVER IMAGES =================
    @ElementCollection
    @CollectionTable(
            name = "school_cover_images",
            joinColumns = @JoinColumn(name = "school_id")
    )
    @Column(name = "image")
    private List<String> coverImages;


    @Column(unique = true)
    private String slug;

// ================= GETTERS & SETTERS =================


    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public List<String> getCoverImages() {
        return coverImages;
    }

    public void setCoverImages(List<String> coverImages) {
        this.coverImages = coverImages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public SchoolAdmin getSchoolAdmin() {
        return schoolAdmin;
    }

    public void setSchoolAdmin(SchoolAdmin schoolAdmin) {
        this.schoolAdmin = schoolAdmin;
    }

    public List<HOD> getHods() {
        return hods;
    }

    public void setHods(List<HOD> hods) {
        this.hods = hods;
    }
}


//package com.example.stud_erp.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import jakarta.persistence.*;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Table(name = "schools")
//public class School {
//
//    // =====================================================
//    // ID
//    // =====================================================
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    // =====================================================
//    // BASIC INFO
//    // =====================================================
//
//    private String schoolName;
//
//    @Column(unique = true)
//    private String schoolCode;
//
//    @Column(unique = true)
//    private String slug;
//
//    private String tagline;
//
//    private String logo;
//
//    // =====================================================
//    // SCHOOL DETAILS
//    // =====================================================
//
//    @Column(length = 5000)
//    private String about;
//
//    @Column(length = 5000)
//    private String mission;
//
//    @Column(length = 5000)
//    private String vision;
//
//    @Column(length = 5000)
//    private String principalMessage;
//
//    private String board;
//
//    private String schoolType;
//
//    private String medium;
//
//    private Integer establishedYear;
//
//    // =====================================================
//    // CONTACT INFO
//    // =====================================================
//
//    private String email;
//
//    private String phone;
//
//    private String website;
//
//    // =====================================================
//    // ADDRESS
//    // =====================================================
//
//    private String address;
//
//    private String city;
//
//    private String state;
//
//    private String country;
//
//    private String pincode;
//
//    // =====================================================
//    // SOCIAL LINKS
//    // =====================================================
//
//    private String facebookLink;
//
//    private String instagramLink;
//
//    private String youtubeLink;
//
//    // =====================================================
//    // STATUS
//    // =====================================================
//
//    private Boolean active = true;
//
//    // =====================================================
//    // AUDIT
//    // =====================================================
//
//    private LocalDateTime createdAt;
//
//    private LocalDateTime updatedAt;
//
//    // =====================================================
//    // COVER IMAGES
//    // =====================================================
//
//    @ElementCollection
//    @CollectionTable(
//            name = "school_cover_images",
//            joinColumns = @JoinColumn(name = "school_id")
//    )
//
//    @Column(name = "image")
//    private List<String> coverImages = new ArrayList<>();
//
//    // =====================================================
//    // SCHOOL ADMIN
//    // =====================================================
//
//    @OneToOne(
//            mappedBy = "school",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//
//    @JsonIgnoreProperties({
//            "school",
//            "hibernateLazyInitializer",
//            "handler"
//    })
//
//    private SchoolAdmin schoolAdmin;
//
//    // =====================================================
//    // HODS
//    // =====================================================
//
//    @OneToMany(
//            mappedBy = "school",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//
//    @JsonIgnoreProperties({
//            "school",
//            "hibernateLazyInitializer",
//            "handler"
//    })
//
//    private List<HOD> hods = new ArrayList<>();
//
//    // =====================================================
//    // FACILITIES
//    // =====================================================
//
//    @OneToMany(
//            mappedBy = "school",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//
//    @JsonIgnoreProperties({
//            "school",
//            "hibernateLazyInitializer",
//            "handler"
//    })
//
//    private List<Facility> facilities = new ArrayList<>();
//
//    // =====================================================
//    // GALLERY
//    // =====================================================
//
//    @OneToMany(
//            mappedBy = "school",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//
//    @JsonIgnoreProperties({
//            "school",
//            "hibernateLazyInitializer",
//            "handler"
//    })
//
//    private List<Gallery> gallery = new ArrayList<>();
//
//    // =====================================================
//    // TESTIMONIALS
//    // =====================================================
//
//    @OneToMany(
//            mappedBy = "school",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//
//    @JsonIgnoreProperties({
//            "school",
//            "hibernateLazyInitializer",
//            "handler"
//    })
//
//    private List<Testimonial> testimonials =
//            new ArrayList<>();
//
//    // =====================================================
//    // SCHOOL STATISTICS
//    // =====================================================
//
//    @OneToOne(
//            mappedBy = "school",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//
//    @JsonIgnoreProperties({
//            "school",
//            "hibernateLazyInitializer",
//            "handler"
//    })
//
//    private SchoolStatistics statistics;
//
//    // =====================================================
//    // LIFECYCLE
//    // =====================================================
//
//    @PrePersist
//    public void onCreate() {
//
//        createdAt = LocalDateTime.now();
//
//        updatedAt = LocalDateTime.now();
//    }
//
//    @PreUpdate
//    public void onUpdate() {
//
//        updatedAt = LocalDateTime.now();
//    }
//
//    // =====================================================
//    // GETTERS & SETTERS
//    // =====================================================
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getSchoolName() {
//        return schoolName;
//    }
//
//    public void setSchoolName(String schoolName) {
//        this.schoolName = schoolName;
//    }
//
//    public String getSchoolCode() {
//        return schoolCode;
//    }
//
//    public void setSchoolCode(String schoolCode) {
//        this.schoolCode = schoolCode;
//    }
//
//    public String getSlug() {
//        return slug;
//    }
//
//    public void setSlug(String slug) {
//        this.slug = slug;
//    }
//
//    public String getTagline() {
//        return tagline;
//    }
//
//    public void setTagline(String tagline) {
//        this.tagline = tagline;
//    }
//
//    public String getLogo() {
//        return logo;
//    }
//
//    public void setLogo(String logo) {
//        this.logo = logo;
//    }
//
//    public String getAbout() {
//        return about;
//    }
//
//    public void setAbout(String about) {
//        this.about = about;
//    }
//
//    public String getMission() {
//        return mission;
//    }
//
//    public void setMission(String mission) {
//        this.mission = mission;
//    }
//
//    public String getVision() {
//        return vision;
//    }
//
//    public void setVision(String vision) {
//        this.vision = vision;
//    }
//
//    public String getPrincipalMessage() {
//        return principalMessage;
//    }
//
//    public void setPrincipalMessage(String principalMessage) {
//        this.principalMessage = principalMessage;
//    }
//
//    public String getBoard() {
//        return board;
//    }
//
//    public void setBoard(String board) {
//        this.board = board;
//    }
//
//    public String getSchoolType() {
//        return schoolType;
//    }
//
//    public void setSchoolType(String schoolType) {
//        this.schoolType = schoolType;
//    }
//
//    public String getMedium() {
//        return medium;
//    }
//
//    public void setMedium(String medium) {
//        this.medium = medium;
//    }
//
//    public Integer getEstablishedYear() {
//        return establishedYear;
//    }
//
//    public void setEstablishedYear(Integer establishedYear) {
//        this.establishedYear = establishedYear;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getWebsite() {
//        return website;
//    }
//
//    public void setWebsite(String website) {
//        this.website = website;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public String getState() {
//        return state;
//    }
//
//    public void setState(String state) {
//        this.state = state;
//    }
//
//    public String getCountry() {
//        return country;
//    }
//
//    public void setCountry(String country) {
//        this.country = country;
//    }
//
//    public String getPincode() {
//        return pincode;
//    }
//
//    public void setPincode(String pincode) {
//        this.pincode = pincode;
//    }
//
//    public String getFacebookLink() {
//        return facebookLink;
//    }
//
//    public void setFacebookLink(String facebookLink) {
//        this.facebookLink = facebookLink;
//    }
//
//    public String getInstagramLink() {
//        return instagramLink;
//    }
//
//    public void setInstagramLink(String instagramLink) {
//        this.instagramLink = instagramLink;
//    }
//
//    public String getYoutubeLink() {
//        return youtubeLink;
//    }
//
//    public void setYoutubeLink(String youtubeLink) {
//        this.youtubeLink = youtubeLink;
//    }
//
//    public Boolean getActive() {
//        return active;
//    }
//
//    public void setActive(Boolean active) {
//        this.active = active;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public LocalDateTime getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public List<String> getCoverImages() {
//        return coverImages;
//    }
//
//    public void setCoverImages(List<String> coverImages) {
//        this.coverImages = coverImages;
//    }
//
//    public SchoolAdmin getSchoolAdmin() {
//        return schoolAdmin;
//    }
//
//    public void setSchoolAdmin(SchoolAdmin schoolAdmin) {
//        this.schoolAdmin = schoolAdmin;
//    }
//
//    public List<HOD> getHods() {
//        return hods;
//    }
//
//    public void setHods(List<HOD> hods) {
//        this.hods = hods;
//    }
//
//    public List<Facility> getFacilities() {
//        return facilities;
//    }
//
//    public void setFacilities(List<Facility> facilities) {
//        this.facilities = facilities;
//    }
//
//    public List<Gallery> getGallery() {
//        return gallery;
//    }
//
//    public void setGallery(List<Gallery> gallery) {
//        this.gallery = gallery;
//    }
//
//    public List<Testimonial> getTestimonials() {
//        return testimonials;
//    }
//
//    public void setTestimonials(List<Testimonial> testimonials) {
//        this.testimonials = testimonials;
//    }
//
//    public SchoolStatistics getStatistics() {
//        return statistics;
//    }
//
//    public void setStatistics(SchoolStatistics statistics) {
//        this.statistics = statistics;
//    }
//}