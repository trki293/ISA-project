package com.isa.booking_entities.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.dtos.SystemAdminNewDTO;
import com.isa.booking_entities.models.users.SystemAdmin;
import com.isa.booking_entities.models.users.TypeOfUser;
import com.isa.booking_entities.repositories.IAuthorityRepository;
import com.isa.booking_entities.repositories.ISystemAdminRepository;
import com.isa.booking_entities.services.interfaces.ISystemAdminService;

@Service
public class SystemAdminService implements ISystemAdminService {
	
	private ISystemAdminRepository iSystemAdminRepository;
	
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public SystemAdminService(ISystemAdminRepository iSystemAdminRepository) {
		this.iSystemAdminRepository = iSystemAdminRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
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

	@Override
	public SystemAdmin createSystemAdmin(SystemAdminNewDTO systemAdminNewDTO) {
		SystemAdmin systemAdmin = new SystemAdmin();
		systemAdmin.setDeleted(false);
		systemAdmin.setEmail(systemAdminNewDTO.getEmail());
		systemAdmin.setEnabledLogin(true);
		systemAdmin.setFirstLogin(true);
		systemAdmin.setFirstName(systemAdminNewDTO.getFirstName());
		systemAdmin.setLastName(systemAdminNewDTO.getLastName());
		systemAdmin.setPassword(passwordEncoder.encode(systemAdminNewDTO.getPassword()));
		systemAdmin.setPhoneNumber(systemAdminNewDTO.getPhoneNumber());
		systemAdmin.setResidentalAddress(systemAdminNewDTO.getAddress());
		systemAdmin.setTypeOfUser(TypeOfUser.SYSTEM_ADMIN);
		return systemAdmin;
	}

}
