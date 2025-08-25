package com.example.TransportHospital.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;

@Entity
public class vehicle_detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String vehicle_number;

    @Enumerated(EnumType.STRING)
    private vehicletype type;

    @Enumerated(EnumType.STRING)
    private fueltype fuel_type;

    private String make;

    public enum vehicletype {
        AMBULANCE,
        VAN,
        LORRY,
        BUS,
        CAR,
        CAB,
        THREEWHEELER,
        BIKE
    }

    public enum fueltype {
        PETROL,
        DIESEL
    }

    public vehicle_detail() {

    }

    public vehicle_detail(Long id, String vehicle_number, vehicletype type, fueltype fuel_type, String make) {

        this.id = id;
        this.vehicle_number = vehicle_number;
        this.type = type;
        this.fuel_type = fuel_type;
        this.make = make;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVehicle_number() {
        return vehicle_number;
    }

    public void setVehicle_number(String vehicle_number) {
        this.vehicle_number = vehicle_number;
    }

    public vehicletype getType() {
        return type;
    }

    public void setType(vehicletype type) {
        this.type = type;
    }

    public fueltype getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(fueltype fuel_type) {
        this.fuel_type = fuel_type;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

}
