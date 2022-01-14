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

import com.isa.booking_entities.dtos.CottageReservationHistoryDTO;
import com.isa.booking_entities.services.interfaces.ICottageReservationService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/cottage_reservations", produces = MediaType.APPLICATION_JSON_VALUE)
public class CottageReservationController {
	private ICottageReservationService iCottageReservationService;

	@Autowired
	public CottageReservationController(ICottageReservationService iCottageReservationService) {
		this.iCottageReservationService = iCottageReservationService;
	}
	
	@GetMapping(value = "/getHistoryOfReservation/{email}")
	public ResponseEntity<List<CottageReservationHistoryDTO>> getClientByEmail(@PathVariable String email){
		return new ResponseEntity<List<CottageReservationHistoryDTO>>(iCottageReservationService.getHistoryOfCottageReservations(email), HttpStatus.OK);
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
