package com.isa.booking_entities.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.models.users.SystemAdmin;
import com.isa.booking_entities.repositories.ISystemAdminRepository;
import com.isa.booking_entities.services.interfaces.ISystemAdminService;

@Service
public class SystemAdminService implements ISystemAdminService {
	
	private ISystemAdminRepository iSystemAdminRepository;
	
	@Autowired
	public SystemAdminService(ISystemAdminRepository iSystemAdminRepository) {
		this.iSystemAdminRepository = iSystemAdminRepository;
	}

	@Override
	public SystemAdmin getById(long id) {
		return iSystemAdminRepository.findById(id).orElse(null);
	}

	@Override
	public SystemAdmin save(SystemAdmin systemAdmin) {
		return iSystemAdminRepository.save(systemAdmin);
	}

	@Override
	public SystemAdmin getByEmail(String email) {
		return iSystemAdminRepository.findAll().stream().filter(systemAdminIt -> systemAdminIt.getEmail().equals(email)).findFirst().orElse(null);
	}

}
