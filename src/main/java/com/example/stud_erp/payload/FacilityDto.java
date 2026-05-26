package com.example.stud_erp.payload;

public class FacilityDto {

    // =====================================================
    // ID
    // =====================================================

    private Long id;

    // =====================================================
    // FACILITY INFO
    // =====================================================

    private String title;

    private String description;

    private String icon;

    private Integer totalCount;

    private Boolean active;

    // =====================================================
    // SCHOOL
    // =====================================================

    private Long schoolId;

    private String schoolName;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public FacilityDto() {
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}