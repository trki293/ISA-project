package com.isa.booking_entities.services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.models.users.Authority;
import com.isa.booking_entities.repositories.IAuthorityRepository;
import com.isa.booking_entities.services.interfaces.IAuthorityService;

@Service
public class AuthorityService implements IAuthorityService {
	
	@Autowired
	private IAuthorityRepository iAuthorityRepository;
	
	@Override
	public List<Authority> getById(long id) {
		return iAuthorityRepository.findAll().stream().filter(authority -> authority.getId()==id).collect(Collectors.toList());
	}

	@Override
	public List<Authority> getByName(String name) {
		return iAuthorityRepository.findAll().stream().filter(authority -> authority.getName().equals(name)).collect(Collectors.toList());
	}

}
