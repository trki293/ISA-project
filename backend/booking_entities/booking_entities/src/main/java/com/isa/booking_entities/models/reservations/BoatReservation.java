package com.isa.booking_entities.models.reservations;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.isa.booking_entities.models.entites.Boat;

@Entity
@Table(name = "boat_reservations")
public class BoatReservation extends Reservation {
	@JsonBackReference(value="boat-reservation")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Boat boatForReservation;

	public BoatReservation() {
		// TODO Auto-generated constructor stub
	}

	public Boat getBoatForReservation() {
		return boatForReservation;
	}

	public void setBoatForReservation(Boat boatForReservation) {
		this.boatForReservation = boatForReservation;
	}

}
