package com.example.stud_erp.service;

import com.example.stud_erp.entity.Accountant;
import com.example.stud_erp.repository.AccountantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountantService {

    private final AccountantRepository accountantRepository;

    /* =====================================================
       CONSTRUCTOR INJECTION
    ===================================================== */

    public AccountantService(AccountantRepository accountantRepository) {
        this.accountantRepository = accountantRepository;
    }

    /* =====================================================
       CREATE ACCOUNTANT
    ===================================================== */

    public Accountant createAccountant(Accountant accountant) {

        return accountantRepository.save(accountant);
    }

    /* =====================================================
       ACCOUNTANT LOGIN
    ===================================================== */

    public Accountant loginAccountant(String email,
                                      String password) {

        Accountant accountant = accountantRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Invalid email or password"
                        )
                );

        // CHECK PASSWORD
        if (!accountant.getPassword().equals(password)) {

            throw new RuntimeException(
                    "Invalid email or password"
            );
        }

        // CHECK ACCOUNT ACTIVE STATUS
        if (Boolean.FALSE.equals(accountant.getActive())) {

            throw new RuntimeException(
                    "Accountant account is inactive"
            );
        }

        return accountant;
    }

    /* =====================================================
       GET ALL ACCOUNTANTS
    ===================================================== */

    public List<Accountant> getAllAccountants() {

        return accountantRepository.findAll();
    }

    /* =====================================================
       GET ACCOUNTANT BY ID
    ===================================================== */

    public Accountant getAccountantById(Long id) {

        return accountantRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Accountant not found"
                        )
                );
    }

    /* =====================================================
       UPDATE ACCOUNTANT
    ===================================================== */

    public Accountant updateAccountant(Long id,
                                       Accountant accountant) {

        Accountant existing = getAccountantById(id);

        existing.setFullName(accountant.getFullName());

        existing.setEmail(accountant.getEmail());

        existing.setPhone(accountant.getPhone());

        existing.setSalary(accountant.getSalary());

        existing.setPassword(accountant.getPassword());

        existing.setJoiningDate(accountant.getJoiningDate());

        existing.setRole(accountant.getRole());

        existing.setActive(accountant.getActive());

        // PROFILE IMAGE UPDATE
        if (accountant.getProfileImage() != null) {

            existing.setProfileImage(
                    accountant.getProfileImage()
            );
        }

        return accountantRepository.save(existing);
    }

    /* =====================================================
       UPDATE ACCOUNTANT PROFILE
    ===================================================== */

    public Accountant updateAccountantProfile(Long id,
                                              Accountant accountant) {

        Accountant existing = getAccountantById(id);

        existing.setFullName(accountant.getFullName());

        existing.setEmail(accountant.getEmail());

        existing.setPhone(accountant.getPhone());

        // PROFILE IMAGE UPDATE
        if (accountant.getProfileImage() != null) {

            existing.setProfileImage(
                    accountant.getProfileImage()
            );
        }

        return accountantRepository.save(existing);
    }

    /* =====================================================
       DELETE ACCOUNTANT
    ===================================================== */

    public void deleteAccountant(Long id) {

        accountantRepository.deleteById(id);
    }
}