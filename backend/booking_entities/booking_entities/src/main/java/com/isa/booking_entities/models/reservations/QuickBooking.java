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
import com.isa.booking_entities.models.users.Client;

@Entity
@Table(name = "quick_bookings")
@Inheritance(strategy = JOINED)
public class QuickBooking {

	@Id
	@SequenceGenerator(name = "mySeqGenQuickBooking", sequenceName = "mySeqQuickBooking", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenQuickBooking")
	private long id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "UTC")
	private LocalDateTime timeOfBeginingReservation;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "UTC")
	private LocalDateTime timeOfEndingReservation;

	@Column(name = "maxNumberOfPerson", unique = false, nullable = false)
	private int maxNumberOfPerson;

	@ManyToMany(fetch = FetchType.LAZY)
	private Set<AdditionalServices> additionalServices = new HashSet<AdditionalServices>();

	@Column(name = "totalPrice", unique = false, nullable = false)
	private double totalPrice;

	@Enumerated(EnumType.ORDINAL)
	private TypeOfQuickBooking typeOfQuickBooking;
	
	@Enumerated(EnumType.ORDINAL)
	private StatusOfQuickBooking statusOfQuickBooking;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Client clientForQuickBooking;
	
	public QuickBooking() {
		// TODO Auto-generated constructor stub
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

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Client getClientForQuickBooking() {
		return clientForQuickBooking;
	}

	public void setClientForQuickBooking(Client clientForQuickBooking) {
		this.clientForQuickBooking = clientForQuickBooking;
	}

	public TypeOfQuickBooking getTypeOfQuickBooking() {
		return typeOfQuickBooking;
	}

	public void setTypeOfQuickBooking(TypeOfQuickBooking typeOfQuickBooking) {
		this.typeOfQuickBooking = typeOfQuickBooking;
	}

}
