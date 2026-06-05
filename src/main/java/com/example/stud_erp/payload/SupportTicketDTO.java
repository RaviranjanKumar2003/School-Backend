package com.example.stud_erp.payload;

import lombok.Data;

@Data
public class SupportTicketDTO {

    private Long schoolId;

    private String schoolName;

    private Long schoolAdminId;

    private String schoolAdminName;

    private String subject;

    private Long creatorId;

    private String creatorName;

    private String creatorRole;

    private String description;

    private String category;

    private String priority;

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

    public Long getSchoolAdminId() {
        return schoolAdminId;
    }

    public void setSchoolAdminId(Long schoolAdminId) {
        this.schoolAdminId = schoolAdminId;
    }

    public String getSchoolAdminName() {
        return schoolAdminName;
    }

    public void setSchoolAdminName(String schoolAdminName) {
        this.schoolAdminName = schoolAdminName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getCreatorRole() {
        return creatorRole;
    }

    public void setCreatorRole(String creatorRole) {
        this.creatorRole = creatorRole;
    }
}