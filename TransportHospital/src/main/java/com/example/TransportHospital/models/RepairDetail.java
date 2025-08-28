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
@Table(name = "repair_detail")
public class RepairDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long repairid;

    @ManyToOne
    @JoinColumn(name = "Vehiclenumber", referencedColumnName = "Vehiclenumber", nullable = false)
    private VehicleDetail vehiclenumber;

    @Enumerated(EnumType.STRING)
    private VehicleDetail.VehicleType vehicletype;

    private LocalDate repairdate;
    private BigDecimal mileageatrepair;
    private BigDecimal repaircost;
    private String repairdescription;
    private String serviceprovider;
    private String invoicenumber;
    private String remarks;

    public RepairDetail() {

    }

    public RepairDetail(Long repairid, VehicleDetail vehiclenumber, VehicleDetail.VehicleType vehicletype,
            LocalDate repairdate,
            BigDecimal mileageatrepair, BigDecimal repaircost, String repairdescription, String serviceprovider,
            String invoicenumber, String remarks) {
        super();
        this.repairid = repairid;
        this.vehiclenumber = vehiclenumber;
        this.vehicletype = vehicletype;
        this.repairdate = repairdate;
        this.mileageatrepair = mileageatrepair;
        this.repaircost = repaircost;
        this.repairdescription = repairdescription;
        this.serviceprovider = serviceprovider;
        this.invoicenumber = invoicenumber;
        this.remarks = remarks;
    }

    public Long getRepairid() {
        return repairid;
    }

    public void setRepairid(Long repairid) {
        this.repairid = repairid;
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

    public LocalDate getRepairdate() {
        return repairdate;
    }

    public void setRepairdate(LocalDate repairdate) {
        this.repairdate = repairdate;
    }

    public BigDecimal getMileageatrepair() {
        return mileageatrepair;
    }

    public void setMileageatrepair(BigDecimal mileageatrepair) {
        this.mileageatrepair = mileageatrepair;
    }

    public BigDecimal getRepaircost() {
        return repaircost;
    }

    public void setRepaircost(BigDecimal repaircost) {
        this.repaircost = repaircost;
    }

    public String getRepairdescription() {
        return repairdescription;
    }

    public void setRepairdescription(String repairdescription) {
        this.repairdescription = repairdescription;
    }

    public String getServiceprovider() {
        return serviceprovider;
    }

    public void setServiceprovider(String serviceprovider) {
        this.serviceprovider = serviceprovider;
    }

    public String getInvoicenumber() {
        return invoicenumber;
    }

    public void setInvoicenumber(String invoicenumber) {
        this.invoicenumber = invoicenumber;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
