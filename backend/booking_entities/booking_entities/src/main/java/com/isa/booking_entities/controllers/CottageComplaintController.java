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
import com.isa.booking_entities.dtos.CottageComplaintDisplayDTO;
import com.isa.booking_entities.dtos.CottageComplaintNewDTO;
import com.isa.booking_entities.dtos.CottageComplaintResponseNewDTO;
import com.isa.booking_entities.models.complaints.CottageComplaint;
import com.isa.booking_entities.models.complaints.CottageComplaintResponse;
import com.isa.booking_entities.models.complaints.StatusOfComplaint;
import com.isa.booking_entities.models.entites.Cottage;
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.models.users.SystemAdmin;
import com.isa.booking_entities.services.EmailService;
import com.isa.booking_entities.services.interfaces.IClientService;
import com.isa.booking_entities.services.interfaces.IComplaintResponseService;
import com.isa.booking_entities.services.interfaces.IComplaintService;
import com.isa.booking_entities.services.interfaces.ICottageComplaintResponseService;
import com.isa.booking_entities.services.interfaces.ICottageComplaintService;
import com.isa.booking_entities.services.interfaces.ICottageService;
import com.isa.booking_entities.services.interfaces.ISystemAdminService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/cottage_complaints", produces = MediaType.APPLICATION_JSON_VALUE)
public class CottageComplaintController {
	private ICottageComplaintService iCottageComplaintService;

	private ICottageComplaintResponseService iCottageComplaintResponseService;

	private IComplaintResponseService iComplaintResponseService;

	private IComplaintService iComplaintService;

	private ICottageService iCottageService;

	private IClientService iClientService;

	private ISystemAdminService iSystemAdminService;

	private EmailService emailService;

	private ComplaintDTOConverter complaintDTOConverter;

	@Autowired
	public CottageComplaintController(ICottageComplaintService iCottageComplaintService,
			ICottageComplaintResponseService iCottageComplaintResponseService,
			IComplaintResponseService iComplaintResponseService, IComplaintService iComplaintService,
			ICottageService iCottageService, IClientService iClientService, ISystemAdminService iSystemAdminService,
			EmailService emailService) {
		this.iCottageComplaintService = iCottageComplaintService;
		this.iCottageComplaintResponseService = iCottageComplaintResponseService;
		this.iComplaintResponseService = iComplaintResponseService;
		this.iComplaintService = iComplaintService;
		this.iCottageService = iCottageService;
		this.iClientService = iClientService;
		this.iSystemAdminService = iSystemAdminService;
		this.emailService = emailService;
		this.complaintDTOConverter = new ComplaintDTOConverter();
	}

	@PostMapping("/createCottageComplaint")
	public ResponseEntity<Boolean> createCottageComplaint(@RequestBody CottageComplaintNewDTO cottageComplaintNewDTO) {
		Client client = iClientService.getByEmail(cottageComplaintNewDTO.getClientEmail());
		Cottage cottage = iCottageService.getById(cottageComplaintNewDTO.getCottageId());
		CottageComplaint cottageComplaint = iCottageComplaintService
				.createCottageComplaintByCottageComplaintNewDTO(cottageComplaintNewDTO, client, cottage);
		iComplaintService.save(iCottageComplaintService.save(cottageComplaint));
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/createResponseForCottageComplaint")
	public ResponseEntity<Boolean> createResponseForCottageComplaint(
			@RequestBody CottageComplaintResponseNewDTO cottageComplaintResponseNewDTO) {
		SystemAdmin systemAdmin = iSystemAdminService.getByEmail(cottageComplaintResponseNewDTO.getSystemAdminEmail());
		CottageComplaint cottageComplaint = iCottageComplaintService
				.getById(cottageComplaintResponseNewDTO.getCottageComplaintId());
		CottageComplaintResponse cottageComplaintResponse = iCottageComplaintResponseService
				.createCottageComplain(cottageComplaintResponseNewDTO, systemAdmin, cottageComplaint);
		iComplaintResponseService.save(iCottageComplaintResponseService.save(cottageComplaintResponse));
		cottageComplaint.setStatusOfComplaint(StatusOfComplaint.ANSWERED_ON_COMPLAINT);
		iComplaintService.save(iCottageComplaintService.save(cottageComplaint));
		try {
			emailService.sendResponseToComplaint(cottageComplaint.getClientWhoCreateComplaint().getEmail(),
					cottageComplaintResponse.getText(), "cottage");
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (MailException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/getCottageComplaints")
	public ResponseEntity<List<CottageComplaintDisplayDTO>> getCottageComplaints() {
		return new ResponseEntity<List<CottageComplaintDisplayDTO>>(
				complaintDTOConverter.convertListCottageReservationToListCottageComplaintDisplayDTO(
						iCottageComplaintService.getCottageComplaints()),
				HttpStatus.OK);
	}

	@GetMapping(value = "/getCottageComplaint/{id}")
	public ResponseEntity<CottageComplaint> getCottageComplaints(@PathVariable long id) {
		return new ResponseEntity<>(iCottageComplaintService.getById(id), HttpStatus.OK);
	}
}
