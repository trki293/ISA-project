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
}
