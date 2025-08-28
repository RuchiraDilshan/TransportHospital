package com.example.TransportHospital.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TransportHospital.models.vehicle_detail;

@Repository
public interface vehicle_detail_repository extends JpaRepository<vehicle_detail, Long> {

    Optional<vehicle_detail> findByVehicle_number(String vehicle_number);

}
