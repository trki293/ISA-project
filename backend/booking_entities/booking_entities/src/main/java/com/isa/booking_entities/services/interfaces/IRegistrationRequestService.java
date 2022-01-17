package com.isa.booking_entities.services.interfaces;

import java.util.List;

import com.isa.booking_entities.models.requests.RegistrationRequest;

public interface IRegistrationRequestService {
	RegistrationRequest save(RegistrationRequest registrationRequest);
	RegistrationRequest getById(long id);
	RegistrationRequest getByEmailOfUser(String email);
	List<RegistrationRequest> getOnlyCreatedRegistrationRequests();
}
