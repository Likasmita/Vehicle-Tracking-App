package com.vehicle.violation.tracker.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Service
@FeignClient(name = "email-notifier", url = "localhost:8090")
public interface EmailNotifierProxy {

	@RequestMapping(value = "/sendNotification")
	public void sendNotifier(@RequestParam("email") String email,@RequestParam("vehicleNumbers") List<String> list);

}
