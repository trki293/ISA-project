package com.isa.booking_entities.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.dtos.InstructorComplaintNewDTO;
import com.isa.booking_entities.models.complaints.InstructionsComplaint;
import com.isa.booking_entities.models.complaints.StatusOfComplaint;
import com.isa.booking_entities.models.complaints.TypeOfComplaint;
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.models.users.Instructor;
import com.isa.booking_entities.repositories.IInstructionsComplaintRepository;
import com.isa.booking_entities.services.interfaces.IInstructionsComplaintService;

@Service
public class InstructionsComplaintService implements IInstructionsComplaintService {
	
	private IInstructionsComplaintRepository iInstructionsComplaintRepository;
	
	@Autowired
	public InstructionsComplaintService(IInstructionsComplaintRepository iInstructionsComplaintRepository) {
		this.iInstructionsComplaintRepository = iInstructionsComplaintRepository;
	}
	
	@Override
	public InstructionsComplaint save(InstructionsComplaint instructionsComplaint) {
		return iInstructionsComplaintRepository.save(instructionsComplaint);
	}

	@Override
	public InstructionsComplaint getById(long id) {
		return iInstructionsComplaintRepository.findById(id).orElse(null);
	}
	
	@Override
	public InstructionsComplaint createInstructionsComplaintByInstructorComplaintNewDTO(InstructorComplaintNewDTO instructorComplaintNewDTO,
			Client client, Instructor instructor) {
		InstructionsComplaint instructionsComplaint = new InstructionsComplaint();
		instructionsComplaint.setInstructorForComplaint(instructor);
		instructionsComplaint.setClientWhoCreateComplaint(client);
		instructionsComplaint.setStatusOfComplaint(StatusOfComplaint.WAITING_FOR_RESPONSE);
		instructionsComplaint.setText(instructorComplaintNewDTO.getText());
		instructionsComplaint.setTypeOfComplaint(TypeOfComplaint.INTRUCTIONS_COMPLAINT);
		return instructionsComplaint;
	}
	
	@Override
	public List<InstructionsComplaint> getInstructionsComplaints() {
		return iInstructionsComplaintRepository.findAll().stream().filter(instructionsComplaintIt -> instructionsComplaintIt.getStatusOfComplaint()==StatusOfComplaint.WAITING_FOR_RESPONSE).collect(Collectors.toList());
	}

}
