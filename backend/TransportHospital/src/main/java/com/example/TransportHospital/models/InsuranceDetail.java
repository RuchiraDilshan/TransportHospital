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
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Transient;
import jakarta.persistence.Table;

@Entity
@Table(name = "insurance_detail")
public class InsuranceDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long insuranceid;

	@OneToOne
	@JoinColumn(name = "vehiclenumber", referencedColumnName = "vehiclenumber", nullable = false)
	private VehicleDetail vehicle;

	@Enumerated(EnumType.STRING)
	private VehicleDetail.VehicleType vehicletype;

	private LocalDate datefrom;

	private LocalDate datevalidto;

	private BigDecimal insurancecost;

	@Enumerated(EnumType.STRING)
	private InsuranceStatus insurancestatus;

	private LocalDate licensedatefrom;

	private LocalDate licensedateto;

	private BigDecimal licensecost;

	@Enumerated(EnumType.STRING)
	private LicenseStatus licensestatus;

	public enum InsuranceStatus {

		VALID,
		EXPIRED

	}

	public enum LicenseStatus {

		VALID,
		EXPIRED

	}

	@PrePersist
	@PreUpdate
	private void syncAndCompute() {
		if (vehicle != null)
			this.vehicletype = vehicle.getVehicleType();

	}

	private boolean isExpired() {
		return datevalidto != null && datevalidto.isBefore(LocalDate.now());

	}

	private boolean isExpiredLicense() {
		return licensedateto != null && licensedateto.isBefore(LocalDate.now());
	}

	public InsuranceDetail() {
		super();
	}

	public InsuranceDetail(Long insuranceid, VehicleDetail vehicle, VehicleDetail.VehicleType vehicletype,
			LocalDate datefrom, LocalDate datevalidto, BigDecimal insurancecost, InsuranceStatus insurancestatus,
			LocalDate licensedatefrom, LocalDate licensedateto, BigDecimal licensecost, LicenseStatus licensestatus) {
		super();
		this.insuranceid = insuranceid;
		this.vehicle = vehicle;
		this.vehicletype = vehicletype;
		this.datefrom = datefrom;
		this.datevalidto = datevalidto;
		this.insurancecost = insurancecost;
		this.insurancestatus = insurancestatus;
		this.licensedatefrom = licensedatefrom;
		this.licensedateto = licensedateto;
		this.licensecost = licensecost;
		this.licensestatus = licensestatus;
	}

	public Long getInsuranceId() {
		return insuranceid;
	}

	public void setInsuranceId(Long insuranceid) {
		this.insuranceid = insuranceid;
	}

	public VehicleDetail getVehicleINumber() {
		return vehicle;
	}

	public void setVehicleINumber(VehicleDetail vehicle) {
		this.vehicle = vehicle;
	}

	public VehicleDetail.VehicleType getVehicleType() {
		return vehicletype;
	}

	public void setVehicleType(VehicleDetail.VehicleType vehicletype) {
		this.vehicletype = vehicletype;
	}

	public LocalDate getDate_from() {
		return datefrom;
	}

	public void setDate_from(LocalDate datefrom) {
		this.datefrom = datefrom;
	}

	public LocalDate getDatevalidto() {
		return datevalidto;
	}

	public void setDatevalidto(LocalDate datevalidto) {
		this.datevalidto = datevalidto;
	}

	public BigDecimal getInsurancecost() {
		return insurancecost;
	}

	public void setInsurancecost(BigDecimal insurancecost) {
		this.insurancecost = insurancecost;
	}

	public InsuranceStatus getInsurancestatus() {
		return isExpired() ? InsuranceStatus.EXPIRED : InsuranceStatus.VALID;
	}

	public void setInsurancestatus(InsuranceStatus ignored) {

	}

	public LocalDate getLicensedatefrom() {
		return licensedatefrom;
	}

	public void setLicensedatefrom(LocalDate licensedatefrom) {
		this.licensedatefrom = licensedatefrom;
	}

	public LocalDate getLicensedateto() {
		return licensedateto;
	}

	public void setLicensedateto(LocalDate licensedateto) {
		this.licensedateto = licensedateto;
	}

	public BigDecimal getLicensecost() {
		return licensecost;
	}

	public void setLicensecost(BigDecimal licensecost) {
		this.licensecost = licensecost;
	}

	public LicenseStatus getLicensestatus() {
		return isExpiredLicense() ? LicenseStatus.EXPIRED : LicenseStatus.VALID;
	}

	@Transient
	public String getVehiclenumber() {
		return vehicle != null ? vehicle.getVehicleNumber() : null;
	}

}
