package com.isa.booking_entities.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.models.SystemParameters;
import com.isa.booking_entities.repositories.ISystemParametersRepository;
import com.isa.booking_entities.services.interfaces.ISystemParametersService;

@Service
public class SystemParametersService implements ISystemParametersService {
	
	private ISystemParametersRepository iSystemParametersRepository;
	
	@Autowired
	public SystemParametersService(ISystemParametersRepository iSystemParametersRepository) {
		this.iSystemParametersRepository = iSystemParametersRepository;
	}

	@Override
	public SystemParameters get() {
		return iSystemParametersRepository.findAll().stream().findFirst().orElse(null);
	}

	@Override
	public SystemParameters save(SystemParameters systemParameters) {
		return iSystemParametersRepository.save(systemParameters);
	}

}
