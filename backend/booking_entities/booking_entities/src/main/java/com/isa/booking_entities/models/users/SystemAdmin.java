package com.isa.booking_entities.models.users;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.isa.booking_entities.models.rating.CottageReview;
import com.isa.booking_entities.models.requests.DeleteAccountRequestResponse;

@Entity
@Table(name="system_admins")
public class SystemAdmin extends Users {
	
	@JsonManagedReference
	@OneToMany(mappedBy = "systemAdminWhoReviewRequest", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<DeleteAccountRequestResponse>  deleteAccountRequestResponses = new HashSet<DeleteAccountRequestResponse>();
}
