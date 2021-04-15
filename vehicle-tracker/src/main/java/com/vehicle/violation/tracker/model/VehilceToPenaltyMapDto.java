package com.vehicle.violation.tracker.model;

import org.springframework.stereotype.Component;

@Component
public class VehilceToPenaltyMapDto {

	private String vehicleNumb;

	public String getVehicleNumb() {
		return vehicleNumb;
	}

	public void setVehicleNumb(String vehicleNumb) {
		this.vehicleNumb = vehicleNumb;
	}

	private double toalDue;

	public double getToalDue() {
		return toalDue;
	}

	public void setToalDue(double toalDue) {
		this.toalDue = toalDue;
	}
}
