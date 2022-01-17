package com.isa.booking_entities.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.isa.booking_entities.models.users.BoatOwner;
import com.isa.booking_entities.repositories.IBoatOwnerRepository;
import com.isa.booking_entities.services.interfaces.IBoatOwnerService;

public class BoatOwnerService implements IBoatOwnerService {
	
	private IBoatOwnerRepository iBoatOwnerRepository;
	
	@Autowired
	public BoatOwnerService(IBoatOwnerRepository iBoatOwnerRepository) {
		this.iBoatOwnerRepository = iBoatOwnerRepository;
	}

	@Override
	public BoatOwner save(BoatOwner boatOwner) {
		return iBoatOwnerRepository.save(boatOwner);
	}

	@Override
	public BoatOwner getById(long id) {
		return iBoatOwnerRepository.findById(id).orElse(null);
	}

	@Override
	public BoatOwner getByEmail(String email) {
		return iBoatOwnerRepository.findAll().stream().filter(boatOwnerIt -> boatOwnerIt.getEmail().equals(email)).findFirst().orElse(null);
	}

}
