package com.example.TransportHospital.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;

@Entity
public class vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String vehiclenumber;

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

    public vehicle() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVehiclenumber() {
        return vehiclenumber;
    }

    public void setVehiclenumber(String vehiclenumber) {
        this.vehiclenumber = vehiclenumber;
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

    public vehicle(Long id, String vehiclenumber, vehicletype type, fueltype fuel_type, String make) {
        super();
        this.id = id;
        this.vehiclenumber = vehiclenumber;
        this.type = type;
        this.fuel_type = fuel_type;
        this.make = make;
    }

}
