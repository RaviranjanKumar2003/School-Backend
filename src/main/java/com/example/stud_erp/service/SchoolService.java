package com.example.stud_erp.service;

import com.example.stud_erp.payload.SchoolDto;

import java.util.List;

public interface SchoolService {

    SchoolDto createSchool(SchoolDto dto);

    SchoolDto updateSchool(Long id, SchoolDto dto);

    void deleteSchool(Long id);

    SchoolDto getSchoolById(Long id);

    List<SchoolDto> getAllSchools();
}