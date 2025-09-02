package com.example.TransportHospital.models;

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
@Table(name = "driver_detail")
public class DriverDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driverid;

    @ManyToOne
    @JoinColumn(name = "driverassistantid", referencedColumnName = "driverassistantid", nullable = false)
    private DriverAssistantDetail driverassistantid;

    private String drivername;

    private LocalDate dateofbirth;

    @Enumerated(EnumType.STRING)
    private Category licensecategory;

    private LocalDate licenseexpiredate;

    @Enumerated(EnumType.STRING)
    private ValidityStatus validitystatus;

    @ManyToOne
    @JoinColumn(name = "vehiclenumber", referencedColumnName = "vehiclenumber", nullable = true)
    private VehicleDetail vehiclenumber;

    public enum Category {
        LIGHTVEHICLE,
        HEAVYVEHICLE,
        BOTH
    }

    public enum ValidityStatus {
        VALID,
        EXPIRED
    }

    public DriverDetail() {
        super();
    }

    public DriverDetail(Long driverid, DriverAssistantDetail driverassistantid, String drivername,
            LocalDate dateofbirth, Category licensecategory,
            LocalDate licenseexpiredate,
            ValidityStatus validitystatus) {
        super();
        this.driverid = driverid;
        this.driverassistantid = driverassistantid;
        this.drivername = drivername;
        this.dateofbirth = dateofbirth;
        this.licensecategory = licensecategory;
        this.licenseexpiredate = licenseexpiredate;
        this.validitystatus = validitystatus;
    }

    private boolean isExpired() {
        return licenseexpiredate != null && licenseexpiredate.isBefore(LocalDate.now());
    }

    public Long getDriverid() {
        return driverid;
    }

    public void setDriverid(Long driverid) {
        this.driverid = driverid;
    }

    public DriverAssistantDetail getDriverassistantid() {
        return driverassistantid;
    }

    public void setDriverassistantid(DriverAssistantDetail driverassistantid) {
        this.driverassistantid = driverassistantid;
    }

    public String getDrivername() {
        return drivername;
    }

    public void setDrivername(String drivername) {
        this.drivername = drivername;
    }

    public LocalDate getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(LocalDate dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public Category getLicensecategory() {
        return licensecategory;
    }

    public void setLicensecategory(Category licensecategory) {
        this.licensecategory = licensecategory;

    }

    public ValidityStatus getValiditystatus() {
        return isExpired() ? ValidityStatus.EXPIRED : ValidityStatus.VALID;
    }

    public void setValiditystatus(ValidityStatus ignored) {

    }

}
