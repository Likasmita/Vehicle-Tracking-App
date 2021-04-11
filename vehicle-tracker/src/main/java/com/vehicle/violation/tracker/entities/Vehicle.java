package com.vehicle.violation.tracker.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="VEHICLE")
public class Vehicle implements Serializable {

	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToMany(targetEntity = VehicleViolation.class , cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="vehicle_penalty_Number",referencedColumnName="vehicleNumber")
	private List<VehicleViolation> vehicleViolations;

	@Column(name="vehicleNumber",unique = true)
	private String vehicleNumber;
	
	@Column(name="vehicleOwner")
	private String vehicleOwner;
	
	@Column(name="vehiclePurchasedDate")
	private String vehiclePurchasedDate;
	
	@Column(name="vehicleColor")
	private String vehicleColor;
	
	@Column(name="vehicleBrand")
	private String vehicleBrand;
	
	@Column(name="vehicleModel")
	private String vehicleModel;
	

	/*
	 * public Vehicle(){
	 * 
	 * } public Vehicle(String vehicleNumber, String vehicleOwner, String
	 * vehiclePurchasedDate, String vehicleColor, String vehicleBrand, String
	 * vehicleModel) {
	 * 
	 * this.vehicleNumber = vehicleNumber; this.vehicleOwner = vehicleOwner;
	 * this.vehiclePurchasedDate = vehiclePurchasedDate; this.vehicleColor =
	 * vehicleColor; this.vehicleBrand = vehicleBrand; this.vehicleModel =
	 * vehicleModel; }
	 */



	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public List<VehicleViolation> getVehicleViolations() {
		return vehicleViolations;
	}




	public void setVehicleViolations(List<VehicleViolation> vehicleViolations) {
		this.vehicleViolations = vehicleViolations;
	}




	public String getVehicleNumber() {
		return vehicleNumber;
	}




	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}




	public String getVehicleOwner() {
		return vehicleOwner;
	}




	public void setVehicleOwner(String vehicleOwner) {
		this.vehicleOwner = vehicleOwner;
	}




	public String getVehiclePurchasedDate() {
		return vehiclePurchasedDate;
	}




	public void setVehiclePurchasedDate(String vehiclePurchasedDate) {
		this.vehiclePurchasedDate = vehiclePurchasedDate;
	}




	public String getVehicleColor() {
		return vehicleColor;
	}




	public void setVehicleColor(String vehicleColor) {
		this.vehicleColor = vehicleColor;
	}




	public String getVehicleBrand() {
		return vehicleBrand;
	}




	public void setVehicleBrand(String vehicleBrand) {
		this.vehicleBrand = vehicleBrand;
	}




	public String getVehicleModel() {
		return vehicleModel;
	}




	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}




	@Override
	public String toString() {
		return super.toString();
	}
}
