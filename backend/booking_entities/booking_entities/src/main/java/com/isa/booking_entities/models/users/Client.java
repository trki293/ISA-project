package com.isa.booking_entities.models.users;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.isa.booking_entities.models.rating.Review;
import com.isa.booking_entities.models.reservations.QuickBooking;
import com.isa.booking_entities.models.reservations.Reservation;

@Entity
@Table(name="clients")
public class Client extends Users {
	
	@Column(name = "penaltyPoints", unique = false, nullable = false)
	private long penaltyPoints;
	
	@Column(name = "loyaltyPoints", unique = false, nullable = false)
	private long loyaltyPoints;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "clientForReservation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Reservation> reservations = new HashSet<Reservation>();
	
	@JsonManagedReference
	@OneToMany(mappedBy = "clientForQuickBooking", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<QuickBooking> quickBookings = new HashSet<QuickBooking>();
	
	@JsonManagedReference
	@OneToMany(mappedBy = "clientWhoEvaluating", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Review> reviews= new HashSet<Review>();
	//It is necessary to add an enum for the client's rank
}
