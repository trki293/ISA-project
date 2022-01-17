package com.isa.booking_entities.services.interfaces;

import java.util.List;

import com.isa.booking_entities.dtos.UserUpdateDTO;
import com.isa.booking_entities.models.users.Client;

public interface IClientService {
	Client save(Client client);
	Client updateClient(Client client, UserUpdateDTO userUpdateDTO);
	Client getByEmail(String email);
	Client getById(long id);
	List<Client> getAllNonDeletedClients();
}
