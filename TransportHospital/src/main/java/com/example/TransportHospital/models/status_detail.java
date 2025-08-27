package com.example.TransportHospital.models;

import com.example.TransportHospital.models.vehicle_detail.vehicletype;

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

@Entity
public class status_detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long status_id;

    @ManyToOne
    @JoinColumn(name = "vehicle_number", referencedColumnName = "vehicle_number", nullable = false)
    private vehicle_detail vehicle_number;

    @Enumerated(EnumType.STRING)
    private vehicle_detail.vehicletype vehicle_type;

    @Enumerated(EnumType.STRING)
    private vehiclestatus vehicle_status;

    public enum vehiclestatus {
        RUNNING,
        REPAIR
    }

    @PrePersist
    @PreUpdate
    private void syncVehicleType() {
        if (vehicle_number != null)
            this.vehicle_type = vehicle_number.getVehicle_type();
    }

    public status_detail() {
        super();
    }

    public status_detail(Long status_id, vehicle_detail vehicle_number, vehicletype vehicle_type,
            vehiclestatus vehicle_status) {
        super();
        this.status_id = status_id;
        this.vehicle_number = vehicle_number;
        this.vehicle_type = vehicle_type;
        this.vehicle_status = vehicle_status;
    }

    public Long getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Long status_id) {
        this.status_id = status_id;
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

    public vehiclestatus getStatus() {
        return vehicle_status;
    }

    public void setStatus(vehiclestatus vehicle_status) {
        this.vehicle_status = vehicle_status;
    }

}
