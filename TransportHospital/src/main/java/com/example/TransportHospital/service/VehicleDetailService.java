package com.example.TransportHospital.service;

import org.springframework.stereotype.Service;

import com.example.TransportHospital.models.VehicleDetail;
import com.example.TransportHospital.repository.VehicleDetailRepo;

@Service
public class VehicleDetailService {

    private final VehicleDetailRepo vehicleDetailRepository;

    public VehicleDetailService(VehicleDetailRepo vehicleDetailRepository) {
        this.vehicleDetailRepository = vehicleDetailRepository;
    }

    public VehicleDetail getVehicleByNumber(String vehiclenumber) {
        return vehicleDetailRepository.findByVehiclenumber(vehiclenumber).orElse(null);
    }

    public VehicleDetail getVehicleById(Long vehicleid) {
        return vehicleDetailRepository.findByVehicleid(vehicleid).orElse(null);
    }

    public VehicleDetail saveVehicle(VehicleDetail vehicle) {
        return vehicleDetailRepository.save(vehicle);
    }

}
