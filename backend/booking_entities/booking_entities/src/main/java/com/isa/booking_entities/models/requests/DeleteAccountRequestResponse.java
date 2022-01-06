package com.isa.booking_entities.models.requests;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.isa.booking_entities.models.users.SystemAdmin;

public class DeleteAccountRequestResponse {
	@OneToOne(cascade = CascadeType.ALL)
	private DeleteAccountRequest deleteAccountRequest;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER) 
	private SystemAdmin systemAdminWhoReviewRequest; 
}
