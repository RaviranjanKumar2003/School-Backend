package com.example.stud_erp.payload;

import java.util.List;

public class PublicSchoolResponseDto {

    // =====================================================
    // BASIC INFO
    // =====================================================

    private Long id;

    private String schoolName;

    private String schoolCode;

    private String slug;

    private String tagline;

    private String logo;

    // =====================================================
    // SCHOOL DETAILS
    // =====================================================

    private String about;

    private String mission;

    private String vision;

    private String principalMessage;

    private String board;

    private String schoolType;

    private String medium;

    private Integer establishedYear;

    // =====================================================
    // CONTACT INFO
    // =====================================================

    private String email;

    private String phone;

    private String website;

    // =====================================================
    // ADDRESS
    // =====================================================

    private String address;

    private String city;

    private String state;

    private String country;

    private String pincode;

    // =====================================================
    // SOCIAL LINKS
    // =====================================================

    private String facebook;

    private String instagram;

    private String youtube;

    // =====================================================
    // STATUS
    // =====================================================

    private Boolean active;

    // =====================================================
    // COVER IMAGES
    // =====================================================

    private List<String> coverImages;

    // =====================================================
    // FACILITIES
    // =====================================================

    private List<FacilityDto> facilities;

    // =====================================================
    // GALLERY
    // =====================================================

    private List<GalleryDto> gallery;

    // =====================================================
    // STATISTICS
    // =====================================================

    private SchoolStatisticsDto statistics;

    // =====================================================
    // TESTIMONIALS
    // =====================================================

    private List<TestimonialDto> testimonials;

    // =====================================================
    // GETTERS & SETTERS
    // =====================================================

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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<String> getCoverImages() {
        return coverImages;
    }

    public void setCoverImages(List<String> coverImages) {
        this.coverImages = coverImages;
    }

    public List<FacilityDto> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<FacilityDto> facilities) {
        this.facilities = facilities;
    }

    public List<GalleryDto> getGallery() {
        return gallery;
    }

    public void setGallery(List<GalleryDto> gallery) {
        this.gallery = gallery;
    }

    public SchoolStatisticsDto getStatistics() {
        return statistics;
    }

    public void setStatistics(SchoolStatisticsDto statistics) {
        this.statistics = statistics;
    }

    public List<TestimonialDto> getTestimonials() {
        return testimonials;
    }

    public void setTestimonials(List<TestimonialDto> testimonials) {
        this.testimonials = testimonials;
    }
}