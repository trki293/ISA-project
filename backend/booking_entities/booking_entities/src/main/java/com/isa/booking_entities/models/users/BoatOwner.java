package com.isa.booking_entities.models.users;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.isa.booking_entities.models.complaints.BoatComplaint;
import com.isa.booking_entities.models.entites.Boat;
import com.isa.booking_entities.models.rating.BoatReview;
import com.isa.booking_entities.models.reports.BoatOwnerReport;

@Entity
@Table(name="boat_owners")
public class BoatOwner extends Users {
	
	@Column(name = "loyaltyPoints", unique = false, nullable = false)
	private long loyaltyPoints;
	
	@Enumerated(EnumType.ORDINAL)
	private StatusOfUser statuseOfUser;
	
	@JsonManagedReference(value="boat-owner")
	@OneToMany(mappedBy = "ownerOfBoat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Boat> boats = new HashSet<Boat>();
	
	@JsonManagedReference(value="boat-owner-report")
	@OneToMany(mappedBy = "boatOwnerWhoCreateReport", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<BoatOwnerReport>  boatOwnerReports= new HashSet<BoatOwnerReport>();
	
	public BoatOwner() {
		// TODO Auto-generated constructor stub
	}

	public long getLoyaltyPoints() {
		return loyaltyPoints;
	}

	public void setLoyaltyPoints(long loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}

	public StatusOfUser getStatuseOfUser() {
		return statuseOfUser;
	}

	public void setStatuseOfUser(StatusOfUser statuseOfUser) {
		this.statuseOfUser = statuseOfUser;
	}

	public Set<Boat> getBoats() {
		return boats;
	}

	public void setBoats(Set<Boat> boats) {
		this.boats = boats;
	}

	public Set<BoatOwnerReport> getBoatOwnerReports() {
		return boatOwnerReports;
	}

	public void setBoatOwnerReports(Set<BoatOwnerReport> boatOwnerReports) {
		this.boatOwnerReports = boatOwnerReports;
	}
	
}
