package com.example.TransportHospital.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TransportHospital.models.UserDetail;

public interface UserDetailRepo extends JpaRepository<UserDetail, Long> {

    Optional<UserDetail> findByUseremail(String useremail);

    Optional<UserDetail> findByUsername(String username);

    boolean existsByUseremail(String useremail);

    boolean existsByUsername(String username);

    long countByUseremail(String useremail);
}
