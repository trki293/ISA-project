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

import com.isa.booking_entities.dtos.CottageReservationHistoryDTO;
import com.isa.booking_entities.dtos.CottageReservationNewDTO;
import com.isa.booking_entities.models.entites.Cottage;
import com.isa.booking_entities.models.reservations.CottageQuickBooking;
import com.isa.booking_entities.models.reservations.CottageReservation;
import com.isa.booking_entities.models.reservations.StatusOfReservation;
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.services.EmailService;
import com.isa.booking_entities.services.interfaces.IClientService;
import com.isa.booking_entities.services.interfaces.ICottageQuickBookingService;
import com.isa.booking_entities.services.interfaces.ICottageReservationService;
import com.isa.booking_entities.services.interfaces.ICottageService;
import com.isa.booking_entities.services.interfaces.IQuickBookingService;
import com.isa.booking_entities.services.interfaces.IReservationService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/cottage_reservations", produces = MediaType.APPLICATION_JSON_VALUE)
public class CottageReservationController {
	private ICottageReservationService iCottageReservationService;
	
	private ICottageQuickBookingService iCottageQuickBookingService;
	
	private IQuickBookingService iQuickBookingService;
	
	private ICottageService iCottageService;

	private IClientService iClientService;
	
	private IReservationService iReservationService;
	
	private EmailService emailService;
	
	@Autowired
	public CottageReservationController(IQuickBookingService iQuickBookingService,ICottageQuickBookingService iCottageQuickBookingService,EmailService emailService,ICottageReservationService iCottageReservationService,ICottageService iCottageService,IClientService iClientService,IReservationService iReservationService) {
		this.iCottageReservationService = iCottageReservationService;
		this.iCottageService = iCottageService;
		this.iClientService = iClientService;
		this.iReservationService =iReservationService;
		this.iCottageQuickBookingService =iCottageQuickBookingService;
		this.emailService = emailService;
		this.iQuickBookingService = iQuickBookingService;
	}
	
	@GetMapping(value = "/getHistoryOfReservation/{email}")
	public ResponseEntity<List<CottageReservationHistoryDTO>> getClientByEmail(@PathVariable String email){
		return new ResponseEntity<List<CottageReservationHistoryDTO>>(iCottageReservationService.getHistoryOfCottageReservations(email), HttpStatus.OK);
	}
	
	@PutMapping(value = "/cancel_cottage_reservation/{cottageReservationId}", consumes = "application/json")
	public ResponseEntity<Boolean> cancelCottageReservation(@PathVariable long cottageReservationId) {
		CottageReservation cottageReservation = iCottageReservationService.getById(cottageReservationId);
		cottageReservation.setStatusOfReservation(StatusOfReservation.CANCELED);
		CottageQuickBooking cottageQuickBooking = iCottageQuickBookingService.checkExistCottageQuickBookingForCottageReservation(cottageReservation);
		if (cottageQuickBooking!=null) {
			cottageQuickBooking.setClientForQuickBooking(null);
			iCottageQuickBookingService.save(cottageQuickBooking);
			iQuickBookingService.save(cottageQuickBooking);
		}
		iCottageReservationService.save(cottageReservation);
		iReservationService.save(cottageReservation);
		return new ResponseEntity<Boolean>(HttpStatus.OK);
		
	}
	
	@PostMapping("/create")
	public ResponseEntity<Boolean> createCottageReservation(@RequestBody CottageReservationNewDTO cottageReservationNewDTO) {
		Cottage cottage = iCottageService.getById(cottageReservationNewDTO.getCottageId());
		Client client = iClientService.getByEmail(cottageReservationNewDTO.getClientEmail()); 
		CottageReservation cottageReservation = iCottageReservationService.createReservation(cottageReservationNewDTO,cottage,client);
		iCottageReservationService.save(cottageReservation);
		iReservationService.save(cottageReservation);
		try {
			emailService.sendReservationConfirmationEmail(client.getEmail(), "instruction");
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (MailException | InterruptedException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/history/sort/begin_date/{asc}")
	public ResponseEntity<List<CottageReservationHistoryDTO>> sortCottagesByBeginDate(@RequestBody List<CottageReservationHistoryDTO> cottageReservationHistoryDTOs, @PathVariable String asc) {
		if (asc.equals("asc")) {
			Collections.sort(cottageReservationHistoryDTOs, Comparator.comparing(CottageReservationHistoryDTO::getBeginDate));
		} else if (asc.equals("desc")) {
			Collections.sort(cottageReservationHistoryDTOs, Comparator.comparing(CottageReservationHistoryDTO::getBeginDate).reversed());
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<CottageReservationHistoryDTO>>(cottageReservationHistoryDTOs, HttpStatus.OK);
	}
	
	@PostMapping("/history/sort/duration/{asc}")
	public ResponseEntity<List<CottageReservationHistoryDTO>> sortCottagesByDuration(@RequestBody List<CottageReservationHistoryDTO> cottageReservationHistoryDTOs, @PathVariable String asc) {
		if (asc.equals("asc")) {
			Collections.sort(cottageReservationHistoryDTOs, Comparator.comparing(CottageReservationHistoryDTO::getDuration));
		} else if (asc.equals("desc")) {
			Collections.sort(cottageReservationHistoryDTOs, Comparator.comparing(CottageReservationHistoryDTO::getDuration).reversed());
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<CottageReservationHistoryDTO>>(cottageReservationHistoryDTOs, HttpStatus.OK);
	}
	
	@PostMapping("/history/sort/price/{asc}")
	public ResponseEntity<List<CottageReservationHistoryDTO>> sortCottagesByTotalPrice(@RequestBody List<CottageReservationHistoryDTO> cottageReservationHistoryDTOs, @PathVariable String asc) {
		if (asc.equals("asc")) {
			Collections.sort(cottageReservationHistoryDTOs, Comparator.comparing(CottageReservationHistoryDTO::getPrice));
		} else if (asc.equals("desc")) {
			Collections.sort(cottageReservationHistoryDTOs, Comparator.comparing(CottageReservationHistoryDTO::getPrice).reversed());
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<CottageReservationHistoryDTO>>(cottageReservationHistoryDTOs, HttpStatus.OK);
	}
}
