package com.example.TransportHospital.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TransportHospital.models.vehicle_detail;
import com.example.TransportHospital.service.vehicle_detail_service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/vehicles")
public class vehicle_detail_controller {

    private final vehicle_detail_service vehicleDetailService;

    public vehicle_detail_controller(vehicle_detail_service vehicleDetailService) {
        this.vehicleDetailService = vehicleDetailService;
    }

    @GetMapping("/by_number/{vehicle_number}")
    public ResponseEntity<vehicle_detail> getVehicleByNumber(@PathVariable String vehicle_number) {
        vehicle_detail vehicle = vehicleDetailService.getVehicleByNumber(vehicle_number);
        if (vehicle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vehicle);
    }

    @PostMapping
    public vehicle_detail addVehicle_detail(@RequestBody vehicle_detail vehicle) {
        return vehicleDetailService.saveVehicle(vehicle);
    }

}
