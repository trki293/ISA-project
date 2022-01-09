package com.isa.booking_entities.models.complaints;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cottage_complaint_responses")
public class CottageComplaintResponse extends ComplaintResponse{
	@OneToOne(cascade = CascadeType.ALL)
	private CottageComplaint cottageComplaintForComplaintResponse;
	
	public CottageComplaintResponse() {
		// TODO Auto-generated constructor stub
	}

	public CottageComplaint getCottageComplaintForComplaintResponse() {
		return cottageComplaintForComplaintResponse;
	}

	public void setCottageComplaintForComplaintResponse(CottageComplaint cottageComplaintForComplaintResponse) {
		this.cottageComplaintForComplaintResponse = cottageComplaintForComplaintResponse;
	}
	
}
