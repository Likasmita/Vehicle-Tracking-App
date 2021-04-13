package com.vehicle.tracker.email.notifer.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

	public void sendEmailNotification(List<String> vehicleNumbers, String email)
			throws AddressException, MessagingException{
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email, "abcdef@G");
			}
		});
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(email, false));

		msg.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse("daleilikasmita11@gmail.com"));
		msg.setSubject("Traffic Violators");
		msg.setContent("Hi, Violators are there!" + '\r'
				+ " Please catch them and collect the penalty for these vehicle numbers!" + '\n' + vehicleNumbers,
				"text/html");
		msg.setSentDate(new Date());

		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(vehicleNumbers, "text/html");
		Transport.send(msg);
	}

}
