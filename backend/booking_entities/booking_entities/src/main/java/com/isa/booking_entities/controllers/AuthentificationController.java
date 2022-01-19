package com.isa.booking_entities.controllers;

import java.time.LocalDate;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.isa.booking_entities.converter.UsersDTOConverter;
import com.isa.booking_entities.dtos.UserCreateDTO;
import com.isa.booking_entities.dtos.UserLoginDTO;
import com.isa.booking_entities.dtos.UserTokenStateDTO;
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.models.users.ConfirmationToken;
import com.isa.booking_entities.models.users.Users;
import com.isa.booking_entities.security.TokenUtils;
import com.isa.booking_entities.services.CustomUserDetailsService;
import com.isa.booking_entities.services.EmailService;
import com.isa.booking_entities.services.interfaces.IAuthorityService;
import com.isa.booking_entities.services.interfaces.IClientService;
import com.isa.booking_entities.services.interfaces.IConfirmationTokenService;
import com.isa.booking_entities.services.interfaces.IUsersService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthentificationController {
	private TokenUtils tokenUtils;

	private AuthenticationManager authenticationManager;

	private CustomUserDetailsService userDetailsService;

	private IConfirmationTokenService iConfirmationTokenService;

	private IUsersService iUsersService;

	private UsersDTOConverter usersDTOConverter;

	private IClientService iClientService;

	private IAuthorityService iAuthorityService;
	
	private EmailService emailService;
	
	private PasswordEncoder passwordEncoder;

	@Autowired
	public AuthentificationController(IConfirmationTokenService iConfirmationTokenService, TokenUtils tokenUtils,
			AuthenticationManager authenticationManager, CustomUserDetailsService userDetailsService,
			IUsersService iUsersService, IClientService iClientService, IAuthorityService iAuthorityService,EmailService emailService) {
		this.tokenUtils = tokenUtils;
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
		this.iUsersService = iUsersService;
		this.usersDTOConverter = new UsersDTOConverter(new BCryptPasswordEncoder());
		this.iClientService = iClientService;
		this.iAuthorityService = iAuthorityService;
		this.iConfirmationTokenService = iConfirmationTokenService;
		this.emailService=emailService;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	@PostMapping("/login")
	public ResponseEntity<UserTokenStateDTO> createAuthenticationToken(@RequestBody UserLoginDTO userLoginDTO,
			HttpServletResponse response) {
		Users userInDB = iUsersService.getByEmail(userLoginDTO.getEmail());
		if (authenticationManager == null) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		} else if (userLoginDTO == null) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
		
		if (userInDB == null || !userInDB.isEnabledLogin()) {
			return new ResponseEntity<UserTokenStateDTO>(HttpStatus.BAD_REQUEST);
		}
		//
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userLoginDTO.getEmail(), userLoginDTO.getPassword()));

		// Ubaci korisnika u trenutni security kontekst
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Kreiraj token za tog korisnika
		Users user = (Users) authentication.getPrincipal();
		String jwt = tokenUtils.generateToken(user.getUsername());
		int expiresIn = tokenUtils.getExpiredIn();

		// Vrati token kao odgovor na uspesnu autentifikaciju
		return ResponseEntity.ok(new UserTokenStateDTO(jwt, userInDB.getEmail(), userInDB.getTypeOfUser(), expiresIn));
	}

	@PostMapping("/registration")
	public ResponseEntity<Users> registerUser(@RequestBody UserCreateDTO userCreateDTO,
			UriComponentsBuilder ucBuilder) {
		if (iUsersService.getByEmail(userCreateDTO.getEmail()) != null
				|| !userCreateDTO.getPassword().equals(userCreateDTO.getConfirmationPassword())) {
			System.out.println("Usao ovdje!"+ iUsersService.getByEmail(userCreateDTO.getEmail()).getEmail());
			
		}
		Client client = usersDTOConverter.convertUserCreateDTOToClient(userCreateDTO);
		client.setAuthorities(iAuthorityService.getByName("ROLE_CLIENT"));
		iClientService.save(client);
		Users userCreated = iUsersService.save(client);
		ConfirmationToken confirmationToken = iConfirmationTokenService.save(new ConfirmationToken(userCreated));
		try {
			emailService.sendConfirmationMail(userCreated.getEmail(), confirmationToken.getConfirmationToken());
			return new ResponseEntity<Users>(userCreated, HttpStatus.CREATED);
		} catch (MailException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Users>(HttpStatus.BAD_REQUEST);
		}
		

	}
	
	@PutMapping(value = "/confirm_account/{token}", consumes = "application/json")
	public ResponseEntity<Boolean> confirmAccount(@PathVariable String token) {
		ConfirmationToken confirmationToken = iConfirmationTokenService.getByToken(token);
		System.out.println(confirmationToken.getConfirmationToken());
		if (confirmationToken != null && confirmationToken.getExpiryDate().isAfter(LocalDate.now())) {
			Users users = iUsersService.getByEmail(confirmationToken.getUsers().getEmail());
			users.setEnabledLogin(true);
			iUsersService.save(users);
			return new ResponseEntity<>(HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
			
	}
}
