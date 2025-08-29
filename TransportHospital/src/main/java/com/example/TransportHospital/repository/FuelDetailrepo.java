package com.example.TransportHospital.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TransportHospital.models.FuelDetail;

@Repository
public interface FuelDetailrepo extends JpaRepository<FuelDetail, String> {
    List<FuelDetail> findByVehiclenumber(String vehiclenumber);

    Optional<FuelDetail> findByFuelid(String fuelid);

    boolean existexistsByFuelid(String fuelid);

}
