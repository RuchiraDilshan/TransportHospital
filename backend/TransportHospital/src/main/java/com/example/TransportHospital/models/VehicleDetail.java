package com.example.TransportHospital.models;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicle_detail")
public class VehicleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleid;

    @Column(unique = true, nullable = false)
    private String vehiclenumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleType vehicletype;

    private String make;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FuelType fueltype;

    public enum VehicleType {
        AMBULANCE,
        VAN,
        LORRY,
        BUS,
        CAR,
        CAB,
        THREEWHEELER,
        BIKE
    }

    public enum FuelType {
        PETROL,
        DIESEL
    }

    public void setVehicletype(VehicleType vehicletype) {
        this.vehicletype = vehicletype;
        if (this.insurancedetail != null)
            this.insurancedetail.setVehicleType(vehicletype);
    }

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<FuelDetail> fueldetail;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<ServiceDetail> servicedetail;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<RepairDetail> repairdetail;

    @OneToOne(mappedBy = "vehicle")
    private StatusDetail statusdetail;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<ImageDetail> imagedetail;

    @OneToOne(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private InsuranceDetail insurancedetail;

    // constructors
    public VehicleDetail() {

    }

    public VehicleDetail(Long vehicleid, String vehiclenumber, VehicleType vehicletype, FuelType fueltype,
            String make) {

        this.vehicleid = vehicleid;
        this.vehiclenumber = vehiclenumber;
        this.vehicletype = vehicletype;
        this.fueltype = fueltype;
        this.make = make;
    }

    // Getters and Setters
    public Long getVehicleId() {
        return vehicleid;
    }

    public void setVehicleId(Long vehicleid) {
        this.vehicleid = vehicleid;
    }

    public String getVehicleNumber() {
        return vehiclenumber;
    }

    public void setVehicleNumber(String vehiclenumber) {
        this.vehiclenumber = vehiclenumber;
    }

    public VehicleType getVehicleType() {
        return vehicletype;
    }

    public void setVehicleType(VehicleType vehicletype) {
        this.vehicletype = vehicletype;
    }

    public FuelType getFuelType() {
        return fueltype;
    }

    public void setFuelType(FuelType fueltype) {
        this.fueltype = fueltype;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public InsuranceDetail getInsurance() {
        return insurancedetail;
    }

    public void setInsurance(InsuranceDetail insurance) {
        this.insurancedetail = insurance;
        if (insurance != null) {
            insurance.setVehicleINumber(this);
            insurance.setVehicleType(this.vehicletype);
        }
    }

    @Override
    public String toString() {
        return "VehicleDetail [vehicleid=" + vehicleid + ", vehiclenumber=" + vehiclenumber + ", vehicletype="
                + vehicletype + ", make=" + make + ", fueltype=" + fueltype + "]";
    }

}
