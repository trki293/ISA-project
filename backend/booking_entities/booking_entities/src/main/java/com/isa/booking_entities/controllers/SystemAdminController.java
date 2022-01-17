package com.isa.booking_entities.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.isa.booking_entities.dtos.BoatPreviewDTO;
import com.isa.booking_entities.dtos.SystemAdminNewDTO;
import com.isa.booking_entities.dtos.SystemAdminUpdateDTO;
import com.isa.booking_entities.dtos.SystemAdminUpdatePasswordDTO;

import com.isa.booking_entities.models.entites.Boat;
import com.isa.booking_entities.models.users.BoatOwner;
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.models.users.SystemAdmin;
import com.isa.booking_entities.models.users.Users;
import com.isa.booking_entities.repositories.IAuthorityRepository;
import com.isa.booking_entities.services.interfaces.IAuthorityService;
import com.isa.booking_entities.services.interfaces.ISystemAdminService;
import com.isa.booking_entities.services.interfaces.IUsersService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/system_admins", produces = MediaType.APPLICATION_JSON_VALUE)
public class SystemAdminController {
	private ISystemAdminService iSystemAdminService;
	
	private IUsersService iUsersService;
	
	private IAuthorityService iAuthorityService;
	
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public SystemAdminController(IAuthorityService iAuthorityService, ISystemAdminService iSystemAdminService, IUsersService iUsersService) {
		this.iSystemAdminService = iSystemAdminService;
		this.iUsersService = iUsersService;
		this.iAuthorityService = iAuthorityService;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}
	
	@PostMapping("/createNew")
	public ResponseEntity<Boolean> createNewAdmin(@RequestBody SystemAdminNewDTO systemAdminNewDTO) {
		Users userWithEmail = iUsersService.getByEmail(systemAdminNewDTO.getEmail());
		if (userWithEmail!=null || !systemAdminNewDTO.getPassword().equals(systemAdminNewDTO.getConfirmationPassword())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		SystemAdmin systemAdmin = iSystemAdminService.createSystemAdmin(systemAdminNewDTO);
		systemAdmin.setAuthorities(iAuthorityService.getByName("ROLE_ADMINSYS"));
		iUsersService.save(iSystemAdminService.save(systemAdmin));
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping(value = "/updateSystemAdmin", consumes = "application/json")
	public ResponseEntity<Boolean> updateUser(@RequestBody SystemAdminUpdateDTO systemAdminUpdateDTO) {
		SystemAdmin systemAdmin = iSystemAdminService.getByEmail(systemAdminUpdateDTO.getEmail());
		if (systemAdmin==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		systemAdmin.setFirstName(systemAdminUpdateDTO.getFirstName());
		systemAdmin.setLastName(systemAdminUpdateDTO.getLastName());
		systemAdmin.setPhoneNumber(systemAdminUpdateDTO.getPhoneNumber());
		systemAdmin.setResidentalAddress(systemAdminUpdateDTO.getAddress());
		iUsersService.save(iSystemAdminService.save(systemAdmin));
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping(value = "/changePassword/{systemAdminEmail}", consumes = "application/json")
	public ResponseEntity<Boolean> updateUser(@PathVariable String systemAdminEmail, @RequestBody SystemAdminUpdatePasswordDTO systemAdminUpdatePasswordDTO) {
		SystemAdmin systemAdmin = iSystemAdminService.getByEmail(systemAdminEmail);
		if (systemAdmin==null || !systemAdminUpdatePasswordDTO.getNewPassword().equals(systemAdminUpdatePasswordDTO.getConfirmNewPassword())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		systemAdmin.setPassword(passwordEncoder.encode(systemAdminUpdatePasswordDTO.getNewPassword()));
		systemAdmin.setFirstLogin(false);
		iUsersService.save(iSystemAdminService.save(systemAdmin));
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/checkIfFirstLogin/{systemAdminEmail}")
	public ResponseEntity<Boolean> getAllClients(@PathVariable String systemAdminEmail) {
		SystemAdmin systemAdmin = iSystemAdminService.getByEmail(systemAdminEmail);
		if (systemAdmin==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Boolean>(systemAdmin.isFirstLogin(), HttpStatus.OK);
	}
}
