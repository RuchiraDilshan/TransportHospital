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
    private Long assistant_id;

    private String assistant_name;
    private String contact_number;

    @Column(length = 10, nullable = false)
    private String assistant_contact_number;

    public driver_assistant_detail() {
        super();
    }

    public driver_assistant_detail(Long assistant_id, String assistant_name, String contact_number) {
        super();
        this.assistant_id = assistant_id;
        this.assistant_name = assistant_name;
        this.contact_number = contact_number;
    }

    public Long getAssistant_id() {
        return assistant_id;
    }

    public void setAssistant_id(Long assistant_id) {
        this.assistant_id = assistant_id;
    }

    public String getAssistant_name() {
        return assistant_name;
    }

    public void setAssistant_name(String assistant_name) {
        this.assistant_name = assistant_name;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

}