package com.isa.booking_entities.services.interfaces;

import java.util.List;

import com.isa.booking_entities.models.users.Authority;

public interface IAuthorityService {
	List<Authority> getById(long id);
	List<Authority> getByName(String name);
}
