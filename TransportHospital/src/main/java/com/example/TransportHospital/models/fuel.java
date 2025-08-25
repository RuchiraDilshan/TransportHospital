package com.example.TransportHospital.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.TransportHospital.models.vehicle.fueltype;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Transient;

@Entity
public class fuel {
    @Id
    private String fuelid;

    @ManyToOne
    @JoinColumn(name = "vehiclenumber", referencedColumnName = "vehiclenumber")
    private vehicle vehicle;

    private LocalDate refilled_date;
    private BigDecimal refilled_quantity;
    private BigDecimal refill_cost;
    private BigDecimal fuel_price;

    @Enumerated(EnumType.STRING)
    private vehicle.fueltype fuel_type;

    private BigDecimal odometerreading;

    @PrePersist
    @PreUpdate
    private void syncFuelType() {
        if (vehicle != null)
            this.fuel_type = vehicle.getFuel_type();
    }

    public fuel() {

    }

    public fuel(String fuelid, com.example.TransportHospital.models.vehicle vehicle, LocalDate refilled_date,
            BigDecimal refilled_quantity, BigDecimal refill_cost, BigDecimal fuel_price, fueltype fuel_type,
            BigDecimal odometerreading) {

        this.fuelid = fuelid;
        this.vehicle = vehicle;
        this.refilled_date = refilled_date;
        this.refilled_quantity = refilled_quantity;
        this.refill_cost = refill_cost;
        this.fuel_price = fuel_price;
        this.fuel_type = fuel_type;
        this.odometerreading = odometerreading;
    }

    public String getFuelid() {
        return fuelid;
    }

    public void setFuelid(String fuelid) {
        this.fuelid = fuelid;
    }

    public vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDate getRefilled_date() {
        return refilled_date;
    }

    public void setRefilled_date(LocalDate refilled_date) {
        this.refilled_date = refilled_date;
    }

    public BigDecimal getRefilled_quantity() {
        return refilled_quantity;
    }

    public void setRefilled_quantity(BigDecimal refilled_quantity) {
        this.refilled_quantity = refilled_quantity;
    }

    public BigDecimal getRefill_cost() {
        return refill_cost;
    }

    public void setRefill_cost(BigDecimal refill_cost) {
        this.refill_cost = refill_cost;
    }

    public BigDecimal getFuel_price() {
        return fuel_price;
    }

    public void setFuel_price(BigDecimal fuel_price) {
        this.fuel_price = fuel_price;
    }

    public vehicle.fueltype getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(vehicle.fueltype fuel_type) {
        this.fuel_type = fuel_type;
    }

    public BigDecimal getOdometerreading() {
        return odometerreading;
    }

    public void setOdometerreading(BigDecimal odometerreading) {
        this.odometerreading = odometerreading;
    }

    @Transient
    public String getVehiclenumber() {
        return vehicle != null ? vehicle.getVehiclenumber() : null;
    }

}
