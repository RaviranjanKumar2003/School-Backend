// ======================================================
// DTO -> AboutSchoolDto
// ======================================================

package com.example.stud_erp.payload;

public class AboutSchoolDto {

    private Long id;

    private Long schoolId;

    // BRANDING
    private String tagline;

    private String logo;

    // CONTENT
    private String about;

    private String mission;

    private String vision;

    private String principalMessage;

    private String schoolAdminMessage;

    // SCHOOL DETAILS
    private String board;

    private String schoolType;

    private String medium;

    private Integer establishedYear;

    // CONTACT
    private String website;

    // SOCIAL LINKS
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

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
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