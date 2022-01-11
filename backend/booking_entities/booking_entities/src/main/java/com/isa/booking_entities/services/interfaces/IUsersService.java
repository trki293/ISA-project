package com.isa.booking_entities.services.interfaces;

import java.util.List;

import com.isa.booking_entities.models.users.Users;

public interface IUsersService {
	Users getById(long id);
	Users getByEmail(String email);
	List<Users> getAll();
	Users save(Users user);
}
