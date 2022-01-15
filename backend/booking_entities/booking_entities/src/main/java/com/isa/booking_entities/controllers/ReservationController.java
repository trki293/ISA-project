package com.isa.booking_entities.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.isa.booking_entities.dtos.ReservationFutureDisplayDTO;
import com.isa.booking_entities.models.reservations.TypeOfReservation;
import com.isa.booking_entities.services.interfaces.IReservationService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationController {
	private IReservationService iReservationService;

	@Autowired
	public ReservationController(IReservationService iReservationService) {
		this.iReservationService = iReservationService;
	}
	
	@GetMapping(value = "/getFutureReservations/{email}")
	public ResponseEntity<List<ReservationFutureDisplayDTO>> getFutureReservations(@PathVariable String email){
		return new ResponseEntity<List<ReservationFutureDisplayDTO>>(iReservationService.getFutureReservationsForClient(email), HttpStatus.OK);
	}
	
	@GetMapping(value = "/checkTypeOfReservation/{reservationId}")
	public ResponseEntity<TypeOfReservation> checkTypeOfReservation(@PathVariable long reservationId){
		return new ResponseEntity<TypeOfReservation>(iReservationService.getById(reservationId).getTypeOfReservation(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/checkPossibilityOfCancellation/{reservationId}")
	public ResponseEntity<Boolean> checkPossibilityOfCancellation(@PathVariable long reservationId){
		return new ResponseEntity<>(LocalDateTime.now().plusDays(3).isBefore(iReservationService.getById(reservationId).getTimeOfBeginingReservation()), HttpStatus.OK);
	}
}
