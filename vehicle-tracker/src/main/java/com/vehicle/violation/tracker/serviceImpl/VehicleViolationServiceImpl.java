/**
 * 
 */
package com.vehicle.violation.tracker.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vehicle.violation.tracker.entities.Vehicle;
import com.vehicle.violation.tracker.entities.VehicleViolation;
import com.vehicle.violation.tracker.repository.VehicleRepository;
import com.vehicle.violation.tracker.repository.ViolationRepository;
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

	@Autowired
	ViolationRepository violationRepo;




	/**
	 * This Method used for getting all the vehicle details which has already 
	 * done the violations. 
	 */
	public List<Vehicle> getAllVehiclesOfDeviation(List<String> vehicleNumbers) { 
		List<Vehicle> vehicleList = new ArrayList<Vehicle>();
		if(vehicleNumbers!=null && vehicleNumbers.size()>0) {
			for(String vehicleNum: vehicleNumbers) {
				List<VehicleViolation> violationList = new ArrayList<VehicleViolation>();
				violationList = findViolations(vehicleNum);
				if(violationList.size()>0) {
					Vehicle vehicleObj = findVehicleByNum(vehicleNum);
					vehicleObj.setVehicleViolations(violationList);
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
	public List<VehicleViolation> findViolations(String vehicleNumber) {
		List<VehicleViolation> violationList = new ArrayList<VehicleViolation>();
		violationList = violationRepo.findAll(vehicleNumber);
		return violationList;
	}


	/**
	 * 
	 * @param vehicleNumber
	 * @return
	 */
	public Vehicle findVehicleByNum(String vehicleNumber){


		Vehicle vehicle = vehicleRepo.getVehicleByNumber(vehicleNumber);

		return vehicle;

	}


	/**
	 * 
	 */
	public void saveCSVFile(MultipartFile file) {
		try {
			List<Vehicle> vehicles = new ArrayList<Vehicle>();
			vehicles=UtilTool.readingCSVFile(file.getInputStream());


			for(Vehicle vehicle: vehicles) {
				vehicle.setVehicleViolations(findViolations(vehicle.getVehicleNumber()));

			}


			vehicleRepo.saveAll(vehicles);
		} catch (IOException e) {
			throw new RuntimeException("fail to store csv data: " + e.getMessage());
		}
	}




}



