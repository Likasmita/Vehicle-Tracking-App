package com.vehicle.violation.tracker.endpoints;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vehicle.violation.tracker.entities.Vehicle;
import com.vehicle.violation.tracker.entities.VehicleViolation;
import com.vehicle.violation.tracker.model.VehiclesPenaltyResponse;
import com.vehicle.violation.tracker.model.VehilceToPenaltyMapDto;
import com.vehicle.violation.tracker.services.VehicleViolationService;

/**
 * 
 * @author lika
 *
 */


@RestController
public class VehicleViolationTrackerController {
	

	@Autowired
	VehicleViolationService violationService;
	


	@RequestMapping(value = "/fetchVehicleInfos" , method = RequestMethod.GET)
	public VehiclesPenaltyResponse findAllVehicleDetails(@RequestParam("vehicleNumbers") List<String> vehicleNumbers) {
		VehiclesPenaltyResponse vpResp = new VehiclesPenaltyResponse();
		//get list of vehicle details 
		List<Vehicle> vehicleDetailList =  violationService.getAllVehiclesOfDeviation(vehicleNumbers);
		vpResp.setVehicleDetails(vehicleDetailList);
		
		//populate highlight vehicle details with just vehicle numb to penalty map 
		populateReqVehicleToPenaltyMap(vpResp,vehicleDetailList);
		
		return vpResp;
	}
	
	
	/**
	 * 
	 * @param vpResp
	 * @param vehicleDetailList
	 */
	private void populateReqVehicleToPenaltyMap(VehiclesPenaltyResponse vpResp, List<Vehicle> vehicleDetailList) {
		
		List<VehilceToPenaltyMapDto> VehilceToPenaltyMapDtoList = new ArrayList<>();
				
		for(Vehicle vhcle : vehicleDetailList) {
			double totalDue = 0;
			VehilceToPenaltyMapDto vehilceToPenaltyMapDto = new VehilceToPenaltyMapDto();
			vehilceToPenaltyMapDto.setVehicleNumb(vhcle.getVehicleNumber());
			
			List<VehicleViolation> vhcleVilaotionList =vhcle.getVehicleViolations();
			if(vhcleVilaotionList!=null && !vhcleVilaotionList.isEmpty()) {
				for(VehicleViolation violation : vhcleVilaotionList) {
					totalDue = totalDue + violation.getPenaltyAmount();
				}
			}
			vehilceToPenaltyMapDto.setToalDue(totalDue);
			VehilceToPenaltyMapDtoList.add(vehilceToPenaltyMapDto);
		}
		vpResp.setVehicleToPenaltyMap(VehilceToPenaltyMapDtoList);
	}


	
	/**
	 * additional code for set up , not req 
	 * @param file
	 */
	@RequestMapping(value = "/saveAllInfosOfVehicle" , method = RequestMethod.POST)
	public void insertAllDeviatedVehicle(@RequestParam("file") MultipartFile file) {
		
		violationService.saveCSVFile(file);
	}
	
	



}