package com.isa.booking_entities.controllers;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.isa.booking_entities.dtos.EntitySearchReservationDTO;
import com.isa.booking_entities.dtos.InstructionDisplayDTO;
import com.isa.booking_entities.dtos.InstructionsPreviewDTO;
import com.isa.booking_entities.dtos.InstructionsSearchDTO;
import com.isa.booking_entities.models.entites.Instructions;
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.services.interfaces.IClientService;
import com.isa.booking_entities.services.interfaces.IInstructionsService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/instructions", produces = MediaType.APPLICATION_JSON_VALUE)
public class InstructionsController {
	private IInstructionsService iInstructionsService;
	private IClientService iClientService;

	@Autowired
	public InstructionsController(IInstructionsService iInstructionsService, IClientService iClientService) {
		this.iInstructionsService = iInstructionsService;
		this.iClientService= iClientService;
	}
	
	@PostMapping("/unauthenticated/getAllInstructions")
	public ResponseEntity<List<InstructionsPreviewDTO>> getAllInstructionsForUnautorize(@RequestBody InstructionsSearchDTO instructionsSearchDTO) {
		return new ResponseEntity<>(iInstructionsService.getAllInstructions(instructionsSearchDTO),HttpStatus.OK);
	}
	
	@PostMapping("/getAllInstructionsForClient")
	public ResponseEntity<List<InstructionDisplayDTO>> getAllInstructionsForClient(@RequestBody EntitySearchReservationDTO instructionsSearchReservationDTO) {
		return new ResponseEntity<>(iInstructionsService.getAllInstructionsForClient(instructionsSearchReservationDTO),HttpStatus.OK);
	}
	
	@GetMapping(value = "/checkSubscription/{email}/instructions/{instructionsId}")
	public ResponseEntity<Boolean> checkSubscription(@PathVariable String email, @PathVariable long instructionsId) {
		Client client= iClientService.getByEmail(email);
		Instructions instructions = client.getInstructionsSubscriptions().stream().filter(instructionsIt -> instructionsIt.getId()==instructionsId).findFirst().orElse(null);
		return new ResponseEntity<Boolean>(instructions!=null, HttpStatus.OK);
	}
	
	@PutMapping(value = "/subscribe/{email}/instructions/{instructionsId}", consumes = "application/json")
	public ResponseEntity<Boolean> subscribe(@PathVariable String email, @PathVariable long instructionsId) {
		Client client= iClientService.getByEmail(email);
		Set<Instructions> instructionsSubscriptions = client.getInstructionsSubscriptions();
		Instructions instructions = instructionsSubscriptions.stream().filter(instructionsIt -> instructionsIt.getId()==instructionsId).findFirst().orElse(null);
		if (instructions!=null) {
			client.setInstructionsSubscriptions(instructionsSubscriptions.stream().filter(instructionsIt -> instructionsIt.getId()!=instructionsId).collect(Collectors.toSet()));
		} else {
			instructionsSubscriptions.add(iInstructionsService.getById(instructionsId));
			client.setInstructionsSubscriptions(instructionsSubscriptions);
		}
		iClientService.save(client);
		return new ResponseEntity<Boolean>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAdditionalServicesForInstructions/{id}")
	public ResponseEntity<List<String>> getAdditionalServicesForInstructions(@PathVariable long id) {
		try {
			return new ResponseEntity<>(iInstructionsService.getAdditionalServicesForInstructions(id),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
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
