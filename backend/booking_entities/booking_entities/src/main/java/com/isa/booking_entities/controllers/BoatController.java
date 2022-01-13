package com.isa.booking_entities.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.isa.booking_entities.dtos.BoatPreviewDTO;
import com.isa.booking_entities.dtos.BoatSearchDTO;
import com.isa.booking_entities.dtos.BoatDisplayDTO;
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
}
