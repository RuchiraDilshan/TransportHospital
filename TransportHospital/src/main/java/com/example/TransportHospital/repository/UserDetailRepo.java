package com.example.TransportHospital.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TransportHospital.models.UserDetail;

import com.example.TransportHospital.models.UserDetail.UserRole;

public interface UserDetailRepo extends JpaRepository<UserDetail, Long> {

    Optional<UserDetail> findByUseremail(String useremail);

    Optional<UserDetail> findByUserid(Long userid);

    Optional<UserDetail> findByUsername(String username);

    boolean existsByUseremail(String useremail);

    boolean existsByUsername(String username);

    long countByUserrole(UserRole userrole);

}
