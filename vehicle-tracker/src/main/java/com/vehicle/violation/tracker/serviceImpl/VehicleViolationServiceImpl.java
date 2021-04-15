/**
 * 
 */
package com.vehicle.violation.tracker.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vehicle.violation.tracker.entities.Vehicle;
import com.vehicle.violation.tracker.entities.VehicleViolation;
import com.vehicle.violation.tracker.repository.VehicleRepository;
import com.vehicle.violation.tracker.services.VehicleViolationService;
import com.vehicle.violation.tracker.utils.UtilTool;

/**
 * @author lika
 *
 */
@Service
public class VehicleViolationServiceImpl implements VehicleViolationService {

	@Autowired
	VehicleRepository vehicleRepo;

	/**
	 * This Method used for getting all the vehicle details which has already done
	 * the violations.
	 */
	public List<Vehicle> getAllVehiclesOfDeviation(List<String> vehicleNumbers) {
		List<Vehicle> vehicleList = new ArrayList<Vehicle>();
		if (vehicleNumbers != null && vehicleNumbers.size() > 0) {

			for (String vehicleNum : vehicleNumbers) {
				List<VehicleViolation> violationList = new ArrayList<VehicleViolation>();
				List<VehicleViolation> filtered_violationList = new ArrayList<VehicleViolation>();
				Vehicle vehicleObj = findVehicleByNum(vehicleNum);
				violationList.addAll(vehicleObj.getVehicleViolations());
				filtered_violationList = violationList.stream().filter(obj -> obj.isPenaltyDone() == false)
						.collect(Collectors.toList());
				vehicleObj.setVehicleViolations(filtered_violationList);
				if (vehicleObj.getVehicleViolations().size() > 0) {
					vehicleList.add(vehicleObj);
				}
			}
		}
		return vehicleList;
	}

	/**
	 * 
	 * @param vehicleNumber
	 * @return
	 */
	public Vehicle findVehicleByNum(String vehicleNumber) {

		Vehicle vehicle = vehicleRepo.getVehicleByNumber(vehicleNumber);

		return vehicle;

	}

	/**
	 * An Extra method for insertions but not mandatory
	 */
	public void saveCSVFile(MultipartFile file) {
		try {
			List<Vehicle> vehicles = new ArrayList<Vehicle>();
			vehicles = UtilTool.readingCSVFile(file.getInputStream());
			vehicleRepo.saveAll(vehicles);
		} catch (IOException e) {
			throw new RuntimeException("fail to store csv data: " + e.getMessage());
		}
	}

}
