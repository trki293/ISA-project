package com.isa.booking_entities.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.dtos.InstructionsComplaintResponseNewDTO;
import com.isa.booking_entities.models.complaints.InstructionsComplaint;
import com.isa.booking_entities.models.complaints.InstructionsComplaintResponse;
import com.isa.booking_entities.models.complaints.TypeOfComplaintResponse;
import com.isa.booking_entities.models.users.SystemAdmin;
import com.isa.booking_entities.repositories.IInstructionsComplaintResponseRepository;
import com.isa.booking_entities.services.interfaces.IInstructionsComplaintResponseService;

@Service
public class InstructionsComplaintResponseService implements IInstructionsComplaintResponseService{
	
	private IInstructionsComplaintResponseRepository iInstructionsComplaintResponseRepository;
	
	@Autowired
	public InstructionsComplaintResponseService(IInstructionsComplaintResponseRepository iInstructionsComplaintResponseRepository) {
		this.iInstructionsComplaintResponseRepository = iInstructionsComplaintResponseRepository;
	}

	@Override
	public InstructionsComplaintResponse save(InstructionsComplaintResponse instructionsComplaintResponse) {
		return iInstructionsComplaintResponseRepository.save(instructionsComplaintResponse);
	}

	@Override
	public InstructionsComplaintResponse getById(long id) {
		return iInstructionsComplaintResponseRepository.findById(id).orElse(null);
	}

	@Override
	public InstructionsComplaintResponse createInstructionsComplain(InstructionsComplaintResponseNewDTO instructionsComplaintResponseNewDTO,
			SystemAdmin systemAdmin, InstructionsComplaint instructionsComplaint) {
		InstructionsComplaintResponse instructionsComplaintResponse = new InstructionsComplaintResponse();
		instructionsComplaintResponse.setInstructionsComplaintForComplaintResponse(instructionsComplaint);
		instructionsComplaintResponse.setSystemAdminWhoCreateResponse(systemAdmin);
		instructionsComplaintResponse.setText(instructionsComplaintResponseNewDTO.getText());
		instructionsComplaintResponse.setTypeOfComplaintResponse(TypeOfComplaintResponse.INSTRUCTIONS_RESPONSE_COMPLAINT);
		return instructionsComplaintResponse;
	}
}
