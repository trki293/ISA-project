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

	public BoatComplaintResponse() {
		// TODO Auto-generated constructor stub
	}

	public BoatComplaint getBoatComplaintForComplaintResponse() {
		return boatComplaintForComplaintResponse;
	}

	public void setBoatComplaintForComplaintResponse(BoatComplaint boatComplaintForComplaintResponse) {
		this.boatComplaintForComplaintResponse = boatComplaintForComplaintResponse;
	}

}
