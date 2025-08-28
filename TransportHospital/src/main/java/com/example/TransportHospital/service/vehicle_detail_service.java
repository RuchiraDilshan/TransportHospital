package com.example.TransportHospital.service;

import org.springframework.stereotype.Service;

import com.example.TransportHospital.models.vehicle_detail;
import com.example.TransportHospital.repository.vehicle_detail_repository;

@Service
public class vehicle_detail_service {

    private final vehicle_detail_repository vehicleDetailRepository;

    public vehicle_detail_service(vehicle_detail_repository vehicleDetailRepository) {
        this.vehicleDetailRepository = vehicleDetailRepository;
    }

    public vehicle_detail getVehicleByNumber(String vehicle_number) {
        return vehicleDetailRepository.findByVehicle_number(vehicle_number).orElse(null);
    }

    public vehicle_detail saveVehicle(vehicle_detail vehicle) {
        return vehicleDetailRepository.save(vehicle);
    }

}
