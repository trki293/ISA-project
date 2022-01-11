package com.isa.booking_entities.services.interfaces;

import com.isa.booking_entities.models.users.ConfirmationToken;

public interface IConfirmationTokenService {
	ConfirmationToken save(ConfirmationToken confirmationToken);
	ConfirmationToken getByToken(String token);
}
