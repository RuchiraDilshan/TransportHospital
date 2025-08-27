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
public class image_detail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long image_id;

	@ManyToOne
	@JoinColumn(name = "vehicle_number", referencedColumnName = "vehicle_number", nullable = false)
	private vehicle_detail vehicle_number;

	@Enumerated(EnumType.STRING)
	private vehicle_detail.vehicletype vehicle_type;

	private String vehicle_image;

	@PrePersist
	@PreUpdate
	private void syncVehicleType() {
		if (vehicle_number != null)
			this.vehicle_type = vehicle_number.getVehicle_type();
	}

	public image_detail() {
		super();
	}

	public image_detail(Long image_id, vehicle_detail vehicle_number, vehicletype vehicle_type, String vehicle_image) {
		super();
		this.image_id = image_id;
		this.vehicle_number = vehicle_number;
		this.vehicle_type = vehicle_type;
		this.vehicle_image = vehicle_image;
	}

	public Long getImage_id() {
		return image_id;
	}

	public void setImage_id(Long image_id) {
		this.image_id = image_id;
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

	public String getVehicle_image() {
		return vehicle_image;
	}

	public void setVehicle_image(String vehicle_image) {
		this.vehicle_image = vehicle_image;
	}

}
