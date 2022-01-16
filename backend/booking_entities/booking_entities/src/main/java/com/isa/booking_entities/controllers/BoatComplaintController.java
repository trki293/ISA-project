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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.isa.booking_entities.converter.ComplaintDTOConverter;
import com.isa.booking_entities.dtos.BoatComplaintDisplayDTO;
import com.isa.booking_entities.dtos.BoatComplaintNewDTO;
import com.isa.booking_entities.dtos.BoatComplaintResponseNewDTO;
import com.isa.booking_entities.models.complaints.BoatComplaint;
import com.isa.booking_entities.models.complaints.BoatComplaintResponse;
import com.isa.booking_entities.models.complaints.StatusOfComplaint;
import com.isa.booking_entities.models.entites.Boat;
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.models.users.SystemAdmin;
import com.isa.booking_entities.services.EmailService;
import com.isa.booking_entities.services.interfaces.IBoatComplaintResponseService;
import com.isa.booking_entities.services.interfaces.IBoatComplaintService;
import com.isa.booking_entities.services.interfaces.IBoatService;
import com.isa.booking_entities.services.interfaces.IClientService;
import com.isa.booking_entities.services.interfaces.IComplaintResponseService;
import com.isa.booking_entities.services.interfaces.IComplaintService;
import com.isa.booking_entities.services.interfaces.ISystemAdminService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/boat_complaints", produces = MediaType.APPLICATION_JSON_VALUE)
public class BoatComplaintController {

	private IBoatComplaintService iBoatComplaintService;

	private IBoatComplaintResponseService iBoatComplaintResponseService;

	private IComplaintResponseService iComplaintResponseService;

	private IComplaintService iComplaintService;

	private IBoatService iBoatService;

	private IClientService iClientService;

	private ISystemAdminService iSystemAdminService;

	private EmailService emailService;

	private ComplaintDTOConverter complaintDTOConverter;

	@Autowired
	public BoatComplaintController(IBoatComplaintService iBoatComplaintService,
			IBoatComplaintResponseService iBoatComplaintResponseService,
			IComplaintResponseService iComplaintResponseService, IComplaintService iComplaintService,
			IBoatService iBoatService, IClientService iClientService, ISystemAdminService iSystemAdminService,
			EmailService emailService) {
		this.iBoatComplaintService = iBoatComplaintService;
		this.iBoatComplaintResponseService = iBoatComplaintResponseService;
		this.iComplaintResponseService = iComplaintResponseService;
		this.iComplaintService = iComplaintService;
		this.iBoatService = iBoatService;
		this.iClientService = iClientService;
		this.iSystemAdminService = iSystemAdminService;
		this.emailService = emailService;
		this.complaintDTOConverter = new ComplaintDTOConverter();
	}

	@PostMapping("/createBoatComplaint")
	public ResponseEntity<Boolean> createBoatComplaint(@RequestBody BoatComplaintNewDTO boatComplaintNewDTO) {
		Client client = iClientService.getByEmail(boatComplaintNewDTO.getClientEmail());
		Boat boat = iBoatService.getById(boatComplaintNewDTO.getBoatId());
		BoatComplaint boatComplaint = iBoatComplaintService
				.createBoatComplaintByBoatComplaintNewDTO(boatComplaintNewDTO, client, boat);
		iComplaintService.save(iBoatComplaintService.save(boatComplaint));
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/createResponseForBoatComplaint")
	public ResponseEntity<Boolean> createResponseForBoatComplaint(
			@RequestBody BoatComplaintResponseNewDTO boatComplaintResponseNewDTO) {
		SystemAdmin systemAdmin = iSystemAdminService.getByEmail(boatComplaintResponseNewDTO.getAdminSystemEmail());
		BoatComplaint boatComplaint = iBoatComplaintService.getById(boatComplaintResponseNewDTO.getBoatComplaintId());
		BoatComplaintResponse boatComplaintResponse = iBoatComplaintResponseService
				.createBoatComplain(boatComplaintResponseNewDTO, systemAdmin, boatComplaint);
		iComplaintResponseService.save(iBoatComplaintResponseService.save(boatComplaintResponse));
		boatComplaint.setStatusOfComplaint(StatusOfComplaint.ANSWERED_ON_COMPLAINT);
		iComplaintService.save(iBoatComplaintService.save(boatComplaint));
		try {
			emailService.sendResponseToComplaint(boatComplaint.getClientWhoCreateComplaint().getEmail(),
					boatComplaintResponse.getText(), "boat");
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (MailException | InterruptedException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/getBoatComplaints")
	public ResponseEntity<List<BoatComplaintDisplayDTO>> getBoatComplaints() {
		return new ResponseEntity<List<BoatComplaintDisplayDTO>>(complaintDTOConverter
				.convertListBoatReservationToListBoatComplaintDisplayDTO(iBoatComplaintService.getBoatComplaints()),
				HttpStatus.OK);
	}

	@GetMapping(value = "/getBoatComplaint/{id}")
	public ResponseEntity<BoatComplaint> getBoatComplaints(@PathVariable long id) {
		return new ResponseEntity<>(iBoatComplaintService.getById(id), HttpStatus.OK);
	}
}
