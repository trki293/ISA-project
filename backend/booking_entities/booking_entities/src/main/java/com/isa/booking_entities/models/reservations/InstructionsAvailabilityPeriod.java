package com.isa.booking_entities.models.reservations;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.isa.booking_entities.models.entites.Instructions;

@Entity
@Table(name="instructions_availability_periods")
public class InstructionsAvailabilityPeriod extends AvailabilityPeriod{
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Instructions instructionsForAvailabilityPeriod;
}