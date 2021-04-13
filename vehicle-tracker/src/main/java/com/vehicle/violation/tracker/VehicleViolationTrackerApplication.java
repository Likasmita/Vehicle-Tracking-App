package com.vehicle.violation.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients("com.vehicle.violation.tracker")
@ComponentScan(basePackages="com.vehicle")
public class VehicleViolationTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleViolationTrackerApplication.class, args);
	}

}
