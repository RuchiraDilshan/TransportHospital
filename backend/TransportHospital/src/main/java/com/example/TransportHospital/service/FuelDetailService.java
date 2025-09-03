package com.example.TransportHospital.service;

import com.example.TransportHospital.models.FuelDetail;
import com.example.TransportHospital.models.VehicleDetail;
import com.example.TransportHospital.repository.FuelDetailRepo;
import com.example.TransportHospital.repository.VehicleDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FuelDetailService {

    @Autowired
    private FuelDetailRepo fuelDetailRepo;

    @Autowired
    private VehicleDetailRepo vehicleDetailRepository;

    private static final MathContext MATH_CONTEXT = new MathContext(10, RoundingMode.HALF_UP);
    private static final int SCALE = 2;

    // Create a new fuel record
    public FuelDetail createFuelDetail(FuelDetail fuelDetail, String vehicleNumber) {
        // Validate vehicle exists
        VehicleDetail vehicle = vehicleDetailRepository.findByVehiclenumber(vehicleNumber)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with number: " + vehicleNumber));

        // Validate required fields
        if (fuelDetail.getRefilleddate() == null) {
            throw new RuntimeException("Refill date is required");
        }
        if (fuelDetail.getRefilledquantity() == null
                || fuelDetail.getRefilledquantity().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Valid refill quantity is required");
        }
        if (fuelDetail.getFuelprice() == null || fuelDetail.getFuelprice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Valid fuel price is required");
        }

        // Set vehicle relationship
        fuelDetail.setVehicle(vehicle);

        // Sync fuel type from vehicle
        fuelDetail.setFueltype(vehicle.getFuelType());

        // Calculate total cost based on manual price and quantity
        if (fuelDetail.getRefillcost() == null) {
            BigDecimal totalCost = fuelDetail.getRefilledquantity().multiply(fuelDetail.getFuelprice(), MATH_CONTEXT);
            fuelDetail.setRefillcost(totalCost.setScale(SCALE, RoundingMode.HALF_UP));
        }

        // Validate that calculated cost matches if both are provided
        if (fuelDetail.getRefillcost() != null && fuelDetail.getFuelprice() != null
                && fuelDetail.getRefilledquantity() != null) {
            BigDecimal expectedCost = fuelDetail.getRefilledquantity().multiply(fuelDetail.getFuelprice(),
                    MATH_CONTEXT);
            BigDecimal calculatedCost = expectedCost.setScale(SCALE, RoundingMode.HALF_UP);
            BigDecimal providedCost = fuelDetail.getRefillcost().setScale(SCALE, RoundingMode.HALF_UP);

            if (providedCost.compareTo(calculatedCost) != 0) {
                throw new RuntimeException(
                        "Refill cost does not match calculated cost based on quantity and price. Expected: "
                                + calculatedCost + ", Provided: " + providedCost);
            }
        }

        return fuelDetailRepo.save(fuelDetail);
    }

    // Get all fuel records
    public List<FuelDetail> getAllFuelDetails() {
        return fuelDetailRepo.findAll();
    }

    // Get fuel record by ID
    public Optional<FuelDetail> getFuelDetailById(Long fuelId) {
        return fuelDetailRepo.findById(fuelId);
    }

    // Get fuel records by vehicle number
    public List<FuelDetail> getFuelDetailsByVehicle(String vehicleNumber) {
        return fuelDetailRepo.findByVehicleNumber(vehicleNumber);
    }

    // Get fuel records by date
    public List<FuelDetail> getFuelDetailsByDate(LocalDate date) {
        return fuelDetailRepo.findByRefilleddate(date);
    }

    // Get fuel records by vehicle and date
    public List<FuelDetail> getFuelDetailsByVehicleAndDate(String vehicleNumber, LocalDate date) {
        return fuelDetailRepo.findByVehicleNumberAndRefilleddate(vehicleNumber, date);
    }

    // Get fuel records by date range
    public List<FuelDetail> getFuelDetailsByDateRange(LocalDate startDate, LocalDate endDate) {
        return fuelDetailRepo.findByRefilleddateBetween(startDate, endDate);
    }

    // Get latest fuel record for a vehicle
    public Optional<FuelDetail> getLatestFuelRecord(String vehicleNumber) {
        List<FuelDetail> results = fuelDetailRepo.findTopByVehicleNumberOrderByRefilleddateDesc(vehicleNumber);
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    // Update fuel record
    public FuelDetail updateFuelDetail(Long fuelId, FuelDetail fuelDetail) {
        FuelDetail existingFuel = fuelDetailRepo.findById(fuelId)
                .orElseThrow(() -> new RuntimeException("Fuel record not found with id: " + fuelId));

        // Update fields
        if (fuelDetail.getRefilleddate() != null) {
            existingFuel.setRefilleddate(fuelDetail.getRefilleddate());
        }
        if (fuelDetail.getRefilledquantity() != null) {
            existingFuel.setRefilledquantity(fuelDetail.getRefilledquantity());
        }
        if (fuelDetail.getFuelprice() != null) {
            existingFuel.setFuelprice(fuelDetail.getFuelprice());
        }
        if (fuelDetail.getRefillcost() != null) {
            existingFuel.setRefillcost(fuelDetail.getRefillcost());
        }
        if (fuelDetail.getOdometerreading() != null) {
            existingFuel.setOdometerreading(fuelDetail.getOdometerreading());
        }

        // Recalculate total cost if quantity or price changed
        if ((fuelDetail.getRefilledquantity() != null || fuelDetail.getFuelprice() != null)
                && existingFuel.getRefilledquantity() != null
                && existingFuel.getFuelprice() != null) {

            BigDecimal newCost = existingFuel.getRefilledquantity().multiply(existingFuel.getFuelprice(), MATH_CONTEXT);
            existingFuel.setRefillcost(newCost.setScale(SCALE, RoundingMode.HALF_UP));
        }

        return fuelDetailRepo.save(existingFuel);
    }

    // Delete fuel record
    public void deleteFuelDetail(Long fuelId) {
        if (!fuelDetailRepo.existsById(fuelId)) {
            throw new RuntimeException("Fuel record not found with id: " + fuelId);
        }
        fuelDetailRepo.deleteById(fuelId);
    }

    // Get total fuel cost for a vehicle
    public BigDecimal getTotalFuelCost(String vehicleNumber) {
        Optional<Double> totalCost = fuelDetailRepo.findTotalFuelCostByVehicleNumber(vehicleNumber);
        return totalCost.map(cost -> BigDecimal.valueOf(cost).setScale(SCALE, RoundingMode.HALF_UP))
                .orElse(BigDecimal.ZERO.setScale(SCALE, RoundingMode.HALF_UP));
    }

    // Get total fuel quantity for a vehicle
    public BigDecimal getTotalFuelQuantity(String vehicleNumber) {
        Optional<Double> totalQuantity = fuelDetailRepo.findTotalFuelQuantityByVehicleNumber(vehicleNumber);
        return totalQuantity.map(quantity -> BigDecimal.valueOf(quantity).setScale(SCALE, RoundingMode.HALF_UP))
                .orElse(BigDecimal.ZERO.setScale(SCALE, RoundingMode.HALF_UP));
    }

    // Get average fuel price for a vehicle
    public BigDecimal getAverageFuelPrice(String vehicleNumber) {
        List<FuelDetail> fuelRecords = fuelDetailRepo.findByVehicleNumber(vehicleNumber); // CHANGE THIS LINE
        if (fuelRecords.isEmpty()) {
            return BigDecimal.ZERO.setScale(SCALE, RoundingMode.HALF_UP);
        }

        BigDecimal totalPrice = BigDecimal.ZERO;
        int count = 0;

        for (FuelDetail record : fuelRecords) {
            if (record.getFuelprice() != null) {
                totalPrice = totalPrice.add(record.getFuelprice());
                count++;
            }
        }

        return count > 0 ? totalPrice.divide(BigDecimal.valueOf(count), MATH_CONTEXT)
                .setScale(SCALE, RoundingMode.HALF_UP)
                : BigDecimal.ZERO.setScale(SCALE, RoundingMode.HALF_UP);
    }

    // Check if fuel record exists
    public boolean fuelRecordExists(Long fuelId) {
        return fuelDetailRepo.existsByFuelid(fuelId);
    }

    // Get fuel records by fuel type
    public List<FuelDetail> getFuelDetailsByFuelType(VehicleDetail.FuelType fuelType) {
        return fuelDetailRepo.findByFueltype(fuelType);
    }

    // Get fuel statistics for a vehicle
    public FuelStatistics getFuelStatistics(String vehicleNumber) {
        BigDecimal totalCost = getTotalFuelCost(vehicleNumber);
        BigDecimal totalQuantity = getTotalFuelQuantity(vehicleNumber);
        BigDecimal averagePrice = getAverageFuelPrice(vehicleNumber);

        return new FuelStatistics(totalCost, totalQuantity, averagePrice);
    }

    // Get fuel price history for a vehicle
    public List<Object[]> getFuelPriceHistory(String vehicleNumber) {
        return fuelDetailRepo.findByVehicleNumber(vehicleNumber).stream() // CHANGE THIS LINE
                .filter(record -> record.getRefilleddate() != null && record.getFuelprice() != null)
                .map(record -> new Object[] { record.getRefilleddate(), record.getFuelprice() })
                .toList();
    }

    // Calculate fuel efficiency (km per liter)
    public BigDecimal calculateFuelEfficiency(String vehicleNumber, BigDecimal totalDistance) {
        BigDecimal totalQuantity = getTotalFuelQuantity(vehicleNumber);

        if (totalQuantity.compareTo(BigDecimal.ZERO) == 0 ||
                totalDistance == null || totalDistance.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO.setScale(SCALE, RoundingMode.HALF_UP);
        }

        return totalDistance.divide(totalQuantity, MATH_CONTEXT)
                .setScale(SCALE, RoundingMode.HALF_UP);
    }

    // Statistics DTO
    public static class FuelStatistics {
        private final BigDecimal totalCost;
        private final BigDecimal totalQuantity;
        private final BigDecimal averagePrice;

        public FuelStatistics(BigDecimal totalCost, BigDecimal totalQuantity, BigDecimal averagePrice) {
            this.totalCost = totalCost;
            this.totalQuantity = totalQuantity;
            this.averagePrice = averagePrice;
        }

        public BigDecimal getTotalCost() {
            return totalCost;
        }

        public BigDecimal getTotalQuantity() {
            return totalQuantity;
        }

        public BigDecimal getAveragePrice() {
            return averagePrice;
        }

        public BigDecimal getFuelEfficiency(BigDecimal totalDistance) {
            if (totalQuantity == null || totalQuantity.compareTo(BigDecimal.ZERO) == 0 ||
                    totalDistance == null || totalDistance.compareTo(BigDecimal.ZERO) == 0) {
                return BigDecimal.ZERO.setScale(SCALE, RoundingMode.HALF_UP);
            }
            return totalDistance.divide(totalQuantity, MATH_CONTEXT)
                    .setScale(SCALE, RoundingMode.HALF_UP); // km per liter
        }
    }
}