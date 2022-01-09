package com.isa.booking_entities.models.complaints;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "instructions_complaint_responses")
public class InstructionsComplaintResponse extends ComplaintResponse {
	@OneToOne(cascade = CascadeType.ALL)
	private InstructionsComplaint instructionsComplaintForComplaintResponse;

	public InstructionsComplaintResponse() {
		// TODO Auto-generated constructor stub
	}

	public InstructionsComplaint getInstructionsComplaintForComplaintResponse() {
		return instructionsComplaintForComplaintResponse;
	}

	public void setInstructionsComplaintForComplaintResponse(
			InstructionsComplaint instructionsComplaintForComplaintResponse) {
		this.instructionsComplaintForComplaintResponse = instructionsComplaintForComplaintResponse;
	}

}
