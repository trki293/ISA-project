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
@Table(name = "boat_reviews")
public class BoatReview extends Review {
	@JsonBackReference(value="boat-review")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Boat boatForReview;

	public BoatReview() {
		// TODO Auto-generated constructor stub
	}

	public Boat getBoatForReview() {
		return boatForReview;
	}

	public void setBoatForReview(Boat boatForReview) {
		this.boatForReview = boatForReview;
	}

}
