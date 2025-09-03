package com.example.TransportHospital.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TransportHospital.models.VehicleDetail;
import java.util.List;

@Repository
public interface VehicleDetailRepo extends JpaRepository<VehicleDetail, Long> {

    Optional<VehicleDetail> findByVehiclenumber(String vehiclenumber);

    List<VehicleDetail> findByVehiclenumberContainingIgnoreCase(String vehiclenumber);

    Optional<VehicleDetail> findByVehicleid(Long vehicleid);

}
