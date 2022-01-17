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

import com.isa.booking_entities.dtos.InstructorReportUpdateDTO;
import com.isa.booking_entities.models.reports.InstructorReport;
import com.isa.booking_entities.models.reports.StatusOfReport;
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.services.EmailService;
import com.isa.booking_entities.services.interfaces.IClientService;
import com.isa.booking_entities.services.interfaces.IInstructorReportService;
import com.isa.booking_entities.services.interfaces.IReportService;
import com.isa.booking_entities.services.interfaces.IUsersService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/instructor_reports", produces = MediaType.APPLICATION_JSON_VALUE)
public class InstructorReportController {
	private EmailService emailService;
	
	private IInstructorReportService iInstructorReportService;
	
	private IReportService iReportService;
	
	private IClientService iClientService;
	
	private IUsersService iUsersService;

	@Autowired
	public InstructorReportController(EmailService emailService, IInstructorReportService iInstructorReportService,
			IReportService iReportService, IClientService iClientService, IUsersService iUsersService) {
		this.emailService = emailService;
		this.iInstructorReportService = iInstructorReportService;
		this.iReportService = iReportService;
		this.iClientService = iClientService;
		this.iUsersService = iUsersService;
	}

	@GetMapping(value = "/getOnlySanctionReportForSystemAdmin")
	public ResponseEntity<List<InstructorReport>> getOnlySanctionReportForSystemAdmin(){
		return new ResponseEntity<List<InstructorReport>>(iInstructorReportService.getReportsForSystemAdmin(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getById/{reportId}")
	public ResponseEntity<InstructorReport> getById(@PathVariable long id){
		return new ResponseEntity<InstructorReport>(iInstructorReportService.getById(id), HttpStatus.OK);
	}
	
	@PutMapping(value = "/change_status", consumes = "application/json")
	public ResponseEntity<Boolean> changeStatusOfReport(@RequestBody InstructorReportUpdateDTO instructorReportUpdateDTO) {
		InstructorReport instructorReport = iInstructorReportService.getById(instructorReportUpdateDTO.getInstructorReportId());
		instructorReport.setStatusOfReport(instructorReportUpdateDTO.getStatusOfReport());
		iReportService.save(iInstructorReportService.save(instructorReport));
		if (instructorReportUpdateDTO.getStatusOfReport()==StatusOfReport.APPROVED) {
			Client client = iClientService.getByEmail(instructorReport.getReportingClient().getEmail());
			client.setPenaltyPoints(client.getPenaltyPoints()+1);
			iUsersService.save(iClientService.save(client));
		}
		try {
			emailService.sendNotificationAboutPenaltyPoints(instructorReport.getReportingClient().getEmail(), instructorReport.getReportingClient().getEmail(), instructorReportUpdateDTO.getStatusOfReport());
			emailService.sendNotificationAboutPenaltyPoints(instructorReport.getInstructorWhoCreateReport().getEmail(),instructorReport.getReportingClient().getEmail(), instructorReportUpdateDTO.getStatusOfReport());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (MailException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
}
