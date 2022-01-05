package com.isa.booking_entities.models.rating;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.isa.booking_entities.models.entites.Boat;
import com.isa.booking_entities.models.users.BoatOwner;

@Entity
@Table(name="boat_reviews")
public class BoatReview extends Review{
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Boat boatForReview;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private BoatOwner boatOwnerForReview;
	
	public BoatReview() {
		// TODO Auto-generated constructor stub
	}

	public BoatReview(Boat boatForReview, BoatOwner boatOwnerForReview) {
		this.boatForReview = boatForReview;
		this.boatOwnerForReview = boatOwnerForReview;
	}

	public Boat getBoatForReview() {
		return boatForReview;
	}

	public void setBoatForReview(Boat boatForReview) {
		this.boatForReview = boatForReview;
	}

	public BoatOwner getBoatOwnerForReview() {
		return boatOwnerForReview;
	}

	public void setBoatOwnerForReview(BoatOwner boatOwnerForReview) {
		this.boatOwnerForReview = boatOwnerForReview;
	}
	
}
