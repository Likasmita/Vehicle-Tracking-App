/**
 * 
 */
package com.vehicle.tracker.email.notifer.controller;


import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vehicle.tracker.email.notifer.service.EmailService;
/**
 * @author lika
 *
 */
@RestController
public class EmailNotifierController {

	@Autowired
	EmailService emailService;
	
	   @RequestMapping(value = "/sendNotification")
	   public void sendNotifier(@RequestParam("email") String email,@RequestParam("vehicleNumbers") List<String> list)  {
		  
		   
		   try {
			emailService.sendEmailNotification(email,list);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} 
		   
	   }   
}