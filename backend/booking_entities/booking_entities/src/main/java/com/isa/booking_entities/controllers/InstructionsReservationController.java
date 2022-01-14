package com.isa.booking_entities.controllers;

import java.util.Collections;
import java.util.Comparator;
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

import com.isa.booking_entities.dtos.InstructionsReservationHistoryDTO;
import com.isa.booking_entities.dtos.InstructionsReservationNewDTO;
import com.isa.booking_entities.models.entites.Instructions;
import com.isa.booking_entities.models.reservations.InstructionsReservation;
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.services.EmailService;
import com.isa.booking_entities.services.interfaces.IClientService;
import com.isa.booking_entities.services.interfaces.IInstructionsReservationService;
import com.isa.booking_entities.services.interfaces.IInstructionsService;
import com.isa.booking_entities.services.interfaces.IReservationService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/instructions_reservations", produces = MediaType.APPLICATION_JSON_VALUE)
public class InstructionsReservationController {
	private IInstructionsReservationService iInstructionsReservationService;

	private IInstructionsService iInstructionsService;

	private IClientService iClientService;
	
	private IReservationService iReservationService;
	
	private EmailService emailService;

	@Autowired
	public InstructionsReservationController(EmailService emailService, IInstructionsReservationService iInstructionsReservationService,
			IInstructionsService iInstructionsService, IClientService iClientService, IReservationService iReservationService) {
		this.iInstructionsReservationService = iInstructionsReservationService;
		this.iInstructionsService = iInstructionsService;
		this.iClientService = iClientService;
		this.iReservationService = iReservationService;
		this.emailService = emailService;
	}

	@GetMapping(value = "/getHistoryOfReservation/{email}")
	public ResponseEntity<List<InstructionsReservationHistoryDTO>> getClientByEmail(@PathVariable String email) {
		return new ResponseEntity<List<InstructionsReservationHistoryDTO>>(
				iInstructionsReservationService.getHistoryOfInstructionsReservations(email), HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Boolean> createInstructionsReservation(@RequestBody InstructionsReservationNewDTO instructionsReservationNewDTO) {
		Instructions instructions = iInstructionsService.getById(instructionsReservationNewDTO.getInstructionsId());
		Client client = iClientService.getByEmail(instructionsReservationNewDTO.getClientEmail()); 
		InstructionsReservation instructionsReservation = iInstructionsReservationService.createReservation(instructionsReservationNewDTO,instructions,client);
		iInstructionsReservationService.save(instructionsReservation);
		iReservationService.save(instructionsReservation);
		try {
			emailService.sendReservationConfirmationEmail(client.getEmail(), "instructio");
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (MailException | InterruptedException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/history/sort/begin_date/{asc}")
	public ResponseEntity<List<InstructionsReservationHistoryDTO>> sortInstructionssByBeginDate(
			@RequestBody List<InstructionsReservationHistoryDTO> instructionsReservationHistoryDTOs,
			@PathVariable String asc) {
		if (asc.equals("asc")) {
			Collections.sort(instructionsReservationHistoryDTOs,
					Comparator.comparing(InstructionsReservationHistoryDTO::getBeginDate));
		} else if (asc.equals("desc")) {
			Collections.sort(instructionsReservationHistoryDTOs,
					Comparator.comparing(InstructionsReservationHistoryDTO::getBeginDate).reversed());
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<InstructionsReservationHistoryDTO>>(instructionsReservationHistoryDTOs,
				HttpStatus.OK);
	}

	@PostMapping("/history/sort/duration/{asc}")
	public ResponseEntity<List<InstructionsReservationHistoryDTO>> sortInstructionssByDuration(
			@RequestBody List<InstructionsReservationHistoryDTO> instructionsReservationHistoryDTOs,
			@PathVariable String asc) {
		if (asc.equals("asc")) {
			Collections.sort(instructionsReservationHistoryDTOs,
					Comparator.comparing(InstructionsReservationHistoryDTO::getDuration));
		} else if (asc.equals("desc")) {
			Collections.sort(instructionsReservationHistoryDTOs,
					Comparator.comparing(InstructionsReservationHistoryDTO::getDuration).reversed());
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<InstructionsReservationHistoryDTO>>(instructionsReservationHistoryDTOs,
				HttpStatus.OK);
	}

	@PostMapping("/history/sort/price/{asc}")
	public ResponseEntity<List<InstructionsReservationHistoryDTO>> sortInstructionssByTotalPrice(
			@RequestBody List<InstructionsReservationHistoryDTO> instructionsReservationHistoryDTOs,
			@PathVariable String asc) {
		if (asc.equals("asc")) {
			Collections.sort(instructionsReservationHistoryDTOs,
					Comparator.comparing(InstructionsReservationHistoryDTO::getPrice));
		} else if (asc.equals("desc")) {
			Collections.sort(instructionsReservationHistoryDTOs,
					Comparator.comparing(InstructionsReservationHistoryDTO::getPrice).reversed());
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<InstructionsReservationHistoryDTO>>(instructionsReservationHistoryDTOs,
				HttpStatus.OK);
	}
}
