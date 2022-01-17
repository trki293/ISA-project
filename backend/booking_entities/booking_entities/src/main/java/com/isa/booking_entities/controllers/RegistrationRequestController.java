package com.isa.booking_entities.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.isa.booking_entities.dtos.RegistrationRequestUpdateDTO;
import com.isa.booking_entities.models.requests.RegistrationRequest;
import com.isa.booking_entities.models.requests.StateOfRequest;
import com.isa.booking_entities.models.users.BoatOwner;
import com.isa.booking_entities.models.users.CottageOwner;
import com.isa.booking_entities.models.users.Instructor;
import com.isa.booking_entities.models.users.StatusOfUser;
import com.isa.booking_entities.models.users.SystemAdmin;
import com.isa.booking_entities.models.users.TypeOfUser;
import com.isa.booking_entities.services.EmailService;
import com.isa.booking_entities.services.interfaces.IAuthorityService;
import com.isa.booking_entities.services.interfaces.IBoatOwnerService;
import com.isa.booking_entities.services.interfaces.ICottageOwnerService;
import com.isa.booking_entities.services.interfaces.IInstructorService;
import com.isa.booking_entities.services.interfaces.IRegistrationRequestService;
import com.isa.booking_entities.services.interfaces.ISystemAdminService;
import com.isa.booking_entities.services.interfaces.IUsersService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/registration_requests", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistrationRequestController {
	
	private IRegistrationRequestService iRegistrationRequestService;
	
	private IAuthorityService iAuthorityService;
	
	private IUsersService iUsersService;
	
	private IInstructorService iInstructorService;
	
	private IBoatOwnerService iBoatOwnerService;
	
	private ISystemAdminService iSystemAdminService;
	
	private ICottageOwnerService iCottageOwnerService;
	
	private EmailService emailService;
	
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public RegistrationRequestController(ISystemAdminService iSystemAdminService, IRegistrationRequestService iRegistrationRequestService,
			IUsersService iUsersService, IInstructorService iInstructorService, IBoatOwnerService iBoatOwnerService,
			ICottageOwnerService iCottageOwnerService, EmailService emailService) {
		this.iRegistrationRequestService = iRegistrationRequestService;
		this.iUsersService = iUsersService;
		this.iInstructorService = iInstructorService;
		this.iBoatOwnerService = iBoatOwnerService;
		this.iCottageOwnerService = iCottageOwnerService;
		this.emailService = emailService;
		this.iSystemAdminService = iSystemAdminService;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}
	
	@GetMapping(value = "/getAllNewlyReceivedRequests")
	public ResponseEntity<List<RegistrationRequest>> getAllNewlyReceivedRequests(){
		return new ResponseEntity<List<RegistrationRequest>>(iRegistrationRequestService.getOnlyCreatedRegistrationRequests(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getById/{requestId}")
	public ResponseEntity<RegistrationRequest> getById(@PathVariable long requestId){
		return new ResponseEntity<RegistrationRequest>(iRegistrationRequestService.getById(requestId), HttpStatus.OK);
	}
	
	@PutMapping(value = "/changeStatus/{adminEmail}", consumes = "application/json")
	public ResponseEntity<Boolean> changeStatus(@RequestBody RegistrationRequestUpdateDTO registrationRequestUpdateDTO, @PathVariable String adminEmail) {
		RegistrationRequest registrationRequest = iRegistrationRequestService.getById(registrationRequestUpdateDTO.getRequestId());
		SystemAdmin systemAdmin = iSystemAdminService.getByEmail(adminEmail);
		registrationRequest.setStateOfRequest(registrationRequestUpdateDTO.getStateOfRequest());
		registrationRequest.setSystemAdminWhoReviewRegistrationRequest(systemAdmin);
		iRegistrationRequestService.save(registrationRequest);
		String emailText = registrationRequestUpdateDTO.getStateOfRequest()==StateOfRequest.APPROVED ? "The request was approved. You can log in with your account.\n\nBest regards!" : registrationRequestUpdateDTO.getReasoneForRejectionOfRequest();
		if (registrationRequestUpdateDTO.getStateOfRequest()==StateOfRequest.APPROVED) {
			createUserByRegistrationRequest(registrationRequest);
		}
		try {
			emailService.sendNotificationAboutApprovedRegistrationRequest(registrationRequest.getEmail(), emailText);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (MailException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
			
	}
	
	
	private void createUserByRegistrationRequest(RegistrationRequest registrationRequest) {
		if (registrationRequest.getTypeOfUser()==TypeOfUser.BOAT_OWNER) {
			BoatOwner boatOwner = new BoatOwner();
			boatOwner.setAuthorities(iAuthorityService.getByName("ROLE_BOATOWN"));
			boatOwner.setDeleted(false);
			boatOwner.setEmail(registrationRequest.getEmail());
			boatOwner.setEnabledLogin(true);
			boatOwner.setFirstName(registrationRequest.getFirstName());
			boatOwner.setLastName(registrationRequest.getLastName());
			boatOwner.setLoyaltyPoints(0);
			boatOwner.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
			boatOwner.setPhoneNumber(registrationRequest.getPhoneNumber());
			boatOwner.setResidentalAddress(registrationRequest.getResidentalAddress());
			boatOwner.setStatuseOfUser(StatusOfUser.REGULAR);
			boatOwner.setTypeOfUser(TypeOfUser.BOAT_OWNER);
			iUsersService.save(iBoatOwnerService.save(boatOwner));
		} else if (registrationRequest.getTypeOfUser()==TypeOfUser.COTTAGE_OWNER) {
			CottageOwner cottageOwner = new CottageOwner();
			cottageOwner.setAuthorities(iAuthorityService.getByName("ROLE_COTTAGEOWN"));
			cottageOwner.setDeleted(false);
			cottageOwner.setEmail(registrationRequest.getEmail());
			cottageOwner.setEnabledLogin(true);
			cottageOwner.setFirstName(registrationRequest.getFirstName());
			cottageOwner.setLastName(registrationRequest.getLastName());
			cottageOwner.setLoyaltyPoints(0);
			cottageOwner.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
			cottageOwner.setPhoneNumber(registrationRequest.getPhoneNumber());
			cottageOwner.setResidentalAddress(registrationRequest.getResidentalAddress());
			cottageOwner.setStatuseOfUser(StatusOfUser.REGULAR);
			cottageOwner.setTypeOfUser(TypeOfUser.COTTAGE_OWNER);
			iUsersService.save(iCottageOwnerService.save(cottageOwner));
		} else if (registrationRequest.getTypeOfUser()==TypeOfUser.INSTRUCTOR) {
			Instructor instructor = new Instructor();
			instructor.setAuthorities(iAuthorityService.getByName("ROLE_INSTRUCTOR"));
			instructor.setDeleted(false);
			instructor.setEmail(registrationRequest.getEmail());
			instructor.setEnabledLogin(true);
			instructor.setFirstName(registrationRequest.getFirstName());
			instructor.setLastName(registrationRequest.getLastName());
			instructor.setLoyaltyPoints(0);
			instructor.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
			instructor.setPhoneNumber(registrationRequest.getPhoneNumber());
			instructor.setResidentalAddress(registrationRequest.getResidentalAddress());
			instructor.setStatuseOfUser(StatusOfUser.REGULAR);
			instructor.setTypeOfUser(TypeOfUser.INSTRUCTOR);
			iUsersService.save(iInstructorService.save(instructor));
		}
	}
}
