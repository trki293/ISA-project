package com.isa.booking_entities.models.users;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.isa.booking_entities.models.complaints.Complaint;
import com.isa.booking_entities.models.entites.Boat;
import com.isa.booking_entities.models.entites.Cottage;
import com.isa.booking_entities.models.entites.Instructions;
import com.isa.booking_entities.models.rating.Review;
import com.isa.booking_entities.models.reports.Report;
import com.isa.booking_entities.models.reservations.QuickBooking;
import com.isa.booking_entities.models.reservations.Reservation;

@Entity
@Table(name = "clients")
public class Client extends Users {

	@Column(name = "penaltyPoints", unique = false, nullable = false)
	private long penaltyPoints;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "UTC")
	private LocalDateTime timeOfResetingPenaltyPoints;

	@Column(name = "loyaltyPoints", unique = false, nullable = false)
	private long loyaltyPoints;

	@Enumerated(EnumType.ORDINAL)
	private StatusOfUser statuseOfUser;

	@JsonManagedReference
	@OneToMany(mappedBy = "clientForReservation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Reservation> reservations = new HashSet<Reservation>();

	@JsonManagedReference
	@OneToMany(mappedBy = "reportingClient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Report> reports = new HashSet<Report>();

	@JsonManagedReference
	@OneToMany(mappedBy = "clientForQuickBooking", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<QuickBooking> quickBookings = new HashSet<QuickBooking>();

	@JsonManagedReference(value="client-review")
	@OneToMany(mappedBy = "clientWhoEvaluating", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Review> reviews = new HashSet<Review>();

	@JsonManagedReference
	@OneToMany(mappedBy = "clientWhoCreateComplaint", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Complaint> complaints = new HashSet<Complaint>();

	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Boat> boatSubscriptions = new HashSet<Boat>();

	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Cottage> cottageSubscriptions = new HashSet<Cottage>();

	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Instructions> instructionsSubscriptions = new HashSet<Instructions>();

	public Client() {
		// TODO Auto-generated constructor stub
	}

	public long getPenaltyPoints() {
		return penaltyPoints;
	}

	public void setPenaltyPoints(long penaltyPoints) {
		this.penaltyPoints = penaltyPoints;
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

	public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Set<Report> getReports() {
		return reports;
	}

	public void setReports(Set<Report> reports) {
		this.reports = reports;
	}

	public Set<QuickBooking> getQuickBookings() {
		return quickBookings;
	}

	public void setQuickBookings(Set<QuickBooking> quickBookings) {
		this.quickBookings = quickBookings;
	}

	public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	public Set<Complaint> getComplaints() {
		return complaints;
	}

	public void setComplaints(Set<Complaint> complaints) {
		this.complaints = complaints;
	}

	public Set<Boat> getBoatSubscriptions() {
		return boatSubscriptions;
	}

	public void setBoatSubscriptions(Set<Boat> boatSubscriptions) {
		this.boatSubscriptions = boatSubscriptions;
	}

	public Set<Cottage> getCottageSubscriptions() {
		return cottageSubscriptions;
	}

	public void setCottageSubscriptions(Set<Cottage> cottageSubscriptions) {
		this.cottageSubscriptions = cottageSubscriptions;
	}

	public Set<Instructions> getInstructionsSubscriptions() {
		return instructionsSubscriptions;
	}

	public void setInstructionsSubscriptions(Set<Instructions> instructionsSubscriptions) {
		this.instructionsSubscriptions = instructionsSubscriptions;
	}

	public LocalDateTime getTimeOfResetingPenaltyPoints() {
		return timeOfResetingPenaltyPoints;
	}

	public void setTimeOfResetingPenaltyPoints(LocalDateTime timeOfResetingPenaltyPoints) {
		this.timeOfResetingPenaltyPoints = timeOfResetingPenaltyPoints;
	}

}
