package com.isa.booking_entities.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.isa.booking_entities.dtos.BoatComplaintDisplayDTO;
import com.isa.booking_entities.dtos.DeleteAccountRequestNewDTO;
import com.isa.booking_entities.dtos.DeleteAccountRequestResponseNewDTO;

import com.isa.booking_entities.models.requests.DeleteAccountRequest;
import com.isa.booking_entities.models.requests.DeleteAccountRequestResponse;
import com.isa.booking_entities.models.requests.StateOfDeleteAccountRequest;
import com.isa.booking_entities.models.users.SystemAdmin;
import com.isa.booking_entities.models.users.Users;
import com.isa.booking_entities.services.EmailService;
import com.isa.booking_entities.services.interfaces.IDeleteAccountRequestResponseService;
import com.isa.booking_entities.services.interfaces.IDeleteAccountRequestService;
import com.isa.booking_entities.services.interfaces.ISystemAdminService;
import com.isa.booking_entities.services.interfaces.IUsersService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/delete_account_requests", produces = MediaType.APPLICATION_JSON_VALUE)
public class DeleteAccountRequestController {
	
	private IDeleteAccountRequestService iDeleteAccountRequestService;
	
	private IUsersService iUserService;
	
	private ISystemAdminService iSystemAdminService;
	
	private IDeleteAccountRequestResponseService iDeleteAccountRequestResponseService;
	
	private EmailService emailService;
	
	@Autowired
	public DeleteAccountRequestController(IDeleteAccountRequestService iDeleteAccountRequestService,
			IUsersService iUserService, ISystemAdminService iSystemAdminService,
			IDeleteAccountRequestResponseService iDeleteAccountRequestResponseService, EmailService emailService) {
		this.iDeleteAccountRequestService = iDeleteAccountRequestService;
		this.iUserService = iUserService;
		this.iSystemAdminService = iSystemAdminService;
		this.iDeleteAccountRequestResponseService = iDeleteAccountRequestResponseService;
		this.emailService = emailService;
	}

	@PostMapping("/create")
	public ResponseEntity<Boolean> createDeleteAccountRequest(@RequestBody DeleteAccountRequestNewDTO deleteAccountRequestNewDTO) {
		Users user = iUserService.getByEmail(deleteAccountRequestNewDTO.getUserEmail());
		DeleteAccountRequest deleteAccountRequest = iDeleteAccountRequestService.createDeleteAccountRequest(deleteAccountRequestNewDTO, user);
		iDeleteAccountRequestService.save(deleteAccountRequest);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/checkIfExistCurrentRequest/{userEmail}")
	public ResponseEntity<Boolean> checkIfExistCurrentRequest(@PathVariable String userEmail) {
		Users user = iUserService.getByEmail(userEmail);
		return new ResponseEntity<Boolean>(iDeleteAccountRequestService.checkIfExistCurrentRequest(user), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAllDeleteAccountRequests")
	public ResponseEntity<List<DeleteAccountRequest>> getAllDeleteAccountRequests(@PathVariable String userEmail) {
		return new ResponseEntity<List<DeleteAccountRequest>>(iDeleteAccountRequestService.getAllRequestsThatHaveNoResponse(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getById/{requestId}")
	public ResponseEntity<DeleteAccountRequest> getAllDeleteAccountRequests(@PathVariable long requestId) {
		return new ResponseEntity<DeleteAccountRequest>(iDeleteAccountRequestService.getById(requestId), HttpStatus.OK);
	}
	
	@PostMapping("/createResponse")
	public ResponseEntity<Boolean> createDeleteAccountRequestResponse(@RequestBody DeleteAccountRequestResponseNewDTO deleteAccountRequestResponseNewDTO) {
		SystemAdmin systemAdmin = iSystemAdminService.getByEmail(deleteAccountRequestResponseNewDTO.getSystemAdminEmail()); 
		DeleteAccountRequest deleteAccountRequest = iDeleteAccountRequestService.getById(deleteAccountRequestResponseNewDTO.getDeleteAccountRequestId());
		DeleteAccountRequestResponse deleteAccountRequestResponse = iDeleteAccountRequestResponseService.createDeleteAccountRequestResponse(systemAdmin, deleteAccountRequest, deleteAccountRequestResponseNewDTO.getText());
		iDeleteAccountRequestResponseService.save(deleteAccountRequestResponse);
		deleteAccountRequest.setStateOfRequest(deleteAccountRequestResponseNewDTO.isApproved() ? StateOfDeleteAccountRequest.APPROVED : StateOfDeleteAccountRequest.REJECTED);
		iDeleteAccountRequestService.save(deleteAccountRequest);
		String emailText = deleteAccountRequestResponseNewDTO.isApproved()? "Account deleted.\n\n Best Regards!" : deleteAccountRequestResponseNewDTO.getText(); 
		if (deleteAccountRequestResponseNewDTO.isApproved()) {
			Users user = iUserService.getByEmail(deleteAccountRequest.getUserWhoDeleteAccount().getEmail());
			user.setDeleted(true);
			user.setEnabledLogin(false);
			iUserService.save(user);
		}
		try {
			emailService.sendMailForDeletingUser(deleteAccountRequest.getUserWhoDeleteAccount().getEmail(),emailText);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (MailException | InterruptedException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
}
