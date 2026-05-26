package com.example.stud_erp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "school_statistics")
public class SchoolStatistics {

    // =====================================================
    // ID
    // =====================================================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // =====================================================
    // STUDENTS
    // =====================================================

    private Integer totalStudents = 0;

    private Integer boysCount = 0;

    private Integer girlsCount = 0;

    // =====================================================
    // STAFF
    // =====================================================

    private Integer totalTeachers = 0;

    private Integer totalStaff = 0;

    // =====================================================
    // CLASSES
    // =====================================================

    private Integer totalClasses = 0;

    private Integer totalSections = 0;

    // =====================================================
    // CAMPUS
    // =====================================================

    private Integer totalClassrooms = 0;

    private Integer totalLabs = 0;

    private Integer totalLibraries = 0;

    private Integer totalComputers = 0;

    private Integer totalBuses = 0;

    // =====================================================
    // RESULT & EXPERIENCE
    // =====================================================

    private Double boardResultPercentage = 0.0;

    private Integer yearsOfExperience = 0;

    // =====================================================
    // SCHOOL
    // =====================================================

    @OneToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "school_id")

    @JsonIgnoreProperties({
            "hibernateLazyInitializer",
            "handler",
            "statistics"
    })

    private School school;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public SchoolStatistics() {
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

    public Integer getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(Integer totalStudents) {
        this.totalStudents = totalStudents;
    }

    public Integer getBoysCount() {
        return boysCount;
    }

    public void setBoysCount(Integer boysCount) {
        this.boysCount = boysCount;
    }

    public Integer getGirlsCount() {
        return girlsCount;
    }

    public void setGirlsCount(Integer girlsCount) {
        this.girlsCount = girlsCount;
    }

    public Integer getTotalTeachers() {
        return totalTeachers;
    }

    public void setTotalTeachers(Integer totalTeachers) {
        this.totalTeachers = totalTeachers;
    }

    public Integer getTotalStaff() {
        return totalStaff;
    }

    public void setTotalStaff(Integer totalStaff) {
        this.totalStaff = totalStaff;
    }

    public Integer getTotalClasses() {
        return totalClasses;
    }

    public void setTotalClasses(Integer totalClasses) {
        this.totalClasses = totalClasses;
    }

    public Integer getTotalSections() {
        return totalSections;
    }

    public void setTotalSections(Integer totalSections) {
        this.totalSections = totalSections;
    }

    public Integer getTotalClassrooms() {
        return totalClassrooms;
    }

    public void setTotalClassrooms(Integer totalClassrooms) {
        this.totalClassrooms = totalClassrooms;
    }

    public Integer getTotalLabs() {
        return totalLabs;
    }

    public void setTotalLabs(Integer totalLabs) {
        this.totalLabs = totalLabs;
    }

    public Integer getTotalLibraries() {
        return totalLibraries;
    }

    public void setTotalLibraries(Integer totalLibraries) {
        this.totalLibraries = totalLibraries;
    }

    public Integer getTotalComputers() {
        return totalComputers;
    }

    public void setTotalComputers(Integer totalComputers) {
        this.totalComputers = totalComputers;
    }

    public Integer getTotalBuses() {
        return totalBuses;
    }

    public void setTotalBuses(Integer totalBuses) {
        this.totalBuses = totalBuses;
    }

    public Double getBoardResultPercentage() {
        return boardResultPercentage;
    }

    public void setBoardResultPercentage(Double boardResultPercentage) {
        this.boardResultPercentage = boardResultPercentage;
    }

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}