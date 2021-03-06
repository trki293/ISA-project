package com.isa.booking_entities.models.reservations;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.isa.booking_entities.models.entites.Boat;

@Entity
@Table(name = "boat_quick_bookings")
public class BoatQuickBooking extends QuickBooking {

	@JsonBackReference(value="boat-quick_booking")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Boat boatForQuickReservation;

	public BoatQuickBooking() {
		// TODO Auto-generated constructor stub
	}

	public Boat getBoatForQuickReservation() {
		return boatForQuickReservation;
	}

	public void setBoatForQuickReservation(Boat boatForQuickReservation) {
		this.boatForQuickReservation = boatForQuickReservation;
	}

}
