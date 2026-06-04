package com.example.stud_erp.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "live_class_attendance",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {
                                "live_class_id",
                                "student_id"
                        }
                )
        }
)
public class LiveClassAttendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ================= RELATIONS =================

    @ManyToOne
    @JoinColumn(name = "live_class_id")
    private LiveClass liveClass;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    // ================= ATTENDANCE =================

    private LocalDateTime joinedAt;

    private LocalDateTime leftAt;

    private Long durationMinutes;

    private Boolean attended = true;


// getters setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LiveClass getLiveClass() {
        return liveClass;
    }

    public void setLiveClass(LiveClass liveClass) {
        this.liveClass = liveClass;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }

    public LocalDateTime getLeftAt() {
        return leftAt;
    }

    public void setLeftAt(LocalDateTime leftAt) {
        this.leftAt = leftAt;
    }

    public Long getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Long durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public Boolean getAttended() {
        return attended;
    }

    public void setAttended(Boolean attended) {
        this.attended = attended;
    }
}