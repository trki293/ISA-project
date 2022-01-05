package com.isa.booking_entities.models.users;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.isa.booking_entities.models.BoatOwnerReport;
import com.isa.booking_entities.models.rating.BoatReview;

@Entity
@Table(name="boat_owners")
public class BoatOwner extends Users {
	@JsonManagedReference
	@OneToMany(mappedBy = "boatOwnerForReview", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<BoatReview> boatOwnerReviews = new HashSet<BoatReview>();
	
	@JsonManagedReference
	@OneToMany(mappedBy = "boatOwnerWhoCreateReport", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<BoatOwnerReport>  boatOwnerReports= new HashSet<BoatOwnerReport>();
}
