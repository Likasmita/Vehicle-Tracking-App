package com.vehicle.violation.tracker.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vehicle.violation.tracker.entities.Vehicle;

@Service
public interface VehicleViolationService {
	
	void saveCSVFile(MultipartFile  file);

	List<Vehicle> getAllVehiclesOfDeviation(List<String> vehicleNumbers) ;

}
