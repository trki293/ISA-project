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

import com.isa.booking_entities.dtos.CottageOwnerReportUpdateDTO;
import com.isa.booking_entities.models.reports.CottageOwnerReport;
import com.isa.booking_entities.models.reports.StatusOfReport;
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.services.EmailService;
import com.isa.booking_entities.services.interfaces.IClientService;
import com.isa.booking_entities.services.interfaces.ICottageOwnerReportService;
import com.isa.booking_entities.services.interfaces.IReportService;
import com.isa.booking_entities.services.interfaces.IUsersService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/cottage_owner_reports", produces = MediaType.APPLICATION_JSON_VALUE)
public class CottageOwnerReportController {
	private EmailService emailService;
	
	private ICottageOwnerReportService iCottageOwnerReportService;
	
	private IReportService iReportService;
	
	private IClientService iClientService;
	
	private IUsersService iUsersService;

	@Autowired
	public CottageOwnerReportController(EmailService emailService, ICottageOwnerReportService iCottageOwnerReportService,
			IReportService iReportService, IClientService iClientService, IUsersService iUsersService) {
		this.emailService = emailService;
		this.iCottageOwnerReportService = iCottageOwnerReportService;
		this.iReportService = iReportService;
		this.iClientService = iClientService;
		this.iUsersService = iUsersService;
	}

	@GetMapping(value = "/getOnlySanctionReportForSystemAdmin")
	public ResponseEntity<List<CottageOwnerReport>> getOnlySanctionReportForSystemAdmin(){
		return new ResponseEntity<List<CottageOwnerReport>>(iCottageOwnerReportService.getReportsForSystemAdmin(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getById/{reportId}")
	public ResponseEntity<CottageOwnerReport> getById(@PathVariable long id){
		return new ResponseEntity<CottageOwnerReport>(iCottageOwnerReportService.getById(id), HttpStatus.OK);
	}
	
	@PutMapping(value = "/change_status", consumes = "application/json")
	public ResponseEntity<Boolean> changeStatusOfReport(@RequestBody CottageOwnerReportUpdateDTO cottageOwnerReportUpdateDTO) {
		CottageOwnerReport cottageOwnerReport = iCottageOwnerReportService.getById(cottageOwnerReportUpdateDTO.getCottageOwnerReportId());
		cottageOwnerReport.setStatusOfReport(cottageOwnerReportUpdateDTO.getStatusOfReport());
		iReportService.save(iCottageOwnerReportService.save(cottageOwnerReport));
		if (cottageOwnerReportUpdateDTO.getStatusOfReport()==StatusOfReport.APPROVED) {
			Client client = iClientService.getByEmail(cottageOwnerReport.getReportingClient().getEmail());
			client.setPenaltyPoints(client.getPenaltyPoints()+1);
			iUsersService.save(iClientService.save(client));
		}
		try {
			emailService.sendNotificationAboutPenaltyPoints(cottageOwnerReport.getReportingClient().getEmail(), cottageOwnerReport.getReportingClient().getEmail(), cottageOwnerReportUpdateDTO.getStatusOfReport());
			emailService.sendNotificationAboutPenaltyPoints(cottageOwnerReport.getCottageOwnerWhoCreateReport().getEmail(),cottageOwnerReport.getReportingClient().getEmail(), cottageOwnerReportUpdateDTO.getStatusOfReport());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (MailException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
}
