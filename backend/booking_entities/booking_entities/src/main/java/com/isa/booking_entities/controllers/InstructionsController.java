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

import com.isa.booking_entities.dtos.EntitySearchReservationDTO;
import com.isa.booking_entities.dtos.InstructionDisplayDTO;
import com.isa.booking_entities.dtos.InstructionsPreviewDTO;
import com.isa.booking_entities.dtos.InstructionsSearchDTO;
import com.isa.booking_entities.services.interfaces.IInstructionsService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/instructions", produces = MediaType.APPLICATION_JSON_VALUE)
public class InstructionsController {
	private IInstructionsService iInstructionsService;

	@Autowired
	public InstructionsController(IInstructionsService iInstructionsService) {
		this.iInstructionsService = iInstructionsService;
	}
	
	@PostMapping("/unauthenticated/getAllInstructions")
	public ResponseEntity<List<InstructionsPreviewDTO>> getAllInstructionsForUnautorize(@RequestBody InstructionsSearchDTO instructionsSearchDTO) {
		return new ResponseEntity<>(iInstructionsService.getAllInstructions(instructionsSearchDTO),HttpStatus.OK);
	}
	
	@PostMapping("/getAllInstructionsForClient")
	public ResponseEntity<List<InstructionDisplayDTO>> getAllInstructionsForClient(@RequestBody EntitySearchReservationDTO instructionsSearchReservationDTO) {
		return new ResponseEntity<>(iInstructionsService.getAllInstructionsForClient(instructionsSearchReservationDTO),HttpStatus.OK);
	}
	
	@PostMapping("/sort/title/{asc}")
	public ResponseEntity<List<InstructionDisplayDTO>> sortInstructionsByTitle(@RequestBody List<InstructionDisplayDTO> boatDisplayDTOs, @PathVariable String asc) {
		if (asc.equals("asc")) {
			Collections.sort(boatDisplayDTOs, Comparator.comparing(InstructionDisplayDTO::getTitle));
		} else if (asc.equals("desc")) {
			Collections.sort(boatDisplayDTOs, Comparator.comparing(InstructionDisplayDTO::getTitle).reversed());
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<InstructionDisplayDTO>>(boatDisplayDTOs, HttpStatus.OK);
	}
	
	@PostMapping("/sort/city_address/{asc}")
	public ResponseEntity<List<InstructionDisplayDTO>> sortInstructionsByAddressCity(@RequestBody List<InstructionDisplayDTO> boatDisplayDTOs, @PathVariable String asc) {
		if (asc.equals("asc")) {
			Collections.sort(boatDisplayDTOs, Comparator.comparing(InstructionDisplayDTO::getCityFromAddress));
		} else if (asc.equals("desc")) {
			Collections.sort(boatDisplayDTOs, Comparator.comparing(InstructionDisplayDTO::getCityFromAddress).reversed());
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<InstructionDisplayDTO>>(boatDisplayDTOs, HttpStatus.OK);
	}
	
	@PostMapping("/sort/country_address/{asc}")
	public ResponseEntity<List<InstructionDisplayDTO>> sortInstructionsByAddressCountry(@RequestBody List<InstructionDisplayDTO> boatDisplayDTOs, @PathVariable String asc) {
		if (asc.equals("asc")) {
			Collections.sort(boatDisplayDTOs, Comparator.comparing(InstructionDisplayDTO::getCountryFromAddress));
		} else if (asc.equals("desc")) {
			Collections.sort(boatDisplayDTOs, Comparator.comparing(InstructionDisplayDTO::getCountryFromAddress).reversed());
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<InstructionDisplayDTO>>(boatDisplayDTOs, HttpStatus.OK);
	}
	
	@PostMapping("/sort/average_grade/{asc}")
	public ResponseEntity<List<InstructionDisplayDTO>> sortInstructionsByAverageGrade(@RequestBody List<InstructionDisplayDTO> boatDisplayDTOs, @PathVariable String asc) {
		if (asc.equals("asc")) {
			Collections.sort(boatDisplayDTOs, Comparator.comparing(InstructionDisplayDTO::getAverageGrade));
		} else if (asc.equals("desc")) {
			Collections.sort(boatDisplayDTOs, Comparator.comparing(InstructionDisplayDTO::getAverageGrade).reversed());
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<InstructionDisplayDTO>>(boatDisplayDTOs, HttpStatus.OK);
	}
	
	@PostMapping("/sort/price/{asc}")
	public ResponseEntity<List<InstructionDisplayDTO>> sortInstructionsByPrice(@RequestBody List<InstructionDisplayDTO> boatDisplayDTOs, @PathVariable String asc) {
		if (asc.equals("asc")) {
			Collections.sort(boatDisplayDTOs, Comparator.comparing(InstructionDisplayDTO::getPricePerHour));
		} else if (asc.equals("desc")) {
			Collections.sort(boatDisplayDTOs, Comparator.comparing(InstructionDisplayDTO::getPricePerHour).reversed());
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<InstructionDisplayDTO>>(boatDisplayDTOs, HttpStatus.OK);
	}
}
