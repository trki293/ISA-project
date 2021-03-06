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
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.isa.booking_entities.models.users.Instructor;
import com.isa.booking_entities.services.interfaces.IClientService;
import com.isa.booking_entities.services.interfaces.IInstructionsService;
import com.isa.booking_entities.services.interfaces.IInstructorService;
import com.isa.booking_entities.services.interfaces.IUsersService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/instructions", produces = MediaType.APPLICATION_JSON_VALUE)
public class InstructionsController {
	private IInstructionsService iInstructionsService;
	private IInstructorService iInstructorService;
	private IClientService iClientService;
	private IUsersService iUsersService;
	
	@Autowired
	public InstructionsController(IUsersService iUsersService, IInstructorService iInstructorService, IInstructionsService iInstructionsService, IClientService iClientService) {
		this.iInstructionsService = iInstructionsService;
		this.iClientService= iClientService;
		this.iInstructorService = iInstructorService;
		this.iUsersService = iUsersService;
	}
	
	@PostMapping("/unauthenticated/getAllInstructions")
	public ResponseEntity<List<InstructionsPreviewDTO>> getAllInstructionsForUnautorize(@RequestBody InstructionsSearchDTO instructionsSearchDTO) {
		return new ResponseEntity<>(iInstructionsService.getAllInstructions(instructionsSearchDTO),HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@PostMapping("/getAllInstructionsForClient/{clientEmail}")
	public ResponseEntity<List<InstructionDisplayDTO>> getAllInstructionsForClient(@PathVariable String clientEmail,@RequestBody EntitySearchReservationDTO instructionsSearchReservationDTO) {
		Client client = iClientService.getByEmail(clientEmail);
		return new ResponseEntity<>(iInstructionsService.getAllInstructionsForClient(instructionsSearchReservationDTO, client),HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@GetMapping(value = "/checkSubscription/{email}/instructions/{instructionsId}")
	public ResponseEntity<Boolean> checkSubscription(@PathVariable String email, @PathVariable long instructionsId) {
		Client client= iClientService.getByEmail(email);
		Instructions instructions = client.getInstructionsSubscriptions().stream().filter(instructionsIt -> instructionsIt.getId()==instructionsId).findFirst().orElse(null);
		return new ResponseEntity<Boolean>(instructions!=null, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAllInstructors")
	public ResponseEntity<List<Instructor>> getAllInstructors() {
		return new ResponseEntity<List<Instructor>>(iInstructorService.getAllNonDeletedInstructors(),HttpStatus.OK);
	}
	
	@PutMapping(value = "/deleteInstructor/{instructorId}", consumes = "application/json")
	public ResponseEntity<Boolean> deleteInstructor(@PathVariable long instructorId) {
		Instructor instructor = iInstructorService.getById(instructorId);
		instructor.setDeleted(true);
		instructor.setEnabledLogin(false);
		iUsersService.save(iInstructorService.save(instructor));
		List<InstructionsPreviewDTO> allNonDeletedInstructions = iInstructionsService.getAllNonDeletedInstructions();
		for (InstructionsPreviewDTO instructionsPreviewDTO : allNonDeletedInstructions) {
			Instructions instructions = iInstructionsService.getById(instructionsPreviewDTO.getId());
			instructions.setDeleted(true);
			iInstructionsService.save(instructions);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping(value = "/deleteInstructions/{instructionsId}", consumes = "application/json")
	public ResponseEntity<Boolean> deleteInstructions(@PathVariable long instructionsId) {
		Instructions instructions = iInstructionsService.getById(instructionsId);
		instructions.setDeleted(true);
		instructions.setInstructor(null);
		iInstructionsService.save(instructions);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAllInstructions")
	public ResponseEntity<List<InstructionsPreviewDTO>> getAllInstructions() {
		return new ResponseEntity<>(iInstructionsService.getAllNonDeletedInstructions(),HttpStatus.OK);
	}
	
	@GetMapping(value = "/getInstructionsById/{id}")
	public ResponseEntity<Instructions> getInstructionsById(@PathVariable long id) {
		return new ResponseEntity<Instructions>(iInstructionsService.getById(id),HttpStatus.OK);
	}
	
	@GetMapping(value = "/getInstructorById/{id}")
	public ResponseEntity<Instructor> getInstructorById(@PathVariable long id) {
		return new ResponseEntity<Instructor>(iInstructorService.getById(id),HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
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
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@GetMapping(value = "/getAdditionalServicesForInstructions/{id}")
	public ResponseEntity<List<String>> getAdditionalServicesForInstructions(@PathVariable long id) {
		try {
			return new ResponseEntity<>(iInstructionsService.getAdditionalServicesForInstructions(id),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
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
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
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
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
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
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
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
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
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
