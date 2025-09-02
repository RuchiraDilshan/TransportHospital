package com.example.TransportHospital.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TransportHospital.dto.VehicleDetailDto;
import com.example.TransportHospital.models.VehicleDetail;
import com.example.TransportHospital.service.VehicleDetailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleDetailController {

    private final VehicleDetailService vehicleDetailService;

    public VehicleDetailController(VehicleDetailService vehicleDetailService) {
        this.vehicleDetailService = vehicleDetailService;
    }

    @GetMapping("/number/{vehiclenumber}")
    public ResponseEntity<VehicleDetail> getVehicleByNumber(@PathVariable String vehiclenumber) {
        VehicleDetail vehicle = vehicleDetailService.getVehicleByNumber(vehiclenumber);
        if (vehicle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vehicle);
    }

    @GetMapping("/id/{vehicleid}")
    public ResponseEntity<VehicleDetail> getVehicleById(@PathVariable Long vehicleid) {
        VehicleDetail vehicle = vehicleDetailService.getVehicleById(vehicleid);
        if (vehicle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vehicle);
    }

    @GetMapping("/search")
    public ResponseEntity<List<String>> searchvehiclenumber(@RequestParam String query) {
        List<String> suggestions = vehicleDetailService.getVehicleNumberSuggestions(query);
        return ResponseEntity.ok(suggestions);

    }

    @PutMapping("/id/{vehicleId}")
    public ResponseEntity<VehicleDetail> updateVehicle(
            @PathVariable Long vehicleId,
            @RequestBody VehicleDetailDto dto) {
        try {
            VehicleDetail vehicleDetail = new VehicleDetail();
            vehicleDetail.setVehicleNumber(dto.getVehiclenumber());
            vehicleDetail.setVehicleType(VehicleDetail.VehicleType.valueOf(dto.getVehicletype().toUpperCase()));
            vehicleDetail.setMake(dto.getMake());
            vehicleDetail.setFuelType(VehicleDetail.FuelType.valueOf(dto.getFueltype().toUpperCase()));

            VehicleDetail updatedVehicle = vehicleDetailService.updateVehicle(vehicleId, vehicleDetail);
            return ResponseEntity.ok(updatedVehicle);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @DeleteMapping("/id/{vehicleId}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long vehicleId) {
        try {
            vehicleDetailService.deleteVehicle(vehicleId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<VehicleDetail> addVehicleDetail(@RequestBody VehicleDetailDto dto) {
        try {
            VehicleDetail vehicleDetail = new VehicleDetail();
            vehicleDetail.setVehicleNumber(dto.getVehiclenumber());
            vehicleDetail.setVehicleType(VehicleDetail.VehicleType.valueOf(dto.getVehicletype().toUpperCase()));
            vehicleDetail.setMake(dto.getMake());
            vehicleDetail.setFuelType(VehicleDetail.FuelType.valueOf(dto.getFueltype().toUpperCase()));

            VehicleDetail savedVehicle = vehicleDetailService.saveVehicle(vehicleDetail);
            return ResponseEntity.ok(savedVehicle);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

}
