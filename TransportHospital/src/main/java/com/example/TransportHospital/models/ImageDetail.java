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
@Table(name = "image_detail")
public class ImageDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long imageid;

	@ManyToOne
	@JoinColumn(name = "vehiclenumber", referencedColumnName = "vehiclenumber", nullable = false)
	private VehicleDetail vehiclenumber;

	@Enumerated(EnumType.STRING)
	private VehicleDetail.VehicleType vehicletype;

	private String vehicleimage;

	@PrePersist
	@PreUpdate
	private void syncVehicleType() {
		if (vehiclenumber != null)
			this.vehicletype = vehiclenumber.getVehicleType();
	}

	public ImageDetail() {
		super();
	}

	public ImageDetail(Long imageid, VehicleDetail vehiclenumber, VehicleDetail.VehicleType vehicletype,
			String vehicleimage) {
		super();
		this.imageid = imageid;
		this.vehiclenumber = vehiclenumber;
		this.vehicletype = vehicletype;
		this.vehicleimage = vehicleimage;
	}

	public Long getImageid() {
		return imageid;
	}

	public void setImageid(Long imageid) {
		this.imageid = imageid;
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

	public String getVehicleimage() {
		return vehicleimage;
	}

	public void setVehicleimage(String vehicleimage) {
		this.vehicleimage = vehicleimage;
	}

}
