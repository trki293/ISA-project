package com.isa.booking_entities.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.isa.booking_entities.dtos.UserUpdateDTO;
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.services.interfaces.IClientService;
import com.isa.booking_entities.services.interfaces.IUsersService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/clients", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {
	
	private IClientService iClientService;
	
	private IUsersService iUsersService;

	@Autowired
	public ClientController(IClientService iClientService,IUsersService iUsersService) {
		this.iClientService = iClientService;
		this.iUsersService = iUsersService;
	}
	
	@PutMapping(value = "/update", consumes = "application/json")
	public ResponseEntity<Boolean> updateClientInfo(@RequestBody UserUpdateDTO userUpdateDTO) {
		Client client = iClientService.getByEmail(userUpdateDTO.getEmail());
		if (client.getDeleted() || client==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Client updatedClient = iClientService.updateClient(client, userUpdateDTO);
		iClientService.save(updatedClient);
		iUsersService.save(updatedClient);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/getByEmail/{email}")
	public ResponseEntity<Client> getClientByEmail(@PathVariable String email){
		Client client = iClientService.getByEmail(email);
		return (client==null) ? new ResponseEntity<Client>(client, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
