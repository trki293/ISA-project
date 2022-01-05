package com.isa.booking_entities.models.reservations;

import static javax.persistence.InheritanceType.JOINED;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.isa.booking_entities.models.entites.AdditionalServices;
import com.isa.booking_entities.models.rating.TypeOfReview;
import com.isa.booking_entities.models.users.Client;

@Entity
@Table(name = "quick_bookings")
@Inheritance(strategy = JOINED)
public class QuickBooking {
	
	@Id
	@SequenceGenerator(name = "mySeqGenQuickBooking", sequenceName = "mySeqQuickBooking", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenQuickBooking")
	private long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm", timezone = "UTC")
	private LocalDateTime timeOfBeginingReservation;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm", timezone = "UTC")
	private LocalDateTime timeOfEndingReservation;
	
	@Column(name = "maxNumberOfPerson", unique = false, nullable = false)
	private int maxNumberOfPerson;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<AdditionalServices> additionalServices = new HashSet<AdditionalServices>();
	
	@Column(name = "price", unique = false, nullable = false)
	private double price;
	
	@Enumerated(EnumType.ORDINAL)
	private TypeOfQuickBooking typeOfQuickBooking;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Client clientForQuickBooking;
	
	public QuickBooking() {
		// TODO Auto-generated constructor stub
	}

	public QuickBooking(long id, LocalDateTime timeOfBeginingReservation, LocalDateTime timeOfEndingReservation,
			int maxNumberOfPerson, Set<AdditionalServices> additionalServices, double price,
			Client clientForQuickBooking) {
		this.id = id;
		this.timeOfBeginingReservation = timeOfBeginingReservation;
		this.timeOfEndingReservation = timeOfEndingReservation;
		this.maxNumberOfPerson = maxNumberOfPerson;
		this.additionalServices = additionalServices;
		this.price = price;
		this.clientForQuickBooking = clientForQuickBooking;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getTimeOfBeginingReservation() {
		return timeOfBeginingReservation;
	}

	public void setTimeOfBeginingReservation(LocalDateTime timeOfBeginingReservation) {
		this.timeOfBeginingReservation = timeOfBeginingReservation;
	}

	public LocalDateTime getTimeOfEndingReservation() {
		return timeOfEndingReservation;
	}

	public void setTimeOfEndingReservation(LocalDateTime timeOfEndingReservation) {
		this.timeOfEndingReservation = timeOfEndingReservation;
	}

	public int getMaxNumberOfPerson() {
		return maxNumberOfPerson;
	}

	public void setMaxNumberOfPerson(int maxNumberOfPerson) {
		this.maxNumberOfPerson = maxNumberOfPerson;
	}

	public Set<AdditionalServices> getAdditionalServices() {
		return additionalServices;
	}

	public void setAdditionalServices(Set<AdditionalServices> additionalServices) {
		this.additionalServices = additionalServices;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Client getClientForQuickBooking() {
		return clientForQuickBooking;
	}

	public void setClientForQuickBooking(Client clientForQuickBooking) {
		this.clientForQuickBooking = clientForQuickBooking;
	}
	
}
