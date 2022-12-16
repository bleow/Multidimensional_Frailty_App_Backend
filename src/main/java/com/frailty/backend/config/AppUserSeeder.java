package com.frailty.backend.config;

import com.frailty.backend.entity.AppUser;
import com.frailty.backend.entity.AppUserRole;
import com.frailty.backend.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AppUserSeeder implements CommandLineRunner {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private ArrayList<AppUser> patients;

    private ArrayList<AppUser> clinicians;

    private ArrayList<AppUser> admins;

    public AppUserSeeder() {
        patients = new ArrayList<AppUser>();
        clinicians = new ArrayList<AppUser>();
        admins = new ArrayList<AppUser>();
    }

    @Autowired
    AppUserRepository appUserRepository;

    @Override
    public void run(String... args) throws Exception {
        loadPatients();
        loadClinicians();
        loadAdmins();
        appUserRepository.saveAll(patients);
        appUserRepository.saveAll(clinicians);
        appUserRepository.saveAll(admins);
    }

    private void loadPatients() {
        patients.add(new AppUser("Patient", "Patria", "p@gmail.com", bCryptPasswordEncoder.encode("1111"), AppUserRole.PATIENT, false, true));
    }

    private void loadClinicians() {
        clinicians.add(new AppUser("Clinician", "Claudia", "c@gmail.com", bCryptPasswordEncoder.encode("1111"), AppUserRole.CLINICIAN, false, true));
    }

    private void loadAdmins() {
        admins.add(new AppUser("SuperAdmin", "SuperAdmin", "a@gmail.com", bCryptPasswordEncoder.encode("1111"), AppUserRole.ADMIN, false, true));
    }
}
