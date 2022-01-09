package com.isa.booking_entities.models.reports;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.isa.booking_entities.models.users.BoatOwner;

@Entity
@Table(name = "boat_owner_reports")
public class BoatOwnerReport extends Report {

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private BoatOwner boatOwnerWhoCreateReport;

	public BoatOwnerReport() {
		// TODO Auto-generated constructor stub
	}

	public BoatOwner getBoatOwnerWhoCreateReport() {
		return boatOwnerWhoCreateReport;
	}

	public void setBoatOwnerWhoCreateReport(BoatOwner boatOwnerWhoCreateReport) {
		this.boatOwnerWhoCreateReport = boatOwnerWhoCreateReport;
	}

}
