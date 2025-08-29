package com.example.TransportHospital.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TransportHospital.models.ServiceDetail;

public interface ServiceDetailRepo extends JpaRepository<ServiceDetail, String> {

    Optional<ServiceDetail> findByServiceid(Long serviceid);

    List<ServiceDetail> findByVehiclenumber(String vehiclenumber);

}
