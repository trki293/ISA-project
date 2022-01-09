package com.isa.booking_entities.models.reservations;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.isa.booking_entities.models.entites.Cottage;

public class CottageReservation extends Reservation {
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Cottage cottageForReservation;

	public CottageReservation() {
		// TODO Auto-generated constructor stub
	}

	public Cottage getCottageForReservation() {
		return cottageForReservation;
	}

	public void setCottageForReservation(Cottage cottageForReservation) {
		this.cottageForReservation = cottageForReservation;
	}

}
