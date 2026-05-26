package com.example.stud_erp.repository;

import com.example.stud_erp.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {

    School findBySlug(String slug);

}



//package com.example.stud_erp.repository;
//
//import com.example.stud_erp.entity.School;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface SchoolRepository
//        extends JpaRepository<School, Long> {
//
//    // =====================================================
//    // FIND BY SLUG
//    // =====================================================
//
//    Optional<School> findBySlug(
//            String slug
//    );
//
//    // =====================================================
//    // FIND BY SCHOOL CODE
//    // =====================================================
//
//    Optional<School> findBySchoolCode(
//            String schoolCode
//    );
//
//    // =====================================================
//    // FIND BY EMAIL
//    // =====================================================
//
//    Optional<School> findByEmail(
//            String email
//    );
//
//    // =====================================================
//    // FIND BY SCHOOL NAME
//    // =====================================================
//
//    List<School> findBySchoolNameContainingIgnoreCase(
//            String schoolName
//    );
//
//    // =====================================================
//    // SEARCH BY KEYWORD
//    // =====================================================
//
//    List<School> findBySchoolNameContainingIgnoreCaseOrCityContainingIgnoreCase(
//            String schoolName,
//            String city
//    );
//
//    // =====================================================
//    // CHECK SLUG EXISTS
//    // =====================================================
//
//    boolean existsBySlug(
//            String slug
//    );
//
//    // =====================================================
//    // CHECK SCHOOL CODE EXISTS
//    // =====================================================
//
//    boolean existsBySchoolCode(
//            String schoolCode
//    );
//
//    // =====================================================
//    // FIND ACTIVE SCHOOLS
//    // =====================================================
//
//    List<School> findByActiveTrue();
//
//    // =====================================================
//    // FIND INACTIVE SCHOOLS
//    // =====================================================
//
//    List<School> findByActiveFalse();
//}