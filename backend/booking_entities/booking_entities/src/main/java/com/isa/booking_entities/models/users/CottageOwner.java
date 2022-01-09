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
import com.isa.booking_entities.models.complaints.CottageComplaint;
import com.isa.booking_entities.models.entites.Cottage;
import com.isa.booking_entities.models.rating.CottageReview;
import com.isa.booking_entities.models.reports.CottageOwnerReport;

@Entity
@Table(name="cottage_owners")
public class CottageOwner extends Users {
	
	@Column(name = "loyaltyPoints", unique = false, nullable = false)
	private long loyaltyPoints;
	
	@Enumerated(EnumType.ORDINAL)
	private StatusOfUser statuseOfUser;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "ownerOfCottage", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Cottage>  cottages= new HashSet<Cottage>();
	
	@JsonManagedReference
	@OneToMany(mappedBy = "cottageOwnerForReview", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<CottageReview>  cottageOwnerReviews= new HashSet<CottageReview>();
	
	@JsonManagedReference
	@OneToMany(mappedBy = "cottageOwnerWhoCreateReport", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<CottageOwnerReport>  cottageOwnerReports= new HashSet<CottageOwnerReport>();
	
	@JsonManagedReference
	@OneToMany(mappedBy = "cottageOwnerForComplaint", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<CottageComplaint>  cottageOwnerComplaints= new HashSet<CottageComplaint>();
	
	public CottageOwner() {
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

	public Set<Cottage> getCottages() {
		return cottages;
	}

	public void setCottages(Set<Cottage> cottages) {
		this.cottages = cottages;
	}

	public Set<CottageReview> getCottageOwnerReviews() {
		return cottageOwnerReviews;
	}

	public void setCottageOwnerReviews(Set<CottageReview> cottageOwnerReviews) {
		this.cottageOwnerReviews = cottageOwnerReviews;
	}

	public Set<CottageOwnerReport> getCottageOwnerReports() {
		return cottageOwnerReports;
	}

	public void setCottageOwnerReports(Set<CottageOwnerReport> cottageOwnerReports) {
		this.cottageOwnerReports = cottageOwnerReports;
	}

	public Set<CottageComplaint> getCottageOwnerComplaints() {
		return cottageOwnerComplaints;
	}

	public void setCottageOwnerComplaints(Set<CottageComplaint> cottageOwnerComplaints) {
		this.cottageOwnerComplaints = cottageOwnerComplaints;
	}
	
}
