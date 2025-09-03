package com.example.TransportHospital.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Transient;
import jakarta.persistence.Table;

@Entity
@Table(name = "fuel_detail")
public class FuelDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fuelid;

    @ManyToOne
    @JoinColumn(name = "vehiclenumber")
    private VehicleDetail vehicle;

    private LocalDate refilleddate;
    private BigDecimal refilledquantity;
    private BigDecimal refillcost;
    private BigDecimal fuelprice;

    @Enumerated(EnumType.STRING)
    private VehicleDetail.FuelType fueltype;

    private BigDecimal odometerreading;

    @PrePersist
    @PreUpdate
    private void syncFuelType() {
        if (vehicle != null)
            this.fueltype = vehicle.getFuelType();
    }

    public FuelDetail() {

    }

    public FuelDetail(Long fuelid, VehicleDetail vehicle,
            LocalDate refilleddate,
            BigDecimal refilledquantity, BigDecimal refillcost, BigDecimal fuelprice, VehicleDetail.FuelType fueltype,
            BigDecimal odometerreading) {

        this.fuelid = fuelid;
        this.vehicle = vehicle;
        this.refilleddate = refilleddate;
        this.refilledquantity = refilledquantity;
        this.refillcost = refillcost;
        this.fuelprice = fuelprice;
        this.fueltype = fueltype;
        this.odometerreading = odometerreading;
    }

    public Long getFuelid() {
        return fuelid;
    }

    public void setFuelid(Long fuelid) {
        this.fuelid = fuelid;
    }

    public VehicleDetail getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleDetail vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDate getRefilleddate() {
        return refilleddate;
    }

    public void setRefilleddate(LocalDate refilleddate) {
        this.refilleddate = refilleddate;
    }

    public BigDecimal getRefilledquantity() {
        return refilledquantity;
    }

    public void setRefilledquantity(BigDecimal refilledquantity) {
        this.refilledquantity = refilledquantity;
    }

    public BigDecimal getRefillcost() {
        return refillcost;
    }

    public void setRefillcost(BigDecimal refillcost) {
        this.refillcost = refillcost;
    }

    public BigDecimal getFuelprice() {
        return fuelprice;
    }

    public void setFuelprice(BigDecimal fuelprice) {
        this.fuelprice = fuelprice;
    }

    public VehicleDetail.FuelType getFueltype() {
        return fueltype;
    }

    public void setFueltype(VehicleDetail.FuelType fueltype) {
        this.fueltype = fueltype;
    }

    public BigDecimal getOdometerreading() {
        return odometerreading;
    }

    public void setOdometerreading(BigDecimal odometerreading) {
        this.odometerreading = odometerreading;
    }

    @Transient
    public String getVehicleNumberString() {
        return vehicle != null ? vehicle.getVehicleNumber() : null;
    }

}
