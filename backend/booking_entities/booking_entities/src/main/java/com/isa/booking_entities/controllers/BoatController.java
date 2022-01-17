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

import com.isa.booking_entities.dtos.BoatDisplayDTO;
import com.isa.booking_entities.dtos.BoatPreviewDTO;
import com.isa.booking_entities.dtos.BoatSearchDTO;
import com.isa.booking_entities.dtos.EntitySearchReservationDTO;
import com.isa.booking_entities.models.entites.Boat;
import com.isa.booking_entities.models.users.BoatOwner;
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.services.interfaces.IBoatOwnerService;
import com.isa.booking_entities.services.interfaces.IBoatService;
import com.isa.booking_entities.services.interfaces.IClientService;
import com.isa.booking_entities.services.interfaces.IUsersService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/boats", produces = MediaType.APPLICATION_JSON_VALUE)
public class BoatController {
	private IBoatService iBoatService;
	
	private IBoatOwnerService iBoatOwnerService;
	
	private IClientService iClientService;
	
	private IUsersService iUsersService;

	@Autowired
	public BoatController(IUsersService iUsersService, IBoatOwnerService iBoatOwnerService, IBoatService iBoatService,IClientService iClientService) {
		this.iBoatService = iBoatService;
		this.iClientService = iClientService;
		this.iBoatOwnerService = iBoatOwnerService;
		this.iUsersService = iUsersService;
	}
	
	@PostMapping("/unauthenticated/getAllBoats")
	public ResponseEntity<List<BoatPreviewDTO>> getAllBoatsForUnautorize(@RequestBody BoatSearchDTO instructionsSearchDTO) {
		return new ResponseEntity<>(iBoatService.getAllBoats(instructionsSearchDTO),HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAllBoats")
	public ResponseEntity<List<BoatPreviewDTO>> getAllBoats() {
		return new ResponseEntity<>(iBoatService.getAllNonDeletedBoats(),HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAllBoatOwners")
	public ResponseEntity<List<BoatOwner>> getAllBoatOwners() {
		return new ResponseEntity<List<BoatOwner>>(iBoatOwnerService.getAllNonDeletedBoatOwners(),HttpStatus.OK);
	}
	
	@PostMapping("/getAllBoatsForClient")
	public ResponseEntity<List<BoatDisplayDTO>> getAllBoatsForClient(@RequestBody EntitySearchReservationDTO boatSearchReservationDTO) {
		return new ResponseEntity<>(iBoatService.getAllBoatsForClient(boatSearchReservationDTO),HttpStatus.OK);
	}
	
	@GetMapping(value = "/getBoatById/{id}")
	public ResponseEntity<Boat> getBoatById(@PathVariable long id) {
		return new ResponseEntity<Boat>(iBoatService.getById(id),HttpStatus.OK);
	}
	
	@GetMapping(value = "/getBoatOwnerById/{id}")
	public ResponseEntity<BoatOwner> getBoatOwnerById(@PathVariable long id) {
		return new ResponseEntity<BoatOwner>(iBoatOwnerService.getById(id),HttpStatus.OK);
	}
	
	@PutMapping(value = "/deleteBoatOwner/{boatOwnerId}", consumes = "application/json")
	public ResponseEntity<Boolean> deleteBoatOwner(@PathVariable long boatOwnerId) {
		BoatOwner boatOwner = iBoatOwnerService.getById(boatOwnerId);
		boatOwner.setDeleted(true);
		boatOwner.setEnabledLogin(false);
		iUsersService.save(iBoatOwnerService.save(boatOwner));
		List<BoatPreviewDTO> allNonDeletedBoat = iBoatService.getAllNonDeletedBoats();
		for (BoatPreviewDTO boatPreviewDTO : allNonDeletedBoat) {
			Boat boat = iBoatService.getById(boatPreviewDTO.getId());
			boat.setDeleted(true);
			iBoatService.save(boat);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping(value = "/deleteBoat/{boatId}", consumes = "application/json")
	public ResponseEntity<Boolean> deleteBoat(@PathVariable long boatId) {
		Boat boat = iBoatService.getById(boatId);
		boat.setDeleted(true);
		boat.setOwnerOfBoat(null);
		iBoatService.save(boat);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAdditionalServicesForBoat/{id}")
	public ResponseEntity<List<String>> getAdditionalServicesForBoat(@PathVariable long id) {
		try {
			return new ResponseEntity<>(iBoatService.getAdditionalServicesForBoat(id),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//false ne prati
	@GetMapping(value = "/checkSubscription/{email}/boat/{boatId}")
	public ResponseEntity<Boolean> checkSubscription(@PathVariable String email, @PathVariable long boatId) {
		Client client= iClientService.getByEmail(email);
		Boat boat = client.getBoatSubscriptions().stream().filter(boatIt -> boatIt.getId()==boatId).findFirst().orElse(null);
		return new ResponseEntity<Boolean>(boat!=null, HttpStatus.OK);
	}
	
	@PutMapping(value = "/subscribe/{email}/boat/{boatId}", consumes = "application/json")
	public ResponseEntity<Boolean> subscribe(@PathVariable String email, @PathVariable long boatId) {
		Client client= iClientService.getByEmail(email);
		Set<Boat> boatSubscriptions = client.getBoatSubscriptions();
		Boat boat = boatSubscriptions.stream().filter(boatIt -> boatIt.getId()==boatId).findFirst().orElse(null);
		if (boat!=null) {
			client.setBoatSubscriptions(boatSubscriptions.stream().filter(boatIt -> boatIt.getId()!=boatId).collect(Collectors.toSet()));
		} else {
			boatSubscriptions.add(iBoatService.getById(boatId));
			client.setBoatSubscriptions(boatSubscriptions);
		}
		iClientService.save(client);
		return new ResponseEntity<Boolean>(HttpStatus.OK);
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
