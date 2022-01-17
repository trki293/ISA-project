package com.isa.booking_entities.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.isa.booking_entities.dtos.BoatOwnerReportUpdateDTO;
import com.isa.booking_entities.models.reports.BoatOwnerReport;
import com.isa.booking_entities.models.reports.StatusOfReport;
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.services.EmailService;
import com.isa.booking_entities.services.interfaces.IClientService;
import com.isa.booking_entities.services.interfaces.IBoatOwnerReportService;
import com.isa.booking_entities.services.interfaces.IReportService;
import com.isa.booking_entities.services.interfaces.IUsersService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/boat_owner_reports", produces = MediaType.APPLICATION_JSON_VALUE)
public class BoatOwnerReportController {
	private EmailService emailService;
	
	private IBoatOwnerReportService iBoatOwnerReportService;
	
	private IReportService iReportService;
	
	private IClientService iClientService;
	
	private IUsersService iUsersService;

	@Autowired
	public BoatOwnerReportController(EmailService emailService, IBoatOwnerReportService iBoatOwnerReportService,
			IReportService iReportService, IClientService iClientService, IUsersService iUsersService) {
		this.emailService = emailService;
		this.iBoatOwnerReportService = iBoatOwnerReportService;
		this.iReportService = iReportService;
		this.iClientService = iClientService;
		this.iUsersService = iUsersService;
	}

	@GetMapping(value = "/getOnlySanctionReportForSystemAdmin")
	public ResponseEntity<List<BoatOwnerReport>> getOnlySanctionReportForSystemAdmin(){
		return new ResponseEntity<List<BoatOwnerReport>>(iBoatOwnerReportService.getReportsForSystemAdmin(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getById/{reportId}")
	public ResponseEntity<BoatOwnerReport> getById(@PathVariable long id){
		return new ResponseEntity<BoatOwnerReport>(iBoatOwnerReportService.getById(id), HttpStatus.OK);
	}
	
	@PutMapping(value = "/change_status", consumes = "application/json")
	public ResponseEntity<Boolean> changeStatusOfReport(@RequestBody BoatOwnerReportUpdateDTO boatOwnerReportUpdateDTO) {
		BoatOwnerReport boatOwnerReport = iBoatOwnerReportService.getById(boatOwnerReportUpdateDTO.getBoatOwnerReportId());
		boatOwnerReport.setStatusOfReport(boatOwnerReportUpdateDTO.getStatusOfReport());
		iReportService.save(iBoatOwnerReportService.save(boatOwnerReport));
		if (boatOwnerReportUpdateDTO.getStatusOfReport()==StatusOfReport.APPROVED) {
			Client client = iClientService.getByEmail(boatOwnerReport.getReportingClient().getEmail());
			client.setPenaltyPoints(client.getPenaltyPoints()+1);
			iUsersService.save(iClientService.save(client));
		}
		try {
			emailService.sendNotificationAboutPenaltyPoints(boatOwnerReport.getReportingClient().getEmail(), boatOwnerReport.getReportingClient().getEmail(), boatOwnerReportUpdateDTO.getStatusOfReport());
			emailService.sendNotificationAboutPenaltyPoints(boatOwnerReport.getBoatOwnerWhoCreateReport().getEmail(),boatOwnerReport.getReportingClient().getEmail(), boatOwnerReportUpdateDTO.getStatusOfReport());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (MailException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}	
}
