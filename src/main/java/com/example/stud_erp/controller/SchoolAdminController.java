package com.example.stud_erp.controller;

import com.example.stud_erp.entity.SchoolAdmin;
import com.example.stud_erp.payload.LoginRequest;
import com.example.stud_erp.payload.LoginResponse;
import com.example.stud_erp.service.SchoolAdminService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/school-admin")
public class SchoolAdminController {

    private final SchoolAdminService service;

    public SchoolAdminController(SchoolAdminService service) {
        this.service = service;
    }

    @PostMapping
    public SchoolAdmin create(@RequestBody SchoolAdmin admin) {
        return service.create(admin);
    }

    @PutMapping("/{id}")
    public SchoolAdmin update(@PathVariable Long id,
                              @RequestBody SchoolAdmin admin) {
        return service.update(id, admin);
    }

    @GetMapping("/{id}")
    public SchoolAdmin get(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Deleted";
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return service.login(request.getUsername(), request.getPassword());
    }
}