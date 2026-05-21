package com.example.stud_erp.controller;

import com.example.stud_erp.entity.Accountant;
import com.example.stud_erp.service.AccountantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accountant")
@CrossOrigin("*")
public class AccountantController {

    private final AccountantService accountantService;

    // CONSTRUCTOR INJECTION
    public AccountantController(AccountantService accountantService) {
        this.accountantService = accountantService;
    }

    // CREATE ACCOUNTANT
    @PostMapping("/create")
    public Accountant create(@RequestBody Accountant accountant) {
        return accountantService.createAccountant(accountant);
    }

    // ACCOUNTANT LOGIN
    @PostMapping("/login")
    public Accountant login(@RequestBody Accountant accountant) {
        return accountantService.loginAccountant(
                accountant.getEmail(),
                accountant.getPassword()
        );
    }

    // GET ALL ACCOUNTANTS
    @GetMapping("/all")
    public List<Accountant> getAll() {
        return accountantService.getAllAccountants();
    }

    // GET ACCOUNTANT BY ID
    @GetMapping("/{id}")
    public Accountant getById(@PathVariable Long id) {
        return accountantService.getAccountantById(id);
    }

    // UPDATE ACCOUNTANT
    @PutMapping("/update/{id}")
    public Accountant update(@PathVariable Long id,
                             @RequestBody Accountant accountant) {
        return accountantService.updateAccountant(id, accountant);
    }

    // DELETE ACCOUNTANT
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        accountantService.deleteAccountant(id);
        return "Accountant Deleted Successfully";
    }

    @PutMapping("/profile/update/{id}")
    public Accountant updateProfile(@PathVariable Long id,
                                    @RequestBody Accountant accountant) {
        return accountantService.updateAccountantProfile(id, accountant);
    }
}