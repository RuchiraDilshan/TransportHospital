package com.example.TransportHospital.service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<VehicleDetail> searchVehicleByNumber(String searchTerm) {
        return vehicleDetailRepository.findByVehiclenumberContainingIgnoreCase(searchTerm);
    }

    public List<String> getVehicleNumberSuggestions(String searchTerm) {
        List<VehicleDetail> vehicles = vehicleDetailRepository.findByVehiclenumberContainingIgnoreCase(searchTerm);
        return vehicles.stream()
                .map(VehicleDetail::getVehicleNumber)
                .collect(Collectors.toList());

    }

}
