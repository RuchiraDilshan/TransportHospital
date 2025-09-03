package com.example.TransportHospital.dto;

public class VehicleDetailDto {
    private String vehiclenumber;
    private String vehicletype;
    private String make;
    private String fueltype;

    // constructors

    public VehicleDetailDto() {
        super();
    }

    public VehicleDetailDto(String vehiclenumber, String vehicletype, String make, String fueltype) {
        super();
        this.vehiclenumber = vehiclenumber;
        this.vehicletype = vehicletype;
        this.make = make;
        this.fueltype = fueltype;
    }

    public String getVehiclenumber() {
        return vehiclenumber;
    }

    public String getVehicletype() {
        return vehicletype;
    }

    public String getMake() {
        return make;
    }

    public String getFueltype() {
        return fueltype;
    }

    // getters and setters

    public void setVehiclenumber(String vehiclenumber) {
        this.vehiclenumber = vehiclenumber;
    }

    public void setVehicletype(String vehicletype) {
        this.vehicletype = vehicletype;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setFueltype(String fueltype) {
        this.fueltype = fueltype;
    }

}
