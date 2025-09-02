package com.example.TransportHospital.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "service_detail")
public class ServiceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceid;

    @ManyToOne
    @JoinColumn(name = "vehiclenumber", referencedColumnName = "vehiclenumber", nullable = false)
    private VehicleDetail vehiclenumber;

    @Enumerated(EnumType.STRING)
    private VehicleDetail.VehicleType vehicletype;

    private BigDecimal currentmileage;

    private LocalDate sentdate;

    private LocalDate reciveddate;

    private LocalDate nextservicedate;

    private BigDecimal nextservicemileage;

    private BigDecimal servicecost;

    private String comments;

    public ServiceDetail() {
        super();
    }

    public ServiceDetail(Long serviceid, VehicleDetail vehiclenumber, VehicleDetail.VehicleType vehicletype,
            BigDecimal currentmileage, LocalDate sentdate, LocalDate reciveddate,
            LocalDate nextservicedate, BigDecimal nextservicemileage, BigDecimal servicecost, String comments) {
        super();
        this.serviceid = serviceid;
        this.vehiclenumber = vehiclenumber;
        this.vehicletype = vehicletype;
        this.currentmileage = currentmileage;
        this.sentdate = sentdate;
        this.reciveddate = reciveddate;
        this.nextservicedate = nextservicedate;
        this.nextservicemileage = nextservicemileage;
        this.servicecost = servicecost;
        this.comments = comments;
    }

    public Long getServiceid() {
        return serviceid;
    }

    public void setServiceid(Long serviceid) {
        this.serviceid = serviceid;
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

    public BigDecimal getCurrentmileage() {
        return currentmileage;
    }

    public void setCurrentmileage(BigDecimal currentmileage) {
        this.currentmileage = currentmileage;
    }

    public LocalDate getSentdate() {
        return sentdate;
    }

    public void setSentdate(LocalDate sentdate) {
        this.sentdate = sentdate;
    }

    public LocalDate getReciveddate() {
        return reciveddate;
    }

    public void setReciveddate(LocalDate reciveddate) {
        this.reciveddate = reciveddate;
    }

    public LocalDate getNextservicedate() {
        return nextservicedate;
    }

    public void setNextservicedate(LocalDate nextservicedate) {
        this.nextservicedate = nextservicedate;
    }

    public BigDecimal getNextservicemileage() {
        return nextservicemileage;
    }

    public void setNextservicemileage(BigDecimal nextservicemileage) {
        this.nextservicemileage = nextservicemileage;
    }

    public BigDecimal getServicecost() {
        return servicecost;
    }

    public void setServicecost(BigDecimal servicecost) {
        this.servicecost = servicecost;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}
