package com.example.TransportHospital.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.TransportHospital.models.vehicle_detail.vehicletype;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class repair_detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long repair_id;

    @ManyToOne
    @JoinColumn(name = "Vehicle_number", referencedColumnName = "Vehicle_number", nullable = false)
    private vehicle_detail vehicle_number;

    @Enumerated(EnumType.STRING)
    private vehicle_detail.vehicletype vehicle_type;

    private LocalDate repair_date;
    private BigDecimal mileageat_repair;
    private BigDecimal repair_cost;
    private String repair_description;
    private String service_provider;
    private String invoice_number;
    private String remarks;

    public repair_detail() {

    }

    public repair_detail(Long repair_id, vehicle_detail vehicle_number, vehicletype vehicle_type, LocalDate repair_date,
            BigDecimal mileageat_repair, BigDecimal repair_cost, String repair_description, String service_provider,
            String invoice_number, String remarks) {
        super();
        this.repair_id = repair_id;
        this.vehicle_number = vehicle_number;
        this.vehicle_type = vehicle_type;
        this.repair_date = repair_date;
        this.mileageat_repair = mileageat_repair;
        this.repair_cost = repair_cost;
        this.repair_description = repair_description;
        this.service_provider = service_provider;
        this.invoice_number = invoice_number;
        this.remarks = remarks;
    }

    public Long getRepair_id() {
        return repair_id;
    }

    public void setRepair_id(Long repair_id) {
        this.repair_id = repair_id;
    }

    public vehicle_detail getVehicle_number() {
        return vehicle_number;
    }

    public void setVehicle_number(vehicle_detail vehicle_number) {
        this.vehicle_number = vehicle_number;
    }

    public vehicle_detail.vehicletype getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(vehicle_detail.vehicletype vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public LocalDate getRepair_date() {
        return repair_date;
    }

    public void setRepair_date(LocalDate repair_date) {
        this.repair_date = repair_date;
    }

    public BigDecimal getMileageat_repair() {
        return mileageat_repair;
    }

    public void setMileageat_repair(BigDecimal mileageat_repair) {
        this.mileageat_repair = mileageat_repair;
    }

    public BigDecimal getRepair_cost() {
        return repair_cost;
    }

    public void setRepair_cost(BigDecimal repair_cost) {
        this.repair_cost = repair_cost;
    }

    public String getRepair_description() {
        return repair_description;
    }

    public void setRepair_description(String repair_description) {
        this.repair_description = repair_description;
    }

    public String getService_provider() {
        return service_provider;
    }

    public void setService_provider(String service_provider) {
        this.service_provider = service_provider;
    }

    public String getInvoice_number() {
        return invoice_number;
    }

    public void setInvoice_number(String invoice_number) {
        this.invoice_number = invoice_number;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
