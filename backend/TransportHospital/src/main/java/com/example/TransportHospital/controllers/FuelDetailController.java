package com.example.TransportHospital.controllers;

import com.example.TransportHospital.dto.SuccessResponse;
import com.example.TransportHospital.models.FuelDetail;
import com.example.TransportHospital.models.VehicleDetail;
import com.example.TransportHospital.service.FuelDetailService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fuel-details")
public class FuelDetailController {

    private final FuelDetailService fuelDetailService;

    public FuelDetailController(FuelDetailService fuelDetailService) {
        this.fuelDetailService = fuelDetailService;
    }

    @GetMapping
    public ResponseEntity<List<FuelDetail>> getAllFuelDetails() {
        List<FuelDetail> fuelDetails = fuelDetailService.getAllFuelDetails();
        return ResponseEntity.ok(fuelDetails);
    }

    @GetMapping("/{fuelId}")
    public ResponseEntity<FuelDetail> getFuelDetailById(@PathVariable Long fuelId) {
        Optional<FuelDetail> fuelDetail = fuelDetailService.getFuelDetailById(fuelId);
        return fuelDetail.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/vehicle/{vehicleNumber}")
    public ResponseEntity<List<FuelDetail>> getFuelDetailsByVehicle(@PathVariable String vehicleNumber) {
        List<FuelDetail> fuelDetails = fuelDetailService.getFuelDetailsByVehicle(vehicleNumber);
        return ResponseEntity.ok(fuelDetails);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<FuelDetail>> getFuelDetailsByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<FuelDetail> fuelDetails = fuelDetailService.getFuelDetailsByDate(date);
        return ResponseEntity.ok(fuelDetails);
    }

    @GetMapping("/vehicle/{vehicleNumber}/date/{date}")
    public ResponseEntity<List<FuelDetail>> getFuelDetailsByVehicleAndDate(
            @PathVariable String vehicleNumber,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<FuelDetail> fuelDetails = fuelDetailService.getFuelDetailsByVehicleAndDate(vehicleNumber, date);
        return ResponseEntity.ok(fuelDetails);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<FuelDetail>> getFuelDetailsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<FuelDetail> fuelDetails = fuelDetailService.getFuelDetailsByDateRange(startDate, endDate);
        return ResponseEntity.ok(fuelDetails);
    }

    @GetMapping("/vehicle/{vehicleNumber}/latest")
    public ResponseEntity<FuelDetail> getLatestFuelRecord(@PathVariable String vehicleNumber) {
        Optional<FuelDetail> fuelDetail = fuelDetailService.getLatestFuelRecord(vehicleNumber);
        return fuelDetail.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SuccessResponse> createFuelDetail(
            @RequestBody FuelDetail fuelDetail,
            @RequestParam String vehicleNumber) {
        try {
            FuelDetail createdFuelDetail = fuelDetailService.createFuelDetail(fuelDetail, vehicleNumber);
            SuccessResponse response = new SuccessResponse("Fuel detail created successfully!", createdFuelDetail);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new SuccessResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{fuelId}")
    public ResponseEntity<SuccessResponse> updateFuelDetail(
            @PathVariable Long fuelId,
            @RequestBody FuelDetail fuelDetail) {
        try {
            FuelDetail updatedFuelDetail = fuelDetailService.updateFuelDetail(fuelId, fuelDetail);
            SuccessResponse response = new SuccessResponse("Fuel detail updated successfully!", updatedFuelDetail);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{fuelId}")
    public ResponseEntity<SuccessResponse> deleteFuelDetail(@PathVariable Long fuelId) {
        try {
            fuelDetailService.deleteFuelDetail(fuelId);
            SuccessResponse response = new SuccessResponse("Fuel detail deleted successfully!");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/vehicle/{vehicleNumber}/total-cost")
    public ResponseEntity<SuccessResponse> getTotalFuelCost(@PathVariable String vehicleNumber) {
        BigDecimal totalCost = fuelDetailService.getTotalFuelCost(vehicleNumber);
        SuccessResponse response = new SuccessResponse("Total fuel cost retrieved successfully!", totalCost);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/vehicle/{vehicleNumber}/total-quantity")
    public ResponseEntity<SuccessResponse> getTotalFuelQuantity(@PathVariable String vehicleNumber) {
        BigDecimal totalQuantity = fuelDetailService.getTotalFuelQuantity(vehicleNumber);
        SuccessResponse response = new SuccessResponse("Total fuel quantity retrieved successfully!", totalQuantity);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/vehicle/{vehicleNumber}/average-price")
    public ResponseEntity<SuccessResponse> getAverageFuelPrice(@PathVariable String vehicleNumber) {
        BigDecimal averagePrice = fuelDetailService.getAverageFuelPrice(vehicleNumber);
        SuccessResponse response = new SuccessResponse("Average fuel price retrieved successfully!", averagePrice);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{fuelId}/exists")
    public ResponseEntity<SuccessResponse> fuelRecordExists(@PathVariable Long fuelId) {
        boolean exists = fuelDetailService.fuelRecordExists(fuelId);
        SuccessResponse response = new SuccessResponse("Fuel record existence checked successfully!", exists);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/fuel-type/{fuelType}")
    public ResponseEntity<List<FuelDetail>> getFuelDetailsByFuelType(@PathVariable VehicleDetail.FuelType fuelType) {
        List<FuelDetail> fuelDetails = fuelDetailService.getFuelDetailsByFuelType(fuelType);
        return ResponseEntity.ok(fuelDetails);
    }

    @GetMapping("/vehicle/{vehicleNumber}/statistics")
    public ResponseEntity<SuccessResponse> getFuelStatistics(@PathVariable String vehicleNumber) {
        FuelDetailService.FuelStatistics statistics = fuelDetailService.getFuelStatistics(vehicleNumber);
        SuccessResponse response = new SuccessResponse("Fuel statistics retrieved successfully!", statistics);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/vehicle/{vehicleNumber}/fuel-efficiency")
    public ResponseEntity<SuccessResponse> calculateFuelEfficiency(
            @PathVariable String vehicleNumber,
            @RequestParam BigDecimal totalDistance) {
        BigDecimal efficiency = fuelDetailService.calculateFuelEfficiency(vehicleNumber, totalDistance);
        SuccessResponse response = new SuccessResponse("Fuel efficiency calculated successfully!", efficiency);
        return ResponseEntity.ok(response);
    }
}