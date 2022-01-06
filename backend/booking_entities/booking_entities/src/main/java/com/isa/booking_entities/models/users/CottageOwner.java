package com.isa.booking_entities.models.users;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.isa.booking_entities.models.complaints.CottageComplaint;
import com.isa.booking_entities.models.rating.CottageReview;
import com.isa.booking_entities.models.reports.CottageOwnerReport;

@Entity
@Table(name="cottage_owners")
public class CottageOwner extends Users {
	
	@JsonManagedReference
	@OneToMany(mappedBy = "cottageOwnerForReview", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<CottageReview>  cottageOwnerReviews= new HashSet<CottageReview>();
	
	@JsonManagedReference
	@OneToMany(mappedBy = "cottageOwnerWhoCreateReport", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<CottageOwnerReport>  cottageOwnerReports= new HashSet<CottageOwnerReport>();
	
	@JsonManagedReference
	@OneToMany(mappedBy = "cottageOwnerForComplaint", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<CottageComplaint>  cottageOwnerComplaints= new HashSet<CottageComplaint>();
}
