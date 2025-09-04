package com.example.TransportHospital.dto;

import java.time.LocalDate;

public class FuelDetailDto {
    private String vehiclenumber; // from VehicleDetail
    private String vehicletype; // from VehicleDetail
    private LocalDate refilleddate;
    private double refilledquantity;
    private double fuelprice;
    private double odometerreading;

    // constructors
    public FuelDetailDto() {
        super();
    }

    public FuelDetailDto(String vehiclenumber, String vehicletype,
            LocalDate refilleddate, double refilledquantity,
            double fuelprice, double odometerreading) {
        super();
        this.vehiclenumber = vehiclenumber;
        this.vehicletype = vehicletype;
        this.refilleddate = refilleddate;
        this.refilledquantity = refilledquantity;
        this.fuelprice = fuelprice;
        this.odometerreading = odometerreading;
    }

    // getters
    public String getVehiclenumber() {
        return vehiclenumber;
    }

    public String getVehicletype() {
        return vehicletype;
    }

    public LocalDate getRefilleddate() {
        return refilleddate;
    }

    public double getRefilledquantity() {
        return refilledquantity;
    }

    public double getFuelprice() {
        return fuelprice;
    }

    public double getOdometerreading() {
        return odometerreading;
    }

    // setters
    public void setVehiclenumber(String vehiclenumber) {
        this.vehiclenumber = vehiclenumber;
    }

    public void setVehicletype(String vehicletype) {
        this.vehicletype = vehicletype;
    }

    public void setRefilleddate(LocalDate refilleddate) {
        this.refilleddate = refilleddate;
    }

    public void setRefilledquantity(double refilledquantity) {
        this.refilledquantity = refilledquantity;
    }

    public void setFuelprice(double fuelprice) {
        this.fuelprice = fuelprice;
    }

    public void setOdometerreading(double odometerreading) {
        this.odometerreading = odometerreading;
    }
}
