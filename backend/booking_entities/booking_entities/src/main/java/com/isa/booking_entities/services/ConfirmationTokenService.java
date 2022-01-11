package com.isa.booking_entities.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.models.users.ConfirmationToken;
import com.isa.booking_entities.repositories.IConfirmationTokenRepository;
import com.isa.booking_entities.services.interfaces.IConfirmationTokenService;

@Service
public class ConfirmationTokenService implements IConfirmationTokenService {
	
	@Autowired
	private IConfirmationTokenRepository iConfirmationTokenRepository;
	
	@Override
	public ConfirmationToken save(ConfirmationToken confirmationToken) {
		return iConfirmationTokenRepository.save(confirmationToken);
	}

	@Override
	public ConfirmationToken getByToken(String token) {
		return iConfirmationTokenRepository.findAll().stream().filter(confirmationToken -> confirmationToken.getConfirmationToken().equals(token)).findFirst().orElse(null);
	}

}
