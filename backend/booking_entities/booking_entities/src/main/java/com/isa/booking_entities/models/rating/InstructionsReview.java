package com.isa.booking_entities.models.rating;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.isa.booking_entities.models.entites.Instructions;
import com.isa.booking_entities.models.users.Instructor;

@Entity
@Table(name="instructions_reviews")
public class InstructionsReview extends Review {
	
	@JsonBackReference(value="instructions-review")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Instructions instructionsForReview;
	
	public InstructionsReview() {
		// TODO Auto-generated constructor stub
	}

	public Instructions getInstructionsForReview() {
		return instructionsForReview;
	}

	public void setInstructionsForReview(Instructions instructionsForReview) {
		this.instructionsForReview = instructionsForReview;
	}
	
}
