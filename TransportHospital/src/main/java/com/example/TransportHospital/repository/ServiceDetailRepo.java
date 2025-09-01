package com.example.TransportHospital.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TransportHospital.models.ServiceDetail;

public interface ServiceDetailRepo extends JpaRepository<ServiceDetail, String> {

    Optional<ServiceDetail> findByServiceid(Long serviceid);

    List<ServiceDetail> findByVehiclenumber(String vehiclenumber);

    List<ServiceDetail> findByNextservicedateAfter(LocalDate date);

    List<ServiceDetail> findByNextservicedateBefore(LocalDate date);

}
