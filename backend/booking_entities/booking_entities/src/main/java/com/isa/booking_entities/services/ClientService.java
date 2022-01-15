package com.isa.booking_entities.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.dtos.UserUpdateDTO;
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.repositories.IClientRepository;
import com.isa.booking_entities.services.interfaces.IClientService;

@Service
public class ClientService implements IClientService {
	
	private IClientRepository iClientRepository;

	@Autowired
	public ClientService(IClientRepository iClientRepository) {
		this.iClientRepository = iClientRepository;
	}
	
	@Override
	public Client save(Client client) {
		return iClientRepository.save(client);
	}

	@Override
	public Client updateClient(Client client, UserUpdateDTO userUpdateDTO) {
		client.setFirstName(userUpdateDTO.getFirstName());
		client.setLastName(userUpdateDTO.getLastName());
		client.setResidentalAddress(userUpdateDTO.getAddress());
		client.setPhoneNumber(userUpdateDTO.getPhoneNumber());
		return client;
		
	}

	@Override
	public Client getByEmail(String email) {
		return iClientRepository.findAll().stream().filter(clientIt -> clientIt.getEmail().equals(email)).findFirst().orElse(null);
	}

}
