package com.isa.booking_entities.models.reservations;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.isa.booking_entities.models.entites.Boat;

@Entity
@Table(name = "boat_availability_periods")
public class BoatAvailabilityPeriod extends AvailabilityPeriod {
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Boat boatForAvailabilityPeriod;

	public BoatAvailabilityPeriod() {
		// TODO Auto-generated constructor stub
	}

	public Boat getBoatForAvailabilityPeriod() {
		return boatForAvailabilityPeriod;
	}

	public void setBoatForAvailabilityPeriod(Boat boatForAvailabilityPeriod) {
		this.boatForAvailabilityPeriod = boatForAvailabilityPeriod;
	}

}
