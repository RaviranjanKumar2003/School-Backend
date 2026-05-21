package com.example.stud_erp.payload;

import java.util.List;

public class StudentPromotionRequest {

    private List<Long> studentIds;

    private Long toClassId;

    private String toSection;

    private Long updatedBy;

    private String actionType;



// GETTERS & SETTERS


    public List<Long> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<Long> studentIds) {
        this.studentIds = studentIds;
    }

    public Long getToClassId() {
        return toClassId;
    }

    public void setToClassId(Long toClassId) {
        this.toClassId = toClassId;
    }

    public String getToSection() {
        return toSection;
    }

    public void setToSection(String toSection) {
        this.toSection = toSection;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }
}