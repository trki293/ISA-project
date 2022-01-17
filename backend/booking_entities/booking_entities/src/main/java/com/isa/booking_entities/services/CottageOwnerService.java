package com.isa.booking_entities.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.models.users.CottageOwner;
import com.isa.booking_entities.repositories.ICottageOwnerRepository;
import com.isa.booking_entities.services.interfaces.ICottageOwnerService;

@Service
public class CottageOwnerService implements ICottageOwnerService {
	
	private ICottageOwnerRepository iCottageOwnerRepository;
	
	@Autowired
	public CottageOwnerService(ICottageOwnerRepository iCottageOwnerRepository) {
		this.iCottageOwnerRepository = iCottageOwnerRepository;
	}

	@Override
	public CottageOwner getById(long id) {
		return iCottageOwnerRepository.findById(id).orElse(null);
	}

	@Override
	public CottageOwner getByEmail(String email) {
		return iCottageOwnerRepository.findAll().stream().filter(cottageOwnerIt -> cottageOwnerIt.getEmail().equals(email)).findFirst().orElse(null);
	}

	@Override
	public CottageOwner save(CottageOwner cottageOwner) {
		return iCottageOwnerRepository.save(cottageOwner);
	}

	@Override
	public List<CottageOwner> getAllNonDeletedCottageOwners() {
		return iCottageOwnerRepository.findAll().stream().filter(cottageOwnerIt -> !cottageOwnerIt.getDeleted()).collect(Collectors.toList());
	}

}
