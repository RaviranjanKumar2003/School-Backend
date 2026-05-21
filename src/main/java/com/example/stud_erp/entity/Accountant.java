package com.example.stud_erp.entity;

import com.example.stud_erp.enums.Role;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Accountant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String profileImage;

    private String fullName;

    @Column(unique = true)
    private String email;

    private String password;

    private String phone;

    private Double salary;

    private LocalDate joiningDate;

    private Boolean active;

    @Enumerated(EnumType.STRING)
    private Role role;

    /* =====================================================
       DEFAULT CONSTRUCTOR
    ===================================================== */

    public Accountant() {
    }

    /* =====================================================
       PARAMETERIZED CONSTRUCTOR
    ===================================================== */

    public Accountant(Long id,
                      String fullName,
                      String email,
                      String password,
                      String phone,
                      Double salary,
                      LocalDate joiningDate,
                      Boolean active,
                      Role role) {

        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.salary = salary;
        this.joiningDate = joiningDate;
        this.active = active;
        this.role = role;
    }

    /* =====================================================
       GETTERS & SETTERS
    ===================================================== */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}