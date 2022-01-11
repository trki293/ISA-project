package com.isa.booking_entities.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.isa.booking_entities.dtos.UserCreateDTO;
import com.isa.booking_entities.models.users.Authority;
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.models.users.StatusOfUser;
import com.isa.booking_entities.models.users.TypeOfUser;

public class UserCreateDTOConverter {
	
	private PasswordEncoder passwordEncoder;
	
	public UserCreateDTOConverter() {
		// TODO Auto-generated constructor stub
	}
	
	public UserCreateDTOConverter(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	public Client convertUserCreateDTOToClient(UserCreateDTO userCreateDTO) {
		Client client = new Client();
		client.setEmail(userCreateDTO.getEmail());
		client.setEnabledLogin(false);
		client.setFirstName(userCreateDTO.getFirstName());
		client.setLastName(userCreateDTO.getLastName());
		client.setLoyaltyPoints(0);
		client.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
		client.setPenaltyPoints(0);
		client.setPhoneNumber(userCreateDTO.getPhoneNumber());
		client.setResidentalAddress(userCreateDTO.getAddress());
		client.setStatuseOfUser(StatusOfUser.REGULAR);
		client.setTypeOfUser(TypeOfUser.CLIENT);
		return client;
	}

}
