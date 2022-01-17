package com.isa.booking_entities.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.models.requests.RegistrationRequest;
import com.isa.booking_entities.models.requests.StateOfRequest;
import com.isa.booking_entities.repositories.IRegistrationRequestRepository;
import com.isa.booking_entities.services.interfaces.IRegistrationRequestService;

@Service
public class RegistrationRequestService implements IRegistrationRequestService {
	
	private IRegistrationRequestRepository iRegistrationRequestRepository;
	
	@Autowired
	public RegistrationRequestService(IRegistrationRequestRepository iRegistrationRequestRepository) {
		this.iRegistrationRequestRepository = iRegistrationRequestRepository;
	}

	@Override
	public RegistrationRequest save(RegistrationRequest registrationRequest) {
		return iRegistrationRequestRepository.save(registrationRequest);
	}

	@Override
	public RegistrationRequest getById(long id) {
		return iRegistrationRequestRepository.findById(id).orElse(null);
	}

	@Override
	public RegistrationRequest getByEmailOfUser(String email) {
		return iRegistrationRequestRepository.findAll().stream().filter(registrationRequestIt -> registrationRequestIt.getEmail().equals(email)).findFirst().orElse(null);
	}

	@Override
	public List<RegistrationRequest> getOnlyCreatedRegistrationRequests() {
		return iRegistrationRequestRepository.findAll().stream().filter(registrationRequestIt -> registrationRequestIt.getStateOfRequest() == StateOfRequest.CREATED && registrationRequestIt.getSystemAdminWhoReviewRegistrationRequest()==null).collect(Collectors.toList());
	}

}
