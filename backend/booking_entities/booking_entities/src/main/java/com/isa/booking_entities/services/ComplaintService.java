package com.isa.booking_entities.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.models.complaints.Complaint;
import com.isa.booking_entities.repositories.IComplaintRepository;
import com.isa.booking_entities.services.interfaces.IComplaintService;

@Service
public class ComplaintService implements IComplaintService {

	private IComplaintRepository iComplaintRepository;
	
	@Autowired
	public ComplaintService(IComplaintRepository iComplaintRepository) {
		this.iComplaintRepository = iComplaintRepository;
	}

	@Override
	public Complaint save(Complaint complaint) {
		return iComplaintRepository.save(complaint);
	}

}
