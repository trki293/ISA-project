package com.isa.booking_entities.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.isa.booking_entities.dtos.SystemParametersNewDTO;
import com.isa.booking_entities.models.SystemParameters;
import com.isa.booking_entities.services.interfaces.ISystemParametersService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/system_parameters", produces = MediaType.APPLICATION_JSON_VALUE)
public class SystemParametersController {
	
	private ISystemParametersService iSystemParametersService;

	@Autowired
	public SystemParametersController(ISystemParametersService iSystemParametersService) {
		this.iSystemParametersService = iSystemParametersService;
	}
	
	@PutMapping(value = "/update", consumes = "application/json")
	public ResponseEntity<Boolean> update(@RequestBody SystemParametersNewDTO systemParametersNewDTO) {
		SystemParameters parameters = iSystemParametersService.get();
		parameters.setBookingFee(systemParametersNewDTO.getBookingFee());
		parameters.setDiscountForGold(systemParametersNewDTO.getDiscountForGold());
		parameters.setDiscountForRegular(systemParametersNewDTO.getDiscountForRegular());
		parameters.setDiscountForSilver(systemParametersNewDTO.getDiscountForSilver());
		parameters.setPointsForClients(systemParametersNewDTO.getPointsForClients());
		parameters.setPointsForProviders(systemParametersNewDTO.getPointsForProviders());
		parameters.setThresholdForGold(systemParametersNewDTO.getThresholdForGold());
		parameters.setThresholdForSilver(systemParametersNewDTO.getThresholdForSilver());
		iSystemParametersService.save(parameters);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/get")
	public ResponseEntity<SystemParameters> getSystemParameters(){
		return new ResponseEntity<SystemParameters>(iSystemParametersService.get(), HttpStatus.OK);
	}
}
