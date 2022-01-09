package com.isa.booking_entities.models.rating;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.isa.booking_entities.models.entites.Cottage;
import com.isa.booking_entities.models.users.CottageOwner;

@Entity
@Table(name="cottage_reviews")
public class CottageReview extends Review{
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Cottage cottageForReview;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private CottageOwner cottageOwnerForReview;
	
	public CottageReview() {
		// TODO Auto-generated constructor stub
	}

	public Cottage getCottageForReview() {
		return cottageForReview;
	}

	public void setCottageForReview(Cottage cottageForReview) {
		this.cottageForReview = cottageForReview;
	}

	public CottageOwner getCottageOwnerForReview() {
		return cottageOwnerForReview;
	}

	public void setCottageOwnerForReview(CottageOwner cottageOwnerForReview) {
		this.cottageOwnerForReview = cottageOwnerForReview;
	}
	
}
