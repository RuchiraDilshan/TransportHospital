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
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Transient;

@Entity
public class insurance_detail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long insurance_id;

	@JoinColumn(name = "vehicle_number", referencedColumnName = "vehicle_number", nullable = false)
	private vehicle_detail vehicle_number;

	@Enumerated(EnumType.STRING)
	private vehicle_detail.vehicletype vehicle_type;

	private LocalDate date_from;

	private LocalDate Date_valid_to;

	private BigDecimal insurance_cost;

	private LocalDate lisence_date_from;

	private LocalDate lisence_date_to;

	private BigDecimal lisence_cost;

	@Enumerated(EnumType.STRING)
	private insurancestatus lisence_status;

	@Enumerated(EnumType.STRING)
	private insurancestatus insurance_status;

	@OneToOne
	@JoinColumn(name = "vehicle_id", referencedColumnName = "id", nullable = false, unique = true)
	private vehicle_detail vehicle;

	public enum insurancestatus {

		VALID,
		EXPIRED

	}

	@PrePersist
	@PreUpdate
	private void syncAndCompuute() {
		if (vehicle_number != null)
			this.vehicle_type = vehicle_number.getVehicle_type();

	}

	private boolean isExpired() {
		return Date_valid_to != null && Date_valid_to.isBefore(LocalDate.now());

	}

	private boolean isExpiredLisence() {
		return lisence_date_to != null && lisence_date_to.isBefore(LocalDate.now());
	}

	public insurance_detail() {
		super();
	}

	public insurance_detail(Long insurance_id, vehicle_detail vehicle_number, vehicletype vehicle_type,
			LocalDate date_from, LocalDate date_valid_to, BigDecimal insurance_cost, insurancestatus insurance_status,
			LocalDate lisence_date_from, LocalDate lisence_date_to, BigDecimal lisence_cost,
			insurancestatus lisence_status) {
		super();
		this.insurance_id = insurance_id;
		this.vehicle_number = vehicle_number;
		this.vehicle_type = vehicle_type;
		this.date_from = date_from;
		this.Date_valid_to = date_valid_to;
		this.insurance_cost = insurance_cost;
		this.insurance_status = insurance_status;
		this.lisence_date_from = lisence_date_from;
		this.lisence_date_to = lisence_date_to;
		this.lisence_cost = lisence_cost;
		this.lisence_status = lisence_status;
	}

	public Long getInsurance_id() {
		return insurance_id;
	}

	public void setInsurance_id(Long insurance_id) {
		this.insurance_id = insurance_id;
	}

	public vehicle_detail getvehicle_number() {
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

	public LocalDate getDate_from() {
		return date_from;
	}

	public void setDate_from(LocalDate date_from) {
		this.date_from = date_from;
	}

	public LocalDate getDate_valid_to() {
		return Date_valid_to;
	}

	public void setDate_valid_to(LocalDate date_valid_to) {
		Date_valid_to = date_valid_to;
	}

	public BigDecimal getInsurance_cost() {
		return insurance_cost;
	}

	public void setInsurance_cost(BigDecimal insurance_cost) {
		this.insurance_cost = insurance_cost;
	}

	public insurancestatus getInsurance_status() {
		return isExpired() ? insurancestatus.EXPIRED : insurancestatus.VALID;
	}

	public void setInsurance_status(insurancestatus ignored) {

	}

	public LocalDate getLisence_date_from() {
		return lisence_date_from;
	}

	public void setLisence_date_from(LocalDate lisence_date_from) {
		this.lisence_date_from = lisence_date_from;
	}

	public LocalDate getLisence_date_to() {
		return lisence_date_to;
	}

	public void setLisence_date_to(LocalDate lisence_date_to) {
		this.lisence_date_to = lisence_date_to;
	}

	public BigDecimal getLisence_cost() {
		return lisence_cost;
	}

	public void setLisence_cost(BigDecimal lisence_cost) {
		this.lisence_cost = lisence_cost;
	}

	public insurancestatus getLisence_status() {
		return isExpiredLisence() ? insurancestatus.EXPIRED : insurancestatus.VALID;
	}

	@Transient
	public String getVehicle_number() {
		return vehicle_number != null ? vehicle_number.getVehicle_number() : null;
	}

}
