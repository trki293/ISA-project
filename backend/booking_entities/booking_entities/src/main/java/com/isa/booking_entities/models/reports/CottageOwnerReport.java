package com.isa.booking_entities.models.reports;

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
	
	@JsonBackReference(value="cottage-owner-report")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private CottageOwner cottageOwnerWhoCreateReport;
	
	public CottageOwnerReport() {
		// TODO Auto-generated constructor stub
	}

	public CottageOwner getCottageOwnerWhoCreateReport() {
		return cottageOwnerWhoCreateReport;
	}

	public void setCottageOwnerWhoCreateReport(CottageOwner cottageOwnerWhoCreateReport) {
		this.cottageOwnerWhoCreateReport = cottageOwnerWhoCreateReport;
	}
	
}
