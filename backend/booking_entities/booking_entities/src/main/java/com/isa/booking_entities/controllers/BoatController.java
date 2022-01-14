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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.isa.booking_entities.dtos.BoatDisplayDTO;
import com.isa.booking_entities.dtos.BoatPreviewDTO;
import com.isa.booking_entities.dtos.BoatSearchDTO;
import com.isa.booking_entities.dtos.EntitySearchReservationDTO;
import com.isa.booking_entities.services.interfaces.IBoatService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/boats", produces = MediaType.APPLICATION_JSON_VALUE)
public class BoatController {
	private IBoatService iBoatService;

	@Autowired
	public BoatController(IBoatService iBoatService) {
		this.iBoatService = iBoatService;
	}
	
	@PostMapping("/unauthenticated/getAllBoats")
	public ResponseEntity<List<BoatPreviewDTO>> getAllBoatsForUnautorize(@RequestBody BoatSearchDTO instructionsSearchDTO) {
		return new ResponseEntity<>(iBoatService.getAllBoats(instructionsSearchDTO),HttpStatus.OK);
	}
	
	@PostMapping("/getAllBoatsForClient")
	public ResponseEntity<List<BoatDisplayDTO>> getAllBoatsForClient(@RequestBody EntitySearchReservationDTO boatSearchReservationDTO) {
		return new ResponseEntity<>(iBoatService.getAllBoatsForClient(boatSearchReservationDTO),HttpStatus.OK);
	}
	
	@PostMapping("/sort/title/{asc}")
	public ResponseEntity<List<BoatDisplayDTO>> sortBoatsByTitle(@RequestBody List<BoatDisplayDTO> boatDisplayDTOs, @PathVariable String asc) {
		if (asc.equals("asc")) {
			Collections.sort(boatDisplayDTOs, Comparator.comparing(BoatDisplayDTO::getTitle));
		} else if (asc.equals("desc")) {
			Collections.sort(boatDisplayDTOs, Comparator.comparing(BoatDisplayDTO::getTitle).reversed());
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<BoatDisplayDTO>>(boatDisplayDTOs, HttpStatus.OK);
	}
	
	@PostMapping("/sort/city_address/{asc}")
	public ResponseEntity<List<BoatDisplayDTO>> sortBoatsByAddressCity(@RequestBody List<BoatDisplayDTO> boatDisplayDTOs, @PathVariable String asc) {
		if (asc.equals("asc")) {
			Collections.sort(boatDisplayDTOs, Comparator.comparing(BoatDisplayDTO::getCityFromAddress));
		} else if (asc.equals("desc")) {
			Collections.sort(boatDisplayDTOs, Comparator.comparing(BoatDisplayDTO::getCityFromAddress).reversed());
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<BoatDisplayDTO>>(boatDisplayDTOs, HttpStatus.OK);
	}
	
	@PostMapping("/sort/country_address/{asc}")
	public ResponseEntity<List<BoatDisplayDTO>> sortBoatsByAddressCountry(@RequestBody List<BoatDisplayDTO> boatDisplayDTOs, @PathVariable String asc) {
		if (asc.equals("asc")) {
			Collections.sort(boatDisplayDTOs, Comparator.comparing(BoatDisplayDTO::getCountryFromAddress));
		} else if (asc.equals("desc")) {
			Collections.sort(boatDisplayDTOs, Comparator.comparing(BoatDisplayDTO::getCountryFromAddress).reversed());
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<BoatDisplayDTO>>(boatDisplayDTOs, HttpStatus.OK);
	}
	
	@PostMapping("/sort/average_grade/{asc}")
	public ResponseEntity<List<BoatDisplayDTO>> sortBoatsByAverageGrade(@RequestBody List<BoatDisplayDTO> boatDisplayDTOs, @PathVariable String asc) {
		if (asc.equals("asc")) {
			Collections.sort(boatDisplayDTOs, Comparator.comparing(BoatDisplayDTO::getAverageGrade));
		} else if (asc.equals("desc")) {
			Collections.sort(boatDisplayDTOs, Comparator.comparing(BoatDisplayDTO::getAverageGrade).reversed());
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<BoatDisplayDTO>>(boatDisplayDTOs, HttpStatus.OK);
	}
	
	@PostMapping("/sort/price/{asc}")
	public ResponseEntity<List<BoatDisplayDTO>> sortBoatsByPrice(@RequestBody List<BoatDisplayDTO> boatDisplayDTOs, @PathVariable String asc) {
		if (asc.equals("asc")) {
			Collections.sort(boatDisplayDTOs, Comparator.comparing(BoatDisplayDTO::getPricePerHour));
		} else if (asc.equals("desc")) {
			Collections.sort(boatDisplayDTOs, Comparator.comparing(BoatDisplayDTO::getPricePerHour).reversed());
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<BoatDisplayDTO>>(boatDisplayDTOs, HttpStatus.OK);
	}
}
