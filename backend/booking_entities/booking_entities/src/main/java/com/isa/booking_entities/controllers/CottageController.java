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

import com.isa.booking_entities.dtos.CottagePreviewDTO;
import com.isa.booking_entities.dtos.CottageSearchDTO;
import com.isa.booking_entities.dtos.EntitySearchReservationDTO;
import com.isa.booking_entities.dtos.CottageDisplayDTO;
import com.isa.booking_entities.services.interfaces.ICottageService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/cottages", produces = MediaType.APPLICATION_JSON_VALUE)
public class CottageController {
	
	private ICottageService iCottageService;

	@Autowired
	public CottageController(ICottageService iCottageService) {
		this.iCottageService = iCottageService;
	}
	
	@PostMapping("/unauthenticated/getAllCottages")
	public ResponseEntity<List<CottagePreviewDTO>> getAllCottagesForUnautorize(@RequestBody CottageSearchDTO cottageSearchDTO) {
		return new ResponseEntity<>(iCottageService.getAllCottages(cottageSearchDTO),HttpStatus.OK);
	}
	
	@PostMapping("/getAllCottagesForClient")
	public ResponseEntity<List<CottageDisplayDTO>> getAllCottagesForClient(@RequestBody EntitySearchReservationDTO cottageSearchReservationDTO) {
		return new ResponseEntity<>(iCottageService.getAllCottagesForClient(cottageSearchReservationDTO),HttpStatus.OK);
	}
}
