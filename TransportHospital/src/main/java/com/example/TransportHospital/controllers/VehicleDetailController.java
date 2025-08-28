package com.example.TransportHospital.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TransportHospital.models.VehicleDetail;
import com.example.TransportHospital.service.VehicleDetailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleDetailController {

    private final VehicleDetailService vehicleDetailService;

    public VehicleDetailController(VehicleDetailService vehicleDetailService) {
        this.vehicleDetailService = vehicleDetailService;
    }

    @GetMapping("/{vehiclenumber}")
    public ResponseEntity<VehicleDetail> getVehicleByNumber(@PathVariable String vehiclenumber) {
        VehicleDetail vehicle = vehicleDetailService.getVehicleByNumber(vehiclenumber);
        if (vehicle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vehicle);
    }

    @GetMapping("/{vehicleid}")
    public ResponseEntity<VehicleDetail> getVehicleById(@PathVariable Long vehicleid) {
        VehicleDetail vehicle = vehicleDetailService.getVehicleById(vehicleid);
        if (vehicle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vehicle);
    }

    @PostMapping
    public VehicleDetail addVehicleDetail(@RequestBody VehicleDetail vehicle) {
        return vehicleDetailService.saveVehicle(vehicle);
    }

}
