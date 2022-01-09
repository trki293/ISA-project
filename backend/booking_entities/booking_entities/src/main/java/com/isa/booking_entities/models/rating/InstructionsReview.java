package com.isa.booking_entities.models.rating;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.isa.booking_entities.models.users.Instructor;

@Entity
@Table(name="instructions_reviews")
public class InstructionsReview extends Review {
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Instructor instructorForReview;
	
	public InstructionsReview() {
		// TODO Auto-generated constructor stub
	}

	public Instructor getInstructorForReview() {
		return instructorForReview;
	}

	public void setInstructorForReview(Instructor instructorForReview) {
		this.instructorForReview = instructorForReview;
	}
	
}
