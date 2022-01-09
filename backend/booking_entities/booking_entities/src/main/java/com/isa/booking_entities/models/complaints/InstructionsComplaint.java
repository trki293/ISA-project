package com.isa.booking_entities.models.complaints;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.isa.booking_entities.models.users.Instructor;

@Entity
@Table(name = "instructions_complaints")
public class InstructionsComplaint extends Complaint {
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Instructor instructorForComplaint;

	public InstructionsComplaint() {
		// TODO Auto-generated constructor stub
	}

	public Instructor getInstructorForComplaint() {
		return instructorForComplaint;
	}

	public void setInstructorForComplaint(Instructor instructorForComplaint) {
		this.instructorForComplaint = instructorForComplaint;
	}

}
