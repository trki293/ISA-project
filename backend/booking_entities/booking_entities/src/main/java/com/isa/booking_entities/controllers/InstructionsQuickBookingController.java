package com.isa.booking_entities.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.isa.booking_entities.dtos.InstructionsQuickBookingDisplayDTO;
import com.isa.booking_entities.models.reservations.InstructionsQuickBooking;
import com.isa.booking_entities.models.reservations.InstructionsReservation;
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.services.EmailService;
import com.isa.booking_entities.services.interfaces.IClientService;
import com.isa.booking_entities.services.interfaces.IInstructionsQuickBookingService;
import com.isa.booking_entities.services.interfaces.IInstructionsReservationService;
import com.isa.booking_entities.services.interfaces.IQuickBookingService;
import com.isa.booking_entities.services.interfaces.IReservationService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/instructions_quick_bookings", produces = MediaType.APPLICATION_JSON_VALUE)
public class InstructionsQuickBookingController {
	
	private IInstructionsQuickBookingService iInstructionsQuickBookingService;
	
	private IQuickBookingService iQuickBookingService;
	
	private IInstructionsReservationService iInstructionsReservationService;
	
	private IReservationService iReservationService;
	
	private IClientService iClientService;
	
	private EmailService emailService;
	
	@Autowired	
	public InstructionsQuickBookingController(IInstructionsQuickBookingService iInstructionsQuickBookingService,
			IQuickBookingService iQuickBookingService, IInstructionsReservationService iInstructionsReservationService,
			IReservationService iReservationService, IClientService iClientService, EmailService emailService) {
		this.iInstructionsQuickBookingService = iInstructionsQuickBookingService;
		this.iQuickBookingService = iQuickBookingService;
		this.iInstructionsReservationService = iInstructionsReservationService;
		this.iReservationService = iReservationService;
		this.iClientService = iClientService;
		this.emailService = emailService;
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@GetMapping(value = "/getQuickBooking/client/{email}/instructions/{instructionsId}")
	public ResponseEntity<List<InstructionsQuickBookingDisplayDTO>> getQuickBookingForInstructions(@PathVariable String email, @PathVariable long instructionsId){
		Client client = iClientService.getByEmail(email);
		return new ResponseEntity<>(iInstructionsQuickBookingService.getFutureFreeQuickBookingsForInstructions(instructionsId, client), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@PostMapping("/createInstructionsReservationForQuickBooking/{instructionsQuickBookingId}/client/{email}")
	public ResponseEntity<Boolean> createInstructionsReservationForQuickBooking(@PathVariable String email, @PathVariable long instructionsQuickBookingId) {
		Client client = iClientService.getByEmail(email);
		InstructionsQuickBooking instructionsQuickBooking = iInstructionsQuickBookingService.getById(instructionsQuickBookingId);
		InstructionsReservation instructionsReservation = iInstructionsQuickBookingService.createInstructionsReservationByInstructionsQuickBooking(instructionsQuickBooking, client);
		instructionsQuickBooking.setClientForQuickBooking(client);
		iInstructionsQuickBookingService.save(instructionsQuickBooking);
		iQuickBookingService.save(instructionsQuickBooking);
		iInstructionsReservationService.save(instructionsReservation);
		iReservationService.save(instructionsReservation);
		
		try {
			emailService.sendReservationConfirmationEmail(email, "instructions");
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (MailException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}

}
