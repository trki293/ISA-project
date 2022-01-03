package com.isa.booking_entities.models.reservations;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.isa.booking_entities.models.entites.Instructions;

public class InstructionsReservation extends Reservation {
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Instructions instructionsForReservation;
}
