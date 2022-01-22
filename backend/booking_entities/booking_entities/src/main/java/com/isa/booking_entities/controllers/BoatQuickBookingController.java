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

import com.isa.booking_entities.dtos.BoatQuickBookingDisplayDTO;
import com.isa.booking_entities.models.reservations.BoatQuickBooking;
import com.isa.booking_entities.models.reservations.BoatReservation;
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.services.EmailService;
import com.isa.booking_entities.services.interfaces.IBoatQuickBookingService;
import com.isa.booking_entities.services.interfaces.IBoatReservationService;
import com.isa.booking_entities.services.interfaces.IClientService;
import com.isa.booking_entities.services.interfaces.IQuickBookingService;
import com.isa.booking_entities.services.interfaces.IReservationService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/boat_quick_bookings", produces = MediaType.APPLICATION_JSON_VALUE)
public class BoatQuickBookingController {
	
	private IBoatQuickBookingService iBoatQuickBookingService;
	
	private IQuickBookingService iQuickBookingService;
	
	private IBoatReservationService iBoatReservationService;
	
	private IReservationService iReservationService;
	
	private IClientService iClientService;
	
	private EmailService emailService;
	
	@Autowired	
	public BoatQuickBookingController(IBoatQuickBookingService iBoatQuickBookingService,
			IQuickBookingService iQuickBookingService, IBoatReservationService iBoatReservationService,
			IReservationService iReservationService, IClientService iClientService, EmailService emailService) {
		this.iBoatQuickBookingService = iBoatQuickBookingService;
		this.iQuickBookingService = iQuickBookingService;
		this.iBoatReservationService = iBoatReservationService;
		this.iReservationService = iReservationService;
		this.iClientService = iClientService;
		this.emailService = emailService;
	}	
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@GetMapping(value = "/getQuickBooking/client/{email}/boat/{boatId}")
	public ResponseEntity<List<BoatQuickBookingDisplayDTO>> getQuickBookingForBoat(@PathVariable String email, @PathVariable long boatId){
		Client client = iClientService.getByEmail(email);
		return new ResponseEntity<List<BoatQuickBookingDisplayDTO>>(iBoatQuickBookingService.getFutureFreeQuickBookingsForBoat(boatId, client), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@PostMapping("/createBoatReservationForQuickBooking/{boatQuickBookingId}/client/{email}")
	public ResponseEntity<Boolean> createBoatReservationForQuickBooking(@PathVariable String email, @PathVariable long boatQuickBookingId) {
		Client client = iClientService.getByEmail(email);
		BoatQuickBooking boatQuickBooking = iBoatQuickBookingService.getById(boatQuickBookingId);
		BoatReservation boatReservation = iBoatQuickBookingService.createBoatReservationByBoatQuickBooking(boatQuickBooking, client);
		boatQuickBooking.setClientForQuickBooking(client);
		iBoatQuickBookingService.save(boatQuickBooking);
		iQuickBookingService.save(boatQuickBooking);
		iBoatReservationService.save(boatReservation);
		iReservationService.save(boatReservation);
		
		try {
			emailService.sendReservationConfirmationEmail(email, "boat");
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (MailException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}

}
