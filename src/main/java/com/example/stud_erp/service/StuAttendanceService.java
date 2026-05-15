//package com.example.stud_erp.service;
//
//import com.example.stud_erp.entity.StuAttendance;
//import com.example.stud_erp.payload.StuAttendanceDTO;
//
//import java.time.LocalDate;
//import java.util.List;
//
//public interface StuAttendanceService {
//
//    String save(Integer classNumber, LocalDate date, List<StuAttendanceDTO> list);
//
//    List<StuAttendanceDTO> getByClassAndDate(Integer classNumber, LocalDate date);
//
//    List<StuAttendance> getByDate(LocalDate date);
//
//}



package com.example.stud_erp.service;

import com.example.stud_erp.entity.StuAttendance;
import com.example.stud_erp.payload.StuAttendanceDTO;

import java.time.LocalDate;
import java.util.List;

public interface StuAttendanceService {

    // SAVE
    String save(
            Long schoolId,
            Long classId,
            LocalDate date,
            Long takenById,
            String takenByName,
            String takenByRole,
            List<StuAttendanceDTO> list
    );

    // GET
    List<StuAttendanceDTO> getByClassAndDate(
            Long schoolId,
            Long classId,
            LocalDate date
    );

    // DAILY SUMMARY
    List<StuAttendance> getByDate(
            Long schoolId,
            LocalDate date
    );

}