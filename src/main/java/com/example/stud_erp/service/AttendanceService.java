package com.example.stud_erp.service;

import com.example.stud_erp.payload.ClassSessionDTO;

import java.util.List;

public interface AttendanceService {

    ClassSessionDTO saveAttendance(ClassSessionDTO dto);

    List<ClassSessionDTO> getClassAttendance(Integer classNumber);

    List<ClassSessionDTO> getStudentAttendance(Long studentId);
}