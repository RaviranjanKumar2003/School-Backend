package com.example.stud_erp.controller;

import com.example.stud_erp.entity.Result;
import com.example.stud_erp.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
@CrossOrigin("*")
public class ResultController {

    @Autowired
    private ResultRepository repo;

    @PostMapping
    public Result save(@RequestBody Result r) {
        return repo.save(r);
    }

    @GetMapping("/student/{id}")
    public List<Result> getByStudent(@PathVariable Long id) {
        return repo.findByStudentId(id);
    }

    @GetMapping
    public List<Result> getAll() {
        return repo.findAll();
    }
}