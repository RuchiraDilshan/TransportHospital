package com.example.TransportHospital.models;

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
import jakarta.persistence.Table;

@Entity
@Table(name = "status_detail")
public class StatusDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statusid;

    @ManyToOne
    @JoinColumn(name = "vehiclenumber", referencedColumnName = "vehiclenumber", nullable = false)
    private VehicleDetail vehiclenumber;

    @Enumerated(EnumType.STRING)
    private VehicleDetail.VehicleType vehicletype;

    @Enumerated(EnumType.STRING)
    private vehiclestatus vehiclestatus;

    public enum vehiclestatus {
        RUNNING,
        REPAIR
    }

    @PrePersist
    @PreUpdate
    private void syncVehicleType() {
        if (vehiclenumber != null)
            this.vehicletype = vehiclenumber.getVehicleType();
    }

    public StatusDetail() {
        super();
    }

    public StatusDetail(Long statusid, VehicleDetail vehiclenumber, VehicleDetail.VehicleType vehicletype,
            vehiclestatus vehiclestatus) {
        super();
        this.statusid = statusid;
        this.vehiclenumber = vehiclenumber;
        this.vehicletype = vehicletype;
        this.vehiclestatus = vehiclestatus;
    }

    public Long getStatusid() {
        return statusid;
    }

    public void setStatusid(Long statusid) {
        this.statusid = statusid;
    }

    public VehicleDetail getVehiclenumber() {
        return vehiclenumber;
    }

    public void setVehiclenumber(VehicleDetail vehiclenumber) {
        this.vehiclenumber = vehiclenumber;
    }

    public VehicleDetail.VehicleType getVehicletype() {
        return vehicletype;
    }

    public void setVehicletype(VehicleDetail.VehicleType vehicletype) {
        this.vehicletype = vehicletype;
    }

    public vehiclestatus getStatus() {
        return vehiclestatus;
    }

    public void setStatus(vehiclestatus vehiclestatus) {
        this.vehiclestatus = vehiclestatus;
    }

}
