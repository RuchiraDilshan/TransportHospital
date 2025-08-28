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
    private VehicleType vehicletype;

    private String make;

    @Enumerated(EnumType.STRING)
    private FuelType fueltype;

    @OneToOne(mappedBy = "vehicleid", cascade = CascadeType.ALL, orphanRemoval = true)
    private InsuranceDetail insurancedetail;

    @ManyToOne
    @JoinColumn(name = "driverid", referencedColumnName = "driverid", nullable = false)
    private DriverDetail driverid;

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

    @PrePersist
    private void autoCreateInsurance() {
        if (this.insurancedetail == null) {
            InsuranceDetail ins = new InsuranceDetail();
            ins.setVehicleId(this);
            ins.setVehicleType(this.vehicletype);
            this.insurancedetail = ins;
        }
    }

    public void setVehicletype(VehicleType vehicletype) {
        this.vehicletype = vehicletype;
        if (this.insurancedetail != null)
            this.insurancedetail.setVehicleType(vehicletype);
    }

    @OneToMany(mappedBy = "vehiclenumber", cascade = CascadeType.ALL)
    private List<FuelDetail> fueldetail;

    @OneToMany(mappedBy = "vehiclenumber", cascade = CascadeType.ALL)
    private List<ServiceDetail> servicedetail;

    @OneToMany(mappedBy = "vehiclenumber", cascade = CascadeType.ALL)
    private List<RepairDetail> repairdetail;

    @OneToOne(mappedBy = "vehiclenumber")
    private StatusDetail statusdetail;

    @OneToMany(mappedBy = "vehiclenumber", cascade = CascadeType.ALL)
    private List<ImageDetail> imagedetail;

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
            insurance.setVehicleId(this);
            insurance.setVehicleType(this.vehicletype);
        }
    }

}
