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
}
