package com.isa.booking_entities.controllers;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.isa.booking_entities.dtos.BoatReservationHistoryDTO;
import com.isa.booking_entities.services.interfaces.IBoatReservationService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/boat_reservations", produces = MediaType.APPLICATION_JSON_VALUE)
public class BoatReservationController {
	private IBoatReservationService iBoatReservationService;

	@Autowired
	public BoatReservationController(IBoatReservationService iBoatReservationService) {
		this.iBoatReservationService = iBoatReservationService;
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
