package com.isa.booking_entities.models.complaints;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.isa.booking_entities.models.entites.Boat;
import com.isa.booking_entities.models.users.BoatOwner;

@Entity
@Table(name = "boat_complaints")
public class BoatComplaint extends Complaint{
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Boat boatForComplaint;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private BoatOwner boatOwnerForComplaint;
}
