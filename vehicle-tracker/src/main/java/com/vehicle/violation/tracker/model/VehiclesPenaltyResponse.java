package com.vehicle.violation.tracker.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.vehicle.violation.tracker.entities.Vehicle;

@Component
public class VehiclesPenaltyResponse {

	private List<Vehicle> vehicleDetails;

	private List<VehilceToPenaltyMapDto> vehicleToPenaltyMap;

	/**
	 * @return the vehicleDetails
	 */
	public List<Vehicle> getVehicleDetails() {
		return vehicleDetails;
	}

	/**
	 * @param vehicleDetails the vehicleDetails to set
	 */
	public void setVehicleDetails(List<Vehicle> vehicleDetails) {
		this.vehicleDetails = vehicleDetails;
	}

	/**
	 * @return the vehicleToPenaltyMap
	 */
	public List<VehilceToPenaltyMapDto> getVehicleToPenaltyMap() {
		return vehicleToPenaltyMap;
	}

	/**
	 * @param vehicleToPenaltyMap the vehicleToPenaltyMap to set
	 */
	public void setVehicleToPenaltyMap(List<VehilceToPenaltyMapDto> vehicleToPenaltyMap) {
		this.vehicleToPenaltyMap = vehicleToPenaltyMap;
	}

}
