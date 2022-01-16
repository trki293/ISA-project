package com.isa.booking_entities.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.models.complaints.ComplaintResponse;
import com.isa.booking_entities.repositories.IComplaintResponseRepository;
import com.isa.booking_entities.services.interfaces.IComplaintResponseService;

@Service
public class ComplaintResponseService implements IComplaintResponseService {
	
	private IComplaintResponseRepository iComplaintResponseRepository;
	
	@Autowired
	public ComplaintResponseService(IComplaintResponseRepository iComplaintResponseRepository) {
		this.iComplaintResponseRepository = iComplaintResponseRepository;
	}

	@Override
	public ComplaintResponse save(ComplaintResponse complaintResponse) {
		return iComplaintResponseRepository.save(complaintResponse);
	}

}
