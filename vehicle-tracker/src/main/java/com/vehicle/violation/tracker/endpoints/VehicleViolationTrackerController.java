package com.vehicle.violation.tracker.endpoints;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vehicle.violation.tracker.entities.Vehicle;
import com.vehicle.violation.tracker.entities.VehicleViolation;
import com.vehicle.violation.tracker.model.VehiclesPenaltyResponse;
import com.vehicle.violation.tracker.model.VehilceToPenaltyMapDto;
import com.vehicle.violation.tracker.services.EmailNotifierProxy;
import com.vehicle.violation.tracker.services.VehicleViolationService;

/**
 * 
 * @author lika
 *
 */

@RestController
@EnableEurekaClient
public class VehicleViolationTrackerController {

	@Autowired
	VehicleViolationService violationService;

	@Autowired
	EmailNotifierProxy emailProxy;

	@RequestMapping(value = "/fetchVehicleInfos", method = RequestMethod.GET)
	public VehiclesPenaltyResponse findAllVehicleDetails(@RequestParam("email") String email,
			@RequestParam("vehicleNumbers") List<String> vehicleNumbers) {
		VehiclesPenaltyResponse vpResp = new VehiclesPenaltyResponse();
		// get list of vehicle details
		List<Vehicle> vehicleDetailList = violationService.getAllVehiclesOfDeviation(vehicleNumbers);
		vpResp.setVehicleDetails(vehicleDetailList);

		// populate highlight vehicle details with just vehicle numb to penalty map
		populateReqVehicleToPenaltyMap(email, vpResp, vehicleDetailList);

		return vpResp;
	}

	/**
	 * 
	 * @param vpResp
	 * @param vehicleDetailList
	 */
	private void populateReqVehicleToPenaltyMap(String email, VehiclesPenaltyResponse vpResp,
			List<Vehicle> vehicleDetailList) {

		List<VehilceToPenaltyMapDto> VehilceToPenaltyMapDtoList = new ArrayList<>();
		List<String> list = new LinkedList<String>();
		if (vehicleDetailList != null && vehicleDetailList.size() > 0) {
			for (Vehicle vhcle : vehicleDetailList) {
				double totalDue = 0;
				VehilceToPenaltyMapDto vehilceToPenaltyMapDto = new VehilceToPenaltyMapDto();
				vehilceToPenaltyMapDto.setVehicleNumb(vhcle.getVehicleNumber());

				List<VehicleViolation> vhcleVilaotionList = vhcle.getVehicleViolations();
				if (vhcleVilaotionList != null && !vhcleVilaotionList.isEmpty()) {
					for (VehicleViolation violation : vhcleVilaotionList) {
						totalDue = totalDue + violation.getPenaltyAmount();
					}
				}
				vehilceToPenaltyMapDto.setToalDue(totalDue);
				list.add(vhcle.getVehicleNumber());

				VehilceToPenaltyMapDtoList.add(vehilceToPenaltyMapDto);
			}
			emailProxy.sendNotifier(email, list);
		}
		vpResp.setVehicleToPenaltyMap(VehilceToPenaltyMapDtoList);
	}

	/**
	 * additional code for set up , not required
	 * 
	 * @param file
	 */
	@RequestMapping(value = "/saveAllInfosOfVehicle", method = RequestMethod.POST)
	public void insertAllDeviatedVehicle(@RequestParam("file") MultipartFile file) {

		violationService.saveCSVFile(file);
	}

}
