package com.isa.booking_entities.models.reservations;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.isa.booking_entities.models.entites.Cottage;

@Entity
@Table(name = "cottage_availability_periods")
public class CottageAvailabilityPeriod extends AvailabilityPeriod {
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Cottage cottageForAvailabilityPeriod;

	public CottageAvailabilityPeriod() {
		// TODO Auto-generated constructor stub
	}

	public Cottage getCottageForAvailabilityPeriod() {
		return cottageForAvailabilityPeriod;
	}

	public void setCottageForAvailabilityPeriod(Cottage cottageForAvailabilityPeriod) {
		this.cottageForAvailabilityPeriod = cottageForAvailabilityPeriod;
	}

}