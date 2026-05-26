package com.example.stud_erp.repository;

import com.example.stud_erp.entity.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GalleryRepository
        extends JpaRepository<Gallery, Long> {

    List<Gallery> findBySchoolId(Long schoolId);

    List<Gallery> findBySchoolIdAndActiveTrue(Long schoolId);
}