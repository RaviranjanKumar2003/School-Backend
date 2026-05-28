// ======================================================
// SERVICE -> AboutSchoolService
// ======================================================

package com.example.stud_erp.service;

import com.example.stud_erp.payload.AboutSchoolDto;

public interface AboutSchoolService {

    AboutSchoolDto createOrUpdate(
            Long schoolId,
            AboutSchoolDto dto
    );

    AboutSchoolDto getBySchoolId(Long schoolId);
}