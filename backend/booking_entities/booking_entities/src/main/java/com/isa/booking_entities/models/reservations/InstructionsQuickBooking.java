package com.isa.booking_entities.models.reservations;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.isa.booking_entities.models.entites.Instructions;

@Entity
@Table(name = "instructions_quick_bookings")
public class InstructionsQuickBooking extends QuickBooking {

	@JsonBackReference(value="instructions-quick-booking")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Instructions instructionsForQuickReservation;

	public InstructionsQuickBooking() {
		// TODO Auto-generated constructor stub
	}

	public Instructions getInstructionsForQuickReservation() {
		return instructionsForQuickReservation;
	}

	public void setInstructionsForQuickReservation(Instructions instructionsForQuickReservation) {
		this.instructionsForQuickReservation = instructionsForQuickReservation;
	}

}
