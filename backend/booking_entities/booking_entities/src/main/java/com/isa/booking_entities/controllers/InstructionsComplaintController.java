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
import com.isa.booking_entities.dtos.InstructionsComplaintResponseNewDTO;
import com.isa.booking_entities.dtos.InstructorComplaintDisplayDTO;
import com.isa.booking_entities.dtos.InstructorComplaintNewDTO;
import com.isa.booking_entities.models.complaints.InstructionsComplaint;
import com.isa.booking_entities.models.complaints.InstructionsComplaintResponse;
import com.isa.booking_entities.models.complaints.StatusOfComplaint;
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.models.users.Instructor;
import com.isa.booking_entities.models.users.SystemAdmin;
import com.isa.booking_entities.services.EmailService;
import com.isa.booking_entities.services.interfaces.IClientService;
import com.isa.booking_entities.services.interfaces.IComplaintResponseService;
import com.isa.booking_entities.services.interfaces.IComplaintService;
import com.isa.booking_entities.services.interfaces.IInstructionsComplaintResponseService;
import com.isa.booking_entities.services.interfaces.IInstructionsComplaintService;
import com.isa.booking_entities.services.interfaces.IInstructorService;
import com.isa.booking_entities.services.interfaces.ISystemAdminService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/instructions_complaints", produces = MediaType.APPLICATION_JSON_VALUE)
public class InstructionsComplaintController {

	private IInstructionsComplaintService iInstructionsComplaintService;

	private IInstructionsComplaintResponseService iInstructionsComplaintResponseService;

	private IComplaintResponseService iComplaintResponseService;

	private IComplaintService iComplaintService;

	private IInstructorService iInstructorService;

	private IClientService iClientService;

	private ISystemAdminService iSystemAdminService;

	private EmailService emailService;

	private ComplaintDTOConverter complaintDTOConverter;

	@Autowired
	public InstructionsComplaintController(IInstructionsComplaintService iInstructionsComplaintService,
			IInstructionsComplaintResponseService iInstructionsComplaintResponseService,
			IComplaintResponseService iComplaintResponseService, IComplaintService iComplaintService,
			IInstructorService iInstructorService, IClientService iClientService,
			ISystemAdminService iSystemAdminService, EmailService emailService) {
		this.iInstructionsComplaintService = iInstructionsComplaintService;
		this.iInstructionsComplaintResponseService = iInstructionsComplaintResponseService;
		this.iComplaintResponseService = iComplaintResponseService;
		this.iComplaintService = iComplaintService;
		this.iInstructorService = iInstructorService;
		this.iClientService = iClientService;
		this.iSystemAdminService = iSystemAdminService;
		this.emailService = emailService;
		this.complaintDTOConverter = new ComplaintDTOConverter();
	}

	@PostMapping("/createInstructionsComplaint")
	public ResponseEntity<Boolean> createInstructionsComplaint(
			@RequestBody InstructorComplaintNewDTO instructorComplaintNewDTO) {
		Client client = iClientService.getByEmail(instructorComplaintNewDTO.getClientEmail());
		Instructor instructor = iInstructorService.getById(instructorComplaintNewDTO.getInstructorId());
		InstructionsComplaint instructionsComplaint = iInstructionsComplaintService
				.createInstructionsComplaintByInstructorComplaintNewDTO(instructorComplaintNewDTO, client, instructor);
		iComplaintService.save(iInstructionsComplaintService.save(instructionsComplaint));
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/createResponseForInstructionsComplaint")
	public ResponseEntity<Boolean> createResponseForInstructionsComplaint(
			@RequestBody InstructionsComplaintResponseNewDTO instructionsComplaintResponseNewDTO) {
		SystemAdmin systemAdmin = iSystemAdminService
				.getByEmail(instructionsComplaintResponseNewDTO.getSystemAdminEmail());
		InstructionsComplaint instructionsComplaint = iInstructionsComplaintService
				.getById(instructionsComplaintResponseNewDTO.getInstructionsComplaintId());
		InstructionsComplaintResponse instructionsComplaintResponse = iInstructionsComplaintResponseService
				.createInstructionsComplain(instructionsComplaintResponseNewDTO, systemAdmin, instructionsComplaint);
		iComplaintResponseService.save(iInstructionsComplaintResponseService.save(instructionsComplaintResponse));
		instructionsComplaint.setStatusOfComplaint(StatusOfComplaint.ANSWERED_ON_COMPLAINT);
		iComplaintService.save(iInstructionsComplaintService.save(instructionsComplaint));
		try {
			emailService.sendResponseToComplaint(instructionsComplaint.getClientWhoCreateComplaint().getEmail(),
					instructionsComplaintResponse.getText(), "instructions");
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (MailException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/getInstructionsComplaints")
	public ResponseEntity<List<InstructorComplaintDisplayDTO>> getInstructionsComplaints() {
		return new ResponseEntity<List<InstructorComplaintDisplayDTO>>(
				complaintDTOConverter.convertListInstructionsReservationToListInstructorComplaintDisplayDTO(
						iInstructionsComplaintService.getInstructionsComplaints()),
				HttpStatus.OK);
	}

	@GetMapping(value = "/getInstructionsComplaint/{id}")
	public ResponseEntity<InstructionsComplaint> getInstructionsComplaints(@PathVariable long id) {
		return new ResponseEntity<>(iInstructionsComplaintService.getById(id), HttpStatus.OK);
	}

}
