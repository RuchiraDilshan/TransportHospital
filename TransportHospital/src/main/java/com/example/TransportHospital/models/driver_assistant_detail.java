package com.example.TransportHospital.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class driver_assistant_detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driver_assistant_id;

    private String driver_assistant_name;

    @Column(length = 10, nullable = false)
    private String driver_assistant_contact_number;

    public driver_assistant_detail() {
        super();
    }

    public driver_assistant_detail(Long driver_assistant_id, String driver_assistant_name,
            String driver_assistant_contact_number) {
        super();
        this.driver_assistant_id = driver_assistant_id;
        this.driver_assistant_name = driver_assistant_name;
        this.driver_assistant_contact_number = driver_assistant_contact_number;
    }

    public Long getDriver_assistant_id() {
        return driver_assistant_id;
    }

    public void setDriver_assistant_id(Long driver_assistant_id) {
        this.driver_assistant_id = driver_assistant_id;
    }

    public String getDriver_assistant_name() {
        return driver_assistant_name;
    }

    public void setDriver_assistant_name(String driver_assistant_name) {
        this.driver_assistant_name = driver_assistant_name;
    }

    public String getDriver_assistant_contact_number() {
        return driver_assistant_contact_number;
    }

    public void setDriver_assistant_contact_number(String driver_assistant_contact_number) {
        this.driver_assistant_contact_number = driver_assistant_contact_number;
    }

}