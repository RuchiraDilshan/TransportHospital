package com.example.TransportHospital.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.TransportHospital.models.vehicle_detail.vehicletype;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class service_detail {
    @Id
    @Column(unique = true, nullable = false)
    private String service_id;

    @ManyToOne
    @JoinColumn(name = "vehicle_number", referencedColumnName = "vehicle_number", nullable = false)
    private vehicle_detail vehicle_number;

    @Enumerated(EnumType.STRING)
    private vehicle_detail.vehicletype vehicle_type;

    private LocalDate last_service_date;

    private BigDecimal current_mileage;

    private LocalDate sent_date;

    private LocalDate recived_date;

    private LocalDate next_service_date;

    private BigDecimal next_service_mileage;

    private BigDecimal service_cost;

    private String comments;

    public service_detail() {
        super();
    }

    public service_detail(String service_id, vehicle_detail vehicle_number, vehicletype vehicle_type,
            LocalDate last_service_date, BigDecimal current_mileage, LocalDate sent_date, LocalDate recived_date,
            LocalDate next_service_date, BigDecimal next_service_mileage, BigDecimal service_cost, String comments) {
        super();
        this.service_id = service_id;
        this.vehicle_number = vehicle_number;
        this.vehicle_type = vehicle_type;
        this.last_service_date = last_service_date;
        this.current_mileage = current_mileage;
        this.sent_date = sent_date;
        this.recived_date = recived_date;
        this.next_service_date = next_service_date;
        this.next_service_mileage = next_service_mileage;
        this.service_cost = service_cost;
        this.comments = comments;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
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

    public LocalDate getLast_service_date() {
        return last_service_date;
    }

    public void setLast_service_date(LocalDate last_service_date) {
        this.last_service_date = last_service_date;
    }

    public BigDecimal getCurrent_mileage() {
        return current_mileage;
    }

    public void setCurrent_mileage(BigDecimal current_mileage) {
        this.current_mileage = current_mileage;
    }

    public LocalDate getSent_date() {
        return sent_date;
    }

    public void setSent_date(LocalDate sent_date) {
        this.sent_date = sent_date;
    }

    public LocalDate getRecived_date() {
        return recived_date;
    }

    public void setRecived_date(LocalDate recived_date) {
        this.recived_date = recived_date;
    }

    public LocalDate getNext_service_date() {
        return next_service_date;
    }

    public void setNext_service_date(LocalDate next_service_date) {
        this.next_service_date = next_service_date;
    }

    public BigDecimal getNext_service_mileage() {
        return next_service_mileage;
    }

    public void setNext_service_mileage(BigDecimal next_service_mileage) {
        this.next_service_mileage = next_service_mileage;
    }

    public BigDecimal getService_cost() {
        return service_cost;
    }

    public void setService_cost(BigDecimal service_cost) {
        this.service_cost = service_cost;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}
