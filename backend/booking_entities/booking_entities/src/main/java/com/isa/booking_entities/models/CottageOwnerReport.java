package com.isa.booking_entities.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.isa.booking_entities.models.users.CottageOwner;

@Entity
@Table(name = "cottage_owner_reports")
public class CottageOwnerReport extends Report {
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private CottageOwner cottageOwnerWhoCreateReport;
	
}
