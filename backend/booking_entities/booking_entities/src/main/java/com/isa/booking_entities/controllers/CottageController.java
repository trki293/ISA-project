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

import com.isa.booking_entities.dtos.CottageDisplayDTO;
import com.isa.booking_entities.dtos.CottagePreviewDTO;
import com.isa.booking_entities.dtos.CottageSearchDTO;
import com.isa.booking_entities.dtos.EntitySearchReservationDTO;
import com.isa.booking_entities.models.entites.Cottage;
import com.isa.booking_entities.models.entites.Cottage;
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.services.interfaces.IClientService;
import com.isa.booking_entities.services.interfaces.ICottageService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/cottages", produces = MediaType.APPLICATION_JSON_VALUE)
public class CottageController {
	
	private ICottageService iCottageService;
	
	private IClientService iClientService;

	@Autowired
	public CottageController(ICottageService iCottageService,IClientService iClientService) {
		this.iCottageService = iCottageService;
		this.iClientService = iClientService;
	}
	
	@PostMapping("/unauthenticated/getAllCottages")
	public ResponseEntity<List<CottagePreviewDTO>> getAllCottagesForUnautorize(@RequestBody CottageSearchDTO cottageSearchDTO) {
		return new ResponseEntity<>(iCottageService.getAllCottages(cottageSearchDTO),HttpStatus.OK);
	}
	
	@PostMapping("/getAllCottagesForClient")
	public ResponseEntity<List<CottageDisplayDTO>> getAllCottagesForClient(@RequestBody EntitySearchReservationDTO cottageSearchReservationDTO) {
		return new ResponseEntity<>(iCottageService.getAllCottagesForClient(cottageSearchReservationDTO),HttpStatus.OK);
	}
	
	@GetMapping(value = "/checkSubscription/{email}/cottage/{cottageId}")
	public ResponseEntity<Boolean> checkSubscription(@PathVariable String email, @PathVariable long cottageId) {
		Client client= iClientService.getByEmail(email);
		Cottage cottage = client.getCottageSubscriptions().stream().filter(cottageIt -> cottageIt.getId()==cottageId).findFirst().orElse(null);
		return new ResponseEntity<Boolean>(cottage!=null, HttpStatus.OK);
	}
	
	@PutMapping(value = "/subscribe/{email}/cottage/{cottageId}", consumes = "application/json")
	public ResponseEntity<Boolean> subscribe(@PathVariable String email, @PathVariable long cottageId) {
		Client client= iClientService.getByEmail(email);
		Set<Cottage> cottageSubscriptions = client.getCottageSubscriptions();
		Cottage cottage = cottageSubscriptions.stream().filter(cottageIt -> cottageIt.getId()==cottageId).findFirst().orElse(null);
		if (cottage!=null) {
			client.setCottageSubscriptions(cottageSubscriptions.stream().filter(cottageIt -> cottageIt.getId()!=cottageId).collect(Collectors.toSet()));
		} else {
			cottageSubscriptions.add(iCottageService.getById(cottageId));
			client.setCottageSubscriptions(cottageSubscriptions);
		}
		iClientService.save(client);
		return new ResponseEntity<Boolean>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAdditionalServicesForCottage/{id}")
	public ResponseEntity<List<String>> getAdditionalServicesForCottage(@PathVariable long id) {
		try {
			return new ResponseEntity<>(iCottageService.getAdditionalServicesForCottage(id),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/sort/title/{asc}")
	public ResponseEntity<List<CottageDisplayDTO>> sortCottagesByTitle(@RequestBody List<CottageDisplayDTO> cottageDisplayDTOs, @PathVariable String asc) {
		if (asc.equals("asc")) {
			Collections.sort(cottageDisplayDTOs, Comparator.comparing(CottageDisplayDTO::getTitle));
		} else if (asc.equals("desc")) {
			Collections.sort(cottageDisplayDTOs, Comparator.comparing(CottageDisplayDTO::getTitle).reversed());
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<CottageDisplayDTO>>(cottageDisplayDTOs, HttpStatus.OK);
	}
	
	@PostMapping("/sort/city_address/{asc}")
	public ResponseEntity<List<CottageDisplayDTO>> sortCottagesByAddressCity(@RequestBody List<CottageDisplayDTO> cottageDisplayDTOs, @PathVariable String asc) {
		if (asc.equals("asc")) {
			Collections.sort(cottageDisplayDTOs, Comparator.comparing(CottageDisplayDTO::getCityFromAddress));
		} else if (asc.equals("desc")) {
			Collections.sort(cottageDisplayDTOs, Comparator.comparing(CottageDisplayDTO::getCityFromAddress).reversed());
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<CottageDisplayDTO>>(cottageDisplayDTOs, HttpStatus.OK);
	}
	
	@PostMapping("/sort/country_address/{asc}")
	public ResponseEntity<List<CottageDisplayDTO>> sortCottagesByAddressCountry(@RequestBody List<CottageDisplayDTO> cottageDisplayDTOs, @PathVariable String asc) {
		if (asc.equals("asc")) {
			Collections.sort(cottageDisplayDTOs, Comparator.comparing(CottageDisplayDTO::getCountryFromAddress));
		} else if (asc.equals("desc")) {
			Collections.sort(cottageDisplayDTOs, Comparator.comparing(CottageDisplayDTO::getCountryFromAddress).reversed());
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<CottageDisplayDTO>>(cottageDisplayDTOs, HttpStatus.OK);
	}
	
	@PostMapping("/sort/average_grade/{asc}")
	public ResponseEntity<List<CottageDisplayDTO>> sortCottagesByAverageGrade(@RequestBody List<CottageDisplayDTO> cottageDisplayDTOs, @PathVariable String asc) {
		if (asc.equals("asc")) {
			Collections.sort(cottageDisplayDTOs, Comparator.comparing(CottageDisplayDTO::getAverageGrade));
		} else if (asc.equals("desc")) {
			Collections.sort(cottageDisplayDTOs, Comparator.comparing(CottageDisplayDTO::getAverageGrade).reversed());
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<CottageDisplayDTO>>(cottageDisplayDTOs, HttpStatus.OK);
	}
	
	@PostMapping("/sort/price/{asc}")
	public ResponseEntity<List<CottageDisplayDTO>> sortCottagesByPrice(@RequestBody List<CottageDisplayDTO> cottageDisplayDTOs, @PathVariable String asc) {
		if (asc.equals("asc")) {
			Collections.sort(cottageDisplayDTOs, Comparator.comparing(CottageDisplayDTO::getPricePerNight));
		} else if (asc.equals("desc")) {
			Collections.sort(cottageDisplayDTOs, Comparator.comparing(CottageDisplayDTO::getPricePerNight).reversed());
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<CottageDisplayDTO>>(cottageDisplayDTOs, HttpStatus.OK);
	}
}
