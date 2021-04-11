package com.vehicle.violation.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.vehicle")
public class VehicleViolationTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleViolationTrackerApplication.class, args);
	}

}
