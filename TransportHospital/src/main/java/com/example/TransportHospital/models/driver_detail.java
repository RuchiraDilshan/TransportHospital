package com.example.TransportHospital.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class driver_detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driver_id;
    @ManyToOne
    @JoinColumn(name = "assistant_id", referencedColumnName = "assistant_id", nullable = false)
    private driver_assistant_detail assistant_id;
    private String driver_name;

    @Column(unique = true, nullable = true)
    private String driver_license_number;
    private LocalDate date_of_birth;

    @Enumerated(EnumType.STRING)
    private category license_category;

    private LocalDate license_expire_date;

    @Enumerated(EnumType.STRING)
    private validitystatus validity_status;

    public enum category {
        LIGHTVEHICLE,
        HEAVYVEHICLE,
        BOTH
    }

    public enum validitystatus {
        VALID,
        EXPIRED
    }

    public driver_detail() {
        super();
    }

    public driver_detail(Long driver_id, driver_assistant_detail assistant_id, String driver_name,
            String driver_license_number, LocalDate date_of_birth, category license_category,
            LocalDate license_expire_date,
            validitystatus validity_status) {
        super();
        this.driver_id = driver_id;
        this.assistant_id = assistant_id;
        this.driver_name = driver_name;
        this.driver_license_number = driver_license_number;
        this.date_of_birth = date_of_birth;
        this.license_category = license_category;
        this.license_expire_date = license_expire_date;
        this.validity_status = validity_status;
    }

    private boolean isExpired() {
        return license_expire_date != null && license_expire_date.isBefore(LocalDate.now());
    }

    public Long getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(Long driver_id) {
        this.driver_id = driver_id;
    }

    public driver_assistant_detail getAssistant_id() {
        return assistant_id;
    }

    public void setAssistant_id(driver_assistant_detail assistant_id) {
        this.assistant_id = assistant_id;
    }

    public String getDriver_name() {
        return driver_name;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }

    public String getDriver_license_number() {
        return driver_license_number;
    }

    public void setDriver_license_number(String driver_license_number) {
        this.driver_license_number = driver_license_number;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public category getLicense_category() {
        return license_category;
    }

    public void setLicense_category(category license_category) {
        this.license_category = license_category;

    }

    public validitystatus getValidity_status() {
        return isExpired() ? validitystatus.EXPIRED : validitystatus.VALID;
    }

    public void setValidity_status(validitystatus ignored) {

    }

}
