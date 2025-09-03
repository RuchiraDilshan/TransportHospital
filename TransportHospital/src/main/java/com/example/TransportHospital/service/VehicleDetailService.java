package com.example.TransportHospital.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.TransportHospital.models.VehicleDetail;
import com.example.TransportHospital.repository.VehicleDetailRepo;

@Service
public class VehicleDetailService {

    private final VehicleDetailRepo vehicleDetailRepository;

    public VehicleDetailService(VehicleDetailRepo vehicleDetailRepository) {
        this.vehicleDetailRepository = vehicleDetailRepository;
    }

    public VehicleDetail getVehicleByNumber(String vehiclenumber) {
        return vehicleDetailRepository.findByVehiclenumber(vehiclenumber)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Vehicle not found with number: " + vehiclenumber));
    }

    public VehicleDetail getVehicleById(Long vehicleid) {
        return vehicleDetailRepository.findByVehicleid(vehicleid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Vehicle not found with id: " + vehicleid));
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

    // to update vehicle details

    public VehicleDetail updateVehicle(Long vehicleid, VehicleDetail updatedVehicle) {
        VehicleDetail existingVehicle = vehicleDetailRepository.findById(vehicleid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Vehicle not found with id: " + vehicleid));

        existingVehicle.setVehicleNumber(updatedVehicle.getVehicleNumber());
        existingVehicle.setVehicleType(updatedVehicle.getVehicleType());
        existingVehicle.setMake(updatedVehicle.getMake());
        existingVehicle.setFuelType(updatedVehicle.getFuelType());

        return vehicleDetailRepository.save(existingVehicle);
    }

    // to delete vehicle

    public void deleteVehicle(Long vehicleid) {
        VehicleDetail vehicle = vehicleDetailRepository.findById(vehicleid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Vehicle not found with id: " + vehicleid));
        vehicleDetailRepository.delete(vehicle);
    }
}