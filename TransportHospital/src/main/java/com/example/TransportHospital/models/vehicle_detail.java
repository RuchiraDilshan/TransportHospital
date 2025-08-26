package com.example.TransportHospital.models;

import jakarta.persistence.*;

@Entity
public class vehicle_detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String vehicle_number;

    @Enumerated(EnumType.STRING)
    private vehicletype vehicle_type;

    private String make;

    @Enumerated(EnumType.STRING)
    private fueltype fuel_type;

    @OneToOne(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    private insurance_detail insurance_detail;

    @ManyToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "driver_id", nullable = false)
    private driver_detail driver_id;

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

    @PrePersist
    private void autoCreateInsurance() {
        if (this.insurance_detail == null) {
            insurance_detail ins = new insurance_detail();
            ins.setVehicle_number(this);
            ins.setVehicle_type(this.vehicle_type);
            this.insurance_detail = ins;
        }
    }

    public void setvehicle_type(vehicletype vehicle_type) {
        this.vehicle_type = vehicle_type;
        if (this.insurance_detail != null)
            this.insurance_detail.setVehicle_type(vehicle_type);
    }

    public vehicle_detail() {

    }

    public vehicle_detail(Long id, String vehicle_number, vehicletype vehicle_type, fueltype fuel_type, String make) {

        this.id = id;
        this.vehicle_number = vehicle_number;
        this.vehicle_type = vehicle_type;
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

    public vehicletype getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(vehicletype vehicle_type) {
        this.vehicle_type = vehicle_type;
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

    public insurance_detail getInsurance() {
        return insurance_detail;
    }

    public void setInsurance(insurance_detail insurance) {
        this.insurance_detail = insurance;
        if (insurance != null) {
            insurance.setVehicle_number(this);
            insurance.setVehicle_type(this.vehicle_type);
        }
    }

}
