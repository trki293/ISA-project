package com.isa.booking_entities.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.models.users.Users;
import com.isa.booking_entities.repositories.IUsersRepository;
import com.isa.booking_entities.services.interfaces.IUsersService;

@Service
public class UsersService implements IUsersService {
	
	@Autowired
	private IUsersRepository iUsersRepository;
	
	
	@Override
	public Users getById(long id) {
		return iUsersRepository.findById(id).orElse(null);
	}

	@Override
	public List<Users> getAll() {
		return iUsersRepository.findAll();
	}

	@Override
	public Users getByEmail(String email) {
		return iUsersRepository.findAll().stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
	}

}
