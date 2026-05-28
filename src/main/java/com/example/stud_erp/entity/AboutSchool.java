// ======================================================
// ENTITY -> AboutSchool
// ======================================================

package com.example.stud_erp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "about_school")
public class AboutSchool {

    // ======================================================
    // ID
    // ======================================================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ======================================================
    // SCHOOL RELATION
    // ======================================================

    @OneToOne
    @JoinColumn(name = "school_id")

    @JsonIgnoreProperties({
            "aboutSchool",
            "schoolAdmin",
            "hods",
            "hibernateLazyInitializer",
            "handler"
    })

    private School school;

    // ======================================================
    // BRANDING
    // ======================================================

    private String tagline;

    private String logo;

    // ======================================================
    // CONTENT
    // ======================================================

    @Lob
    @Column(columnDefinition = "TEXT")
    private String about;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String mission;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String vision;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String principalMessage;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String schoolAdminMessage;

    // ======================================================
    // SCHOOL DETAILS
    // ======================================================

    private String board;

    private String schoolType;

    private String medium;

    private Integer establishedYear;

    // ======================================================
    // CONTACT
    // ======================================================

    private String website;

    // ======================================================
    // SOCIAL LINKS
    // ======================================================

    private String facebookLink;

    private String instagramLink;

    private String youtubeLink;

// GETTERS & SETTERS


    public String getSchoolAdminMessage() {
        return schoolAdminMessage;
    }

    public void setSchoolAdminMessage(String schoolAdminMessage) {
        this.schoolAdminMessage = schoolAdminMessage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public String getVision() {
        return vision;
    }

    public void setVision(String vision) {
        this.vision = vision;
    }

    public String getPrincipalMessage() {
        return principalMessage;
    }

    public void setPrincipalMessage(String principalMessage) {
        this.principalMessage = principalMessage;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public Integer getEstablishedYear() {
        return establishedYear;
    }

    public void setEstablishedYear(Integer establishedYear) {
        this.establishedYear = establishedYear;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFacebookLink() {
        return facebookLink;
    }

    public void setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
    }

    public String getInstagramLink() {
        return instagramLink;
    }

    public void setInstagramLink(String instagramLink) {
        this.instagramLink = instagramLink;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }
}