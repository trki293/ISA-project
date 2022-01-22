package com.isa.booking_entities.models.reservations;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.isa.booking_entities.models.entites.Instructions;

@Entity
@Table(name = "instructions_reservations")
public class InstructionsReservation extends Reservation {
	@JsonBackReference(value="instructions-reservation")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Instructions instructionsForReservation;
	
	public InstructionsReservation() {
		// TODO Auto-generated constructor stub
	}

	public Instructions getInstructionsForReservation() {
		return instructionsForReservation;
	}

	public void setInstructionsForReservation(Instructions instructionsForReservation) {
		this.instructionsForReservation = instructionsForReservation;
	}
	
}
