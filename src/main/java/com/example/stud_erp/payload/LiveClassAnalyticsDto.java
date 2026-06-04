package com.example.stud_erp.payload;

public class LiveClassAnalyticsDto {

    private Long liveClassId;

    private Integer totalStudents;

    private Integer joinedStudents;

    private Integer absentStudents;

    private Double attendancePercentage;

    private Double averageDurationMinutes;

 // getters setters


    public Long getLiveClassId() {
        return liveClassId;
    }

    public void setLiveClassId(Long liveClassId) {
        this.liveClassId = liveClassId;
    }

    public Integer getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(Integer totalStudents) {
        this.totalStudents = totalStudents;
    }

    public Integer getJoinedStudents() {
        return joinedStudents;
    }

    public void setJoinedStudents(Integer joinedStudents) {
        this.joinedStudents = joinedStudents;
    }

    public Integer getAbsentStudents() {
        return absentStudents;
    }

    public void setAbsentStudents(Integer absentStudents) {
        this.absentStudents = absentStudents;
    }

    public Double getAttendancePercentage() {
        return attendancePercentage;
    }

    public void setAttendancePercentage(Double attendancePercentage) {
        this.attendancePercentage = attendancePercentage;
    }

    public Double getAverageDurationMinutes() {
        return averageDurationMinutes;
    }

    public void setAverageDurationMinutes(Double averageDurationMinutes) {
        this.averageDurationMinutes = averageDurationMinutes;
    }
}