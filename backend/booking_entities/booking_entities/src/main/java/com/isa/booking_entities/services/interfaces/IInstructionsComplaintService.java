package com.isa.booking_entities.services.interfaces;

import java.util.List;

import com.isa.booking_entities.dtos.InstructorComplaintNewDTO;
import com.isa.booking_entities.models.complaints.InstructionsComplaint;
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.models.users.Instructor;

public interface IInstructionsComplaintService {
	InstructionsComplaint save(InstructionsComplaint instructionsComplaint);
	InstructionsComplaint getById(long id);
	InstructionsComplaint createInstructionsComplaintByInstructorComplaintNewDTO(
			InstructorComplaintNewDTO instructorComplaintNewDTO, Client client, Instructor instructor);
	List<InstructionsComplaint> getInstructionsComplaints();
}
