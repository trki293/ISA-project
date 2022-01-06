package com.isa.booking_entities.models.complaints;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "boat_complaint_responses")
public class BoatComplaintResponse extends ComplaintResponse {
	@OneToOne(cascade = CascadeType.ALL)
	private BoatComplaint boatComplaintForComplaintResponse;
}
