package com.example.stud_erp.payload;

import lombok.Data;

import java.util.List;

@Data
public class SchoolDto {

    // ================= SCHOOL INFO =================
    private Long id;

    private String schoolName;

    private String schoolCode;

    private String address;

    private String email;

    private String phone;

    // ================= SCHOOL ADMIN =================
    private SchoolAdminDto schoolAdmin;

    private List<String> coverImages;

    // ================= HODS =================
    private List<HODDto> hods;




    // ================= DEFAULT CONSTRUCTOR =================
    public SchoolDto() {
    }

    public List<String> getCoverImages() {
        return coverImages;
    }

    public void setCoverImages(List<String> coverImages) {
        this.coverImages = coverImages;
    }

    // ================= GET ID =================
    public Long getId() {
        return id;
    }

    // ================= SET ID =================
    public void setId(Long id) {
        this.id = id;
    }

    // ================= GET SCHOOL NAME =================
    public String getSchoolName() {
        return schoolName;
    }

    // ================= SET SCHOOL NAME =================
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    // ================= GET SCHOOL CODE =================
    public String getSchoolCode() {
        return schoolCode;
    }

    // ================= SET SCHOOL CODE =================
    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    // ================= GET ADDRESS =================
    public String getAddress() {
        return address;
    }

    // ================= SET ADDRESS =================
    public void setAddress(String address) {
        this.address = address;
    }

    // ================= GET EMAIL =================
    public String getEmail() {
        return email;
    }

    // ================= SET EMAIL =================
    public void setEmail(String email) {
        this.email = email;
    }

    // ================= GET PHONE =================
    public String getPhone() {
        return phone;
    }

    // ================= SET PHONE =================
    public void setPhone(String phone) {
        this.phone = phone;
    }

    // ================= GET SCHOOL ADMIN =================
    public SchoolAdminDto getSchoolAdmin() {
        return schoolAdmin;
    }

    // ================= SET SCHOOL ADMIN =================
    public void setSchoolAdmin(SchoolAdminDto schoolAdmin) {
        this.schoolAdmin = schoolAdmin;
    }

    // ================= GET HODS =================
    public List<HODDto> getHods() {
        return hods;
    }

    // ================= SET HODS =================
    public void setHods(List<HODDto> hods) {
        this.hods = hods;
    }

    // ================= TO STRING =================
    @Override
    public String toString() {

        return "SchoolDto{" +
                "id=" + id +
                ", schoolName='" + schoolName + '\'' +
                ", schoolCode='" + schoolCode + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", schoolAdmin=" + schoolAdmin +
                ", hods=" + hods +
                '}';
    }
}



//package com.example.stud_erp.payload;
//
//import java.util.List;
//
//public class SchoolDto {
//
//    // =====================================================
//    // ID
//    // =====================================================
//
//    private Long id;
//
//    // =====================================================
//    // BASIC INFO
//    // =====================================================
//
//    private String schoolName;
//
//    private String schoolCode;
//
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
//    private String about;
//
//    private String mission;
//
//    private String vision;
//
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
//    private Boolean active;
//
//    // =====================================================
//    // SCHOOL ADMIN
//    // =====================================================
//
//    private SchoolAdminDto schoolAdmin;
//
//    // =====================================================
//    // COVER IMAGES
//    // =====================================================
//
//    private List<String> coverImages;
//
//    // =====================================================
//    // HODS
//    // =====================================================
//
//    private List<HODDto> hods;
//
//    // =====================================================
//    // DEFAULT CONSTRUCTOR
//    // =====================================================
//
//    public SchoolDto() {
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
//    public SchoolAdminDto getSchoolAdmin() {
//        return schoolAdmin;
//    }
//
//    public void setSchoolAdmin(SchoolAdminDto schoolAdmin) {
//        this.schoolAdmin = schoolAdmin;
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
//    public List<HODDto> getHods() {
//        return hods;
//    }
//
//    public void setHods(List<HODDto> hods) {
//        this.hods = hods;
//    }
//
//    // =====================================================
//    // TO STRING
//    // =====================================================
//
//    @Override
//    public String toString() {
//
//        return "SchoolDto{" +
//                "id=" + id +
//                ", schoolName='" + schoolName + '\'' +
//                ", schoolCode='" + schoolCode + '\'' +
//                ", slug='" + slug + '\'' +
//                ", tagline='" + tagline + '\'' +
//                ", logo='" + logo + '\'' +
//                ", board='" + board + '\'' +
//                ", schoolType='" + schoolType + '\'' +
//                ", medium='" + medium + '\'' +
//                ", establishedYear=" + establishedYear +
//                ", email='" + email + '\'' +
//                ", phone='" + phone + '\'' +
//                ", website='" + website + '\'' +
//                ", city='" + city + '\'' +
//                ", state='" + state + '\'' +
//                ", country='" + country + '\'' +
//                ", active=" + active +
//                '}';
//    }
//}