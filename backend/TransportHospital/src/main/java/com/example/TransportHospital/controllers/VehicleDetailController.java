package com.example.TransportHospital.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TransportHospital.dto.SuccessResponse;
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
        return ResponseEntity.ok(vehicle);
    }

    @GetMapping("/id/{vehicleid}")
    public ResponseEntity<VehicleDetail> getVehicleById(@PathVariable Long vehicleid) {
        VehicleDetail vehicle = vehicleDetailService.getVehicleById(vehicleid);
        return ResponseEntity.ok(vehicle);
    }

    @GetMapping("/search")
    public ResponseEntity<List<String>> searchvehiclenumber(@RequestParam String query) {
        List<String> suggestions = vehicleDetailService.getVehicleNumberSuggestions(query);
        return ResponseEntity.ok(suggestions);

    }

    @PutMapping("/{vehicleId}")
    public ResponseEntity<SuccessResponse> updateVehicle(
            @PathVariable Long vehicleId,
            @RequestBody VehicleDetailDto dto) {
        VehicleDetail vehicleDetail = new VehicleDetail();
        vehicleDetail.setVehicleNumber(dto.getVehiclenumber());
        vehicleDetail.setVehicleType(VehicleDetail.VehicleType.valueOf(dto.getVehicletype().toUpperCase()));
        vehicleDetail.setMake(dto.getMake());
        vehicleDetail.setFuelType(VehicleDetail.FuelType.valueOf(dto.getFueltype().toUpperCase()));

        VehicleDetail updatedVehicle = vehicleDetailService.updateVehicle(vehicleId, vehicleDetail);

        SuccessResponse response = new SuccessResponse("Vehicle updated successfully!", updatedVehicle);
        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<SuccessResponse> deleteVehicle(@PathVariable Long vehicleId) {
        vehicleDetailService.deleteVehicle(vehicleId);

        // Return success response with message
        SuccessResponse response = new SuccessResponse("Vehicle deleted successfully!");
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<SuccessResponse> addVehicleDetail(@RequestBody VehicleDetailDto dto) {
        VehicleDetail vehicleDetail = new VehicleDetail();
        vehicleDetail.setVehicleNumber(dto.getVehiclenumber());
        vehicleDetail.setVehicleType(VehicleDetail.VehicleType.valueOf(dto.getVehicletype().toUpperCase()));
        vehicleDetail.setMake(dto.getMake());
        vehicleDetail.setFuelType(VehicleDetail.FuelType.valueOf(dto.getFueltype().toUpperCase()));

        VehicleDetail savedVehicle = vehicleDetailService.saveVehicle(vehicleDetail);

        // Return success response with message
        SuccessResponse response = new SuccessResponse("Vehicle added successfully!", savedVehicle);
        return ResponseEntity.ok(response);

    }

}
