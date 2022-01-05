package com.isa.booking_entities.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.isa.booking_entities.models.users.BoatOwner;
import com.isa.booking_entities.models.users.CottageOwner;

@Entity
@Table(name = "boat_owner_reports")
public class BoatOwnerReport {
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private BoatOwner boatOwnerWhoCreateReport;
	
}
