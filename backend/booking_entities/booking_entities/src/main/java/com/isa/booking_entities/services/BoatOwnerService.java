package com.isa.booking_entities.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.models.users.BoatOwner;
import com.isa.booking_entities.repositories.IBoatOwnerRepository;
import com.isa.booking_entities.services.interfaces.IBoatOwnerService;

@Service
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

	@Override
	public List<BoatOwner> getAllNonDeletedBoatOwners() {
		return iBoatOwnerRepository.findAll().stream().filter(boatOwnerIt -> !boatOwnerIt.getDeleted()).collect(Collectors.toList());
	}

}
