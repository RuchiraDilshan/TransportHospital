package com.example.TransportHospital.repository;

import com.example.TransportHospital.models.FuelDetail;
import com.example.TransportHospital.models.VehicleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FuelDetailRepo extends JpaRepository<FuelDetail, Long> {

    // Find by vehicle number (using the actual field name 'vehiclenumber')
    @Query("SELECT f FROM FuelDetail f JOIN f.vehicle v WHERE v.vehiclenumber = :vehicleNumber")
    List<FuelDetail> findByVehicleNumber(@Param("vehicleNumber") String vehicleNumber);

    // Find by date
    List<FuelDetail> findByRefilleddate(LocalDate date);

    // Find by vehicle number and date
    @Query("SELECT f FROM FuelDetail f JOIN f.vehicle v WHERE v.vehiclenumber = :vehicleNumber AND f.refilleddate = :date")
    List<FuelDetail> findByVehicleNumberAndRefilleddate(@Param("vehicleNumber") String vehicleNumber,
            @Param("date") LocalDate date);

    // Find by date range
    List<FuelDetail> findByRefilleddateBetween(LocalDate startDate, LocalDate endDate);

    // Find latest record for a vehicle
    @Query("SELECT f FROM FuelDetail f JOIN f.vehicle v WHERE v.vehiclenumber = :vehicleNumber ORDER BY f.refilleddate DESC")
    List<FuelDetail> findTopByVehicleNumberOrderByRefilleddateDesc(@Param("vehicleNumber") String vehicleNumber);

    // Find total fuel cost
    @Query("SELECT SUM(f.refillcost) FROM FuelDetail f JOIN f.vehicle v WHERE v.vehiclenumber = :vehicleNumber")
    Optional<Double> findTotalFuelCostByVehicleNumber(@Param("vehicleNumber") String vehicleNumber);

    // Find total fuel quantity
    @Query("SELECT SUM(f.refilledquantity) FROM FuelDetail f JOIN f.vehicle v WHERE v.vehiclenumber = :vehicleNumber")
    Optional<Double> findTotalFuelQuantityByVehicleNumber(@Param("vehicleNumber") String vehicleNumber);

    // Find by fuel type
    List<FuelDetail> findByFueltype(VehicleDetail.FuelType fuelType);

    // Check if exists by fuel ID
    boolean existsByFuelid(Long fuelId);

}