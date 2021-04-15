package com.vehicle.violation.tracker.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VEHICLE_VIOLATION")
public class VehicleViolation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int violationId;

	@Column(name = "violationDate")
	private String violationDate;

	@Column(name = "penaltyAmount")
	private double penaltyAmount;

	@Column(name = "violationDone")
	private String violationDone;

	@Column(name = "vehicleNumber")
	private String vehicleNumber;

	@Column(name = "penaltyDone")
	private boolean penaltyDone;

	@Column(name = "penaltyType")
	private String penaltyType;

	@Column(name = "vehicleType")
	private String vehicleType;

	@ManyToOne
	@JoinColumn(name = "vehicleId")
	private Vehicle vehicle;

	public int getViolationId() {
		return violationId;
	}

	public void setViolationId(int violationId) {
		this.violationId = violationId;
	}

	public String getViolationDate() {
		return violationDate;
	}

	public void setViolationDate(String violationDate) {
		this.violationDate = violationDate;
	}

	public double getPenaltyAmount() {
		return penaltyAmount;
	}

	public void setPenaltyAmount(double penaltyAmount) {
		this.penaltyAmount = penaltyAmount;
	}

	public String getViolationDone() {
		return violationDone;
	}

	public void setViolationDone(String violationDone) {
		this.violationDone = violationDone;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public boolean isPenaltyDone() {
		return penaltyDone;
	}

	public void setPenaltyDone(boolean penaltyDone) {
		this.penaltyDone = penaltyDone;
	}

	public String getPenaltyType() {
		return penaltyType;
	}

	public void setPenaltyType(String penaltyType) {
		this.penaltyType = penaltyType;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

}
