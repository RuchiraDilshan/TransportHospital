package com.example.TransportHospital.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.TransportHospital.models.UserDetail;
import com.example.TransportHospital.repository.UserDetailRepo;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserDetailRepo userDetailRepo;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserDetailRepo userDetailRepo, PasswordEncoder passwordEncoder) {
        this.userDetailRepo = userDetailRepo;
        this.passwordEncoder = passwordEncoder;

    }

    @Override

    public void run(String... args) throws Exception {
        if (userDetailRepo.countByUserrole(UserDetail.UserRole.SUPERADMIN) == 0) {
            UserDetail superAdmin = new UserDetail();
            superAdmin.setUsername("superadmin1");
            superAdmin.setUseremail("superadmin1@transporthospital.com");
            superAdmin.setUserpassword(passwordEncoder.encode("Admin1@12345."));
            superAdmin.setUserrole(UserDetail.UserRole.SUPERADMIN);
            superAdmin.setIsActive(true);

            userDetailRepo.save(superAdmin);

        }
    }

}
