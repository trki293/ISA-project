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

import com.isa.booking_entities.dtos.CottageQuickBookingDisplayDTO;
import com.isa.booking_entities.models.reservations.CottageQuickBooking;
import com.isa.booking_entities.models.reservations.CottageReservation;
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.services.EmailService;
import com.isa.booking_entities.services.interfaces.IClientService;
import com.isa.booking_entities.services.interfaces.ICottageQuickBookingService;
import com.isa.booking_entities.services.interfaces.ICottageReservationService;
import com.isa.booking_entities.services.interfaces.IQuickBookingService;
import com.isa.booking_entities.services.interfaces.IReservationService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/cottage_quick_bookings", produces = MediaType.APPLICATION_JSON_VALUE)
public class CottageQuickBookingController {
	
	private ICottageQuickBookingService iCottageQuickBookingService;
	
	private IQuickBookingService iQuickBookingService;
	
	private ICottageReservationService iCottageReservationService;
	
	private IReservationService iReservationService;
	
	private IClientService iClientService;
	
	private EmailService emailService;
	
	@Autowired	
	public CottageQuickBookingController(ICottageQuickBookingService iCottageQuickBookingService,
			IQuickBookingService iQuickBookingService, ICottageReservationService iCottageReservationService,
			IReservationService iReservationService, IClientService iClientService, EmailService emailService) {
		this.iCottageQuickBookingService = iCottageQuickBookingService;
		this.iQuickBookingService = iQuickBookingService;
		this.iCottageReservationService = iCottageReservationService;
		this.iReservationService = iReservationService;
		this.iClientService = iClientService;
		this.emailService = emailService;
	}	
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@GetMapping(value = "/getQuickBooking/client/{email}/cottage/{cottageId}")
	public ResponseEntity<List<CottageQuickBookingDisplayDTO>> getQuickBookingForCottage(@PathVariable String email, @PathVariable long cottageId){
		Client client = iClientService.getByEmail(email);
		return new ResponseEntity<List<CottageQuickBookingDisplayDTO>>(iCottageQuickBookingService.getFutureFreeQuickBookingsForCottage(cottageId, client), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@PostMapping("/createCottageReservationForQuickBooking/{cottageQuickBookingId}/client/{email}")
	public ResponseEntity<Boolean> createCottageReservationForQuickBooking(@PathVariable String email, @PathVariable long cottageQuickBookingId) {
		Client client = iClientService.getByEmail(email);
		CottageQuickBooking cottageQuickBooking = iCottageQuickBookingService.getById(cottageQuickBookingId);
		CottageReservation cottageReservation = iCottageQuickBookingService.createCottageReservationByCottageQuickBooking(cottageQuickBooking, client);
		cottageQuickBooking.setClientForQuickBooking(client);
		iCottageQuickBookingService.save(cottageQuickBooking);
		iQuickBookingService.save(cottageQuickBooking);
		iCottageReservationService.save(cottageReservation);
		iReservationService.save(cottageReservation);
		
		try {
			emailService.sendReservationConfirmationEmail(email, "cottage");
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (MailException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
}
