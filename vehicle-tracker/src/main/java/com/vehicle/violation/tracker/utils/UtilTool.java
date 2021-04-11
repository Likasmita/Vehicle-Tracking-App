package com.vehicle.violation.tracker.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.vehicle.violation.tracker.entities.Vehicle;


@Component
public class UtilTool {
	  public static String TYPE = "text/csv";
	  static String[] HEADERs = { "VehicleNumber", "VehicleOwner", "VehiclePurchasedDate", "vehicleColor","VehicleBrand","VehicleModel" };

	  public static boolean hasCSVFormat(MultipartFile file) {
	    if (TYPE.equals(file.getContentType())
	    		|| file.getContentType().equals("application/vnd.ms-excel")) {
	      return true;
	    }

	    return false;
	  }

	  public static List<Vehicle> readingCSVFile(InputStream is) {
	    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        CSVParser csvParser = new CSVParser(fileReader,
	            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

	      List<Vehicle> vehicleList = new ArrayList<>();

	      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			/*
			 * for (CSVRecord csvRecord : csvRecords) { Vehicle vehicle = new Vehicle(
			 * csvRecord.get("VehicleNumber"), csvRecord.get("VehicleOwner"),
			 * csvRecord.get("VehiclePurchasedDate"), csvRecord.get("vehicleColor"),
			 * csvRecord.get("VehicleBrand"), csvRecord.get("VehicleModel") );
			 * 
			 * vehicleList.add(vehicle); }
			 */

	      return vehicleList;
	    } catch (IOException e) {
	      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
	    }
	  }
	}