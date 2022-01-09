package com.isa.booking_entities.models.reservations;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.isa.booking_entities.models.entites.Cottage;

@Entity
@Table(name = "cottage_quick_bookings")
public class CottageQuickBooking extends QuickBooking {
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Cottage cottageForQuickReservation;

	public CottageQuickBooking() {
		// TODO Auto-generated constructor stub
	}

	public Cottage getCottageForQuickReservation() {
		return cottageForQuickReservation;
	}

	public void setCottageForQuickReservation(Cottage cottageForQuickReservation) {
		this.cottageForQuickReservation = cottageForQuickReservation;
	}

}
