package com.example.stud_erp.controller;

import com.example.stud_erp.entity.Result;
import com.example.stud_erp.entity.RecheckRequest;
import com.example.stud_erp.repository.ResultRepository;
import com.example.stud_erp.repository.RecheckRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
@CrossOrigin("*")
public class ResultController {

    @Autowired
    private ResultRepository repo;

    @Autowired
    private RecheckRepository recheckRepo;

    // ✅ SAVE RESULT
    @PostMapping
    public Result save(@RequestBody Result r) {
        return repo.save(r);
    }

    // ✅ GET BY STUDENT
    @GetMapping("/student/{id}")
    public List<Result> getByStudent(@PathVariable Long id) {
        return repo.findByStudentId(id);
    }

    // ✅ GET ALL
    @GetMapping
    public List<Result> getAll() {
        return repo.findAll();
    }

    // 🔥 UPDATE MARKS (RECHECK MAIN FEATURE)
    @PutMapping("/update-marks")
    public Result updateMarks(
            @RequestParam Long studentId,
            @RequestParam String subject,
            @RequestParam int newMarks,
            @RequestParam Long requestId
    ) {

        // 🔍 find result
        Result result = repo
                .findByStudentIdAndSubject(studentId, subject)
                .orElseThrow(() -> new RuntimeException("Result not found"));

        // ❌ validation
        if (newMarks < 0) {
            throw new RuntimeException("Marks cannot be negative");
        }

        if (newMarks > result.getTotalMarks()) {
            throw new RuntimeException("Marks cannot exceed total marks");
        }

        // 🔥 update marks
        result.setMarks(newMarks);
        Result updated = repo.save(result);

        // 🔥 mark recheck as COMPLETED
        RecheckRequest req = recheckRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        req.setStatus("COMPLETED");
        recheckRepo.save(req);

        return updated;
    }
}