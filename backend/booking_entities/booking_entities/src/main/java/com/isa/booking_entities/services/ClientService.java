package com.isa.booking_entities.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.repositories.IClientRepository;
import com.isa.booking_entities.services.interfaces.IClientService;

@Service
public class ClientService implements IClientService {
	
	@Autowired
	private IClientRepository iClientRepository;
	
	@Override
	public Client save(Client client) {
		return iClientRepository.save(client);
	}

}
