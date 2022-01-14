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

import com.isa.booking_entities.dtos.CottageDisplayDTO;
import com.isa.booking_entities.dtos.CottagePreviewDTO;
import com.isa.booking_entities.dtos.CottageSearchDTO;
import com.isa.booking_entities.dtos.EntitySearchReservationDTO;
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
