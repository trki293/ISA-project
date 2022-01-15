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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.isa.booking_entities.dtos.BoatReservationHistoryDTO;
import com.isa.booking_entities.dtos.BoatReservationNewDTO;
import com.isa.booking_entities.models.entites.Boat;
import com.isa.booking_entities.models.reservations.BoatQuickBooking;
import com.isa.booking_entities.models.reservations.BoatReservation;
import com.isa.booking_entities.models.reservations.StatusOfReservation;
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.services.EmailService;
import com.isa.booking_entities.services.interfaces.IBoatQuickBookingService;
import com.isa.booking_entities.services.interfaces.IBoatReservationService;
import com.isa.booking_entities.services.interfaces.IBoatService;
import com.isa.booking_entities.services.interfaces.IClientService;
import com.isa.booking_entities.services.interfaces.IQuickBookingService;
import com.isa.booking_entities.services.interfaces.IReservationService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/boat_reservations", produces = MediaType.APPLICATION_JSON_VALUE)
public class BoatReservationController {
	private IBoatReservationService iBoatReservationService;
	
	private IBoatQuickBookingService iBoatQuickBookingService;
	
	private IQuickBookingService iQuickBookingService;
	
	private IBoatService iBoatService;

	private IClientService iClientService;
	
	private IReservationService iReservationService;
	
	private EmailService emailService;
	
	@Autowired
	public BoatReservationController(IQuickBookingService iQuickBookingService,IBoatQuickBookingService iBoatQuickBookingService,EmailService emailService,IBoatReservationService iBoatReservationService,IBoatService iBoatService,IClientService iClientService,IReservationService iReservationService) {
		this.iBoatReservationService = iBoatReservationService;
		this.iBoatService = iBoatService;
		this.iClientService = iClientService;
		this.iReservationService =iReservationService;
		this.iBoatQuickBookingService =iBoatQuickBookingService;
		this.emailService = emailService;
		this.iQuickBookingService = iQuickBookingService;
	}
	
	@GetMapping(value = "/getHistoryOfReservation/{email}")
	public ResponseEntity<List<BoatReservationHistoryDTO>> getClientByEmail(@PathVariable String email){
		return new ResponseEntity<List<BoatReservationHistoryDTO>>(iBoatReservationService.getHistoryOfBoatReservations(email), HttpStatus.OK);
	}
	
	@PostMapping("/history/sort/begin_date/{asc}")
	public ResponseEntity<List<BoatReservationHistoryDTO>> sortBoatsByBeginDate(@RequestBody List<BoatReservationHistoryDTO> boatReservationHistoryDTOs, @PathVariable String asc) {
		if (asc.equals("asc")) {
			Collections.sort(boatReservationHistoryDTOs, Comparator.comparing(BoatReservationHistoryDTO::getBeginDate));
		} else if (asc.equals("desc")) {
			Collections.sort(boatReservationHistoryDTOs, Comparator.comparing(BoatReservationHistoryDTO::getBeginDate).reversed());
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<BoatReservationHistoryDTO>>(boatReservationHistoryDTOs, HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Boolean> createBoatReservation(@RequestBody BoatReservationNewDTO boatReservationNewDTO) {
		Boat boat = iBoatService.getById(boatReservationNewDTO.getBoatId());
		Client client = iClientService.getByEmail(boatReservationNewDTO.getClientEmail()); 
		BoatReservation boatReservation = iBoatReservationService.createReservation(boatReservationNewDTO,boat,client);
		iBoatReservationService.save(boatReservation);
		iReservationService.save(boatReservation);
		try {
			emailService.sendReservationConfirmationEmail(client.getEmail(), "boat");
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (MailException | InterruptedException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping(value = "/cancel_boat_reservation/{boatReservationId}", consumes = "application/json")
	public ResponseEntity<Boolean> cancelBoatReservation(@PathVariable long boatReservationId) {
		BoatReservation boatReservation = iBoatReservationService.getById(boatReservationId);
		boatReservation.setStatusOfReservation(StatusOfReservation.CANCELED);
		BoatQuickBooking boatQuickBooking = iBoatQuickBookingService.checkExistBoatQuickBookingForBoatReservation(boatReservation);
		if (boatQuickBooking!=null) {
			boatQuickBooking.setClientForQuickBooking(null);
			iBoatQuickBookingService.save(boatQuickBooking);
			iQuickBookingService.save(boatQuickBooking);
		}
		iBoatReservationService.save(boatReservation);
		iReservationService.save(boatReservation);
		return new ResponseEntity<Boolean>(HttpStatus.OK);
		
	}
	
	@PostMapping("/history/sort/duration/{asc}")
	public ResponseEntity<List<BoatReservationHistoryDTO>> sortBoatsByDuration(@RequestBody List<BoatReservationHistoryDTO> boatReservationHistoryDTOs, @PathVariable String asc) {
		if (asc.equals("asc")) {
			Collections.sort(boatReservationHistoryDTOs, Comparator.comparing(BoatReservationHistoryDTO::getDuration));
		} else if (asc.equals("desc")) {
			Collections.sort(boatReservationHistoryDTOs, Comparator.comparing(BoatReservationHistoryDTO::getDuration).reversed());
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<BoatReservationHistoryDTO>>(boatReservationHistoryDTOs, HttpStatus.OK);
	}
	
	@PostMapping("/history/sort/price/{asc}")
	public ResponseEntity<List<BoatReservationHistoryDTO>> sortBoatsByTotalPrice(@RequestBody List<BoatReservationHistoryDTO> boatReservationHistoryDTOs, @PathVariable String asc) {
		if (asc.equals("asc")) {
			Collections.sort(boatReservationHistoryDTOs, Comparator.comparing(BoatReservationHistoryDTO::getPrice));
		} else if (asc.equals("desc")) {
			Collections.sort(boatReservationHistoryDTOs, Comparator.comparing(BoatReservationHistoryDTO::getPrice).reversed());
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<BoatReservationHistoryDTO>>(boatReservationHistoryDTOs, HttpStatus.OK);
	}
}
